package Loops;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This application receives a list of products and then prints at the screen all of them ordered by the price
 * @author LSViana
 */
public class ProductList {
	static ArrayList<String> products = new ArrayList<>();
	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		readProducts();
		printProducts();
	}
	static void readProducts() {
		System.err.println("Bem-vindo ao nosso Gerenciador de Lista de Produtos: [Digite FIM para sair]");
		try	{ Thread.sleep(10); } catch(Exception exc) { }
		//
		String currentProduct = ""; // Empty String
		do {
			System.out.print("\tDigite o nome do produto: ");
			currentProduct = input.nextLine();
			products.add(currentProduct);
		} while(!currentProduct.toUpperCase().equals("FIM"));
		products.remove(products.size() - 1);
	}
	static void printProducts() {
		System.out.println();
		System.err.println("Lista de Produtos: ");
		try	{ Thread.sleep(10); } catch(Exception exc) { }
		for(int i = 0; i < products.size(); i++) {
			System.out.printf("Produto %d: %s\n", i + 1, products.get(i));
		}
		System.out.println("\tFim da lista. Agradecemos a preferência!");
	}
}