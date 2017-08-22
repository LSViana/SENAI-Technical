package br.senai.sp.informatica.oop.bank;
import java.text.NumberFormat;

import javax.naming.OperationNotSupportedException;
public class Account {
	static NumberFormat formatter = NumberFormat.getCurrencyInstance();
	//
	public String name;
	public double balance;
	//
	/**
	 * This method shows the current amount of the account where it's been called.
	 */
	public void showBalance() {
		System.out.printf("The customer %s has %s\n", name, formatter.format(balance));
	}
	/**
	 * This method subtracts the supplied value of the current amount of the account where it's been called.
	 * @param value The value to be subtracted
	 * @throws OperationNotSupportedException If the value is negative, this Exception is thrown
	 */
	public void withDraw(double value) throws OperationNotSupportedException {
		if(value <= 0)
			throw new OperationNotSupportedException("The 'value' can't be negative.");
		balance -= value;
	}
	/**
	 * This method adds the supplied value of the current amount of the account where it's been called.
	 * @param value The value to be added
	 * @throws OperationNotSupportedException If the value is negative, this Exception is thrown
	 */
	public void deposit(double value) throws OperationNotSupportedException {
		if(value <= 0)
			throw new OperationNotSupportedException("The 'value' can't be negative.");		
		balance += value;
	}
	/**
	 * This method transfer the supplied value from the amount of the account where it's been called to the account object supplied, i.e., the value is subtracted from the amount of the current account and added to the balance of the supplied account parameter
	 * @param destiny The account object to receive the value
	 * @param value The value to be transferred
	 * @throws OperationNotSupportedException If the value is negative, this Exception is thrown
	 */
	public void transferTo(Account destiny, double value) throws OperationNotSupportedException {
		withDraw(value);
		destiny.deposit(value);
	}
}
