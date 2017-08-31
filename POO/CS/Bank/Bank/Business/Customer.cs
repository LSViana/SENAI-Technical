using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bank.Business
{
    class Customer
    {
        public Customer()
        {
            // Standard Constructor
        }
        public Customer(String Name, String CPF)
        {
            this.Name = Name;
            this.CPF = CPF;
        }
        public string Name { get; set; }
        public string CPF { get; set; }
    }
}
