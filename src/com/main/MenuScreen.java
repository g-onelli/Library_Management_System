
package com.main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.entityClasses.*;
import com.main.db.DB;
import com.main.utility.BookAndVideoUtility;
import com.main.utility.LibrarianUtility;
import com.main.utility.PatronUtility;
import com.main.utility.RoomUtility;
import com.menuCalls.MenuDisplay;

public class MenuScreen {
    public static void main(String[] args) {
        DB db = new DB();
        db.dbConnect();
        Scanner loginChoice = new Scanner(System.in);
        Scanner login = new Scanner(System.in);
        MenuDisplay.mainMenuDisplay();
        int patron_id;
        System.out.println("Please choose an option: ");
        int loginInput = loginChoice.nextInt();
        if (loginInput == 0) {
            MenuDisplay.closingProgram();
            MenuDisplay.exitMessage();
        }
        else if (loginInput == 1) {
            // Call switch statement for librarian menu
            while (true) {
                System.out.println("------------Librarian Login-----------\\r\\n");
                System.out.println("Enter Id: ");
                int id = login.nextInt();
                LibrarianUtility util = new LibrarianUtility();
                boolean isValidId = util.validateId(db.showLibrarians(), id);
                if (!isValidId) {
                    System.out.println("Invalid ID, Try Again");
                } else {
                    System.out.println("Enter Password: ");
                    login.nextLine();
                    String password = login.nextLine();
                    boolean isValidPass = util.validatePass(db.showLibrarians(), id, password);
                    if (!isValidPass) {
                        System.out.println("Invalid Password, Try Again");
                    } else {
                        System.out.println("Login Successful...");
                        break;
                    }
                }
            }

            while (true) {
                MenuDisplay.libMenuDisplay();
                int librarianInput = loginChoice.nextInt();
                if (librarianInput == 0) {
                    System.out.println("Exiting.. Bye");
                    break;
                }
                // Call switch statement for librarian menu
                switch (librarianInput) {
                    case 1:

                        while (true) {
                            MenuDisplay.viewVideoBooks();
                            int vid = loginChoice.nextInt();
                            if (vid == 0) {
                                System.out.println("Exiting.. Bye");
                                break;
                            }
                            switch (vid) {
                                case 1:
                                    // Display all the books from the database
                                    List<book> books;
                                    books = db.showBooks();
                                    for (book b : books) {
                                        System.out.println(b.toString());
                                    }
                                    break;
                                case 2:
                                    // Display all the videos from the database
                                    List<video> videos;
                                    videos = db.showVideos();
                                    for (video v : videos) {
                                        System.out.println(v.toString());
                                    }
                                    break;
                                case 3:
                                    // Add Books
                                    book temp;
                                    System.out.println("Enter Book Title:");
                                    loginChoice.nextLine();
                                    String title = loginChoice.nextLine();
                                    System.out.println("Enter Book Author:");
                                    String author = loginChoice.nextLine();
                                    System.out.println("Enter Book Publisher:");
                                    String publisher = loginChoice.nextLine();
                                    System.out.println("Enter Book Call Number:");
                                    double callNum = loginChoice.nextDouble();
                                    System.out.println("Enter Book Genre:");
                                    loginChoice.nextLine();
                                    String genre = loginChoice.nextLine();
                                    temp = new book(0, title, author, publisher, callNum, genre);
                                    // Add book to database using the DB class
                                    System.out.println(db.insertBook(temp));
                                    break;
                                case 4:
                                    // Add Videos
                                    video tempVid;
                                    System.out.println("Enter Video Title:");
                                    loginChoice.nextLine();
                                    title = loginChoice.nextLine();
                                    System.out.println("Enter Video Director:");
                                    String director = loginChoice.nextLine();
                                    System.out.println("Enter Video Release Date:");
                                    String release = loginChoice.nextLine();
                                    System.out.println("Enter Video Call Number:");
                                    callNum = loginChoice.nextDouble();
                                    System.out.println("Enter Video Genre:");
                                    loginChoice.nextLine();
                                    genre = loginChoice.nextLine();
                                    tempVid = new video(0, title, director, release, callNum, genre);
                                    // add video to database using db class
                                    System.out.println(db.insertVideo(tempVid));
                                    break;
                                case 5:
                                    // remove books
                                    System.out.println("Enter Book ID to Remove:");
                                    int bookRem = loginChoice.nextInt();
                                    // remove book from database and return success/fail state
                                    System.out.println(db.removeBook(bookRem));
                                    break;
                                case 6:
                                    // remove videos
                                    System.out.println("Enter Video ID to Remove:");
                                    int vidRem = loginChoice.nextInt();
                                    // remove book from database and return success/fail state
                                    System.out.println(db.removeVideo(vidRem));
                                    break;
                                case 7:
                                    MenuDisplay.searchBooksDisplay();
                                    loginChoice.nextLine();
                                    String tag = loginChoice.nextLine();
                                    MenuDisplay.searchTermDisplay();
                                    String term = loginChoice.nextLine();
                                    List<book> bList = db.searchBooks(tag, term);
                                    for (book b : bList) {
                                        System.out.println(b);
                                    }
                                    break;
                                case 8:
                                    MenuDisplay.searchVideosDisplay();
                                    loginChoice.nextLine();
                                    tag = loginChoice.nextLine();
                                    MenuDisplay.searchTermDisplay();
                                    term = loginChoice.nextLine();
                                    List<video> vList = db.searchVideos(tag, term);
                                    for (video v : vList) {
                                        System.out.println(v);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case 2:
                        // Room collections -output list of rooms from the database
                        System.out.println("List of schedulable rooms:");

                        List<String> list = db.showRooms();
                        for (String r : list) {
                            System.out.println(r);
                        }
                        break;
                    case 3:
                        // View / modify patrons
                        while (true) {
                            MenuDisplay.viewModifyPatrons();
                            int vMP = loginChoice.nextInt();
                            if (vMP == 0) {
                                System.out.println("Exiting.. Bye");
                                break;
                            }
                            switch (vMP) {
                                case 1:
                                    // show all patrons
                                    List<patron> pats;
                                    pats = db.showPatrons();
                                    System.out.println("List of Patrons:");
                                    for (patron p : pats) {
                                        System.out.println(p.toString());
                                    }
                                    break;
                                case 2:
                                    // Register patrons
                                    patron tempPat;
                                    System.out.println("Enter Patron Name:");
                                    loginChoice.nextLine();
                                    String name = loginChoice.nextLine();
                                    System.out.println("Enter Card Exp. Date:");
                                    String expDate = loginChoice.nextLine();
                                    System.out.println("Enter Patron Password:");
                                    String password = loginChoice.nextLine();
                                    tempPat = new patron(0, name, expDate, 0, password);
                                    System.out.println(db.insertPatron(tempPat));
                                    // add tempPat to the database
                                    break;
                                case 3:
                                    // Remove Patrons
                                    System.out.println("Enter Patron ID to Remove Patron:");
                                    loginChoice.nextLine();
                                    int toRem = loginChoice.nextInt();
                                    // remove patron from database and return success/fail state
                                    System.out.println(db.removePatron(toRem));
                                default:
                                    break;
                            }
                        }
                        break;
                    case 4:
                    	while(true){
                    		MenuDisplay.eventMenuDisplay();
    						int eventInput = loginChoice.nextInt();
    						if(eventInput==0) {
    							System.out.println();
    							System.out.println("Returning to librarian menu.");
    							System.out.println();
    							break;
    						}
    						switch(eventInput) {
    							case 1:
    								List<event> eventList = db.fetchEvents();
    								for(event e: eventList) {
    									System.out.println(e);
    								}
    								break;
    							case 2:
    								System.out.println("Please input the event id: ");
    								loginChoice.nextLine();
    								String eventID = loginChoice.nextLine();

    								System.out.println("Please input the field you would like to alter(date,description,title):");
    								String modField = loginChoice.nextLine();

    								System.out.println("Please input the new value for the selected field: ");
    								String modValue = loginChoice.nextLine();
    								db.editEvent(eventID,modField,modValue);
    								break;
    							case 3:
    								loginChoice.nextLine();
    								System.out.println("Please input title: ");
    								String title = loginChoice.nextLine();
    								System.out.println("Please input description: ");
    								String description = loginChoice.nextLine();
    								System.out.println("Please input date: ");
    								String date = loginChoice.nextLine();
    								System.out.println("Please input personal id number: ");
    								int libId = loginChoice.nextInt();
    								db.addEvent(date, description, title, libId);
    								break;
    							case 4:
    								System.out.println("Please input the id value of the event you wish to delete: ");
    								int idNum = loginChoice.nextInt();
    								db.deleteEvent(idNum);
    								break;
    							default:
    								System.out.println();
    								System.out.println("Returning to librarian menu.");
    								System.out.println();
    								break;
    						}
                    	}
                    	break;
						
					case 5:
						while(true) {
							MenuDisplay.requestMenuDisplay();
							int requestChoice = loginChoice.nextInt();
							if(requestChoice==1) {
								List<request> reqList=db.checkRequests();
								for(request r: reqList) {
									System.out.println(r);
								}

							}else if(requestChoice==2) {
								System.out.println("Please input the id of the request that is being filled out: ");
								int id = loginChoice.nextInt();
								loginChoice.nextLine();
								System.out.println("Please input the book's publisher: ");
								String pub = loginChoice.nextLine();
								System.out.println("Please input the genre of the book: ");
								String genre = loginChoice.nextLine();
								System.out.println("Please input the library's call number for the book, max 4 numbers: ");
								double callNum = loginChoice.nextDouble();
								db.confirmRequest(id, genre,pub,callNum);
							}else {
								System.out.println();
								System.out.println("Returning to librarian menu");
								System.out.println();
								break;
							}		
						}
						break;
					case 6:
						loginChoice.nextLine();
						System.out.println("Please input the patron id: ");
						int libId = loginChoice.nextInt();
						loginChoice.nextLine();
						System.out.println("Please input the new expiration date: ");
						String exDate = loginChoice.nextLine();
						db.changeExpirationDate(libId, exDate);
						break;
					default:
						break;
					}
			}

        }else if(loginInput == 2){
            //Call switch statement for patron menu
            int id;
            while(true) {
                System.out.println("------------Patron Login-----------\r\n");
                System.out.println("Enter Id: ");
                id = login.nextInt();
                PatronUtility util = new PatronUtility();
                boolean isValidId = util.validateId(db.showPatrons(), id);
                if (!isValidId) {
                    System.out.println("Invalid ID, Try Again");
                } else {
                    System.out.println("Enter Password: ");
                    login.nextLine();
                    String password = login.nextLine();
                    boolean isValidPass = util.validatePass(db.showPatrons(), id, password);
                    if (!isValidPass) {
                        System.out.println("Invalid Password, Try Again");
                    } else {
                        System.out.println("Login Successful...");
                        patron_id = id;
                        break;
                    }
                }
            }
            while (true) {
                MenuDisplay.patMenuDisplay();
                int patronInput = loginChoice.nextInt();
                if (patronInput == 0) {
                    System.out.println("Exiting.. Bye");
                    break;
                }
                switch (patronInput) {
                    case 1: // 1. View video and book collections
                        while(true) {
                            MenuDisplay.viewVideoBooksPat();
                            patronInput = loginChoice.nextInt();
                            if (patronInput == 0) {
                                System.out.println("Exiting.. Bye");
                                break;
                            }
                            switch(patronInput) {
                                case 1:
                                    // Display all the books from the database
                                    List<book> books;
                                    books = db.showBooks();
                                    books.stream().forEach(e -> System.out.println( "Title: " + e.getTitle() + ", "
                                            + "Author: " + e.getAuthor() + ", "
                                            + "Publisher: " + e.getPublisher() + ", "
                                            + "Call Number: " + e.getCallNumber() + ", "
                                            + "Genre: " + e.getGenre()));
                                    break;
                                case 2:
                                    // Display all the videos from the database
                                    List<video> videos;
                                    videos = db.showVideos();
                                    videos.stream().forEach(e -> System.out.println("Title: "+  e.getTitle() + ", "
                                            + "Director: " + e.getDirector() + ", "
                                            + "ReleaseDate: " + e.getReleaseDate() + ", "
                                            + "Call Number: " + e.getCallNumber() + ", "
                                            + "Genre: "+ e.getGenre()));
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case 2: // 2. View room collections

                        System.out.println("List of schedulable rooms:");
                        List<String> list = db.showRooms();
                        for(String r: list) {
                            System.out.println(r);
                        }
                        break;
                    case 3: //3. View your checked out books and videos
                        List<String> bookList= db.fetchCheckedOutBooks(id);
                        if(bookList.isEmpty())
                            System.out.println("You have no books checked out");
                        else {
                            System.out.println("Here are your checked out books");
                            for (String s : bookList) {
                                System.out.println(s);
                            }
                        }
                        List<String> videoList = db.fetchCheckedOutVideos(id);
                        if(videoList.isEmpty())
                            System.out.println("You have no videos checked out");
                        else{
                            System.out.println("Here are your checked out videos");
                            for(String s: videoList){
                                System.out.println(s);
                            }
                        }
                        break;
                    case 4: // 4. Submit book requests
                        request request = new request();
                        System.out.println("Enter Book Title");
                        String name = login.nextLine();
                        System.out.println("Enter Book Author");
                        String author = login.nextLine();
                        System.out.println("Enter Book Description");
                        String desc = login.nextLine();
                        long millis=System.currentTimeMillis();
                        java.sql.Date date=new java.sql.Date(millis);
                        request.setTitle(name);
                        request.setAuthor(author);
                        request.setDescription(desc);
                        request.setSubmissionDate(date);
                        db.requestBook(request, patron_id);
                        System.out.println("Book requested!");
                        break;
                    case 5: //5. View overdue books and videos
                        List<String> overdueBooks= db.fetchOverdueBooks(id);
                        if(overdueBooks.isEmpty())
                            System.out.println("You have no books overdue");
                        else {
                            System.out.println("Here are your overdue books");
                            for (String s : overdueBooks) {
                                System.out.println(s);
                            }
                        }
                        List<String> overdueVideos= db.fetchOverdueVideos(id);
                        if(overdueVideos.isEmpty())
                            System.out.println("You have no videos overdue");
                        else{
                            System.out.println("Here are your overdue videos");
                            for(String s: overdueVideos){
                                System.out.println(s);
                            }
                        }
                        break;
                    case 6: // 6. Reserve a room
                        while(true) {
                            System.out.println("********************************");
                            System.out.println("Available Rooms");
                            List<String> roomList = db.showFreeRooms();
                            for(String s: roomList){
                                System.out.println(s);
                            }
                            System.out.println("********************************");
                            System.out.println("Enter a room number:");
                            int roomId = login.nextInt();
                            RoomUtility util = new RoomUtility();
                            boolean isValidId = util.validateRoomId(db.checkFreeRooms(), roomId);
                            if(!isValidId) {
                                System.out.println("Invalid ID or Room Reserved, Try Again");
                            }
                            else{
                                checkedOutRoom reserve = new checkedOutRoom();
                                reserve.setPatrons_id(patron_id);
                                reserve.setRooms_roomnumber(roomId);
                                long millis2=System.currentTimeMillis();
                                java.sql.Date date2=new java.sql.Date(millis2);
                                reserve.setDueDate(date2);
                                db.reserveRoom(reserve);
                                System.out.println("Room Reserved.");
                                break;
                            }
                        }
                        break;
                    case 7://check in a book or video
                        System.out.println("********************************");
                        System.out.println("Checking in book, video, or both?");
                        double callNum;
                        String choice = login.next();
                        BookAndVideoUtility itemUtil = new BookAndVideoUtility();
                        int isValidCallNum;
                        if (choice.equalsIgnoreCase("book")) {
                            System.out.println("Enter book call number");
                            try{
                                callNum = login.nextDouble();
                            }
                            catch (Exception e){
                                System.out.println("Invalid input, returning to menu!!!");
                                login.next();
                                break;
                            }
                            isValidCallNum = itemUtil.validateCallNumber(db.getAvailableBooks(), db.getAvailableVideos(), callNum);
                            if(isValidCallNum == 0 || isValidCallNum == -1){
                                System.out.println("Call Number is invalid or book is already checked in");
                            }
                            else{
                                System.out.println(db.checkInBook(callNum));
                                break;
                            }
                        } else if (choice.equalsIgnoreCase("video")) {
                            System.out.println("Enter video call number");
                            try{
                                callNum = login.nextDouble();
                            }
                            catch (Exception e){
                                System.out.println("Invalid input, returning to menu!!!");
                                login.next();
                                break;
                            }
                            isValidCallNum = itemUtil.validateCallNumber(db.getAvailableBooks(), db.getAvailableVideos(), callNum);
                            if(isValidCallNum == 1 || isValidCallNum == -1){
                                System.out.println("Call Number is invalid or video is already checked in");
                            }
                            else{
                                System.out.println(db.checkInVideo(callNum));
                                break;
                            }
                            break;

                        } else if (choice.equalsIgnoreCase("both")) {
                            System.out.println("Enter book call number");
                            try{
                                callNum = login.nextDouble();
                            }
                            catch (Exception e){
                                System.out.println("Invalid input, returning to menu!!!");
                                login.next();
                                break;
                            }
                            isValidCallNum = itemUtil.validateCallNumber(db.getAvailableBooks(), db.getAvailableVideos(), callNum);
                            if(isValidCallNum == 0 || isValidCallNum == -1){
                                System.out.println("Call Number is invalid or book is already checked in");
                            }
                            else{
                                System.out.println(db.checkInBook(callNum));
                            }

                            System.out.println("Enter video call number");
                            try{
                                callNum = login.nextDouble();
                            }
                            catch (Exception e){
                                System.out.println("Invalid input, returning to menu!!!");
                                login.next();
                                break;
                            }
                            isValidCallNum = itemUtil.validateCallNumber(db.getAvailableBooks(), db.getAvailableVideos(), callNum);
                            if(isValidCallNum == 1 || isValidCallNum == -1){
                                System.out.println("Call Number is invalid or video is already checked in");
                            }
                            else{
                                System.out.println(db.checkInVideo(callNum));
                            }
                        }
                        else{
                            System.out.println("Invalid input, returning to menu");
                            break;
                        }
                        break;
                    case 8: //Checkout a book or video
                        List<book> availBooks = db.getAvailableBooks();
                        List<video> availVideos = db.getAvailableVideos();

                        if (availBooks.isEmpty() && availVideos.isEmpty()) {
                            System.out.println("There are no books or videos available to checkout");
                            break;
                        } else {
                            if(!availBooks.isEmpty()){
                                System.out.println("Available Books:");
                                availBooks.stream().forEach(e -> System.out.println( "Title: " + e.getTitle() + ", "
                                        + "Author: " + e.getAuthor() + ", "
                                        + "Publisher: " + e.getPublisher() + ", "
                                        + "Call Number: " + e.getCallNumber() + ", "
                                        + "Genre: " + e.getGenre()));
                            }
                            if (!availVideos.isEmpty()){
                                System.out.println("Available Videos:");
                                availVideos.stream().forEach(e -> System.out.println("Title: "+  e.getTitle() + ", "
                                        + "Director: " + e.getDirector() + ", "
                                        + "ReleaseDate: " + e.getReleaseDate() + ", "
                                        + "Call Number: " + e.getCallNumber() + ", "
                                        + "Genre: "+ e.getGenre()));
                            }
                            System.out.println("Enter Book or Video Call Number");
                            try{
                                callNum = login.nextDouble();
                            }
                            catch (Exception e){
                                System.out.println("Invalid input, returning to menu!!!");
                                login.next();
                                break;
                            }
                             itemUtil = new BookAndVideoUtility();
                            isValidCallNum = itemUtil.validateCallNumber(db.getAvailableBooks(), db.getAvailableVideos(), callNum);
                            if (isValidCallNum == -1 || isValidCallNum == 3) {
                                System.out.println("Invalid Call Number or the item is checked out, Try Again");
                            } else if (isValidCallNum == 0) {
                                checkedOutBook bReserve = new checkedOutBook();
                                book b = new book();
                                for (book bList : availBooks) {
                                    if (bList.getCallNumber() == callNum) {
                                        b = bList;
                                    }
                                }
                                bReserve.setPatrons_id(id);
                                bReserve.setBooks_id(b.getId());
                                LocalDate dueDate = LocalDate.now().plusWeeks(2);
                                bReserve.setDueDate(dueDate);
                                db.checkoutBook(bReserve);
                                System.out.println("Book Checked Out and is due on: " + dueDate);
                                break;
                            } else if (isValidCallNum == 1) {
                                checkedOutVideo vReserve = new checkedOutVideo();
                                video v = new video();
                                for (video vList : availVideos) {
                                    if (vList.getCallNumber() == callNum) {
                                        v = vList;
                                    }
                                }
                                vReserve.setPatrons_id(id);
                                vReserve.setVideos_id(v.getId());
                                LocalDate dueDate = LocalDate.now().plusWeeks(2);
                                vReserve.setDueDate(dueDate);
                                db.checkOutVideo(vReserve);
                                System.out.println("Video Checked Out and is due on: " + dueDate);

                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        else {
            System.out.println("Invalid input");
            System.out.println("Exiting...");
        }
    }
}
