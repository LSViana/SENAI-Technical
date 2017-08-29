using Grange.Business;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Grange
{
    class Program
    {
        static void Main(string[] args)
        {
            // Tests with 'this' keyword
            WorkingWithChickens();
            //
            Console.ReadKey();
        }

        static void WorkingWithChickens()
        {
            // Manipulating some 'static' values at classes
            Chicken donega = new Chicken();
            Chicken thayto = new Chicken();
            Console.WriteLine("Thayto's eggs: {0} | Donegá's eggs: {1} | Grange's eggs: {2}", thayto.Eggs, donega.Eggs, Chicken.EggsAtGrange);
            Console.WriteLine("Thayto is throwing an egg...", thayto.ThrowEgg());
            Console.WriteLine("Thayto is throwing an egg...", thayto.ThrowEgg());
            Console.WriteLine("Thayto is throwing an egg...", thayto.ThrowEgg());
            Console.WriteLine("Thayto's eggs: {0} | Donegá's eggs: {1} | Grange's eggs: {2}", thayto.Eggs, donega.Eggs, Chicken.EggsAtGrange);
            Console.WriteLine("Donegá is throwing an egg...", donega.ThrowEgg());
            Console.WriteLine("Donegá is throwing an egg...", donega.ThrowEgg());
            Console.WriteLine("Thayto's eggs: {0} | Donegá's eggs: {1} | Grange's eggs: {2}", thayto.Eggs, donega.Eggs, Chicken.EggsAtGrange);
            // Exploring the return values of methods
            thayto.ThrowEgg().ThrowEgg().ThrowEgg();
        }
    }
}
