package com.tyss.assignment;

import java.util.Scanner;

import com.tyss.assignment.factory.Factory;

public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int iterate;
		do {
			System.out.println("welcome to Quiz");
			System.out.println("Press 1 for Register");
			System.out.println("Press 2 for Login");
			int option = scanner.nextInt();
			Factory factory = new Factory();
			if (option == 1) {
				factory.register();
			}
			if (option == 2) {
				factory.login();
			}
			System.out.println("Press 1 for one more time Login/Register");
			System.out.println("Press 2 for exit");
			iterate = scanner.nextInt();
		} while (iterate == 1);
	}

}
