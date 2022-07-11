package com.main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entityClasses.event;
import com.entityClasses.librarian;
import com.entityClasses.patron;
import com.entityClasses.request;
import com.entityClasses.room;


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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_schema","root","092046Dra*fee");
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
	
	List<event> eventList = new ArrayList<>();
	
	public List<event> fetchEvents(){
		//DB dbObj = new DB();
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
						result.getString("title")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return eventList;
	}
	
	public event fetchEvent(String input) {
		int idNum = Integer.parseInt(input);
		//System.out.println(idNum);
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
		String cmdSQL = "update events SET id=?, date=?, description=?, title=?";
		System.out.println("This is modfield "+modField);
		System.out.println(modField=="title");
		try {
			PreparedStatement cmd = con.prepareStatement(cmdSQL);
			switch(modField) {
				case "date":
					cmd.setInt(1, modEvent.getId());
					cmd.setString(2, modValue);
					cmd.setString(3, modEvent.getDescription());
					cmd.setString(4, modEvent.getTitle());
					break;
				case "title":
					cmd.setInt(1, modEvent.getId());
					cmd.setString(2, modEvent.getDate());
					cmd.setString(3, modEvent.getDescription());
					cmd.setString(4, modValue);
					break;
				case "description":
					cmd.setInt(1, modEvent.getId());
					cmd.setString(2, modEvent.getDate());
					cmd.setString(3, modValue);
					cmd.setString(4, modEvent.getTitle());
					break;
				default:
					System.out.println("That is not a field that is accepted. Please try again.");
					break;
			}
			cmd.executeUpdate();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbClose();
		
	}
	
	public List<request> checkRequests() {
		dbConnect();
		List<request> reqList = new ArrayList<>();
		try {
			String sqlCmd = "select * from requests";
			PreparedStatement cmd = con.prepareStatement(sqlCmd);
			ResultSet result = cmd.executeQuery();
			
			while(result.next()) {
				//public request(int id, String description, String submissionDate, String title)
				reqList.add(new request(result.getInt("id"),//change the id to the actual event id
						result.getString("description"),result.getString("submissionDate"),
						result.getString("title")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbClose();
		return reqList;
	}
	
	public patron fetchPatron(int input) {
		//int idNum = Integer.parseInt(input);
		//System.out.println(idNum);
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
		String cmdSQL = "update patrons SET id=?, name=?, cardExpirationDate=?, balance=?, password=?";
		patron tempPat = fetchPatron(id);
		if(tempPat != null) {
			try {
				PreparedStatement cmd = con.prepareStatement(cmdSQL);
				cmd.setString(1, tempPat.getName());
				cmd.setString(2, newDate);
				cmd.setDouble(3, tempPat.getBalance());
				cmd.setString(4, tempPat.getPassword());
				cmd.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Your card expiration date has been extened to "+newDate);
		}else {
			System.out.println("Sorry, we could not find a patron with that id. Please recheck input.");
		}
		
		dbClose();
	}
	
}
