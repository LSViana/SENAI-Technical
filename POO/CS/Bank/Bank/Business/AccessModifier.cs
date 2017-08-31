using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Bank.Business
{
    class AccessModifier
    {
        public int publicField = 3;
        private int privateField = 4;
        // In C#, the default access modifier to the class' variables is 'PRIVATE'
        int defaultPrivateField = 5;
        protected int protectedField = 6;
        // The most similar of 'Default/Package' Java access modifier, it makes the element visible only for those classes that are at the same assembly, i. e., the same solution/'.dll'/'.exe'.
        internal int internalField = 7;
    }
    class AccessInheritance : AccessModifier
    {
        public AccessInheritance()
        {
            // Non-accessible fields, but they are still inside this class, but, you can't modify from here
            // this.privateField = -4;
            // this.defaultPrivateField = -5;

            // Accessible fields, accessible 
            this.publicField = -3;
            this.protectedField = -6;
            this.internalField = -7;
        }
    }
}
