using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MergeSort
{
    public partial class Form1 : Form
    {
        public static Random Random { get; set; } = new Random();
        public List<Int32> Numbers { get; set; } = new List<int>();
        public Point[] FinalPositions { get; set; }
        public Color ThemeColor { get; set; } = Color.Orange;
        public List<FlowLayoutPanel> Containers { get; set; } = new List<FlowLayoutPanel>();
        public Size PanelSize { get; set; }
        public int PanelMargin { get; set; }
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var Str_Numbers = textBoxNumbers.Text.Split(' ');
            foreach (var item in Controls.OfType<FlowLayoutPanel>().ToArray())
            {
                item.Dispose();
                Controls.Remove(item);
            }
            Numbers.Clear();
            Containers.Clear();
            var baseSize = ClientRectangle.Width / (Str_Numbers.Length + 2);
            PanelSize = new Size(baseSize, baseSize);
            PanelMargin = 10;
            var Container = new FlowLayoutPanel()
            {
                AutoSize = true,
                Location = new Point(baseSize, ClientRectangle.Height / 2),
                BackColor = Color.Red,
                BorderStyle = BorderStyle.FixedSingle,
                Anchor = AnchorStyles.None
            };
            Containers.Add(Container);
            for (int i = 0; i < Str_Numbers.Length; i++)
            {
                Numbers.Add(int.Parse(Str_Numbers[i]));
                var lb = new Label()
                {
                    AutoSize = false,
                    Text = Numbers[i].ToString(),
                    Size = PanelSize,
                    BackColor = ThemeColor,
                    Padding = new Padding(PanelMargin),
                    TextAlign = ContentAlignment.MiddleCenter,
                    Font = new Font("Segoe UI", 18),
                    Anchor = AnchorStyles.None,
                    Tag = Numbers[i]
                };
                SetCircular(lb);
                Container.Controls.Add(lb);
            }
            Controls.AddRange(Containers.ToArray());
        }

        private void SetCircular(Label lb)
        {
            var gPath = new GraphicsPath();
            gPath.AddEllipse(lb.ClientRectangle);
            lb.Region = new Region(gPath);
        }

        public enum States { SEPARATING, ORDERING, JOINING };

        public States CurrentState { get; set; } = States.SEPARATING;

        private void button2_Click(object sender, EventArgs e)
        {
            var _containers = Containers.ToList();
            switch (CurrentState)
            {
                case States.SEPARATING:
                    foreach (var container in _containers)
                    {
                        var _controls = container.Controls.OfType<Control>().ToArray();
                        if (_controls.Count() <= 2)
                        {
                            CurrentState = States.ORDERING;
                            button2_Click(sender, e);
                            return;
                        }
                        var firstFLP = new FlowLayoutPanel()
                        {
                            AutoSize = true,
                            Location = container.Location,
                            BackColor = GetRandomColor()
                        };
                        var secondFLP = new FlowLayoutPanel()
                        {
                            AutoSize = true,
                            Location = container.Location,
                            BackColor = GetRandomColor()
                        };
                        var secondLoc = secondFLP.Location;
                        secondLoc.Offset(container.Controls.Count / 2 * (container.Controls[0].Width + PanelMargin), 0);
                        secondFLP.Location = secondLoc;
                        var middle = container.Controls.Count / 2;
                        for (int i = 0; i < _controls.Length; i++)
                        {
                            if (i >= middle)
                                secondFLP.Controls.Add(_controls[i]);
                            else
                                firstFLP.Controls.Add(_controls[i]);
                        }
                        var newPanels = new FlowLayoutPanel[] { firstFLP, secondFLP };
                        Controls.AddRange(newPanels);
                        Controls.Remove(container);
                        //
                        Containers.Remove(container);
                        Containers.AddRange(newPanels);
                    }
                    break;
                case States.ORDERING:
                    foreach (var container in _containers)
                    {
                        MoveCircles(container);
                    }
                    CurrentState = States.JOINING;
                    break;
                case States.JOINING:
                    for (int i = 0; i < Containers.Count; i += 2)
                    {
                        try
                        {
                            var newContainer = new FlowLayoutPanel()
                            {
                                AutoSize = true,
                                BackColor = GetRandomColor(),
                                Location = Containers[i].Location
                            };
                            var _first = new Stack<Control>(Containers[i].Controls.OfType<Control>().Reverse());
                            var _second = new Stack<Control>(Containers[i + 1].Controls.OfType<Control>().Reverse());
                            //foreach (var first in _first)
                            while (_first.Count != 0 || _second.Count != 0)
                            {
                                if (_first.Count == 0)
                                {
                                    newContainer.Controls.Add(_second.Pop());
                                    continue;
                                }
                                else if (_second.Count == 0)
                                {
                                    newContainer.Controls.Add(_first.Pop());
                                    continue;
                                }
                                else if ((Int32)_first.Peek().Tag > (Int32)_second.Peek().Tag)
                                {
                                    newContainer.Controls.Add(_second.Pop());
                                }
                                else
                                {
                                    newContainer.Controls.Add(_first.Pop());
                                }
                            }
                            Controls.Add(newContainer);
                            Controls.Remove(Containers[i]);
                            Controls.Remove(Containers[i + 1]);
                            //
                            _containers.Add(newContainer);
                            _containers.Remove(Containers[i]);
                            _containers.Remove(Containers[i + 1]);
                        }
                        catch (Exception exc)
                        {
                            i = 0;
                        }
                    }
                    CurrentState = States.SEPARATING;
                    break;
            }
        }

        private void MoveCircles(FlowLayoutPanel container)
        {
            try
            {
                var first = container.Controls[0] as Label;
                var second = container.Controls[1] as Label;
                if ((Int32)first.Tag > (Int32)second.Tag)
                {
                    var _tempText = first.Text;
                    var _tempTag = first.Tag;
                    //
                    first.Tag = second.Tag;
                    first.Text = second.Text;
                    second.Tag = _tempTag;
                    second.Text = _tempText;
                }
            }
            catch (Exception exc)
            {
                //
            }
        }

        private static Color GetRandomColor()
        {
            return Color.FromArgb(Random.Next(256), Random.Next(256), Random.Next(256));
        }
    }
}
