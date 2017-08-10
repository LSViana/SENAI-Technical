using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Forms.VisualStyles;

namespace Reading
{
    class Program
    {
        public static void Main()
        {
            CalculateCircumference();
        }

        public static void CalculateCircumference()
        {
            Console.Write("Input a valid radius value: ");
            double radius = Double.Parse(Console.ReadLine());
            //
            double circumference = 2 * Math.PI * radius;
            double area = Math.PI * Math.Pow(radius, 2);
            String resultFormatter = "The radius received: {0:F2}\nCircumference: {1:F2}\nArea: {2:F2}";
            Console.Write(resultFormatter, radius, circumference, area);
        }

        public static void UsingBitwiseOperators()
        {
            int value = -3;
            value >>= 1;
            Console.WriteLine(value + " (" + Convert.ToString(value, 2) + ")");
        }

        public static void UsingAssignmentOperators()
        {
            int someNumber = 6;
            someNumber += 3;
            Console.WriteLine(someNumber);
        }

        public static void UsingLogicalOperators()
        {
            int firstNumber = 6;
            Console.WriteLine((firstNumber >= 1) && (firstNumber <= 10));
            //
            Console.WriteLine((firstNumber >= 1) || (firstNumber <= 10));
            //
            Console.WriteLine((firstNumber > 3) + " - " + !(firstNumber > 3));
        }

        public static void UsingComparisonOperators()
        {
            int number = 34;
            Console.WriteLine(number == 34);
            //
            String stringRep = "34";
            Console.WriteLine(stringRep == "34");
            //
            String similarString = "34";
            Console.WriteLine(stringRep == similarString);
            //
            Console.WriteLine(7.GetType() == typeof(int));
        }

        public static void UsingMathOperators()
        {
            // Using 'int' literals
            int modulus = 3 % 2;
            Console.Write("Modulus: {0}\n", modulus);
            int intDivision = 3 / 2;
            Console.Write("Division: {0}\n", intDivision);
            // Using 'double' literals
            double doubleDivision = 3.0 / 2;
            Console.Write("Division: {0:F1}\n", doubleDivision);
        }

        public static void UsingForms()
        {
            Form myForm = new Form() { StartPosition = FormStartPosition.CenterScreen };
            LoadQuestion(myForm);
            myForm.ShowDialog();
            LoadResult(myForm);
            myForm.ShowDialog();
        }

        public static void LoadResult(Form f)
        {
            f.Controls.Clear();
            //
            Label result = new Label();
            f.Controls.Add(result);
            //
            result.AutoSize = false;
            result.Dock = DockStyle.Fill;
            result.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            //
            var userName = (f.Tag as string);
            result.Text = "Welcome, " + (!String.IsNullOrWhiteSpace(userName) ? userName : "Guest") + "!";
        }

        public static void LoadQuestion(Form f)
        {
            f.FormBorderStyle = FormBorderStyle.FixedSingle;
            f.Size = new Size(300, 100);
            //
            Button userButton = new Button();
            f.Controls.Add(userButton);
            //
            f.AcceptButton = userButton;
            userButton.Dock = DockStyle.Top;
            userButton.Text = "OK";
            //
            TextBox userInput = new TextBox();
            f.Controls.Add(userInput);
            f.Load += (a, b) => { userInput.Select(); };
            //
            userInput.Dock = DockStyle.Top;
            //
            Label describeUserRole = new Label();
            f.Controls.Add(describeUserRole);
            //
            describeUserRole.Dock = DockStyle.Top;
            describeUserRole.AutoSize = false;
            describeUserRole.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            describeUserRole.Text = "What is your name?";
            //
            userButton.Click += (a, b) => {
                Form parentContainer = ((a as Button).Parent as Form);
                parentContainer.Tag = userInput.Text;
                parentContainer.Close();
            };
        }

        public static void UsingConsole()
        {
            Console.Write("What is your name? ");
            String userName = Console.ReadLine();
            Console.WriteLine("Welcome, {0}!", userName);
            //
            Console.ReadKey();
        }
    }
}
