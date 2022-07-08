package com.main.utility;

import java.util.List;

import com.entityClasses.patron;

public class PatronUtility {
	public boolean validateId(List<patron> list, int id) {
		boolean isPresent = false;
		for(patron e: list) {
			if(e.getId() == id) {
				isPresent = true;
				break;
			}
			
		}
		return isPresent;
	}
}
