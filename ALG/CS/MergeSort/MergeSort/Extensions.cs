using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MergeSort
{
    public static class Extensions
    {
        public static Point GetCenter(this Rectangle r)
        {
            return new Point(r.Width / 2, r.Height / 2);
        }
        public static Double PercentageToAngle(this Int32 i)
        {
            return Math.PI * 2 * i / 100d;
        }
    }
}
