package com.main.utilities;

import com.entityClasses.event;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventsFunctions {
	Connection con;
	String sqlCmd = "select * from events";
	List<event> eventList = new ArrayList<>();
	
	public List<event> fetchEvents(){
		List<event> eventList = new ArrayList<>();
		try {
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
		return eventList;
	}
	
	public void editEvent(Scanner input) {
		
	}
	
	public void deleteEvent(int id) {
		String deleteCmd = "delete from event where id=?";
		try {
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
		
	}
}
