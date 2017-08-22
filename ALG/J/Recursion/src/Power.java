import java.util.Scanner;
public class Power {
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("\t\tPower Calculator\n");
		System.out.print("Type the base to calculate the power: ");
		int basis = input.nextInt();
		System.out.print("Type the exponent to calculate the power: ");
		int exponent = input.nextInt();
		//
		System.out.printf("The result of %d^%d is: %f\n", basis, exponent, power(basis, exponent));
	}
	public static double power(int b, int e) {
		if(e == 0)
			return 1;
		if(e < 0)
			return (1.0 / b) * power(b, e + 1);
		else 
		return b * power(b, e - 1);
	}
}
