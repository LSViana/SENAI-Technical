using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bank.Business
{
    class Account
    {
        public enum AccountType { Saving, Current , Salary };
        private static int CurrentId = 1;
        public static double BankBalance { get; set; }
        public static void ShowBankBalance()
        {
            Console.WriteLine("The Bank balance is {0:C2}", BankBalance);
        }
        //
        public int AccountId { get; }
        public double Balance { get; set; }
        public Customer Customer { get; set; }
        public Account(double Balance, Customer Customer)
        {
            AccountId = CurrentId++;
            this.Customer = Customer;
            Deposit(Balance);
        }
        //
        /// <summary>
        /// This method shows the current amount of the account where it's been called
        /// </summary>
        public void ShowBalance()
        {
            Console.WriteLine("The customer {0} has {1:C}", Customer.Name, Balance);
        }
        /// <summary>
        /// This method subtracts the supplied value of the current amount of the account where it's been called
        /// </summary>
        /// <param name="value">The value to be subtracted</param>
        public void WithDraw(double value)
        {
            if (value <= 0)
                throw new InvalidOperationException("The 'value' can't be negative.");
            Balance -= value;
            BankBalance -= value;
        }
        /// <summary>
        /// This method adds the supplied value of the current amount of the account where it's been called
        /// </summary>
        /// <param name="value">The value to be added</param>
        public void Deposit(double value)
        {
            if (value <= 0)
                throw new InvalidOperationException("The 'value' can't be negative.");
            Balance += value;
            BankBalance += value;
        }
        /// <summary>
        /// This method transfer the supplied value from the amount of the account where it's been called to the account object supplied, i.e., the value is subtracted from the amount of the current account and added to the balance of the supplied account parameter
        /// </summary>
        /// <param name="destiny">The account object to receive the value</param>
        /// <param name="value">The value to be transferred</param>
        public void TransferTo(Account destiny, double value)
        {
            WithDraw(value);
            destiny.Deposit(value);
        }
    }
}
