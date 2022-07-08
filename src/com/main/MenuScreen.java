package com.main;

import java.util.Scanner;

import com.entityClasses.*;
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
				case 1:
					MenuDisplay.viewVideoBooks();
					int vid = loginChoice.nextInt();
					while(true){
						if(librarianInput == 0) {
							System.out.println("Exiting.. Bye");
							break;
						}
						switch(vid) {
						case 1:
							//Display all the books from the database
							break;
						case 2:
							//Display all the videos from the database
							break;
						case 3:
							//Add Books
							book temp;
							System.out.println("Enter Book Title:");
							String title = loginChoice.nextLine();
							System.out.println("Enter Book Author:");
							String author = loginChoice.nextLine();
							System.out.println("Enter Book Publisher:");
							String publisher = loginChoice.nextLine();
							System.out.println("Enter Book Call Number:");
							double callNum = loginChoice.nextDouble();
							System.out.println("Enter Book Genere:");
							String genere = loginChoice.nextLine();
							temp = new book(0, title, author, publisher, callNum, genere);
							//Add book to database using the DB class
							break;
						case 4:
							//Add Videos
							video tempVid;
							System.out.println("Enter Video Title:");
							title = loginChoice.nextLine();
							System.out.println("Enter Video Director:");
							String director = loginChoice.nextLine();
							System.out.println("Enter Video Release Date:");
							String release = loginChoice.nextLine();
							System.out.println("Enter Video Call Number:");
							callNum = loginChoice.nextDouble();
							System.out.println("Enter Video Genere:");
							genere = loginChoice.nextLine();
							tempVid = new video(0, title, director, release, callNum, genere);
							// add video to database using db class
							break;
						case 5:
							//remove books
							System.out.println("Enter Book ID to Remove:");
							int bookRem = loginChoice.nextInt();
							//remove book from database and return success/fail state
							break;
						case 6:
							//remove videos
							System.out.println("Enter Video ID to Remove:");
							int vidRem =  loginChoice.nextInt();
							//remove book from database and return success/fail state
							break;
						default:
							break;
						}
					}
					break;
				case 2:
					//Room collections -output list of rooms from the database
					System.out.println("List of schedulable rooms:");
					break;
				case 3:
					//View / modify patrons
					MenuDisplay.viewModifyPatrons();
					int vMP = loginChoice.nextInt();
					while(true){
						if(librarianInput == 0) {
							System.out.println("Exiting.. Bye");
							break;
						}
						switch(vMP) {
						case 1:
							//View all patrons from database
							break;
						case 2:
							//Register patrons
							patron tempPat;
							System.out.println("Enter Patron Name:");
							String name = loginChoice.nextLine();
							System.out.println("Enter Card Exp. Date:");
							String expDate = loginChoice.nextLine();
							tempPat = new patron(0, name, expDate, 0);
							//add tempPat to the database
							break;
						case 3:
							//Remove Patrons
							System.out.println("Enter Patron ID to Remove Patron:");
							int toRem = loginChoice.nextInt();
							//remove patron from database and return success/fail state
						default:
							break;
						}
					}
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