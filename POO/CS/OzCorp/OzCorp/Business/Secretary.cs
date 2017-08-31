using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OzCorp.Business
{
    public class Secretary : Employee
    {
        public Secretary(string Name, string RG, long CPF, string Enrollment, string Email, string Password, BloodType Blood, Gender Gender) : base(Name, RG, CPF, Enrollment, Email, Password, Role.GetRole(Roles.Secretary), Blood, Gender)
        {
        }
    }
}
