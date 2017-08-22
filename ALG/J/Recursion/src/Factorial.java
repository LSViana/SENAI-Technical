import java.util.Scanner;
public class Factorial {
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("\t\tFactorial Calculator\n");
		System.out.print("Type the number to calculate the factorial: ");
		//
		int value = input.nextInt();
		System.out.printf("The result is %d is: %d\n", value, factorial(value));
	}
	public static int factorial(int n) {
		if(n == 0)
			return 1;
		return n * factorial(n - 1);
	}
}
