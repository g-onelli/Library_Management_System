package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.time.LocalDate;

import com.entityClasses.book;
import com.entityClasses.librarian;
import com.entityClasses.patron;
import com.entityClasses.room;
import com.entityClasses.video;
import com.entityClasses.checkedOutRoom;

public class DB {
	Connection con;
	public void dbConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_schema","root","Password123");
			System.out.println("Connection Established...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void dbClose() {
		try {
			con.close();
			System.out.println("Connection Closed...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<librarian> showLibrarians() {
		dbConnect();
		String sql = "select * from librarians";
		List<librarian> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {	
				list.add(new librarian(rst.getInt("id"),rst.getString("name"),rst.getDouble("salary"),rst.getString("position"),rst.getString("email"),rst.getString("phoneNumber"),rst.getString("password")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	public List<book> showBooks() {
		dbConnect();
		String sql = "select * from librarians";
		List<book> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {	
				list.add(new book(rst.getInt("id"),rst.getString("title"),rst.getString("author"),rst.getString("publisher"),rst.getDouble("callNumber"),rst.getString("genre")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	
	public List<patron> showPatrons() {
		dbConnect();
		String sql = "select * from patrons";
		List<patron> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {	
				list.add(new patron(rst.getInt("id"),rst.getString("name"),rst.getString("cardExpirationDate"),rst.getDouble("balance"),rst.getString("password")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	
	public String insertPatron(patron newPatron) {
		dbConnect();
		String sql = "insert into patrons (name,cardExpirationDate) values (?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPatron.getName());
			pstmt.setString(2, newPatron.getCardExpirationDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			dbClose();
			return "Failed to insert Patron.";
		}
		dbClose();
		return "Succesfully inserted Patron.";
	}
	
	public String removePatron(int id) {
		dbConnect();
		String sql = "delete from patrons where id=?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			dbClose();
			return "Failed to remove Patron.";
		}
		dbClose();
		return "Successfully removed patron.";
	}

	public List<room> showRooms() {
		dbConnect();
		String sql = "select * from rooms";
		List<room> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {	
				list.add(new room(rst.getInt("roomNumber"),rst.getInt("capacity"),rst.getInt("hasPresenterTools")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}

	public List<String> fetchCheckedOutBooks(int id) {

		dbConnect();

		List<String> list = new ArrayList<>();

		String sql = "select * from books b, checkedoutbooks cb where cb.books_id = b.id and cb.patrons_id = ?";

		try {
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, id);
			ResultSet rst = pStmt.executeQuery();
			while (rst.next()) {

				list.add("Title: " + rst.getString("title") + ", " + "Call Number: " + rst.getString("callNumber") + ", Due Date: " + rst.getString("dueDate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		dbClose();
		return list;
	}

	public List<String> fetchCheckedOutVideos(int id) {

		dbConnect();

		List<String> list = new ArrayList<>();

		String sql = "select * from videos v, checkedoutvideos cv where cv.videos_id = v.id and cv.patrons_id = ?";

		try {
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, id);
			ResultSet rst = pStmt.executeQuery();
			while (rst.next()) {
				list.add("Title: " + rst.getString("title") + ", " + "Call Number: " + rst.getString("callNumber") + ", Due Date: " + rst.getString("dueDate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		dbClose();
		return list;
	}

	public List<String> fetchOverdueBooks(int id) {

		dbConnect();

		List<String> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long diff;



		String sql = "select title, callNumber, dueDate from books b, checkedoutbooks cb where cb.books_id = b.id and cb.patrons_id = ? and dueDate < ?";

		try {
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setString(2, java.time.LocalDate.now().toString());
			ResultSet rst = pStmt.executeQuery();
			Date firstDate = sdf.parse(java.time.LocalDate.now().toString());
			Date secondDate;
			if(!rst.next())
				diff = 0;
			else{
				secondDate = sdf.parse(rst.getString("dueDate"));
				diff = secondDate.getTime() - firstDate.getTime();
			}
			while (rst.next()) {

				list.add("Title: " + rst.getString("title") + ", " + "Call Number: " + rst.getString("callNumber") + ", Days Overdue: " + diff);
			}

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}

		dbClose();
		return list;
	}
	public List<String> fetchOverdueVideos(int id) {

		dbConnect();

		List<String> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		long diff;

		String sql = "select title, callNumber, dueDate from videos v, checkedoutvideos cv where cv.videos_id = v.id and cv.patrons_id = ? and dueDate < ?";

		try {
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setString(2, java.time.LocalDate.now().toString());
			ResultSet rst = pStmt.executeQuery();
			Date firstDate = sdf.parse(java.time.LocalDate.now().toString());
			Date secondDate;
			if(!rst.next())
				diff = 0;
			else{
				secondDate = sdf.parse(rst.getString("dueDate"));
				diff = secondDate.getTime() - firstDate.getTime();
			}

			while (rst.next()) {
				list.add("Title: " + rst.getString("title") + ", " + "Call Number: " + rst.getString("callNumber") + ", Days Overdue: " + diff);
			}

		} catch (SQLException | ParseException e ) {
			e.printStackTrace();
		}

		dbClose();
		return list;
	}
}
