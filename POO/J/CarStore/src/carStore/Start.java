package carStore;
import java.awt.Color;
import business.Car;
import business.Engine;
import business.Engine.TypesOfEngine;
public class Start {
	public static void main(String[] args) {
		// Introducing to constructors
		workingWithCars();
	}
	static void workingWithCars() {
		// "Without constructor", i. e., using the standard compiler supplied by Java
		Car ferrari = new Car();
		ferrari.color = Color.BLUE;
		ferrari.maxSpeed = 319;
		ferrari.model = "Enzo";
		ferrari.zeroToHundred = 3.73;
		Engine f50 = new Engine(TypesOfEngine.V6, 312);
		ferrari.engine = f50;
		//
		// Using the user-defined constructor
		Engine veyron = new Engine(TypesOfEngine.V8, 450);
		Car bugatti = new Car("Veyron", 407, Color.RED, 2.81, veyron);
		//
		System.out.printf("Showing the results:\n\t%s\n\t%s", ferrari, bugatti);
	}
}
