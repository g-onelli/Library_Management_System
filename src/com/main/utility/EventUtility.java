package com.main.utility;

import java.util.List;

import com.entityClasses.event;
import com.main.db.DB;

public class EventUtility {
	public boolean validateEvent(int id) {
		DB db = new DB();
		boolean validation = false;
		List<event> eventList = db.fetchEvents();
		for(event e:eventList) {
			if(e.getId()==id) {
				validation = true;
				break;
			}
		}
		return validation;
	}
}
