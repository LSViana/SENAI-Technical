package br.com.ozcorp.business;
public enum Role {
	Director("Director", 25000, Department.Administrative),
	Secretary("Secretary", 7500, Department.Administrative),
	Manager("Manager", 17500, Department.Administrative),
	Engineer("Engineer", 22500, Department.Technical),
	Analyst("Analyst", 15000, Department.DataProcessingCenter);
	Role(String title, double baseSalary, Department department) {
		this.title = title;
		this.baseSalary = baseSalary;
		this.department = department;
	}
	private String title;
	private double baseSalary;
	private Department department;
	
	public double getBaseSalary() {
		return baseSalary;
	}
	
	@Override
	public String toString() {
		return title;
	}
	public String nameOf() {
		return super.toString();
	}
}
