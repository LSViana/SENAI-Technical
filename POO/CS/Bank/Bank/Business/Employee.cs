using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bank.Business
{
    class Employee
    {
        // Properties' area
        private String name;
        public String Name
        {
            get { return name; }
            set { if (String.IsNullOrWhiteSpace(value) || value.Length < 3) throw new InvalidOperationException("The name must be at least 3 characters long."); name = value; }
        }
        private String password;
        public String Password
        {
            get { return password; }
            set { if (String.IsNullOrWhiteSpace(value) || value.Length < 8) throw new InvalidOperationException("The password must be at least 8 characters long."); password = value; }
        }
        private double salary;
        public double Salary
        {
            get { return salary; }
            set { if(value <= 0) throw new InvalidOperationException("The value for salary must be a positive value.");  salary = value; }
        }
        private bool active;
        public bool Active
        {
            get { return active; }
            set { active = value; }
        }
        // Constructors' area
        // Standard constructor to allow standard creation
        public Employee()
        {
            // Just for tests
        }
        // Auto-generated constructor
        public Employee(String Name, String Password, double Salary, bool Active)
        {
            this.Name = Name;
            this.Password = Password;
            this.Salary = Salary;
            this.Active = Active;
        }
        // Overwritten methods
        public override string ToString()
        {
            return String.Format("Name: {0} - Password: {1} - Salary: {2:C2} - Active: {3}", Name, Password, Salary, Active ? "Yes" : "No");
        }
    }
}
