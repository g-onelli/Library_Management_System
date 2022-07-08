package com.main.utility;

import java.util.List;

import com.entityClasses.patron;

public class PatronUtility {
	public boolean validateId(List<patron> list, int id) {
		boolean isPresent = false;
		for(patron p: list) {
			if(p.getId() == id) {
				isPresent = true;
				break;
			}
			
		}
		return isPresent;
	}
	public boolean validatePass(List<patron> list, int id, String password) {
		boolean isPresent = false;
		for(patron p: list) {
			if(p.getId() == id) {
				if(p.getPassword().equals(password)) {
					isPresent = true;
					break;
				}
			}
		}
		return isPresent;
	}
}
