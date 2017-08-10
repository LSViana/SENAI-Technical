using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Arrays
{
    class MultidimensionalArray
    {
        public MultidimensionalArray()
        {
            // A multidimensional array
            String[][] jaggedUsers = new String[][]
            {
                new String[] { "Ricardo", "M", "DF", "oi" },
                new String[] { "Sandra", "F", "MG" },
                new String[] { "Beatriz", "F", "MG" }
            };

            String[,] rectUsers = new String[3, 3]
            {
                { "Ricardo", "M", "DF" },
                { "Sandra", "F", "MG" },
                { "Beatriz", "F", "MG" }
            };

            // Printing elements using [row][col] index
            Console.WriteLine(jaggedUsers[0][0] + " - " +
                jaggedUsers[0][1] + " - " +
                jaggedUsers[0][2]
                );
            // Printing elements using [row, col] index
            Console.WriteLine(rectUsers[0, 0] + " - " +
                rectUsers[0, 1] + " - " +
                rectUsers[0, 2]
                );

            // Getting the length (total number of items in the base array)
            Console.WriteLine(jaggedUsers.GetLength(0));
            // Getting the length (total number of elements in an unique row, as it is a jagged array, each row can contain different number of items
            Console.WriteLine(jaggedUsers[0].GetLength(0));
        }
    }
}
