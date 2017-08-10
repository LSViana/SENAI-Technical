package ordering;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.util.Random;

public class Ordering {
	// main method
	public static void main(String[] args) {
		fillVector();
		//
		// Bubble Sort
		{
			Instant start = Instant.now();
			bubbleSort();
			System.out.println(Duration.between(start, Instant.now()));
			fillVector();
		}
		// Insertion Sort
		{
			Instant start = Instant.now();
			insertionSort();
			System.out.println(Duration.between(start, Instant.now()));
			fillVector();
		}
		// Selection Sort
		{
			Instant start = Instant.now();
			selectionSort();
			System.out.println(Duration.between(start, Instant.now()));
			fillVector();
		}
	}

	// Ordering code
	static int[] vector = new int[50000];
	static Random random = new Random(1);

	static void fillVector() {
		for (int i = 0; i < vector.length; i++) {
			vector[i] = random.nextInt(999);
		}
	}

	static void bubbleSort() {
		System.out.println("\tBubble Sort");
		long  changes = 0, iterations = 0;
		//
		for (int i = 0; i < vector.length; i++) {
			iterations++;
			for (int j = 0; j < vector.length - i - 1; j++) {
				iterations++;
				if (vector[j] > vector[j + 1]) {
					changes++;
					vector[j + 1] += vector[j];
					vector[j] = vector[j + 1] - vector[j];
					vector[j + 1] -= vector[j];
				}
			}
		}
		//
		System.out.printf("Changes: %d\nIterations: %d\n", changes, iterations);
	}

	static void insertionSort() {
		System.out.println("\tInsertion Sort");
		long changes = 0, iterations = 0;
		//
		int i = 0, j = 0;
		for(i = 1; i < vector.length; i++) {
			iterations++;
			// 'j' receives the current value of 'i'
			j = i;
			while(vector[j - 1] > vector[j]) {
				iterations++;
				// Do the change
				changes++;
				vector[j] += vector[j - 1];
				vector[j - 1] = vector[j] - vector[j - 1];
				vector[j] -= vector[j - 1];
				// The variable 'j' goes to the previous one
				j--;
				if (j == 0)
					break;
			}
		}
		//
		System.out.printf("Changes: %d\nIterations: %d\n", changes, iterations);
	}

	static void selectionSort() {
		System.out.println("\tSelection Sort");
		long changes = 0, iterations = 0;
		//
		int i = 0, j = 0, min = 0;
		for(j = 0; j < vector.length - 1; j++) {
			iterations++;
			for(i = j + 1; i < vector.length - 1; i++) {
				iterations++;
				// If the current element is lower than the marked
				if(vector[i] < vector[min]) {
					changes++;
					vector[j] += vector[min];
					vector[min] = vector[j] - vector[min];
					vector[min] -= vector[j];
				}
			}
		}
		//
		System.out.printf("Changes: %d\nIterations: %d\n", changes, iterations);
	}
}
