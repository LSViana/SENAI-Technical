package exceptionHandling;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.activity.InvalidActivityException;

public class ExceptionHandling {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws InvalidActivityException {
		// divideByZero();
		// divideByZeroHandled();
		// testingException();
	}

	static void testingException() {
		try {
			throwingException();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	static void throwingException() throws InvalidActivityException, SQLException {
		if (1 == 1)
			throw new SQLException("O que houve?");
		else
			throw new InvalidActivityException("Você foi burro, seu porra!");
	}

	static void divideByZero() {
		System.out.print("Type a numerator: ");
		int numerator = input.nextInt();
		System.out.print("Type a denominator: ");
		int denominator = input.nextInt();
		System.out.printf("\n\tFinal result: %d\n", numerator / denominator);
	}

	static void divideByZeroHandled() {
		boolean validNumber = false;
		do {
			try {
				System.out.print("Type a numerator: ");
				int numerator = input.nextInt();
				System.out.print("Type a denominator: ");
				int denominator = input.nextInt();
				validNumber = true;
				System.out.printf("\n\tFinal result: %d\n", numerator / denominator);
			} catch (InputMismatchException im) {
				System.err.println("Use only numbers!");
				validNumber = false;
			} catch (ArithmeticException a) {
				System.err.println("You can't make this operation, follow the mathematical rules.");
				validNumber = false;
			} finally {
				System.out.println("Resources are now free.");
				input.nextLine(); // When the 'InputMismatchException' is thrown, the keyboard tries to read the
									// 'nextInt()', because the method is called again, but, there is another thing,
									// not valid as a number, inside the System.out stream, then, it reads forever.
									// It doesn't happen with the 'ArithmeticException' because the value read is
									// still a number, then, it can be consumed at the next call to 'nextInt()'
									// method.
			}
		} while (!validNumber);
	}
}
