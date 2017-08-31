using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OzCorp.Business
{
    public enum BloodTypes : int
    {
        On, Op, An, Ap, Bn, Bp, ABn, ABp
    }
    public struct BloodType
    {
        public static BloodType GetBloodType(BloodTypes BloodType)
        {
            return bloodtypes[(int)BloodType];
        }
        private static List<BloodType> bloodtypes = new List<BloodType>()
        {
            new BloodType("O-"),
            new BloodType("O+"),
            new BloodType("A-"),
            new BloodType("A+"),
            new BloodType("B-"),
            new BloodType("B+"),
            new BloodType("AB-"),
            new BloodType("AB+"),
        };
        public BloodType(String Name)
        {
            this.Name = Name;
        }
        public String Name { get; set; }
        public override string ToString()
        {
            return Name;
        }
    }
}
