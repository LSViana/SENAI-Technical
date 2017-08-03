using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Arrays
{
    public partial class Release_bmiCalc : Form
    {
        private double userHeight { get; set; }
        private double userWeight { get; set; }
        private bool firstStep { get; set; }
        public Release_bmiCalc()
        {
            InitializeComponent();
            firstStep = true;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (firstStep)
            {
                userWeight = (double)numericUpDown1.Value;
                numericUpDown1.Value = 0;
                numericUpDown1.Select();
                Text = "What's your height?";
                //
                firstStep = false;
            }
            else
            {
                userHeight = (double)numericUpDown1.Value;
                //
                var bmi = userWeight / Math.Pow(userHeight, 2);
                MessageBox.Show(String.Format("Your BMI is: {0:F2}", bmi), "BMI Calculator", MessageBoxButtons.OK, MessageBoxIcon.Information);
                //
                Close();
            }
        }

        private void Release_bmiCalc_Load(object sender, EventArgs e)
        {
            numericUpDown1.Select();
        }
    }
}
