package com.main;
import java.util.Scanner;

import com.main.db.DB;
import com.main.utility.LibrarianUtility;
import com.main.utility.PatronUtility;
import com.menuCalls.MenuDisplay;
public class MenuScreen {
	public static void main(String[] args) {
		DB db = new DB();
		db.dbConnect();
		Scanner loginChoice = new Scanner(System.in);
		Scanner login = new Scanner(System.in);
		MenuDisplay.mainMenuDisplay();
		System.out.println("Please choose an option: ");
		int loginInput = loginChoice.nextInt();
		if(loginInput==0) {
			MenuDisplay.closingProgram();
			MenuDisplay.exitMessage();
		}
		if(loginInput == 1) {
			//Call switch statement for librarian menu
			System.out.println("------------Librarian Login-----------\\r\\n");
			System.out.println("Enter Id: ");
			int id = login.nextInt();
			LibrarianUtility util = new LibrarianUtility();
			boolean isValid = util.validateId(db.showLibrarians(), id);
			if(!isValid) {
				System.out.println("Invalid ID, Try Again");
				break;
			}
			MenuDisplay.libMenuDisplay();
			int librarianInput = loginChoice.nextInt();
			
			
			while(true){
				if(librarianInput == 0) {
					System.out.println("Exiting.. Bye");
					break;
				}
				
				switch(librarianInput) {
				case 1: //1. View video and book collections
					break;
				case 2: //2. View room collections
					break;
				case 3: //3. View/Modify Patrons
					break;
				case 4: //4. Events
					break;
				case 5: //5. View book request
					break;
				case 6: //6. Renew Library cards
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
				case 1: //1. View video and book collections
					break;
				case 2: //2. View room collections
					break;
				case 3: //3. View your checked out books and videos
					break;
				case 4: //4. Submit book requests
					break;
				case 5: //5. View overdue books and videos\r\n"
					
					break;
				default:
					break;
				}
			}
		}
		
	}
}
