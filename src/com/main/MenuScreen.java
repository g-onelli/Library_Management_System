
package com.main;


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
        System.out.println("Please choose an option: ");
        int loginInput = loginChoice.nextInt();
        if (loginInput == 0) {
            MenuDisplay.closingProgram();
            MenuDisplay.exitMessage();
        }
        if (loginInput == 1) {
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
                        System.out.println("Login Sucessful...");
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
                        // MenuDisplay.viewVideoBooks();
                        int vid = loginChoice.nextInt();
                        while (true) {
                            if (librarianInput == 0) {
                                System.out.println("Exiting.. Bye");
                                break;
                            }
                            switch (vid) {
                                case 1:
                                    // Display all the books from the database
                                    List<book> books = db.showBooks();
                                    for (book b : books) {
                                        System.out.println(b.toString());
                                    }
                                    break;
                                case 2:
                                    // Display all the videos from the database
                                    List<video> videos = db.showVideos();
                                    for (video v : videos) {
                                        System.out.println(v.toString());
                                    }
                                    break;
                                case 3:
                                    // Add Books
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
                                    // Add book to database using the DB class
                                    System.out.println(db.insertBook(temp));
                                    break;
                                case 4:
                                    // Add Videos
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
                                default:
                                    break;
                            }
                        }
                        break;
                    case 2:
                        // Room collections -output list of rooms from the database
                        System.out.println("List of schedulable rooms:");

                        List<room> rooms = db.showRooms();

                        for (room r : rooms) {
                            System.out.println(r.toString());
                        }
                        break;
                    case 3:
                        // View / modify patrons
                        // MenuDisplay.viewModifyPatrons();
                        int vMP = loginChoice.nextInt();
                        while (true) {
                            if (librarianInput == 0) {
                                System.out.println("Exiting.. Bye");
                                break;
                            }
                            switch (vMP) {
                                case 1:
                                    // show all patrons
                                    List<patron> pats = db.showPatrons();

                                    System.out.println("List of Patrons:");
                                    for (patron p : pats) {
                                        System.out.println(p.toString());
                                    }
                                    break;
                                case 2:
                                    // Register patrons
                                    patron tempPat;
                                    System.out.println("Enter Patron Name:");
                                    String name = loginChoice.nextLine();
                                    System.out.println("Enter Card Exp. Date:");
                                    String expDate = loginChoice.nextLine();
                                    System.out.println("Enter Patron Password:");
                                    String password = loginChoice.nextLine();
                                    tempPat = new patron(0, name, expDate, 0, password);
                                    System.out.println(db.insertPatron(tempPat));
                                    // tempPat = new patron(0, name, expDate, 0);
                                    // add tempPat to the database
                                    break;
                                case 3:
                                    // Remove Patrons
                                    System.out.println("Enter Patron ID to Remove Patron:");
                                    int toRem = loginChoice.nextInt();
                                    // remove patron from database and return success/fail state
                                    System.out.println(db.removePatron(toRem));
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

        } else {
            //Call switch statement for patron menu
            int id;
            while (true) {
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
                        System.out.println("Login Sucessful...");
                        break;
                    }
                }
            }


            MenuDisplay.patMenuDisplay();
            while (true) {
                MenuDisplay.patMenuDisplay();
                int patronInput = loginChoice.nextInt();
                if (patronInput == 0) {
                    System.out.println("Exiting.. Bye");
                    break;
                }
                switch (patronInput) {
                    case 1: // 1. View video and book collections
                        break;
                    case 2: // 2. View room collections
                        List<room> list = db.showRooms();
                        for (room r : list) {
                            System.out.println(r);
                        }
                        break;
                    case 3: //3. View your checked out books and videos
                        List<String> bookList = db.fetchCheckedOutBooks(id);
                        if (bookList.isEmpty())
                            System.out.println("You have no books checked out");
                        else {
                            System.out.println("Here are your checked out books");
                            for (String s : bookList) {
                                System.out.println(s);
                            }
                        }
                        List<String> videoList = db.fetchCheckedOutVideos(id);
                        if (videoList.isEmpty())
                            System.out.println("You have no videos checked out");
                        else {
                            System.out.println("Here are your checked out videos");
                            for (String s : videoList) {
                                System.out.println(s);
                            }
                        }
                        break;
                    case 4: // 4. Submit book requests
                        break;
                    case 5: //5. View overdue books and videos
                        List<String> overdueBooks = db.fetchOverdueBooks(id);
                        if (overdueBooks.isEmpty())
                            System.out.println("You have no books overdue");
                        else {
                            System.out.println("Here are your overdue books");
                            for (String s : overdueBooks) {
                                System.out.println(s);
                            }
                        }
                        List<String> overdueVideos = db.fetchOverdueVideos(id);
                        if (overdueVideos.isEmpty())
                            System.out.println("You have no videos overdue");
                        else {
                            System.out.println("Here are your overdue videos");
                            for (String s : overdueVideos) {
                                System.out.println(s);
                            }
                        }
                        break;
                    case 6: // 6. Reserve a room
                        while (true) {
                            System.out.println("********************************");
                            System.out.println("Available Rooms");
                            System.out.println(db.showFreeRooms());
                            System.out.println("********************************");
                            System.out.println("Enter a room number:");
                            int roomId;
                            try{
                                roomId = login.nextInt();
                            }
                            catch (Exception e){
                                System.out.println("Invalid input, returning to menu!!!");
                                login.next();
                                break;
                            }
                            RoomUtility util = new RoomUtility();
                            boolean isValidId = util.validateRoomId(db.showFreeRooms(), roomId);
                            if (!isValidId) {
                                System.out.println("Invalid ID or Room Reserved, Try Again");
                            } else {
                                System.out.println("Enter Patron ID: ");
                                checkedOutRoom reserve = new checkedOutRoom();
                                int pId = login.nextInt();
                                reserve.setPatrons_id(pId);
                                reserve.setRooms_roomnumber(roomId);
                                long millis = System.currentTimeMillis();
                                java.sql.Date date = new java.sql.Date(millis);
                                System.out.println(date);
                                reserve.setDueDate(date);
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
                            System.out.println(db.checkInBook(callNum));
                            break;
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
                            System.out.println(db.checkInVideo(callNum));
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
                            System.out.println(db.checkInBook(callNum));
                            System.out.println("Enter book call number");
                            try{
                                callNum = login.nextDouble();
                            }
                            catch (Exception e){
                                System.out.println("Invalid input, returning to menu!!!");
                                login.next();
                                break;
                            }
                            System.out.println(db.checkInVideo(callNum));
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
                            BookAndVideoUtility itemUtil = new BookAndVideoUtility();
                            int isValidCallNum = itemUtil.validateCallNumber(db.getAvailableBooks(), db.getAvailableVideos(), callNum);
                            if (isValidCallNum == -1) {
                                System.out.println("Invalid Call Number or the item is checked out, Try Again");
                            } else if (isValidCallNum == 0) {
                                book b = new book();
                                for (book bList : availBooks) {
                                    if (bList.getCallNumber() == callNum) {
                                        b = bList;
                                    }
                                }
                                checkedOutBook bReserve = new checkedOutBook();
                                bReserve.setPatrons_id(id);
                                bReserve.setBooks_id(b.getId());
                                long millis = System.currentTimeMillis();
                                java.sql.Date date = new java.sql.Date(millis);
                                bReserve.setDueDate(date);
                                db.checkoutBook(bReserve);
                                System.out.println("Book Checked Out and is due on: " + date);
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
                                long millis = System.currentTimeMillis();
                                java.sql.Date date = new java.sql.Date(millis);
                                System.out.println(date);
                                vReserve.setDueDate(date);
                                db.checkOutVideo(vReserve);
                                System.out.println("Book Checked Out and is due on: " + date);
                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
}