using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Ordering
{
    class Program
    {
        // Main method
        static void Main(string[] args)
        {
            FillVector();
            //
            CountPerformance(BubbleSort);
            CountPerformance(InsertionSort);
            CountPerformance(SelectionSort);
            //
            Console.ReadKey();
        }

        // Ordering code
        static int[] vector = new int[50000];
        static Random random = new Random(1);
        static void FillVector()
        {
            for (int i = 0; i < vector.Length; i++)
            {
                vector[i] = random.Next(999);
            }
        }

        static void CountPerformance(Action action)
        {
            Stopwatch counter = Stopwatch.StartNew();
            action?.Invoke();
            Console.WriteLine("{0}: {1}", action.Method.Name, counter.Elapsed);
            FillVector();
        }
        /// <summary>
        /// This method organizes a sequence of 'integer' elements inside a sequence passing by each of them, from the first one until the last position minus 'i', initially this value is 0, then, it goes until the last position but one, because at each iteration, it compares the current value to the next one, so, when the 'i' is increased, it does not visit anymore the last positions, making the code a little faster, because it doesn't spend useless time.
        /// </summary>
        static void BubbleSort()
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
        static void InsertionSort()
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
                while(vector[j - 1] > vector[j])
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

        static void SelectionSort()
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
                if(min != j)
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
