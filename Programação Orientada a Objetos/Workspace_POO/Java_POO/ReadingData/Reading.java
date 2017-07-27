import java.util.Scanner;

public class Reading {
	private static Scanner scanner;
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		//
		System.out.print("What is your name? ");
		String userName = scanner.nextLine();
		System.out.printf("Welcome, %s!\n", userName);
	}
}