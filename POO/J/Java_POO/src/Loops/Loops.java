package Loops;

import java.util.ArrayList;

public class Loops {
	public static void main(String[] args) {
		doWhileLoop();
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
	static void foreachLoop() {
		// In 'foreach' structures the code become less error prone
		int[] vector = { 2, 4, 6, 8 };
		//
		for (int value : vector) {
			System.out.print(value + " ");
		}
		System.out.println();
		//
		// Using collection and Foreach
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		for (Integer value : list) {
			System.out.println(value);
		}
	}
	static void doWhileLoop() {
		// The main difference between 'while' and 'do-while' structures is that even the condition is false at the first time at 'do-while', it executes its code and after the first test is performed. At the 'while', the test is performed before any execution of its body, so, only if the test at the current iteration returns true, its body is executed, only if the condition is false the iteration is stopped.
		int value = 10;
		System.out.println("While: ");
		while(value < 10) {
			System.out.print(value++ + " ");
		}
		System.out.println();
		value = 10;
		System.out.println("Do-While: ");
		do {
			System.out.print(value++ + " ");
		} while(value < 10);
	}
	static void writeSquare(int size) {
		// Function to print a square with the supplied value
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// To have fun, I am writing at the Error Stream c:
				System.err.print("* ");
			}			
			System.err.println();
		}
	}
}
