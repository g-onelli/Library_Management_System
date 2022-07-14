package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.entityClasses.*;

public class DB {
	Connection con;
	public void dbConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("Driver loaded...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_schema","root","Password123");
			//System.out.println("Connection Established...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void dbClose() {
		try {
			con.close();
			//System.out.println("Connection Closed...");
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
		String sql = "select * from books";
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
<<<<<<< Updated upstream

	public patron getPatron(int id){
		dbConnect();
		String sql = "select * from patrons where id = ?";
		patron p = new patron();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rst = pstmt.executeQuery();

			rst.next();
			 p.setId(rst.getInt("id"));
			 p.setName(rst.getString("name"));
			 p.setCardExpirationDate(rst.getString("cardExpirationDate"));
			 p.setBalance(rst.getInt("balance"));
			 p.setPassword(rst.getString("password"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return p;
	}

	public librarian getLibrarian(int id){
		dbConnect();
		String sql = "select * from librarians where id = ?";
		librarian l = new librarian();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rst = pstmt.executeQuery();

			rst.next();
			l.setId(rst.getInt("id"));
			l.setName(rst.getString("name"));
			l.setPosition(rst.getString("position"));
			l.setSalary(rst.getInt("salary"));
			l.setPassword(rst.getString("password"));
			l.setEmail(rst.getString("email"));
			l.setPhoneNumber(rst.getString("phoneNumber"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return l;
	}
	
=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
	public List<video> showVideos() {
		dbConnect();
		String sql = "select * from videos";
		List<video> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {	
				list.add(new video(rst.getInt("id"),rst.getString("title"),rst.getString("director"),rst.getString("releaseDate"),rst.getDouble("callNumber"),rst.getString("genre")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	public String insertBook(book temp) {
		dbConnect();
		String sql = "insert into books (title,author,publisher,callNumber,genre) values (?,?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, temp.getTitle());
			pstmt.setString(2, temp.getAuthor());
			pstmt.setString(3, temp.getPublisher());
			pstmt.setDouble(4, temp.getCallNumber());
			pstmt.setString(5, temp.getGenre());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			dbClose();
			return "Failed to insert Book.";
		}
		dbClose();
		return "Succesfully inserted Book.";
	}
	public String insertVideo(video temp) {
		dbConnect();
		String sql = "insert into videos (title,director,releaseDate,callNumber,genre) values (?,?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, temp.getTitle());
			pstmt.setString(2, temp.getDirector());
			pstmt.setString(3, temp.getReleaseDate());
			pstmt.setDouble(4, temp.getCallNumber());
			pstmt.setString(5, temp.getGenre());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			dbClose();
			return "Failed to insert Book.";
		}
		dbClose();
		return "Succesfully inserted Book.";
	}
	public String removeBook(int bookRem) {
		dbConnect();
		String sql = "delete from books where id=?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bookRem);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			dbClose();
			return "Failed to remove Book.";
		}
		dbClose();
		return "Successfully removed Book.";
	}
	public String removeVideo(int vidRem) {
		dbConnect();
		String sql = "delete from videos where id=?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vidRem);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			dbClose();
			return "Failed to remove Video.";
		}
		dbClose();
		return "Successfully removed Video.";
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
		long days;




		String sql = "select title, callNumber, dueDate from books b, checkedoutbooks cb where cb.books_id = b.id and cb.patrons_id = ? and dueDate < ?";

		try {
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setString(2, java.time.LocalDate.now().toString());
			ResultSet rst = pStmt.executeQuery();
			LocalDate local = java.time.LocalDate.now();
			LocalDate due;
			while (rst.next()) {
				due = LocalDate.parse(rst.getString("dueDate"));
				days = ChronoUnit.DAYS.between(due, local);

				list.add("Title: " + rst.getString("title") + ", " + "Call Number: " + rst.getString("callNumber") + ", Days Overdue: " + days);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		dbClose();
		return list;
	}
	public List<String> fetchOverdueVideos(int id) {

		dbConnect();

		List<String> list = new ArrayList<>();
		long days;

		String sql = "select title, callNumber, dueDate from videos v, checkedoutvideos cv where cv.videos_id = v.id and cv.patrons_id = ? and dueDate < ?";

		try {
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1, id);
			pStmt.setString(2, java.time.LocalDate.now().toString());
			ResultSet rst = pStmt.executeQuery();
			LocalDate local = java.time.LocalDate.now();
			LocalDate due;

			while (rst.next()) {
				due = LocalDate.parse(rst.getString("dueDate"));
				days = ChronoUnit.DAYS.between(due, local);

				list.add("Title: " + rst.getString("title") + ", " + "Call Number: " + rst.getString("callNumber") + ", Days Overdue: " + days);
			}

		} catch (SQLException e ) {
			e.printStackTrace();
		}

		dbClose();
		return list;
	}
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
	public List<book> getAvailableBooks(){
		dbConnect();
		String sql = "select * from books where id NOT IN (select books_id from checkedoutbooks);";
		List<book> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();

			while(rst.next()) {
				list.add(new book(rst.getInt("id"),rst.getString("title"),rst.getString("author"), rst.getString("publisher"), rst.getDouble("callNumber"), rst.getString("genre")));
<<<<<<< Updated upstream
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}

	public List<video> getAvailableVideos(){
		dbConnect();
		String sql = "select * from videos where id NOT IN (select videos_id from checkedoutvideos);";
		List<video> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();

			while(rst.next()) {
				list.add(new video(rst.getInt("id"),rst.getString("title"),rst.getString("director"), rst.getString("releaseDate"), rst.getDouble("callNumber"), rst.getString("genre")));
=======
>>>>>>> Stashed changes
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
<<<<<<< Updated upstream


	public void checkoutBook(checkedOutBook bReserve) {
		dbConnect();
		String sql = "insert into checkedOutBooks(patrons_id,books_id,dueDate) "
				+ "values (?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bReserve.getPatrons_id());
			pstmt.setInt(2, bReserve.getBooks_id());
			pstmt.setDate(3, bReserve.getDueDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}

	public void checkOutVideo(checkedOutVideo vReserve) {
		dbConnect();
		String sql = "insert into checkedoutvideos(patrons_id,videos_id,dueDate) "
				+ "values (?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vReserve.getPatrons_id());
			pstmt.setInt(2, vReserve.getVideos_id());
			pstmt.setDate(3, vReserve.getDueDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}

	public List<room> showFreeRooms() {
		dbConnect();
=======
	public List<video> getAvailableVideos(){
		dbConnect();
		String sql = "select * from videos where id NOT IN (select videos_id from checkedoutvideos);";
		List<video> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();

			while(rst.next()) {
				list.add(new video(rst.getInt("id"),rst.getString("title"),rst.getString("director"), rst.getString("releaseDate"), rst.getDouble("callNumber"), rst.getString("genre")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	public void checkoutBook(checkedOutBook bReserve) {
		dbConnect();
		String sql = "insert into checkedOutBooks(patrons_id,books_id,dueDate) "
				+ "values (?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bReserve.getPatrons_id());
			pstmt.setInt(2, bReserve.getBooks_id());
			pstmt.setDate(3, bReserve.getDueDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}
	public void checkOutVideo(checkedOutVideo vReserve) {
		dbConnect();
		String sql = "insert into checkedoutvideos(patrons_id,videos_id,dueDate) "
				+ "values (?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vReserve.getPatrons_id());
			pstmt.setInt(2, vReserve.getVideos_id());
			pstmt.setDate(3, vReserve.getDueDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
	}
	public List<room> showFreeRooms() {
		dbConnect();
>>>>>>> Stashed changes
		String sql = "select * from rooms where roomNumber NOT IN (select rooms_roomNumber from checkedoutrooms);";

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
	public void reserveRoom(checkedOutRoom reserve) {
		dbConnect();
		String sql = "insert into checkedOutRooms(patrons_id,rooms_roomNumber,dueDate) "
				+ "values (?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reserve.getPatrons_id());
			pstmt.setInt(2, reserve.getRooms_roomnumber());
			pstmt.setDate(3, reserve.getDueDate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		
	}
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
	public String checkInBook(double callNum) {
		dbConnect();
		String sql = "delete cb from checkedoutbooks cb left join books b on b.id = cb.books_id where callNumber = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, callNum);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			dbClose();
			return "The book was never checked out, or was already checked back in.";
		}
		dbClose();
		return "Successfully checked in.";
	}
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
	public String checkInVideo(double callNum) {
		dbConnect();
		String sql = "delete cv from checkedoutvideos cv left join videos v on v.id = cv.videos_id where callNumber = ?;";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setDouble(1, callNum);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			dbClose();
			return "The video was never checked out, or was already checked back in.";
		}
		dbClose();
		return "Successfully checked in.";
	}

}
