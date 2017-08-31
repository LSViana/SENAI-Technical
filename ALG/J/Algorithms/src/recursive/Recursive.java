package recursive;

import java.util.Scanner;

public class Recursive {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Factorial Calculator\n");
		//
		// System.out.printf("Type a value to calculate its factorial: ");
		// int value = input.nextInt();
		// System.out.printf("\n[IT] The result of %d! is: %d\n", value,
		// loopFactorial(value));
		// System.out.printf("[RC] The result of %d! is: %d\n", value,
		// recursiveFactorial(value));
		// System.out.print("Type two value to calculate the power [base and exponent]:
		// ");
		// int base = input.nextInt();
		// int expon = input.nextInt();
		// System.out.printf("\n[IT] The result of %d^%d is: %.10f\n", base, expon,
		// recursivePower(base, expon));
		// System.out.printf("[RC] The result of %d^%d is: %.10f\n", base, expon,
		// recursivePower(base, expon));
		System.out.print("Type how many numbers you want to appear: ");
		times = input.nextInt();
		System.out.printf("\n[IT] The result of Fibonacci of %d is: \n", times);
		recursiveFibonacci(1);
		resetFibonacci();
		System.out.printf("\n[RC] The result of Fibonacci of %d is: \n", times);
		recursiveFibonacci(1);
	}
	
	static void resetFibonacci() {
		fibPRE = 1;
		fibCUR = 1;
	}

	static long fibPRE = 1;
	static long fibCUR = 1;
	static int times = Integer.MAX_VALUE;

	static void recursiveFibonacci(int counter) {
		if (counter <= times) {
			if (counter == 1) {
				System.out.print(fibCUR + " ");
				recursiveFibonacci(++counter);
			} else if (counter == 2) {
				System.out.print(fibCUR + " ");
				recursiveFibonacci(++counter);
			} else {
				long temp = fibCUR;
				fibCUR += fibPRE;
				fibPRE = temp;
				System.out.print(fibCUR + " ");
				recursiveFibonacci(++counter);
			}
		}
	}

	// Power Calculation
	static double recursivePower(int base, int exponent) {
		if (exponent == 0)
			return 1;
		else if (exponent > 0)
			return base * recursivePower(base, exponent - 1);
		else
			return (1f / (base)) * recursivePower(base, exponent + 1);
	}

	// Factorial Calculation
	static long loopFactorial(int number) {
		long result = 1;
		for (; number > 0; number--)
			result *= number;
		return result;
	}

	static long recursiveFactorial(int number) {
		if (number == 0)
			return 1;
		else
			return number * recursiveFactorial(number - 1);
	}
}