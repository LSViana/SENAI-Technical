using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ExceptionHandling
{
    class Program
    {
        static void Main(string[] args)
        {
            //DivideByZero();
            //DivideByZeroHandled();
            //TestingException();
        }

        private static void TestingException()
        {
            try
            {
                ThrowingException();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }

        static void ThrowingException()
        {
            if (1 == 1)
                throw new ExecutionEngineException("Errooooooou!");
            else
                throw new FieldAccessException("Você foi burro, seu porra!");
        }

        private static void DivideByZeroHandled()
        {
            bool validNumber = false;
            do
            {
                try
                {
                    Console.Write("Type a numerator: ");
                    int numerator = int.Parse(Console.ReadLine());
                    Console.Write("Type a denominator: ");
                    int denominator = int.Parse(Console.ReadLine());
                    validNumber = true;
                    Console.Write("\n\tFinal result: {0}\n", numerator / denominator);
                }
                catch (ContextMarshalException cm)
                {
                    Console.WriteLine("Use only numbers!");
                    validNumber = false;
                }
                catch (ArithmeticException a)
                {
                    Console.WriteLine("You can't make this operation, follow the mathematical rules.");
                    validNumber = false;
                }
                finally
                {
                    Console.WriteLine("Resources are now free.");
                    //input.nextLine(); // When the 'InputMismatchException' is thrown, the keyboard tries to read the
                    // 'nextInt()', because the method is called again, but, there is another thing,
                    // not valid as a number, inside the System.out stream, then, it reads forever.
                    // It doesn't happen with the 'ArithmeticException' because the value read is
                    // still a number, then, it can be consumed at the next call to 'nextInt()'
                    // method.
                }
            } while (!validNumber);
        }

        private static void DivideByZero()
        {
            Console.Write("Type a numerator: ");
            int numerator = int.Parse(Console.ReadLine());
            Console.Write("Type a denominator: ");
            int denominator = int.Parse(Console.ReadLine());
            Console.Write("\n\tFinal result: {0}\n", numerator / denominator);
        }
    }
}
