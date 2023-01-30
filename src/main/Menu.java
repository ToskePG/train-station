package main;

import java.util.Scanner;

public class Menu {
	
	Scanner input = new Scanner(System.in);
	
	
	
	
	private int checkInput(String number, String message) {
		
		boolean interceptor = true;
		while(interceptor) {
			try {
				return Integer.parseInt(number);
			}catch(Exception e) {
				System.out.println(message);
				number = input.nextLine();
			}
		}
		return 0;
	}	
}
