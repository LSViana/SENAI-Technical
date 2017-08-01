using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Arrays
{
    class Testing_bmiCalc
    {
        public Testing_bmiCalc()
        {
            // Weight
            double weight = 70;
            // Height
            double height = 1.7;
            // Code
            double bmi = weight / Math.Pow(height, 2);
            // Diagnostic
            String diagnostic = (bmi > 20 && bmi <= 25) ? "Ideal weight" : "Not ideal weight";
            Console.WriteLine("IMC: {0:F2}\nDiagnostic: {1}\n", bmi, diagnostic);
        }
    }
}
