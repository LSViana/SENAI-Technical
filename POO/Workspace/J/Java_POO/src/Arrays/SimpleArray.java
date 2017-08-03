package Arrays;
import java.util.Arrays;
public class SimpleArray {
	public static void main(String[] args) {
		// Declaring a new array
		// <type>[] <var_name> = new <type>[<capacity>];
		
		// First way to declare an array
		String[] countries1 = new String[4];
		countries1[0] = "Brasil";
		countries1[1] = "Rússia";
		countries1[2] = "Índia";
		countries1[3] = "China";
		
		// Second way to declare an array
		String[] countries2 = { "Brasil", "Rússia", "Índia", "China" };
		
		// Printing an array
		// Using 'Arrays'
		System.out.println(Arrays.toString(countries1));
		
		// Using foreach structure to print an array
		for(String country : countries1)
			System.out.print(country + " ");
		System.out.println();
		
		// Looking for a value inside an Array, but it is able to return only 0 (zero) or 1 (one), because it is made to work on trees, where each branch has two other
		int russiaIndex = Arrays.binarySearch(countries1, "Rússia");
		System.out.println("'Rússia' found at: " + russiaIndex);
		
		// Ordering an Array using Arrays.sort class method
		Arrays.sort(countries1, 0, 4);
		
		// Printing an ordered array
		for(String country : countries1)
			System.out.print(country + " ");
		System.out.println();
	}
}
