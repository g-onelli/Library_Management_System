package com.menuCalls;

public class MenuDisplay {
	public static void mainMenuDisplay() {
		String mainMenu = "—--------Library Management—--------\r\n"
				+ "1. Librarian Login\r\n"
				+ "2. Patron Login\r\n"
				+ "0. To Exit\r\n"
				+ "—-----------------------------------------------\r\n"
				+ "";
		System.out.println(mainMenu);
	}

	public static void libMenuDisplay() {
		String libMenu = "—------------Librarian Menu—-----------\r\n"
				+ "Welcome, username!\r\n"
				+ "1. View video and book collections\r\n"
				+ "2. View room collections\r\n"
				+ "3. View/Modify Patrons\r\n"
				+ "4. Events\r\n"
				+ "5. Book requests\r\n"
				+ "6. Renew Library cards\r\n"
				+ "0. To Exit\r\n"
				+ "—-----------------------------------------------\r\n"
				+ "";
		System.out.println(libMenu);
	}
	
	public static void requestMenuDisplay() {
		String reqMenu = "—------------Librarian Menu—-----------\r\n"
				+ "Welcome, username!\r\n"
				+ "1. View book requests\r\n"
				+ "2. Complete book requests\r\n"
				+ "0. To Exit\r\n"
				+ "—-----------------------------------------------\r\n"
				+ "";
		System.out.println(reqMenu);
	}
	
	public static void patMenuDisplay() {
		String patMenu = "—------------Patron Menu—-----------\r\n"
				+ "Welcome! \r\n"
				+ "1. View video and book collections\r\n"
				+ "2. View room collections\r\n"
				+ "3. View your checked out books and videos\r\n"
				+ "4. Submit book requests\r\n"
				+ "5. View overdue books and videos\r\n"
				+ "6. Reserve a room\r\n"
				+ "7. Check in a book or video\r\n"
				+ "8. Checkout a book or video\r\n"
				+ "0. To Exit\r\n"
				+ "—-----------------------------------------------\r\n"
				+ "";
		System.out.println(patMenu);
	}

	public static void viewVideoBooks() {
		String vidmenu = "——Video and Book Collections—----\r\n" +
				"1. View Books\r\n" +
				"2. View Videos\r\n" +
				"3. Add Book\r\n" +
				"4. Add Video\r\n" +
				"5. Remove Book\r\n" +
				"6. Remove Video\r\n" +
				"7. Search for Books\r\n" +
				"8. Search for Videos\r\n" +
				"0. To Exit\r\n" +
				"—-----------------------------------------------\r\n" +
				"";
		System.out.println(vidmenu);
	}

	public static void viewModifyPatrons() {
		String vModPat = "—---------View/Modify Patrons—------\r\n" +
				"1. View All Patrons\r\n" +
				"2. Register Patron\r\n" +
				"3. Remove Patron\r\n" +
				"0. To Exit\r\n" +
				"—---------------------------------------------\r\n" +
				"";
		System.out.println(vModPat);
	}

	public static void eventMenuDisplay() {
		String patMenu = "—------------Event Menu—-----------\r\n"
				+ "1. View event schedule\r\n"
				+ "2. Edit event\r\n"
				+ "3. Add event\r\n"
				+ "4. Remove event\r\n"
				+ "0. Return to librarian menu\r\n"
				+ "—-----------------------------------------------\r\n"
				+ "";
		System.out.println(patMenu);
	}

	public static void searchBooksDisplay() {
		String srMenu = "------Please Enter Tag to Search By------\r\n"
				+   "Title, Author, Publisher, or Genre\r\n"
				+   "-----------------------------------------\r\n";
		System.out.println(srMenu);

	}
	public static void searchVideosDisplay() {
		String srMenu = "------Please Enter Tag to Search By------\r\n"
				+   "Title, Director, or Genre\r\n"
				+   "-----------------------------------------\r\n";
		System.out.println(srMenu);

	}

	public static void searchTermDisplay() {
		String termMenu = "-------Enter Term to Search For--------\r\n";
		System.out.println(termMenu);
	}

	public static void closingProgram() {
		String closeMessage = "Exiting the program...\r\n";
		System.out.println(closeMessage);
	}

	public static void exitMessage() {
		String exitResponse = "The program has been closed.\r\n"
				+"Thank you for coming to our library.\r\n"+
				"Have a great day.";
		System.out.println(exitResponse);
	}
	public static void viewVideoBooksPat() {
		String vidmenu = "——Video and Book Collections—----\r\n" +
				"1. View Books\r\n" +
				"2. View Videos\r\n" +
				"0. To Exit\r\n" +
				"—-----------------------------------------------\r\n" +
				"";
		System.out.println(vidmenu);
	}
}
