package br.senai.sp.informatica.accessModifier;
// This class tests the Default/Package access modifier
public class AccessModifierStart {
	// Accessing the Default/Package fields from AccessModifier class
	private AccessModifierStart() {
		AccessModifier am = new AccessModifier();
		// Accessible fields
		am.defaultField = 0;
		am.protectedField = 0;
		am.publicField = 0;
		
		// Non-accessible fields
		// am.privateField = 0;
	}
}
