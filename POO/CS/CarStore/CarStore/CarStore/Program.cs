using CarStore.Business;
using System;
using System.Drawing;

namespace CarStore
{
    class Program
    {
        static void Main(string[] args)
        {
            // Introducing to constructors
            WorkingWithCars();
            //
            Console.ReadKey();
        }
        static void WorkingWithCars()
        {
            // "Without constructor", i. e., using the standard compiler supplied by Java
            Car ferrari = new Car();
            ferrari.Color = Color.Blue;
            ferrari.MaxSpeed = 319;
            ferrari.Model = "Enzo";
            ferrari.ZeroToHundred = 3.73;
            Engine f50 = new Engine(Engine.TypesOfEngine.V6, 312);
            ferrari.Engine = f50;
            //
            // Using the user-defined constructor
            Engine veyron = new Engine(Engine.TypesOfEngine.V8, 450);
            Car bugatti = new Car("Veyron", 407, Color.Red, 2.81, veyron);
            //
            Console.WriteLine("Showing the results:\n\t{0}\n\t{1}", ferrari, bugatti);
        }
    }
}
