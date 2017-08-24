using PetShop.Business;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Reflection;

// Form content
//Application.EnableVisualStyles();
//Application.SetCompatibleTextRenderingDefault(false);
//Application.Run(new Form1());
namespace PetShop
{
    static class Start
    {
        /// <summary>
        /// Ponto de entrada principal para o aplicativo.
        /// </summary>
        [STAThread]
        static void Main()
        {
            // Brief introduction to declaring variables and using methods
            //WorkingWithDogs();

            // Creating some examples as an exercise
            //WorkingWithPeople();
            
            // Tests to multiply by minus 1 using binary valuess
            //int a = 3;
            //Console.WriteLine(a + ":" + InverseSignal(a));
        }

        private static Int32 InverseSignal(Int32 value)
        {
            return (Int32)(0b100000000000000000000000000000000 - value);
        }

        private static void WorkingWithPeople()
        {
            // #1 First Object
            Person franca = new Person();
            franca.Name = "França";
            franca.Age = 17;
            franca.ShowAge();
            franca.Birthday();
            // #2 Second Object
            Person juscelino = new Person();
            juscelino.Name = "Juscelino";
            juscelino.Age = 35;
            juscelino.ShowAge();
            // Showing the difference of age between the two objects
            franca.AgeDifference(juscelino);
        }

        private static void WorkingWithDogs()
        {
            // Defining a new 'Dog' variable
            Dog franca = new Dog();
            // Defining new values to instance variables [or properties, in C# context]
            franca.Name = "França";
            franca.Breed = "Rottweiler";
            franca.Height = 48;
            // Invoking acessible methods on that instance
            franca.Bark();

            // Defining another new 'Dog' variable
            Dog erika = new Dog();
            erika.Name = "Érika";
            erika.Breed = "Boxer";
            erika.Height = 32;

            // Invoking the same method from two different objects of the same type, and they will have distinct outputs, possible, because there are two different objects at memory
            franca.Shower();
            erika.Shower();

            // Using Reflection to execute private methods under an instance
            //var info = franca.GetType().GetMethod("Bark", BindingFlags.NonPublic | BindingFlags.Instance);
        }
    }
}