package br.com.ozcorp.business;
public class Director extends Employee {
	public Director(String name, String rg, long cpf, String enrollment, String email, String password,
			BloodType blood, Gender gender) {
		super(name, rg, cpf, enrollment, email, password, Role.Director, blood, gender);
	}

}
