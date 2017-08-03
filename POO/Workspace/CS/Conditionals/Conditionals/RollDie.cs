using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Conditionals
{
    public partial class RollDie : Form
    {
        private int playedValue { get; set; }
        private static Random random { get; set; } = new Random();
        public RollDie(int playedValue)
        {
            InitializeComponent();
            this.playedValue = playedValue;
        }

        private void RollDie_Load(object sender, EventArgs e)
        {
            //
            var randomValue = 1 + random.Next(6);
            labelResult.Text = randomValue.ToString();
            //
            if(randomValue == playedValue)
            {
                panelRight.BringToFront();
            }
            else
            {
                panelWrong.BringToFront();
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
