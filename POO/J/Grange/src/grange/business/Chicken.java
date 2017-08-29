package grange.business;
public class Chicken {
	// Class Model
	private static int eggsAtGrange;
	public static int getEggsAtGrange() {
		return eggsAtGrange;
	}
	// Object Model
	public int eggs;
	public Chicken throwEgg() {
		eggs++;
		eggsAtGrange++;
		return this;
	}
}
