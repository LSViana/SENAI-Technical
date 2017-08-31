/**
 * @author Lucas Viana
 */
package br.senai.sp.informatica.oop;
import java.text.NumberFormat;
import javax.naming.OperationNotSupportedException;
import br.senai.sp.informatica.oop.bank.Product;
import br.senai.sp.informatica.oop.bank.Employee;
import br.senai.sp.informatica.accessModifier.AccessModifier;
import java.util.List;
import java.util.ArrayList;
import br.senai.sp.informatica.oop.mathematics.Mathematics;
import enumerations.ChessPiece;
import br.senai.sp.informatica.oop.bank.Account;
import br.senai.sp.informatica.oop.bank.Customer;
public class Start {
	public static NumberFormat formatter = NumberFormat.getCurrencyInstance();
	//
	public static void main(String[] args) throws OperationNotSupportedException {
	    // Some tests with OOP concepts
		//workingWithAccounts();
		
		// Working with methods that return values
		//workingWithMatematics();
		
		// Working with some access modifiers
		//workingWithAccessModifiers();
		//workingWithEmployees();
		//workingWithProducts();
		
		// Manipulating enumerations and its values
		workingWithEnumerations();
	}
	
	public static void workingWithEnumerations() {
		// Printing all the values in an enumeration
		for (ChessPiece piece : ChessPiece.values())
			System.out.println(piece.index + " - " + piece.name());
	}

	public static void workingWithProducts() {
		// Declaring the Shopping List
		List<Product> products = new ArrayList<Product>();
		// Adding some products and validating their data

		Product orange = new Product();
		orange.setBarCode(1_111_111_111);
		orange.setCategory("Juicy Fruits");
		orange.setPerishable(true);
		orange.setPrice(2.2);
		orange.setDescription("A tropical fruit");
		products.add(orange);
		
		Product grape = new Product();
		grape.setBarCode(1_111_111_112L);
		grape.setCategory("Purple and Juicy");
		grape.setPerishable(true);
		grape.setPrice(1.8);
		products.add(grape);
		
		// Exhibiting the total price of the list
		double totalPrice = 0;
		for (Product product : products) {
			System.out.println(product + "\n");
			totalPrice += product.getPrice();
		}
		System.out.printf("\tTotal price: %s\n", formatter.format(totalPrice));
	}
	
	public static void workingWithEmployees() {
		Employee employee = new Employee();
		// Manipulating the 'name' property
		employee.setName("Lucas");
		System.out.println(employee);
		
		// Manipulating the 'active' property
		employee.setActive(true);
		System.out.println(employee);
	}

	public static void workingWithAccessModifiers() {
		AccessModifier am = new AccessModifier();
		// Public field is accessible
		am.publicField = 0;
		
		// Protected field is not accessible, due the miss of inheritance of the current class against the AccessModifier class
		// am.protectedField = 0;
		
		// Private field is not accessible because it is outside the class
		// am.privateField = 0;
		
		// Default/Package field is NOT accessible because it is outside the package where the class was declared
		// am.defaultField = 0;
	}

	public static void workingWithMatematics() {
		// Looking for the highest value
		System.out.println(Mathematics.highest(4.5, 2d, 13.0, 12d, 0991390123d, 1313.03, 113.2));
		System.out.println(Mathematics.highest(1, 2, 3, 4, 5, 6, 7, 13));
		System.out.println();
		// Making the sum of all values
		System.out.println(Mathematics.sum(1, 2, 3));
		System.out.println(Mathematics.sum(1, 2, 3));
		System.out.println(Mathematics.sum(1.2, 2.4, 3.6));
		System.out.println();
		// Finding the whole/integer part of a number's square root
		System.out.println(Mathematics.squareRoot(27));
		System.out.println(Mathematics.squareRoot(9));
		System.out.println();
		// Finding the average between a group of values
		System.out.println(Mathematics.average(30, 10));
		System.out.println(Mathematics.average(2.3, 4.5, 9.0, 3.2, 0.1));
	}
	
	public static void workingWithAccounts() throws OperationNotSupportedException {
		// Creating and manipulating Customer objects
		Customer lucas = new Customer("Lucas Viana", "123456789.12");
		Customer thayto = new Customer("Rafael Thayto", "987654321.98");
		//
		// Creating and manipulating Account objects
		Account origin = new Account(10_000.01, lucas);
		//
		Account.showBankBalance();
//		origin.name = "Lucas";
//		origin.balance = 10_000.01;
		//
		origin.showBalance();
		origin.withDraw(1000);
		Account.showBankBalance();
		origin.showBalance();
		origin.deposit(500);
		Account.showBankBalance();
		//
		Account destiny = new Account(3000.99, thayto);
		//
//		destiny.name = "Rafael";
//		destiny.balance = 3000.99;
		//
		origin.showBalance();
		destiny.showBalance();
		origin.transferTo(destiny, 500.01);
		origin.showBalance();
		destiny.showBalance();
		Account.showBankBalance();
	}
}
