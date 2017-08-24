using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IMC
{
    public partial class Welcome : Form
    {
        public Welcome()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            ShowScreen();
        }

        void ShowScreen()
        {
            if(numericUpDown1.Value == 0 || numericUpDown2.Value == 0)
            {
                MessageBox.Show("Correct the inserted values, any of them can be lower than or equal to 0 (zero)", "Body Mass Index Calculator");
                return;
            }
            // Calculating the result and classification
            double weight = (double)numericUpDown1.Value;
            double height = (int)numericUpDown2.Value;
            double result = CalculateImc(weight, height);
            String classif = "";
            //
            if (result < 18.5)
            {
                classif = "Abaixo do Peso Normal";
            }
            else if (result < 25)
            {
                classif = "Peso Normal";
            }
            else if (result < 30)
            {
                classif = "Excesso de Peso";
            }
            else if (result < 35)
            {
                classif = "Obesidade Classe I";
            }
            else if (result < 40)
            {
                classif = "Obesidade Classe II";
            }
            else
            {
                classif = "Obesidade Classe III";
            }
            // Showing the result
            MessageBox.Show(
                String.Format("Your Body Mass Index is: {0:F2}\nClassification: {1}", result, classif), "Body Mass Index Calculator",
                MessageBoxButtons.OK, MessageBoxIcon.Information);
            // Performing a fix on the values at components
            numericUpDown1.Value = 0;
            numericUpDown2.Value = 0;
        }

        static double CalculateImc(double weight, double height)
        {
            return weight / Math.Pow((height / 100d), 2);
        }

        private void Welcome_FormClosed(object sender, FormClosedEventArgs e)
        {
            MessageBox.Show("Always come back, we appreciate your visit!", "Body Mass Index Calculator", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
    }
}
