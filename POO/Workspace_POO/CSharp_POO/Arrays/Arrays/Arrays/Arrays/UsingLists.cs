using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Arrays
{
    class UsingLists
    {
        public UsingLists()
        {
            // Creating a List that can hold references to String objects
            List<String> colors = new List<string>();

            // Add new elements to the List
            colors.Add("White");

            // Prints all elements of the List variable
            foreach (var item in colors)
                Console.Write(item + " ");
            Console.WriteLine();

            colors.Add("Red");

            // Prints all elements of the List variable
            foreach (var item in colors)
                Console.Write(item + " ");
            Console.WriteLine();

            // Removing an element with the specified content, or reference
            colors.Remove("White");

            // Prints all elements of the List variable
            foreach (var item in colors)
                Console.Write(item + " ");
            Console.WriteLine();

            // Verifying if a List contains a value
            Console.WriteLine(colors.Contains("White"));
            Console.WriteLine(colors.Contains("Red"));
        }
    }
}
