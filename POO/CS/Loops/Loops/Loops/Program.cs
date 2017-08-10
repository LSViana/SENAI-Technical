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
            ForLoop();
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
    }
}
