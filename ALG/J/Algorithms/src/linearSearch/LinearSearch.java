package linearSearch;

import java.util.Scanner;
import java.util.Random;

public class LinearSearch {
	static Scanner input = new Scanner(System.in);
	static int[] values = { 543, 2131, 154, 213, 1, 123, 12, 1, 97, 984 };
	public static void main(String[] args) {
		insertionSort();
	}
	
	private static void insertionSort() {
		int j = 0;
		for (int i = 1; i < values.length; i++) {
			j = i;
			do {
				if(values[j - 1] > values[j]) {
					values[j - 1] += values[j];
					values[j] = values[j - 1] - values[j];
					values[j - 1] -= values[j];
				}
			}
			while(--j != 0);
			continue;
		}
		//
		printValues();
	}
	
	private static void selectionSort() {
		int min = Integer.MIN_VALUE;
		for (int i = 0; i < values.length; i++) {
			min = i;
			for (int j = i + 1; j < values.length; j++) {
				if(values[j] < values[min]) {
					min = j;
				}
			}
			if(min != i) {
				values[i] += values[min];
				values[min] = values[i] - values[min];
				values[i] -= values[min];
			}
		}
		//
		printValues();
	}

	private static void bubbleSort() {
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < i; j++) {
				if(values[i] < values[j]) {
					values[i] += values[j];
					values[j] = values[i] - values[j];
					values[i] -= values[j];
				}
			}
		}
		//
		printValues();
	}
	
	private static void printValues() {
		for (int i = 0; i < values.length; i++) {
			System.out.print("<" + values[i] + "> ");
		}
	}

	private static void linearSearch() {
		float[] heights = new float[10];
		int iterator = 0;
		float highestHeight = Integer.MIN_VALUE;
		// Code
		for (iterator = 0; iterator < heights.length; iterator++) {
			do {
				System.out.printf("Input the height %d (in cm): ", iterator + 1);
				heights[iterator] = input.nextFloat();
				if (heights[iterator] > highestHeight) {
					highestHeight = heights[iterator];
				}
			} while (heights[iterator] <= 0);
		}
		System.out.println();
		System.out.printf("The highest height is: %.2fcm\n", highestHeight);
	}

	private static void testingLinearSearch() {
		// Variables
		float[] heights = new float[10];
		int iterator = 0, highestIndex = heights.length;
		float highestHeight = Integer.MIN_VALUE;
		// Code
		for (iterator = 0; iterator < heights.length; iterator++) {
			do {
				System.out.printf("Input the height %d (in cm): ", iterator + 1);
				heights[iterator] = input.nextFloat();
				if (heights[iterator] > highestHeight) {
					highestHeight = heights[iterator];
					highestIndex = iterator;
				}
			} while (heights[iterator] <= 0);
		}
		System.out.println();
		System.out.printf("The highest height is: %.2fcm\n" + "The highest index is: %d\n", highestHeight,
				highestIndex + 1);
	}
}
