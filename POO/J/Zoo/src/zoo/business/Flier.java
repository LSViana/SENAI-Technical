package zoo.business;
public class Flier extends Animal {
	protected Flier(double weight, String food) {
		super(weight, food);
	}
	
	public void fly() {
		System.out.println("I flied!");
	}
	
	public void put() {
		System.out.println("I put!");
	}
}
