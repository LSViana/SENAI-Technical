using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OzCorp
{
    public enum Departments : int
    {
        Administrative,
        Technical,
        DataProcessingCenter
    }
    public struct Department
    {
        public static Department GetDepartment(Departments Department)
        {
            return departments[(int)Department];
        }
        private static List<Department> departments = new List<Department>()
        {
            new Department("Administrative", "ADM"),
            new Department("Technical", "TEC"),
            new Department("Data Processing Center", "DPC")
        };
        public Department(String Name, String Initials)
        {
            this.Name = Name;
            this.Initials = Initials;
        }
        public String Name { get; set; }
        public String Initials { get; set; }
        public override string ToString()
        {
            return Name;
        }
    }
}
