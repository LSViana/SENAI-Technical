using Bank.Business;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Bank
{
    static class Program
    {
        /// <summary>
        /// Ponto de entrada principal para o aplicativo.
        /// </summary>
        [STAThread]
        static void Main()
        {
            //ExecuteForm();

            // Some tests with OOP concepts
            //WorkingWithAccounts();

            // Working with methods that return values
            //WorkingWithMathematics();
        }

        private static void WorkingWithMathematics()
        {
            // Looking for the highest value
            Console.WriteLine(Mathematics.Highest(4.5, 2d, 13.0, 12d, 0991390123d, 1313.03, 113.2));
            Console.WriteLine(Mathematics.Highest(4.5, 2d, 13.0, 12d, 0991390123d, 1313.03, 113.2));
            Console.WriteLine();
            // Making the sum of all values
            Console.WriteLine(Mathematics.Sum(3, 2, 5));
            Console.WriteLine(Mathematics.Sum(3.3, 2.2, 5.5));
            Console.WriteLine();
            // Finding the whole/integer part of a number's square root
            Console.WriteLine(Mathematics.SquareRoot(27));
            Console.WriteLine(Mathematics.SquareRoot(9));
        }

        private static void WorkingWithAccounts()
        {
            Account origin = new Account();
            //
            origin.Name = "Lucas";
            origin.Balance = 10_000.01;
            //
            origin.ShowBalance();
            origin.WithDraw(1000);
            origin.ShowBalance();
            origin.Deposit(500);
            //
            Account destiny = new Account();
            //
            destiny.Name = "Rafael";
            destiny.Balance = 3000.99;
            //
            origin.ShowBalance();
            destiny.ShowBalance();
            origin.TransferTo(destiny, 500.01);
            origin.ShowBalance();
            destiny.ShowBalance();
        }

        private static void ExecuteForm()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Form1());
        }
    }
}
