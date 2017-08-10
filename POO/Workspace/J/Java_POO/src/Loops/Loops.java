package Loops;

public class Loops {
	public static void main(String[] args) {
		forLoop();
	}
	
	static void forLoop() {
		// In 'for' structures, post or pre increment don't change the behavior
		// Using for loop to count up
		for (int i = 0; i < 5; i++) {
			System.out.println(i);
		}
		System.out.println();
		// Using for loop to count down
		for (int i = 4; i >= 0; i--) {
			System.out.println(i);
		}
	}
}
