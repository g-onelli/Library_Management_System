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
import com.entityClasses.request;
import com.entityClasses.room;
import com.entityClasses.video;
import com.entityClasses.checkedOutRoom;

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
	
	public String insertPatron(patron newPatron) {
		dbConnect();
		String sql = "insert into patrons (name,cardExpirationDate,balance,password) values (?,?,?,?)";

		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPatron.getName());
			pstmt.setString(2, newPatron.getCardExpirationDate());
			pstmt.setDouble(3, newPatron.getBalance());
			pstmt.setString(4, newPatron.getPassword());
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
			System.out.println(pstmt);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			dbClose();
			return "Failed to remove Patron.";
		}
		dbClose();
		return "Successfully removed patron.";
	}
	
	public patron fetchPatron(int input) {
		dbConnect();
		patron onePatron = new patron();
		String sqlCmd = "select * from patrons where id=?";
		try {
			PreparedStatement cmd = con.prepareStatement(sqlCmd);
			cmd.setInt(1,input);
			ResultSet result = cmd.executeQuery();
			result.next();
			// public patron(int id, String name, String cardExpirationDate, double balance, String password)
			onePatron = new patron(result.getInt("id"),
					result.getString("name"),
					result.getString("cardExpirationDate"),
					result.getDouble("balance"),
					result.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return onePatron;
	}
	
	public void changeExpirationDate(int id, String newDate) {
		PatronUtility utility = new PatronUtility();
		String cmdSQL = "update patrons SET name=?, cardExpirationDate=?, balance=?, password=?";
		boolean idCheck = utility.validateId(showPatrons(),id);
		if(idCheck) {
			patron tempPat = fetchPatron(id);
			try {
				PreparedStatement cmd = con.prepareStatement(cmdSQL);
				cmd.setString(1, tempPat.getName());
				cmd.setString(2, newDate);
				cmd.setDouble(3, tempPat.getBalance());
				cmd.setString(4, tempPat.getPassword());
				cmd.executeUpdate();
				System.out.println("Your card expiration date has been extended to "+newDate);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("Sorry, we could not find a patron with that id. Please recheck input.");
		}
		
		dbClose();
	}

	public List<String> showRooms() {
		dbConnect();
		String sql = "select * from rooms";
		List<String> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {	
				list.add("Room Number: " + rst.getInt("roomNumber") + ", " + "Capacity: " + rst.getInt("capacity") + ", PresenterTools: " + rst.getInt("hasPresenterTools"));						
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	
	public List<event> fetchEvents(){//returns a list of all events being hosted by the library
		dbConnect();
		List<event> eventList = new ArrayList<>();
		try {
			String sqlCmd = "select * from events";
			PreparedStatement cmd = con.prepareStatement(sqlCmd);
			ResultSet result = cmd.executeQuery();
			
			while(result.next()) {
				//event(id, String date, String description, String title)
				eventList.add(new event(result.getInt("librarians_id"),//change the id to the actual event id
						result.getString("date"),result.getString("description"),
						result.getString("title")));}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return eventList;
	}
			
	
	public event fetchEvent(String input) {//returns a single event, via id, being hosted by the library
		int idNum = Integer.parseInt(input);
		dbConnect();
		event oneEvent = new event();
		String sqlCmd = "select * from events where id=?";
		try {
			PreparedStatement cmd = con.prepareStatement(sqlCmd);
			cmd.setInt(1,idNum);
			ResultSet result = cmd.executeQuery();
			result.next();
			oneEvent = new event(result.getInt("id"),
					result.getString("date"),
					result.getString("description"),
					result.getString("title"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return oneEvent;
	}
	
	public void editEvent(String eventId, String modField, String modValue) {
		event modEvent = fetchEvent(eventId);
		String cmdSQL = "update events SET date=?, description=?, title=? where id=?";
		try {
			PreparedStatement cmd = con.prepareStatement(cmdSQL);
			switch(modField) {
				case "date":
					cmd.setString(1, modValue);
					cmd.setString(2, modEvent.getDescription());
					cmd.setString(3, modEvent.getTitle());
					cmd.setInt(4, modEvent.getId());
					break;
				case "title":
					cmd.setString(1, modEvent.getDate());
					cmd.setString(2, modEvent.getDescription());
					cmd.setString(3, modValue);
					cmd.setInt(4, modEvent.getId());
					break;
				case "description":
					cmd.setString(1, modEvent.getDate());
					cmd.setString(2, modValue);
					cmd.setString(3, modEvent.getTitle());
					cmd.setInt(4, modEvent.getId());
					break;
				default:
					System.out.println("That is not a field that is accepted. Please try again.");
					break;
			}
			cmd.executeUpdate();
			System.out.println("Your event has now been updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		
	}
	
	
	public void addEvent(String date, String descrption, String title, int libID) {
		dbConnect();
		String cmdSQL = "insert into events(date,description,title,librarians_id) values(?,?,?,?)";
		try {
			PreparedStatement cmd = con.prepareStatement(cmdSQL);
			cmd.setString(1, date);
			cmd.setString(2, descrption);
			cmd.setString(3, title);
			cmd.setInt(4, libID);
			
			cmd.executeUpdate();
			System.out.println("Your event has been added to the database");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		
	}
	
	
	public void deleteEvent(int id) {
		dbConnect();
		String cmdSQL="delete from events where id=?";
		//int idNum = Integer.parseInt(id);
		try {
			PreparedStatement cmd = con.prepareStatement(cmdSQL);
			cmd.setInt(1,id);
			cmd.executeUpdate();
			System.out.println("Your entry has been delete. Have a nice day!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		
	}

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
			return "Failed to insert Video.";
		}
		dbClose();
		return "Succesfully inserted Video.";
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
	public List<String> showFreeRooms() {
		dbConnect();
		String sql = "select roomNumber, capacity, hasPresenterTools " + 
				"from rooms " + 
				"where roomNumber NOT IN (select rooms_roomNumber from checkedoutrooms);";
		List<String> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rst = pstmt.executeQuery();
			
			while(rst.next()) {	
				list.add("Room Number: " + rst.getInt("roomNumber") + ", " + "Capacity: " + rst.getInt("capacity") + ", PresenterTools: " + rst.getInt("hasPresenterTools"));						
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return list;
	}
	public List<room> checkFreeRooms() {
		dbConnect();
		String sql = "select roomNumber, capacity, hasPresenterTools " + 
				"from rooms " + 
				"where roomNumber NOT IN (select rooms_roomNumber from checkedoutrooms);";
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
	public void requestBook(request bookRequest, int id) {
		dbConnect();
		String sql = "insert into requests(description,submissionDate,title,patrons_id,author) "
				+ "values (?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookRequest.getDescription());
			pstmt.setDate(2, bookRequest.getSubmissionDate());
			pstmt.setString(3, bookRequest.getTitle());
			pstmt.setInt(4, id);
			pstmt.setString(5, bookRequest.getAuthor());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		
	}
}
