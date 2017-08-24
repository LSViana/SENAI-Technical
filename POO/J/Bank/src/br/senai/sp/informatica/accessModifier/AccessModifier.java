package br.senai.sp.informatica.accessModifier;

public class AccessModifier {
	public int publicField = 3;
	private int privateField = 4;
	// In Java, the default access modifier to the class' variables is 'PACKAGE'
	int defaultField = 5;
	protected int protectedField = 6;
}
class AccessInheritance extends AccessModifier {
	public AccessInheritance() {
		// Non-accessible fields, but they are still inside this class, but, you can't modify from here
		// this.privateField = -4;
		
		// Accessible fields, accessible 
		this.publicField = -3;
		this.defaultField = -5;
		this.protectedField = -6;
	}
}
