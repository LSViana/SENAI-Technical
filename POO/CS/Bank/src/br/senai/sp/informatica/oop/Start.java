/**
 * @author Lucas Viana
 */
package br.senai.sp.informatica.oop;

import javax.naming.OperationNotSupportedException;

import br.senai.sp.informatica.oop.bank.Account;
import br.senai.sp.informatica.oop.mathematics.Mathematics;

public class Start {
	public static void main(String[] args) throws OperationNotSupportedException {
	    // Some tests with OOP concepts
		//workingWithAccounts();
		
		// Working with methods that return values
		//workingWithMatematics();
	}
	
	private static void workingWithMatematics() {
		// Looking for the highest value
		System.out.println(Mathematics.highest(4.5, 2d, 13.0, 12d, 0991390123d, 1313.03, 113.2));
		System.out.println(Mathematics.highest(1,2,3,4,5,6,7, 13));
		System.out.println();
		// Making the sum of all values
		System.out.println(Mathematics.sum(1, 2, 3));
		System.out.println(Mathematics.sum(1.2, 2.4, 3.6));
		System.out.println();
		// Finding the whole/integer part of a number's square root
		System.out.println(Mathematics.squareRoot(27));
		System.out.println(Mathematics.squareRoot(9));
	}
	
	private static void workingWithAccounts() throws OperationNotSupportedException {
		Account origin = new Account();
		//
		origin.name = "Lucas";
		origin.balance = 10_000.01;
		//
		origin.showBalance();
		origin.withDraw(1000);
		origin.showBalance();
		origin.deposit(500);
		//
		Account destiny = new Account();
		//
		destiny.name = "Rafael";
		destiny.balance = 3000.99;
		//
		origin.showBalance();
		destiny.showBalance();
		origin.transferTo(destiny, 500.01);
		origin.showBalance();
		destiny.showBalance();
	}
}
