package binarySearch;

import java.util.Random;

public class BinarySearch {
	public static int iteration = 0;
	static int[] vector = new int[1000];

	public static void main(String[] args) {
		// int[] vector = new int[10000];
		fillVector(vector);
		insertionSort(vector);
		int class_indexOf = class_binarySearch(vector, 46);
		int my_indexOf = class_binarySearch(vector, 46);
		//
		System.out.printf("Found at: %d\nLoops: %d\n", class_indexOf, iteration);
		System.out.printf("Found at: %d\nLoops: %d\n", my_indexOf, iteration);
	}

	// Binary Search Code
	static int class_binarySearch(int[] vector, int value) {
		int left = 0, right = vector.length - 1, mid = (left + right) / 2;
		iteration = 0;
		while (left <= right) {
			iteration++;
			if (vector[mid] == value)
				return mid;
			else if (vector[mid] > value) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
			mid = (left + right) / 2;
		}
		return -1;
	}

	static int my_binarySearch(int[] vector, int value) {
		int left = 0, right = vector.length - 1, mid = (left + right) / 2;
		// iteration = 0;
		while (vector[mid] != value) {
			// iteration++;
			if (mid == 0 || left == right - 1)
				return -1;
			if (vector[mid] > value) {
				right = mid;
			} else {
				left = mid;
			}
			mid = (left + right) / 2;
		}
		return mid;
	}

	// Linear Search Code
	static int linearSearch(int[] vector, int value) {
		// Search through the whole vector
		for (int i = 0; i < vector.length; i++) {
			// If it finds, returns the current index
			if (vector[i] == value) {
				System.out.printf("Found! [%d]\n", i);
				return i;
			}
		}
		System.out.printf("Not found! [%d]\n", vector.length);
		// Otherwise, it returns a signal of 'not found'
		return -1;
	}

	///
	static Random random = new Random(1);

	///
	// Ordering Code
	static void fillVector(int[] vector) {
		for (int i = 0; i < vector.length; i++) {
			vector[i] = random.nextInt(999);
		}
	}

	static void bubbleSort(int[] vector) {
		System.out.println("\tBubble Sort");
		long changes = 0, iterations = 0;
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

	static void insertionSort(int[] vector) {
		System.out.println("\tInsertion Sort");
		long changes = 0, iterations = 0;
		//
		int i = 0, j = 0;
		for (i = 1; i < vector.length; i++) {
			iterations++;
			// 'j' receives the current value of 'i'
			j = i;
			while (vector[j - 1] > vector[j]) {
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

	static void selectionSort(int[] vector) {
		System.out.println("\tSelection Sort");
		long changes = 0, iterations = 0;
		//
		int i = 0, j = 0, min = 0;
		for (j = 0; j < vector.length - 1; j++) {
			iterations++;
			for (i = j + 1; i < vector.length - 1; i++) {
				iterations++;
				// If the current element is lower than the marked
				if (vector[i] < vector[min]) {
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
