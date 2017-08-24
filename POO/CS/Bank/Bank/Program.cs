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

            // Working with some access modifiers
            //WorkingWithAccessModifiers();
            //WorkingWithEmployees();
            WorkingWithProducts();
        }

        public static void WorkingWithProducts()
        {
            // Declaring the Shopping List
            List<Product> products = new List<Product>();
            // Adding some products and validating their data

            Product orange = new Product();
            orange.BarCode = 1_111_111_111L;
            orange.Category = "Juicy Fruits";
            orange.Perishable = true;
            orange.Price = 2.2;
            orange.Description  = "A tropical fruit";
            products.Add(orange);

            Product grape = new Product();
            grape.BarCode = 1_111_111_112L;
            grape.Category = "Purple and Juicy";
            grape.Perishable = true;
            grape.Price = 1.8;
            products.Add(grape);

            // Exhibiting the total price of the list
            double totalPrice = 0;
            foreach (Product product in products)
            {
                Console.WriteLine(product + "\n");
                totalPrice += product.Price;
            }
            Console.WriteLine("\tTotal price: {0:C2}\n", totalPrice);
        }

        public static void WorkingWithEmployees()
        {
            Employee employee = new Employee();
            // Manipulating the 'name' property
            employee.Name = "Lucas";
            Console.WriteLine(employee);

            // Manipulating the 'active' property
            employee.Active = true;
            Console.WriteLine(employee);
        }

        public static void WorkingWithAccessModifiers()
        {
            AccessModifier am = new AccessModifier();
            // Public field is accessible
            am.publicField = 0;
            am.internalField = 0;

            // Protected field is not accessible, due the miss of inheritance of the current class against the AccessModifier class
            // am.protectedField = 0;

            // Private field is not accessible because it is outside the class
            // am.privateField = 0;

            // The standard PRIVATE field is not accessible due the rules of this keyword
            // am.defaultPrivateField = 0;
        }

        public  static void WorkingWithMathematics()
        {
            // Looking for the highest value
            Console.WriteLine(Mathematics.Highest(4.5, 2d, 13.0, 12d, 0991390123d, 1313.03, 113.2));
            Console.WriteLine(Mathematics.Highest(1, 2, 3, 4, 5, 6, 7, 13));
            Console.WriteLine();
            // Making the sum of all values
            Console.WriteLine(Mathematics.Sum(3, 2, 5));
            Console.WriteLine(Mathematics.Sum(3.3, 2.2, 5.5));
            Console.WriteLine();
            // Finding the whole/integer part of a number's square root
            Console.WriteLine(Mathematics.SquareRoot(27));
            Console.WriteLine(Mathematics.SquareRoot(9));
            Console.WriteLine();
            // Finding the average between a group of values
            Console.WriteLine(Mathematics.Average(30, 10));
            Console.WriteLine(Mathematics.Average(2.3, 4.5, 9.0, 3.2, 0.1));
            Console.WriteLine();
        }

        public static void WorkingWithAccounts()
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
