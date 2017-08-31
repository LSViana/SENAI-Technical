package br.com.ozcorp.business;
public class Secretary extends Employee {
	public Secretary(String name, String rg, long cpf, String enrollment, String email, String password, 
			BloodType blood, Gender gender) {
		super(name, rg, cpf, enrollment, email, password, Role.Secretary, blood, gender);
	}
}
