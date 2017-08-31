package br.com.ozcorp;
import br.com.ozcorp.business.*;
public class Start {
	public static void main(String[] args) {
		initializeEnterprise();
	}

	static void initializeEnterprise() {
		// Creating Employee instances
		Employee rafathayto = new Analyst("Rafael Thayto", "[RG]", 47744265856L, "[Enrollment]", "rafael@email.com", "123456789", BloodType.ABp, Gender.Feminine);
        Employee olsen = new Director("João Olsen", "[RG]", 66585632547L, "[Enrollment]", "olsen@email.com", "987654321", BloodType.Bp, Gender.Masculine);
        Employee baeta = new Engineer("Gabriel Baeta", "[RG]", 21532546879L, "[Enrollment]", "baeta@email.com", "135792468", BloodType.Op, Gender.Masculine);
        
        // Showing Data
        System.out.println(rafathayto);
        System.out.println(olsen);
        System.out.println(baeta);
	}
}