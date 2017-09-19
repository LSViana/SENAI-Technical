using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.IO.Compression;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CompressFiles
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (ofd.ShowDialog() != DialogResult.OK)
                return;

            using (var zip = ZipFile.Open(ofd.FileName + ".zip", ZipArchiveMode.Create))
            {
                zip.CreateEntryFromFile(ofd.FileName, Path.GetFileName(ofd.FileName), CompressionLevel.Optimal);
            }
        }
    }
}
