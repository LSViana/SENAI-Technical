import java.util.Scanner;
import javax.swing.JOptionPane;

public class Reading {
	private static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		//
		calculateCircumference();
	}

	public static void calculateCircumference() {
		System.out.print("Input a valid radius value: ");
		double radius = scanner.nextDouble();
		//
		double circumference = 2 * Math.PI * radius;
		double area = Math.PI * Math.pow(radius, 2);
		String resultFormatter = "The radius received: %.2f\nCircumference: %.2f\nArea: %.2f";
		System.out.printf(resultFormatter, radius, circumference, area);
	}

	public static void usingBitwiseOperators() {
		int value = 3;
		value >>= 1;
		System.out.println(value + " (" + Integer.toBinaryString(value) + ")");
	}

	public static void usingAssignmentOperators() {
		int someNumber = 6;
		someNumber += 3;
		System.out.println(someNumber);
	}

	public static void usingLogicalOperators() {
		int firstNumber = 6;
		System.out.println((firstNumber >= 1) && (firstNumber <= 10));
		//
		System.out.println((firstNumber >= 1) || (firstNumber <= 10));
		//
		System.out.println((firstNumber > 3) + " - " + !(firstNumber > 3));
	}

	public static void usingComparisonOperators() {
		int number = 34;
		System.out.println(number == 34);
		//
		String stringRep = "34";
		System.out.println(stringRep == "34");
		//
		String similarString = "34";
		System.out.println(stringRep == similarString);
		//
		/* System.out.println(7 instanceof Integer) */ // Not allowed
		Integer wrapperNumber = 34;
		System.out.println(wrapperNumber instanceof Integer);
	}

	public static void usingMathOperators() {
		// Using 'int' literals
		int modulus = 3 % 2; // Returns '1' (as an int)
		System.out.printf("Modulus: %d\n", modulus);
		int intDivision = 3 / 2; // Returns '1' (as an int)
		System.out.printf("Division: %d\n", intDivision);
		// Using 'double' literals
		double doubleDivision = 3.0 / 2; // Returns '1.5' (as a double)
		System.out.printf("Division: %.1f\n", doubleDivision);
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