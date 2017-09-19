using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

namespace PInvoke_EXE
{
    class Program
    {
        [DllImport("PInvoke_DLL.dll")]
        private static extern void ShowMessage();

        static void Main(string[] args)
        {
            PInvoke_DLL.PInvoke_DLL.ShowMessage();
            //
            Console.ReadKey();
        }
    }
}
