package com.main.utility;

import java.util.List;

import com.entityClasses.librarian;

public class LibrarianUtility {
	public boolean validateId(List<librarian> list, int id) {
		boolean isPresent = false;
		for(librarian l: list) {
			if(l.getId() == id) {
				isPresent = true;
				break;
			}
			
		}
		return isPresent;
	}

	public boolean validatePass(List<librarian> list, int id, String password) {
		boolean isPresent = false;
		for(librarian l: list) {
			if(l.getId() == id) {
				if(l.getPassword().equals(password)) {
					isPresent = true;
					break;
				}
			}
		}
		return isPresent;
	}
}
