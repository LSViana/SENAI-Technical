package business;

public class Person {
	public String name;
	public int age;
	public void birthday() {
		age++;
		System.out.printf("You are getting one year old! Congratulations for your %d years of life!\n", age);
	}
	public void ageDifference(Person p) {
		System.out.printf("The difference of age between %s and %s is: %d.\n", name, p.name, Math.abs(age - p.age));
	}
	public void showAge() {
		System.out.printf("Current age: %d\n", age);
	}
}
