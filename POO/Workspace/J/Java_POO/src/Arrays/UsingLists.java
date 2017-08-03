package Arrays;

import java.util.ArrayList;

public class UsingLists {
	public static void main(String[] args) {
		// Creating an ArrayList that can hold references to String objets
		ArrayList<String> colors = new ArrayList<>();

		// Add new elements to the ArrayList
		colors.add("White");

		// Prints all elements of the ArrayList variable
		System.out.println(colors.toString());

		// Adding a new item to a specified index
		colors.add(0, "Red");
		// Since this point, the item 'White' becomes the index 1 and the item, 'Vermelho' remains at index 0
		
		// Prints all elements of the ArrayList variable
				System.out.println(colors.toString());

		// Removing an element with the specified content, or reference
		colors.remove("White");
		// In this single case, it is equivalent to execute 'remove(1);'

		// Prints all elements of the ArrayList variable
		System.out.println(colors.toString());
		
		// Verifying if an ArrayList contains a value
		System.out.println(colors.contains("White"));
		System.out.println(colors.contains("Red"));
	}
}
