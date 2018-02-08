package br.senai.sp.casinoroyale.model;

public final class GenderClass {
	public enum Gender {
		MALE,
		FEMALE,
		OTHER;	
	}
	public static Gender parse(String value) {
		if(value == null)
			return Gender.OTHER;
		if(value.equalsIgnoreCase(Gender.MALE.toString()))
			return Gender.MALE;
		else if(value.equalsIgnoreCase(Gender.FEMALE.toString()))
			return Gender.FEMALE;
		else
			return Gender.OTHER;
	}
}

