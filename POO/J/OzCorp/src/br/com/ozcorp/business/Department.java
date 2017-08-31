package br.com.ozcorp.business;
public enum Department {
	Administrative("Administrative", "ADM"), Technical("Technical", "TEC"), DataProcessingCenter("Data Processing Center", "DPC");
	Department(String name, String initials) {
		this.name = name;
		this.initials = initials;
	}
	private String name;
	private String initials;
	@Override
	public String toString() {
		return name;
	}
	public String nameOf() {
		return super.toString();
	}
}
