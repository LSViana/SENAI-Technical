import java.util.Scanner;
import javax.swing.JOptionPane;

public class Reading {
	private static Scanner scanner;

	public static void main(String[] args) {
		usingJOptionPane();
	}

	public static void usingJOptionPane() {
		String name = JOptionPane.showInputDialog(null, "What is your name?");
		JOptionPane.showMessageDialog(null, "Welcome " + name + "!");
	}

	public static void usingScanner() {
		scanner = new Scanner(System.in);
		//
		System.out.print("What is your name? ");
		String userName = scanner.nextLine();
		System.out.printf("Welcome, %s!\n", userName);
	}
}