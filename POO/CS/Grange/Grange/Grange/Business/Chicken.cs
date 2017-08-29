using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Grange.Business
{
    class Chicken
    {
        // Class Model
        private static int eggsAtGrange;
        public static int EggsAtGrange
        {
            get { return eggsAtGrange; }
            set { eggsAtGrange = value; }
        }

        // Object Model
        private int eggs;
        public int Eggs
        {
            get { return eggs; }
            set { eggs = value; }
        }
        public Chicken ThrowEgg()
        {
            Eggs++;
            EggsAtGrange++;
            return this;
        }
    }
}
