using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Intro
{
    class Program
    {
        // First 'Main' method autoger
        static void Main(string[] args)
        {
            String ideName = "Visual Studio 2017 Enterprise";
            Console.Write("Hello, {0}!\n", ideName);
            //
            Console.Write("Conte-nos o seu nome: ");
            String userName = Console.ReadLine();
            Console.Write("Seja bem-vindo, {0}!\n", userName);
            //
            Console.ReadKey();
        }
    }
}
