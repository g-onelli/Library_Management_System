package com.main.utility;

import java.util.List;

import com.entityClasses.room;

public class RoomUtility {

	public boolean validateRoomId(List<room> list, int id) {
		boolean isPresent = false;
		for(room r: list) {
			if(r.getRoomNumber() == id) {
				isPresent = true;
				break;
			}
			
		}
		return isPresent;
	}
}
