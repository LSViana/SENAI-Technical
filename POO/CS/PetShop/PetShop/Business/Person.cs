using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PetShop.Business
{
    class Person
    {
        public String Name;
        public Int32 Age;
        public void Birthday()
        {
            Age++;
            Console.WriteLine("You are getting one year old! Congratulations for your {0} years of life!", Age);
        }
        public void AgeDifference(Person p)
        {
            Console.WriteLine("The difference of age between {0} and {1} is: {2}.", Name, p.Name, Math.Abs(Age - p.Age));
        }
        public void ShowAge()
        {
            Console.WriteLine("Current age: {0}.", Age);
        }
    }
}
