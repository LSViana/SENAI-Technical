using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OzCorp.Business
{
    public class Employee
    {
        public String Name { get; set; }
        public String RG { get; set; }
        public long CPF { get; set; }
        public String Enrollment { get; set; }
        public String Email { get; set; }
        public String Password { get; set; }
        public Role Post { get; set; }
        public BloodType Blood { get; set; }
        public Gender Gender { get; set; }
        public Employee(String Name, String RG, long CPF, String Enrollment, String Email, String Password, Role Post, BloodType Blood, Gender Gender)
        {
            this.Name = Name;
            this.RG = RG;
            this.CPF = CPF;
            this.Enrollment = Enrollment;
            this.Email = Email;
            this.Password = Password;
            this.Post = Post;
            this.Blood = Blood;
            this.Gender = Gender;
        }
        public override string ToString()
        {
            return String.Format("Employee's Data:\n{0,-17}{1}\n{2,-17}{3}\n{4,-17}{5}\n{6,-17}{7}\n{8,-17}{9}\n{10,-17}{11}\n{12,-17}{13}\n{14,-17}{15:C2}\n{16,-17}{17}\n{18,-17}{19}\n", "Name:", Name, "RG:", RG, "CPF:", CPF, "Enrollment:", Enrollment, "Email:", Email, "Password:", Password, "Post:", Post, "Base Salary:", Post.BaseSalary, "Blood:", Blood, "Gender:", Gender);
        }
    }
}
