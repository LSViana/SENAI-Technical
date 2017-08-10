using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Arrays.Conditionals
{
    class If
    {
        public If()
        {
            EvenValues();
        }

        private static void EvenValues()
        {
            int value = 11;
            Console.WriteLine("Value: " + value);
            if(value % 2 == 0)
                Console.WriteLine("It is even");
            else
                Console.WriteLine("It is odd");
            //
            value = 10;
            Console.WriteLine("Value: " + value);
            if (value % 2 == 0)
                Console.WriteLine("It is even");
            else
                Console.WriteLine("It is odd");
        }

        private static void TestingIf()
        {
            // Using a bool value to perform a decision
            bool value = true;
            if (value)
                Console.WriteLine("It is true");

            // Testing the variable with value false in an 'if' operator
            value = false;
            if (value)
                Console.WriteLine("It is false");
        }
    }
}
