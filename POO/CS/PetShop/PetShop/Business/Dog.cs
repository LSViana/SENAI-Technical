using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PetShop.Business
{
    class Dog
    {
        public Dog()
        {
            // Standard Constructor :)
        }
        internal String Name;
        internal float Height;
        internal String Breed;
        internal void Bark()
        {
            Console.WriteLine("Au au au!");
        }
        internal void Shower()
        {
            Console.WriteLine("The dog " + Name + " has taken a shower!");
        }
    }
}
