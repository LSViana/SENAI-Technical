package Conditionals;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
public class Conditionals {
	static Random random = new Random();
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) throws Exception {
		swing_RandomDie();
	}

	static void swing_RandomDie() {
		JSpinner userInput = new JSpinner(new SpinnerNumberModel(1, 0, 6, 1));
		int userValue = ((int) userInput.getValue());
		while(userValue != 0) {
			Object[] inputFields = {
					"Número: ", userInput,
					"[Insira zero para sair]"
			};
			JOptionPane.showMessageDialog(null, inputFields, "Bem-vindo ao nosso Jogo de Dados", JOptionPane.PLAIN_MESSAGE);
			int randomValue = 1 + random.nextInt(6);
			userValue = ((int)userInput.getValue());
			if(userValue != 0) {
				if(userValue  == randomValue) {
					JOptionPane.showMessageDialog(null, "Você acertou!\n\nO valor rolado foi: " + randomValue);
				}
				else {
					JOptionPane.showMessageDialog(null, "Você errou!\n\nO valor rolado foi: " + randomValue);
				}
			}
		}
	}

	static void randomDie() throws Exception {
		int userValue = -1;
		while(userValue != 0) {
			System.out.println("Seja bem-vindo ao nosso jogo de dados!");
			System.out.println();
			System.out.print("\tVocê deve inserir um valor de 1 a 6 para lançar seu palpite: ");
			int randomValue = 1 + random.nextInt(6);
			String userInput = "";
			do {
				userInput = scanner.nextLine();
			}
			while(!isNumber(userInput) || (Double.parseDouble(userInput) < 1 || Double.parseDouble(userInput) > 6));
			userValue = Integer.parseInt(userInput);
			if(userValue == 0) {
				System.out.println("Você escolheu sair... Volte sempre!");
			}
			if(randomValue == userValue) {
				System.out.println("Você acertou, parabéns!");
			}
			else {
				System.out.println("Errou, tente novamente!");
			}
			System.out.println("O valor da rolagem do dado foi: " + randomValue + "\n\n");
		}
	}

	static void switchCase() {
		char gender = 'M';
		switch (gender) {
		case 'M':
			System.out.println("Masculine");
			break;

		case 'F':
			System.out.println("Feminine");
			break;
		default:
			System.out.println("Another!");
			break;
		}

		// Testing with a String variable
		String technology = "sqlserver";
		switch (technology) {
		case "c++":
		case "c#":			
		case "java":
			System.out.println("Programming Language");
			break;
		case "sqlserver":
		case "oracle":
		case "mysql":
		case "postgre":
			System.out.println("Database Management System");
			break;
		default:
			System.out.println("Unknown Technology.");
			break;
		}
	}

	static void ifElse(int age) {
		/* until 12 y/o: child
		 * from 12 to 18: adolescent
		 * from 18 to 60: adult
		 * higher than 60: best age
		 */
		if(age < 12) {
			System.out.println("Child!");
		}
		else if(age >= 12 && age < 18) {
			System.out.println("Adolescent!");
		}
		else if(age >= 18 && age < 60) {
			System.out.println("Adult!");
		}
		else if(age >= 60) {
			System.out.println("Best Age!");
		}
	}

	static void evenValues() {
		int value = 11;
		System.out.println("Value: " + value);
		if(value % 2 == 0)
			System.out.println("It is even");
		else
			System.out.println("It is odd");
		//
		value = 10;
		System.out.println("Value: " + value);
		if(value % 2 == 0)
			System.out.println("It is even");
		else
			System.out.println("It is odd");
	}

	static void testingIf() {
		// Using a bool value to perform a decision
		boolean value = true;
		if(value)
			System.out.println("It is true");
		// Testing the variable with value false in an 'if' operator
		value = false;
		if(value)
			System.out.println("It is false");
	}

	static boolean isNumber(String input) {
		try {
			Double.parseDouble(input);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
