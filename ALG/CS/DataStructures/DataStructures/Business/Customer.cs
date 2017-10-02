using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataStructures.Business
{
    public class Customer
    {
        public String Name { get; set; }

        public Customer(String Name)
        {
            this.Name = Name;
        }

        public override string ToString()
        {
            return Name;
        }
    }
}
