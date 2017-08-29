package br.senai.sp.informatica.oop.bank;
import java.text.NumberFormat;

import javax.naming.OperationNotSupportedException;
public class Account {
	public enum AccountType { SAVING, CURRENT, SALARY };
	public static double bankBalance;
	private static int currentId = 1;
	//
	public static void showBankBalance() {
		System.out.printf("The Bank balance is %s\n", formatter.format(bankBalance));
	}
	//
	static NumberFormat formatter = NumberFormat.getCurrencyInstance();
	//
	public int accountId;
	public Customer customer;
	public double balance;
	//
	public Account(double balance, Customer customer) throws OperationNotSupportedException {
		accountId = currentId++;
		this.customer = customer;
		deposit(balance);
	}
	//
	/**
	 * This method shows the current amount of the account where it's been called.
	 */
	public void showBalance() {
		System.out.printf("The customer %s has %s\n", customer.name, formatter.format(balance));
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
		bankBalance -= value;
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
		bankBalance += value;
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
	//
	public static double getBankBalance() {
		return bankBalance;
	}
	public static void setBankBalance(double bankBalance) {
		Account.bankBalance = bankBalance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getAccountId() {
		return accountId;
	}
}
