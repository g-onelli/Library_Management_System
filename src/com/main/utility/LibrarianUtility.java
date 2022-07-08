package com.main.utility;

import java.util.List;

import com.entityClasses.librarian;

public class LibrarianUtility {
	public boolean validateId(List<librarian> list, int id) {
		boolean isPresent = false;
		for(librarian e: list) {
			if(e.getId() == id) {
				isPresent = true;
				break;
			}
			
		}
		return isPresent;
	}
}
