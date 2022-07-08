package com.main;
import java.util.Scanner;

import com.menuCalls.MenuDisplay;
public class MenuScreen {
	public static void main(String[] args) {
		Scanner loginChoice = new Scanner(System.in);
		MenuDisplay.mainMenuDisplay();
		System.out.println("Please choose an option: ");
		int loginInput = loginChoice.nextInt();
		if(loginInput==0) {
			MenuDisplay.closingProgram();
			MenuDisplay.exitMessage();
		}
		while(true) {
			if(loginInput == 1) {
				//Call switch statement for librarian menu
			}else {
				//Call switch statement for patron menu
			}
		}
		
	}
}
