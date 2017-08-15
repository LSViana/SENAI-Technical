using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Loops
{
    class Program
    {
        static void Main(string[] args)
        {
            DoWhileLoop();
            //
            Console.ReadKey();
        }
        static void ForLoop()
        {
            // In 'for' structures, post or pre increment don't change the behavior
            // Using for loop to count up
            for (int i = 0; i < 5; i++)
            {
                Console.WriteLine(i);
            }
            Console.WriteLine();
            // Using for loop to count down
            for (int i = 4; i >= 0; i--)
            {
                Console.WriteLine(i);
            }
        }
        static void ForeachLoop()
        {
            // In 'foreach' structures the code become less error prone
            int[] vector = { 2, 4, 6, 8 };
            //
            foreach (var value in vector)
            {
                Console.WriteLine(value + " ");
            }
            Console.WriteLine();
            //
            // Using collection and Foreach
            List<Int32> list = new List<int>(10);
            // In C# the 'Capacity' property of List<T> could have be used to get a clearer syntax
            for (int i = 0; i < 10; i++)
            {
                list[i] = i;
            }
            foreach (var value in list)
            {
                Console.WriteLine(value);
            }
        }
        static void DoWhileLoop()
        {
            // The main difference between 'while' and 'do-while' structures is that even the condition is false at the first time at 'do-while', it executes its code and after the first test is performed. At the 'while', the test is performed before any execution of its body, so, only if the test at the current iteration returns true, its body is executed, only if the condition is false the iteration is stopped.
            int value = 10;
            Console.WriteLine("While: ");
            while (value < 10)
            {
                Console.Write(value++ + " ");
            }
            Console.WriteLine();
            value = 10;
            Console.WriteLine("Do-While: ");
            do
            {
                Console.Write(value++ + " ");
            } while (value < 10);
        }
        static void WriteSquare(int size)
        {
            // Function to print a square with the supplied value
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                {
                    // To have fun, I am writing at the Error Stream c:
                    Console.Error.Write("* ");
                }
                Console.WriteLine();
            }
        }
    }
}
