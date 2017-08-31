using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BinarySearch
{
    class Program
    {
        //static int[] vector = { 1, 3, 6, 12, 18, 19, 21, 27, 27, 29, 35 };
        static int[] vector = new int[1000];
        static void Main(string[] args)
        {
            //int[] vector = { 1, 3, 6, 12, 9, 46, 7, 31, 3, 1, 0, -7, 14 };
            //int[] vector = new int[10000];
            FillVector(vector);
            InsertionSort(vector);
            //int iteration;
            //int indexOf = BinarySearch(vector, 7, out iteration);
            Count(class_BinarySearch);
            Count(my_BinarySearch);
            //
            //Console.WriteLine("Found at: {0}\nLoops: {1}\n", indexOf, iteration);
            //
            Console.ReadKey();
        }
        // Performance Counter
        static void Count(Func<int[], int, int> func)
        {
            Stopwatch sw = Stopwatch.StartNew();
            int value = func?.Invoke(vector, 7) ?? 0;
            Console.WriteLine("Time: {0}\nIndex: {1}\n", sw.ElapsedMilliseconds, value);
        }
        // Binary Search Code
        static int class_BinarySearch(int[] vector, int value/*, out int iteration*/)
        {
            int left = 0, right = vector.Length, mid = (left + right) / 2;
            //iteration = 0;
            while (left <= right)
            {
                //iteration++;
                if (vector[mid] == value)
                    return mid;
                else if (vector[mid] > value)
                {
                    right = mid - 1;
                }
                else
                {
                    left = mid + 1;
                }
                mid = (left + right) / 2;
            }
            return mid;
        }
        static int my_BinarySearch(int[] vector, int value/*, out int iteration*/)
        {
            int left = 0, right = vector.Length - 1, mid = (left + right) / 2;
            //iteration = 0;
            while (vector[mid] != value)
            {
                //iteration++;
                if (mid == 0 || left == right - 1)
                    return -1;
                if (vector[mid] > value)
                {
                    right = mid;
                }
                else
                {
                    left = mid;
                }
                mid = (left + right) / 2;
            }
            return mid;
        }
        // Linear Search Code
        static int LinearSearch(int[] vector, int value)
        {
            // Search through the whole vector
            for (int i = 0; i < vector.Length; i++)
            {
                // If it finds, returns the current index
                if (vector[i] == value)
                {
                    Console.WriteLine("Found! [{0}]", i);
                    return i;
                }
            }
            Console.WriteLine("Not found! [{0}]", vector.Length);
            // Otherwise, it returns a signal of 'not found'
            return -1;
        }
        ///
        static Random random = new Random(0);
        ///
        // Ordering Code
        static void FillVector(int[] vector)
        {
            for (int i = 0; i < vector.Length; i++)
            {
                vector[i] = random.Next(999);
            }
        }
        /// <summary>
        /// This method organizes a sequence of 'integer' elements inside a sequence passing by each of them, from the first one until the last position minus 'i', initially this value is 0, then, it goes until the last position but one, because at each iteration, it compares the current value to the next one, so, when the 'i' is increased, it does not visit anymore the last positions, making the code a little faster, because it doesn't spend useless time.
        /// </summary>
        static void BubbleSort(int[] vector)
        {
            Console.WriteLine("\tBubble Sort");
            long changes = 0, iterations = 0;
            //
            for (int i = 0; i < vector.Length; i++)
            {
                iterations++;
                for (int j = 0; j < vector.Length - i - 1; j++)
                {
                    iterations++;
                    if (vector[j] > vector[j + 1])
                    {
                        changes++;
                        vector[j + 1] += vector[j];
                        vector[j] = vector[j + 1] - vector[j];
                        vector[j + 1] -= vector[j];
                    }
                }
            }
            //
            Console.Write("Changes: {0}\nIterations: {1}\n", changes, iterations);
        }
        // It passes from the second position until the first one, changing all values that are higher than the current position, id est, if the previous value is higher than the current, this value is changed. And, as it begins at the second position and always verify the previous one, it ensures, before going to the next position, that all the previous ones are ordered.
        static void InsertionSort(int[] vector)
        {
            Console.WriteLine("\tInsertion Sort");
            long changes = 0, iterations = 0;
            //
            int i = 0, j = 0;
            for (i = 1; i < vector.Length; i++)
            {
                iterations++;
                // 'j' receives the current value of 'i'
                j = i;
                while (vector[j - 1] > vector[j])
                {
                    iterations++;
                    // Do the change
                    changes++;
                    vector[j] += vector[j - 1];
                    vector[j - 1] = vector[j] - vector[j - 1];
                    vector[j] -= vector[j - 1];
                    // The variable 'j' goes to the previous one
                    j--;
                    if (j == 0)
                        break;
                }
            }
            //
            Console.Write("Changes: {0}\nIterations: {1}\n", changes, iterations);
        }

        static void SelectionSort(int[] vector)
        {
            Console.WriteLine("\tSelection Sort");
            long changes = 0, iterations = 0;
            //
            int i = 0, j = 0, min = 0;
            for (j = 0; j < vector.Length - 1; j++)
            {
                iterations++;
                for (i = j + 1; i < vector.Length - 1; i++)
                {
                    iterations++;
                    // If the current element is lower than the marked
                    if (vector[i] < vector[min])
                        min = i;
                }
                if (min != j)
                {
                    changes++;
                    vector[j] += vector[min];
                    vector[min] = vector[j] - vector[min];
                    vector[j] -= vector[min];
                }
            }
            //
            Console.Write("Changes: {0}\nIterations: {1}\n", changes, iterations);
        }
    }
}