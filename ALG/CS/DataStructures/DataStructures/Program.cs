using DataStructures.Business;
using DataStructures.Vector;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataStructures
{
    class Program
    {
        static void Main(string[] args)
        {
            TestVector();
            //
            Console.ReadKey();
        }

        private static void TestVector()
        {
            Vector<Customer> Customers = new Vector<Customer>(2);
            Customer thayto = new Customer("Thayto");
            Customer lucas = new Customer("Lucas");
            Customer felipe = new Customer("Felipe");
            Customers.AddRange(new Customer[] { thayto, felipe });
            Customers.PrintData();
            Customers.Add(lucas, 2);
            Customers.PrintData();
        }
    }
}
