using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Arrays
{
    class PlayingCards
    {
        public PlayingCards()
        {
            // Creating the faces array to store each valid face
            String[] faces = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

            // Creating the suit arrays to store each valid suit
            String[] suit = { "Spades", "Clubs", "Hearts", "Diamonds" };

            // Creating a Random variable to generate randomic values
            Random random = new Random();

            // Selecting 10 random cards
            for (int i = 0; i < 10; i++)
            {
                // Generating aleatory values and finding the corresponding card
                String card = faces[random.Next(faces.Length)] + " of " + suit[random.Next(suit.Length)];

                // Showing the final result
                Console.WriteLine("Selected card: " + card);
            }
        }
    }
}
