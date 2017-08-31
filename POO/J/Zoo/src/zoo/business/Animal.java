package zoo.business;

public class Animal {
	public double weight;
	public String food;
	
	protected Animal(double weight, String food) {
		this.weight = weight;
		this.food = food;
	}
	
	public void sleep() {
		System.out.println("I slept!");
	}
	
	public void makeNoise() {
		System.out.println("I made noise!");
	}
}
