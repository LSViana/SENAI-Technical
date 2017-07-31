using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LinearSearch
{
    class Program
    {
        static Random random = new Random();
        static int[] values = new int[10] { 10, 5, 4, 3, 9, 8, 7, 1, 2, 6 };
        static void Main(string[] args)
        {
            InsertionSort();
            // Ending
            Console.ReadKey();
        }

        private static void InsertionSort()
        {
            var j = 0;
            for (int i = 1; i < values.GetLength(0); i++)
            {
                j = i;
                do
                {
                    if (values[j - 1] > values[j])
                    {
                        values[j - 1] += values[j];
                        values[j] = values[j - 1] - values[j];
                        values[j - 1] -= values[j];
                    }
                }
                while (--j != 0);
                continue;
            }
            //
            PrintValues();
        }

        private static void SelectionSort()
        {
            var min = int.MinValue;
            for (int i = 0; i < values.GetLength(0); i++)
            {
                min = i;
                for (int j = i + 1; j < values.GetLength(0); j++)
                {
                    if (values[j] < values[min])
                    {
                        min = j;
                    }
                }
                if (min != i)
                {
                    values[i] += values[min];
                    values[min] = values[i] - values[min];
                    values[i] -= values[min];
                }
            }
            //
            PrintValues();
        }

        private static void BubbleSort()
        {
            for (int i = 0; i < values.GetLength(0); i++)
            {
                for (int j = 0; j < i; j++)
                {
                    if (values[i] < values[j])
                    {
                        values[i] += values[j];
                        values[j] = values[i] - values[j];
                        values[i] -= values[j];
                    }
                }
            }
            //
            PrintValues();
        }

        private static void PrintValues()
        {
            for (int i = 0; i < values.GetLength(0); i++)
            {
                Console.Write("<" + values[i] + "> ");
            }
        }

        private static void LinearSearch()
        {
            // Variables
            float[] heights = new float[10];
            int iterator = 0;
            float highestHeight = int.MinValue;
            // Code
            for (iterator = 0; iterator < heights.GetLength(0); iterator++)
            {
                do
                {
                    Console.Write("Input the height {0} (in cm): ", iterator + 1);
                    heights[iterator] = float.Parse(Console.ReadLine());
                    if (heights[iterator] > highestHeight)
                    {
                        highestHeight = heights[iterator]; ;
                    }
                }
                while (heights[iterator] <= 0);
            }
            Console.WriteLine();
            Console.Write("The highest height is: {0:F2}cm\n", highestHeight);
        }

        private static void TestingLinearSearch()
        {
            // Variables
            float[] heights = new float[10];
            int iterator = 0, highestIndex = heights.GetLength(0);
            float highestHeight = int.MinValue;
            // Code
            for (iterator = 0; iterator < heights.GetLength(0); iterator++)
            {
                do
                {
                    Console.Write("Input the height {0} (in cm): ", iterator + 1);
                    heights[iterator] = float.Parse(Console.ReadLine());
                    if (heights[iterator] > highestHeight)
                    {
                        highestHeight = heights[iterator];
                        highestIndex = iterator;
                    }
                }
                while (heights[iterator] <= 0);
            }
            Console.WriteLine();
            Console.Write("The highest height is: {0:F2}cm\n" +
                "The highest index is: {1}\n", highestHeight, highestIndex + 1);
        }
    }
}
