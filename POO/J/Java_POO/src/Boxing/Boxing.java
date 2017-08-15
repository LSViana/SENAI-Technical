package Boxing;
public class Boxing {
	public static void main(String[] args) {
		// Autoboxing of an 'int' value
        Integer intBox = 3;
        // Unboxing to an 'int' value
        int intUnbox = (int) intBox;

        // The same happens to any primitive variable
        Boolean boolBox = false;
        boolean boolUnbox = (boolean) boolBox;
	}
}