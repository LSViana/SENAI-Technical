using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Conditionals
{
    static class Program
    {
        static Random random = new Random();
        /// <summary>
        /// Ponto de entrada principal para o aplicativo.
        /// </summary>
        [STAThread]
        static void Main()
        {
            // Open the GUI
            //Application.EnableVisualStyles();
            //Application.SetCompatibleTextRenderingDefault(false);
            //Application.Run(new Form1());

            // Code
            forms_RandomDie();
            //
            Console.ReadKey();
        }

        static void forms_RandomDie()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Welcome());
        }

        static void RandomDie()
        {
            int userValue = -1;
            while (userValue != 0)
            {
                Console.WriteLine("Seja bem-vindo ao nosso jogo de dados!");
                Console.WriteLine();
                Console.Write("\tVocê deve inserir um valor de 1 a 6 para lançar seu palpite: ");
                int randomValue = 1 + random.Next(6);
                String userInput = "";
                do
                {
                    userInput = Console.ReadLine();
                }
                while (!IsNumber(userInput) || (Double.Parse(userInput) < 1 || Double.Parse(userInput) > 6));
                userValue = Int32.Parse(userInput);
                if (userValue == 0)
                {
                    Console.WriteLine("Você escolheu sair... Volte sempre!");
                }
                if (randomValue == userValue)
                {
                    Console.WriteLine("Você acertou, parabéns!");
                }
                else
                {
                    Console.WriteLine("Errou, tente novamente!");
                }
                Console.WriteLine("O valor da rolagem do dado foi: " + randomValue + "\n\n");
            }
        }

        static void SwitchCase()
        {
            char gender = 'M';
            switch (gender)
            {
                case 'M':
                    Console.WriteLine("Masculine");
                    break;
                case 'F':
                    Console.WriteLine("Feminine");
                    break;
                default:
                    Console.WriteLine("Another");
                    break;
            }

            // Testing with a String variabel
            String technology = "sqlserver";
            switch (technology)
            {
                case "c++":
                case "c#":
                case "java":
                    Console.WriteLine("Programming Language");
                    break;
                case "sqlserver":
                case "oracle":
                case "mysql":
                case "postgre":
                    Console.WriteLine("Database Management System");
                    break;
                default:
                    Console.WriteLine("Unknown Technology.");
                    break;
            }
        }

        static void IfElse(int age)
        {
            /* until 12 y/o: child
		     * from 12 to 18: adolescent
		     * from 18 to 60: adult
		     * higher than 60: best age
		     */
            if (age < 12)
            {
                Console.WriteLine("Child!");
            }
            else if (age >= 12 && age < 18)
            {
                Console.WriteLine("Adolescent!");
            }
            else if (age >= 18 && age < 60)
            {
                Console.WriteLine("Adult!");
            }
            else if (age >= 60)
            {
                Console.WriteLine("Best Age!");
            }
        }

        static void EvenValues()
        {
            int value = 11;
            Console.WriteLine("Value: " + value);
            if (value % 2 == 0)
                Console.WriteLine("It is even.");
            else
                Console.WriteLine("It is odd.");
            //
            value = 10;
            Console.WriteLine("Value: " + value);
            if (value % 2 == 0)
                Console.WriteLine("It is even.");
            else
                Console.WriteLine("It is odd.");
        }

        static bool IsNumber(String input)
        {
            try
            {
                Double.Parse(input);
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        }
    }
}
