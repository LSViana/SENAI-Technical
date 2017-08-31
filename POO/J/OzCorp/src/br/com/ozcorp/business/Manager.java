package br.com.ozcorp.business;
public class Manager extends Employee {
	public Manager(String name, String rg, long cpf, String enrollment, String email, String password,
			BloodType blood, Gender gender) {
		super(name, rg, cpf, enrollment, email, password, Role.Manager, blood, gender);
	}
}
