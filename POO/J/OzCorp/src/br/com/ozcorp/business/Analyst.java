package br.com.ozcorp.business;
public class Analyst extends Employee {
	public Analyst(String name, String rg, long cpf, String enrollment, String email, String password,
			BloodType blood, Gender gender) {
		super(name, rg, cpf, enrollment, email, password, Role.Analyst, blood, gender);
	}
}
