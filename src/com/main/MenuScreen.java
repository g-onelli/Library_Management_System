package com.main;
import java.util.Scanner;

import com.main.db.DB;
import com.menuCalls.MenuDisplay;
public class MenuScreen {
	public static void main(String[] args) {
		DB db = new DB();
		db.dbConnect();
		Scanner loginChoice = new Scanner(System.in);
		MenuDisplay.mainMenuDisplay();
		System.out.println("Please choose an option: ");
		int loginInput = loginChoice.nextInt();
		if(loginInput==0) {
			MenuDisplay.closingProgram();
			MenuDisplay.exitMessage();
		}
		if(loginInput == 1) {
			//Call switch statement for librarian menu
			MenuDisplay.libMenuDisplay();
			int librarianInput = loginChoice.nextInt();
			while(true){
				if(librarianInput == 0) {
					System.out.println("Exiting.. Bye");
					break;
				}
				
				switch(librarianInput) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				default:
					break;
				}
			}
		}else {
			//Call switch statement for patron menu
			MenuDisplay.patMenuDisplay();
			int patronInput = loginChoice.nextInt();
			while(true){
				if(patronInput == 0) {
					System.out.println("Exiting.. Bye");
					break;
				}
				
				switch(patronInput) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				default:
					break;
				}
			}
		}
		
	}
}
