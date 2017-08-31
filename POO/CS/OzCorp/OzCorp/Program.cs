using OzCorp.Business;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OzCorp
{
    class Program
    {
        static void Main(string[] args)
        {
            InitializeEnterprise();
            //
            Console.ReadKey();
        }
        static void InitializeEnterprise()
        {
            // Creating Employee instances
            Employee rafathayto = new Analyst("Rafael Thayto", "[RG]", 47744265856, "[Enrollment]", "rafael@email.com", "123456789", BloodType.GetBloodType(BloodTypes.ABp), Gender.Feminine);
            Employee olsen = new Director("João Olsen", "[RG]", 66585632547, "[Enrollment]", "olsen@email.com", "987654321", BloodType.GetBloodType(BloodTypes.Bp), Gender.Masculine);
            Employee baeta = new Engineer("Gabriel Baeta", "[RG]", 21532546879, "[Enrollment]", "baeta@email.com", "135792468", BloodType.GetBloodType(BloodTypes.Op), Gender.Masculine);

            // Showing Data
            Console.WriteLine(rafathayto);
            Console.WriteLine(olsen);
            Console.WriteLine(baeta);
        }
    }
}
