package Conditionals;

public class If {
	public static void main(String[] args) {
		EvenValues();
	}
	
	private static void EvenValues() {
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
	
	private static void TestingIf() {
		// Using a bool value to perform a decision
				boolean value = true;
				if(value)
					System.out.println("It is true");
				
				// Testing the variable with value false in an 'if' operator
				value = false;
		        if(value)
		            System.out.println("It is false");
	}
}