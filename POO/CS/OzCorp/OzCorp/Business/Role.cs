using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OzCorp.Business
{
    public enum Roles : int
    {
        Director,
        Secretary,
        Manager,
        Engineer,
        Analyst
    }
    public struct Role
    {
        public static Role GetRole(Roles Role)
        {
            return roles[(int)Role];
        }
        private static List<Role> roles = new List<Role>()
        {
            new Role("Director", 25000, Department.GetDepartment(Departments.Administrative)),
            new Role("Secretary", 7500, Department.GetDepartment(Departments.Administrative)),
            new Role("Manager", 17500, Department.GetDepartment(Departments.Administrative)),
            new Role("Engineer", 22500, Department.GetDepartment(Departments.Technical)),
            new Role("Analyst", 15000, Department.GetDepartment(Departments.DataProcessingCenter))
        };
        public Role(String Title, decimal BaseSalary, Department Department)
        {
            this.Title = Title;
            this.BaseSalary = BaseSalary;
            this.Department = Department;
        }
        public String Title { get; set; }
        public decimal BaseSalary { get; set; }
        public Department Department { get; set; }
        public override string ToString()
        {
            return Title;
        }
    }
}
