using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MergeSort
{
    public static class Extensions
    {
        private static Random Random { get; set; } = new Random();
        public static Color RandomColor()
        {
            return Color.FromArgb(128 + Random.Next(128), 128 + Random.Next(128), 128 + Random.Next(128));
        }
    }
}
