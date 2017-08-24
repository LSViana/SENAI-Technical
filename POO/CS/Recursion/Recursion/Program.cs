using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Recursion
{
    static class Program
    {
        /// <summary>
        /// Ponto de entrada principal para o aplicativo.
        /// </summary>
        [STAThread]
        static void Main()
        {
            //Application.EnableVisualStyles();
            //Application.SetCompatibleTextRenderingDefault(false);
            //Application.Run(new Form1());
            //
            //Console.WriteLine(Factorial(5));
            //Console.WriteLine(Power(3, 2));
            //
            ListDirectory(@"D:\cu");
        }
        public static int depth { get; set; } = 0;
        // Method to list all directories and subdirectories receiving a directory
        public static void ListDirectory(string path)
        {
            var directories = Directory.GetDirectories(path);
            var files = Directory.GetFiles(path);
            for (int i = 0; i < depth; i++)
                Console.Write("\t");
            Console.WriteLine(path);
            depth++;
            foreach (var file in files)
            {
                for (int i = 0; i < depth; i++)
                    Console.Write("\t");
                Console.WriteLine(file);
            }
            foreach (var directory in directories)
            {
                ListDirectory(directory);
                depth--;
            }
        }

        // Method to calculate the Factorial of a given number
        public static int Factorial(int n)
        {
            if (n == 0)
                return 1;
            return n * Factorial(n - 1);
        }

        // Method to calculate the power of a given base and exponent
        private static double Power(int b, int e)
        {
            if (e == 0)
                return 1;
            if (e < 0)
                return (1.0 / b) * Power(b, e + 1);
            else
                return b * Power(b, e - 1);
        }
    }
}
