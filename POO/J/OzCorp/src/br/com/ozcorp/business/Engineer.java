package br.com.ozcorp.business;
public class Engineer extends Employee {
	public Engineer(String name, String rg, long cpf, String enrollment, String email, String password,
			BloodType blood, Gender gender) {
		super(name, rg, cpf, enrollment, email, password, Role.Engineer, blood, gender);
	}
}
