package br.com.ozcorp.business;
public enum BloodType {
	On("O-"), Op("O+"), An("A-"), Ap("A+"), Bn("B-"), Bp("B+"), ABn("AB-"), ABp("AB+");
	BloodType(String name) {
		this.name = name;
	}
	private String name;
	
	@Override
	public String toString() {
		return name;
	}
	public String nameOf() {
		return super.toString();
	}
}
