using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Arrays
{
    class SimpleArray
    {
        public SimpleArray()
        {
            // Declaring a new array
            // <type>[] <var_name> = new <type>[<capacity>];

            // First way to declare an array
            String[] countries1 = new String[4];
            countries1[0] = "Brasil";
            countries1[1] = "Rússia";
            countries1[2] = "Índia";
            countries1[3] = "China";

            // Second way to declare an array
            String[] countries2 = { "Brasil", "Rússia", "Índia", "China" };

            // Using foreach structure to print an array
            foreach(String country in countries1)
                Console.WriteLine(country + " ");
            Console.WriteLine();

            // Looking for a value inside an Array, but it is able to return only 0 (zero) or 1 (one), because it is made to work on trees, where each branch has two other
            int russiaIndex = Array.BinarySearch(countries1, "Rússia");
            Console.WriteLine("'Rússia' found at: " + russiaIndex);

            // Ordering an Array using Arrays.sort class method
            Array.Sort(countries1, 0, countries1.Length);

            // Printing an ordered array
            foreach(String country in countries1)
                Console.WriteLine(country + " ");
            Console.WriteLine();
        }
    }
}
