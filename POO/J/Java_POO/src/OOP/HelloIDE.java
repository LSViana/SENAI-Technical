package OOP;
import java.util.Scanner;
public class HelloIDE {
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		String ideName = "Eclipse Oxygen";
		System.out.printf("Hello, %s!\n", ideName);
		//
		System.out.print("Conte-nos o seu nome: ");
		String userName = input.nextLine();
		System.out.printf("Seja bem-vindo, %s!\n", userName);
	}
}
