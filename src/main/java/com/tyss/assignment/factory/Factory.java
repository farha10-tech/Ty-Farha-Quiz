package com.tyss.assignment.factory;

import java.util.Date;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tyss.assignment.dao.LoginDetails;
import com.tyss.assignment.dto.QuizQuestions;
import com.tyss.assignment.dto.Register;
import com.tyss.assignment.dto.Results;

public class Factory {

	ApplicationContext context = new ClassPathXmlApplicationContext("quiz.xml");
	LoginDetails loginDetails = (LoginDetails) context.getBean("loginDetails");
	Scanner scanner = new Scanner(System.in);
	int result = 0;
	int noOfQuestions = 0;

	public void register() {
		Register register = (Register) context.getBean("register");
		System.out.println("Enter UserName");
		String username = scanner.next();
		register.setUsername(username);
		System.out.println("Enter Password");
		String password = scanner.next();
		register.setPassword(password);
		System.out.println("Enter ConfirmPassword");
		String confirmPassword = scanner.next();
		if (password.equals(confirmPassword)) {
			register.setConfirmpassword(confirmPassword);
			loginDetails.register(register);
			System.out.println("Sucessfully Registered");
		}
		System.out.println("Confirm password is not matched");
	}

	public void login() {

		Register login = (Register) context.getBean("register");
		System.out.println("Enter UserName");
		String username = scanner.next();
		System.out.println("Enter Password");
		String password = scanner.next();
		Register login2 = loginDetails.login(username, password);
		String password1 = login2.getPassword();
		if (password.equals(password1)) {
			System.out.println("Successfully Login");
			int iterate2;
			do {
				System.out.println("1 for JAVA Quiz");
				System.out.println("2 for HTML  Quiz");
				System.out.println("3 for CSS  Quiz");
				int subject = scanner.nextInt();
				subject(subject);
				questions(subject);
				results(subject);
				System.out.println("Press 1 for to Retry");
				System.out.println("Press 2 for Exit");
				iterate2 = scanner.nextInt();
			} while (iterate2 == 1);
		} else {
			System.out.println("wrong password");
		}
	}

	public void questions(int subject) {
		QuizQuestions questions1 = loginDetails.questions(subject);
		System.out.println("Please answer questions in YES/NO format....");
		System.out.println(questions1.getQuestion1());
		noOfQuestions++;
		String answer1 = scanner.next();
		if (answer1.equalsIgnoreCase(questions1.getQuestion1answerString())) {
			result++;
		}
		System.out.println(questions1.getQuestion2());
		noOfQuestions++;
		String answer2 = scanner.next();
		if (answer2.equalsIgnoreCase(questions1.getQuestion2answerString())) {
			result++;
		}
		System.out.println(questions1.getQuestion3());
		noOfQuestions++;
		String answer3 = scanner.next();
		if (answer3.equalsIgnoreCase(questions1.getQuestion3answerString())) {
			result++;
		}
		System.out.println(questions1.getQuestion4());
		noOfQuestions++;
		String answer4 = scanner.next();
		if (answer4.equalsIgnoreCase(questions1.getQuestion4answerString())) {
			result++;
		}
		System.out.println(questions1.getQuestion5());
		noOfQuestions++;
		String answer5 = scanner.next();
		if (answer5.equalsIgnoreCase(questions1.getQuestion5answerString())) {
			result++;
		}
	}

	public void subject(int subject) {
		if (subject == 1) {
			QuizQuestions questions = (QuizQuestions) context.getBean("java");
			
			
			
		} else if (subject == 2) {
			QuizQuestions questions = (QuizQuestions) context.getBean("html");
		} else if (subject == 3) {
			QuizQuestions questions = (QuizQuestions) context.getBean("css");
		}
	}

	public void results(int subject) {
		Results results = (Results) context.getBean("results");
		results.setId(subject);
		Date date = new Date();
		results.setTime_Date(date);
		results.setMarks(result);
		results.setPercentage((result * 100) / noOfQuestions);
		loginDetails.results(results);
		Results results2 = loginDetails.getResults(date);
		System.out.println("Quiz Completed");
		System.out.println("Total marks obtained: " + results2.getMarks());
		System.out.println("Percentage: " + results2.getPercentage() + "%");
		result = 0;

	}

}
