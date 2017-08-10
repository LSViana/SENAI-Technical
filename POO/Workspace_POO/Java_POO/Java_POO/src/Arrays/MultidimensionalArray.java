package Arrays;

public class MultidimensionalArray {
	public static void main(String[] args) {
		// The Java compiler is able to detect that each 'row' is a (new String[]) operation that needs to be done, and here there are not native rectangular arrays
		String[][] users = new String[][] {
			{ "Ricardo", "M", "DF", "oi" },
            { "Sandra", "F", "MG" },
            { "Beatriz", "F", "MG" }
		};
		
		// Printing elements using [row][col] index
		System.out.println(users[0][0] + " - " +
				users[0][1] + " - " +
				users[0][2]
		);
		
		// Getting the length (total number of items in the base array)
		System.out.println(users.length);
	}
}
