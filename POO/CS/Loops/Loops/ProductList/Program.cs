using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Threading.Thread;

namespace ProductList
{
    /// <summary>
    /// This application receives a list of products and then prints at the screen all of them ordered by the price
    /// </summary>
    class ProductList
    {
        static List<String> products = new List<String>();
        static void Main(string[] args)
        {
            ReadProducts();
            PrintProducts();
            //
            Console.ReadKey();
        }
        static void ReadProducts()
        {
            Console.Error.WriteLine("Bem-vindo ao nosso Gerenciador de Lista de Produtos: [Digite FIM para sair]");
            Sleep(10);
            //
            String currentProduct = ""; // Empty String
            do
            {
                Console.Write("\tDigite o nome do produto: ");
                currentProduct = Console.ReadLine();
                products.Add(currentProduct);
            } while (!currentProduct.ToUpper().Equals("FIM"));
            products.RemoveAt(products.Count - 1);
        }
        static void PrintProducts()
        {
            Console.WriteLine();
            Console.Error.WriteLine("Lista de Produtos: ");
            Sleep(10);
            for (int i = 0; i < products.Count; i++)
            {
                Console.Write("Produto {0}: {1}\n", i + 1, products[i]);
            }
            Console.WriteLine("\tFim da lista. Agradecemos a preferência!");
        }
    }
}
