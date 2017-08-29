package grange;
import grange.business.Chicken;
public class Start {
	public static void main(String[] args) {
		workingWithChickens();
	}
	static void workingWithChickens() {
		// Manipulating some 'static' values at classes
		Chicken donega = new Chicken();
		Chicken thayto = new Chicken();
		System.out.printf("Thayto's eggs: %d | Donegá's eggs: %d | Grange's eggs: %d\n", thayto.eggs, donega.eggs, Chicken.getEggsAtGrange());
		System.out.printf("Thayto is throwing an egg...\n", thayto.throwEgg());
		System.out.printf("Thayto is throwing an egg...\n", thayto.throwEgg());
		System.out.printf("Thayto is throwing an egg...\n", thayto.throwEgg());
		System.out.printf("Thayto's eggs: %d | Donegá's eggs: %d | Grange's eggs: %d\n", thayto.eggs, donega.eggs, Chicken.getEggsAtGrange());
		System.out.printf("Donegá is throwing an egg...\n", donega.throwEgg());
		System.out.printf("Donegá is throwing an egg...\n", donega.throwEgg());
		System.out.printf("Thayto's eggs: %d | Donegá's eggs: %d | Grange's eggs: %d\n", thayto.eggs, donega.eggs, Chicken.getEggsAtGrange());
		// Exploring the return values of methods
		thayto.throwEgg().throwEgg().throwEgg();
	}
}
