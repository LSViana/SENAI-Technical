using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarStore.Business
{
    class Engine
    {
        public Engine()
        {
            // Standard Constructor
        }
        public Engine(TypesOfEngine Type, int Potency)
        {
            this.Type = Type;
            this.Potency = Potency;
        }
        //
        public enum TypesOfEngine { V12, V8, V6, V4 };
        public TypesOfEngine Type { get; set; }
        public int Potency { get; set; }
    }
}
