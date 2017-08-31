using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Recursive
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Factorial Calculator\n");
            //
            Console.Write("Type a value to calculate its factorial: ");
            int value = int.Parse(Console.ReadLine());
            Console.Write("\n[IT] The result of {0}! is: {1}\n", value, LoopFactorial(value));
            Console.Write("[RC] The result of {0}! is: {1}\n", value, RecursiveFactorial(value));
            //
            Console.ReadKey();
        }
        // Factorial Calculation
        static int LoopFactorial(int number)
        {
            int result = 1;
            for (; number > 0; number--)
                result *= number;
            return result;
        }
        
        static long RecursiveFactorial(int number)
        {
            if (number == 0)
                return 1;
            else
                return number * RecursiveFactorial(number - 1);
        }
    }
}
