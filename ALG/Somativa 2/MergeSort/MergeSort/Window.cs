using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MergeSort
{
    public partial class Window : Form
    {
        public enum MergeSortStep { DRAWING, DIVIDING, JOINING };
        public MergeSortStep CurrentStep { get; set; } = MergeSortStep.DRAWING;
        public List<List<Int32>> Numbers { get; set; }
        public Size CircleSize { get; set; }
        private bool finished;
        public bool Finished
        {
            get { return finished = false; ; }
            set { buttonStart.Enabled = value; buttonNext.Enabled = !value; finished = value; }
        }

        public Window()
        {
            InitializeComponent();
            Finished = true;
        }

        private void buttonStart_Click(object sender, EventArgs e)
        {
            if (!String.IsNullOrWhiteSpace(textBoxNumbers.Text))
            {
                Controls.OfType<Label>().ToList().ForEach(a => a.Dispose());
                Finished = false;
                CircleSize = new Size(50, 50);
                var strNumbers = textBoxNumbers.Text.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
                Numbers = new List<List<Int32>> { { strNumbers.Select(a => Int32.Parse(a)).ToList() } };
                ShowMergeSort();
            }
        }

        private void ShowMergeSort()
        {
            if (Finished)
                return;
            switch (CurrentStep)
            {
                case MergeSortStep.DRAWING:
                    DrawCircles(Numbers.First(), CircleSize, (ClientRectangle.Width - CircleSize.Width * Numbers.First().Count()) / 2);
                    CurrentStep = MergeSortStep.DIVIDING;
                    break;
                case MergeSortStep.DIVIDING:
                    if (Numbers.Any(a => a.Count() > 1))
                        DivideNumbers(Numbers, CircleSize);
                    else
                    {
                        CurrentStep = MergeSortStep.JOINING;
                        ShowMergeSort();
                    }
                    break;
                case MergeSortStep.JOINING:
                    if (Numbers.Count() != 1)
                        JoinNumbers(Numbers, CircleSize);
                    else
                    {
                        CurrentStep = MergeSortStep.DRAWING;
                        Finished = true;
                    }
                    break;
                default:
                    break;
            }
        }

        private void DivideNumbers(List<List<int>> numbers, Size circleSize)
        {
            DivideList(numbers);
            int startPosition = InitialPosition(numbers, ref circleSize);
            for (int i = 0; i < numbers.Count(); i++)
            {
                var numbersGroup = numbers.ElementAt(i);
                DrawCircles(numbersGroup, circleSize, startPosition * (i + 1));
            }
        }


        private void DivideList(List<List<int>> numbers)
        {
            int positions = numbers.Count;
            for (int i = 0; i < positions; i++)
            {
                var originalList = numbers.First();
                numbers.Remove(originalList);
                numbers.Add(originalList.Take(originalList.Count / 2).ToList());
                numbers.Add(originalList.Skip(originalList.Count / 2).ToList());
            }
        }

        private void JoinNumbers(List<List<int>> numbers, Size circleSize)
        {
            JoinList(numbers);
            var startPosition = InitialPosition(numbers, ref circleSize);
            Controls.OfType<Label>().ToList().ForEach(a => a.Dispose());
            //
            for (int i = 0; i < numbers.Count(); i++)
            {
                var numbersGroup = numbers.ElementAt(i);
                DrawCircles(numbersGroup, circleSize, startPosition * (i + 1));
            }
        }

        private void JoinList(List<List<int>> numbers)
        {
            int positions = numbers.Count / 2;
            for (int i = 0; i < positions; i++)
            {
                var first = numbers.First();
                numbers.Remove(first);
                var second = numbers.First();
                numbers.Remove(second);
                var result = new List<int>(first.Count * 2);
                while (first.Count != 0 || second.Count != 0)
                {
                    int value = 0;
                    if (first.Count == 0)
                    {
                        value = second.First();
                        second.Remove(value);
                        result.Add(value);
                    }
                    else if (second.Count == 0)
                    {
                        value = first.First();
                        first.Remove(value);
                        result.Add(value);
                    }
                    else if (first.First() > second.First())
                    {
                        value = second.First();
                        second.Remove(value);
                        result.Add(value);
                    }
                    else
                    {
                        value = first.First();
                        first.Remove(value);
                        result.Add(value);
                    }
                }
                numbers.Add(result);
            }
        }

        private void DrawCircles(IEnumerable<int> numbers, Size circleSize, Int32 initialPosition)
        {
            for (int i = 0; i < numbers.Count(); i++)
            {
                var circle = new Label()
                {
                    Anchor = AnchorStyles.None,
                    BorderStyle = BorderStyle.FixedSingle,
                    AutoSize = false,
                    TextAlign = ContentAlignment.MiddleCenter,
                    BackColor = Extensions.RandomColor(),
                    Size = circleSize,
                    Text = numbers.ElementAt(i).ToString(),
                    Location = new Point(initialPosition + (circleSize.Width * i), (ClientRectangle.Height - circleSize.Height) / 2),
                    Parent = this
                };
            }
        }

        private void buttonNext_Click(object sender, EventArgs e)
        {
            if (Numbers == null)
            {
                buttonStart_Click(buttonStart, e);
                return;
            }
            else
            {
                ShowMergeSort();
            }
        }
        private int InitialPosition(List<List<int>> numbers, ref Size circleSize)
        {
            var startPosition = (ClientRectangle.Width - circleSize.Width * numbers.First().Count()) / (numbers.Count() + 1);
            Controls.OfType<Label>().ToList().ForEach(a => a.Dispose());
            return startPosition;
        }
    }
}
