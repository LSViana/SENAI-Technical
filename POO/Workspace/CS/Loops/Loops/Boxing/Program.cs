using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Boxing
{
    /// <summary>
    /// Note: Autoboxing doesn't happen in C#, due to the lack of actual primitive types, all types here are classes or structures, and they inherit the 'primitive type behavior' by implementing some Interfaces.
    /// </summary>
    class Boxing
    {
        static void Main(String[] args)
        {
            // Autoboxing of an 'int' value
            Int32 intBox = 3;
            // Unboxing to an 'int' value
            int intUnbox = (int) intBox;

            // The same happens to any primitive variable
            Boolean boolBox = false;
            bool boolUnbox = (bool) boolBox;
        }
    }
}
