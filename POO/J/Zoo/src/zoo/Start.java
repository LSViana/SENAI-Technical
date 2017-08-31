package zoo;
import zoo.business.*;
public class Start {
	public static void main(String[] args) {
		testingAnimals();
	}

	private static void testingAnimals() {
		Chicken thayto = new Chicken(2, "Milho");
		thayto.food = "Milho";
		thayto.weight = 2;
		thayto.makeNoise();
		thayto.sleep();
		thayto.put();
		thayto.fly();
		
		Animal tani = new Dog(30, "Criancinha");
		tani.food = "Criancinha";
		tani.weight = 30;
		tani.makeNoise();
		tani.sleep();
		
		Alien et = new Alien(33, "Humanos");
		et.sleep();
		
		// Testing 'instanceof'
		System.out.println(tani instanceof Dog);
		System.out.println(tani instanceof Animal);
		System.out.println(tani instanceof Chicken);
	}
}
