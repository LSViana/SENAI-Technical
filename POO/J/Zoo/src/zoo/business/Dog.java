package zoo.business;
public class Dog extends Animal {
	public Dog(double weight, String food) {
		super(weight, food);
	}
	public void hideBone() {
		System.out.println("I hid the bone!");
	}
}
