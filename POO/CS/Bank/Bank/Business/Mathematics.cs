using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bank.Business
{
    class Mathematics
    {
        public static T Highest<T>(params T[] values) where T : IComparable
        {
            T highest = values[0];
            for (int i = 1; i < values.Length; i++)
            {
                if (values[i].CompareTo(highest) > 0)
                highest = values[i];
            }
            return highest;
        }
        public static T Sum<T>(params T[] values)
        {
            dynamic sum = values[0];
            for (int i = 1; i < values.Length; i++)
            {
                sum += values[i];
            }
            return sum;
        }
        public static Int32 SquareRoot(dynamic value)
        {
            Int32 counter = 0;
            Int32 carrier = 1;
            while (value >= 0)
            {
                value -= carrier;
                carrier += 2;
                counter++;
            }
            return counter - 1;
        }
    }
}
