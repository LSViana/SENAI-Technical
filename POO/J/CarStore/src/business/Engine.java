package business;
public class Engine {
	public Engine() {
		// Standard Constructor
	}
	public Engine(TypesOfEngine type, int potency) {
		super();
		this.type = type;
		this.potency = potency;
	}
	//
	public enum TypesOfEngine { V12, V8, V6, V4 };
	public TypesOfEngine type;
	public int potency;
}
