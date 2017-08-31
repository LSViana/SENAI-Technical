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
            Employee rafathayto = new Employee("Rafael Thayto", "[RG]", 47744265856, "[Enrollment]", "rafael@email.com", "123456789", Role.GetRole(Roles.Analyst), BloodType.GetBloodType(BloodTypes.ABp), Gender.Feminine);
            Employee olsen = new Employee("João Olsen", "[RG]", 66585632547, "[Enrollment]", "olsen@email.com", "987654321", Role.GetRole(Roles.Director), BloodType.GetBloodType(BloodTypes.Bp), Gender.Masculine);
            Employee baeta = new Employee("Gabriel Baeta", "[RG]", 21532546879, "[Enrollment]", "baeta@email.com", "135792468", Role.GetRole(Roles.Engineer), BloodType.GetBloodType(BloodTypes.Op), Gender.Masculine);

            // Showing Data
            Console.WriteLine(rafathayto);
            Console.WriteLine(olsen);
            Console.WriteLine(baeta);
        }
    }
}
