package Arrays;

import java.util.Random;

public class PlayingCards {
	public static void main(String[] args) {
		// Creating the faces array to store each valid face
		String[] faces = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

		// Creating the suit arrays to store each valid suit
		String[] suit = { "Spades", "Clubs", "Hearts", "Diamonds" };

		// Creating a Random variable to generate randomic values
		Random random = new Random();

		// Selecting 10 random cards
		for (int i = 0; i < 10; i++) {
			// Generating aleatory values and finding the correspoding card
			String card = faces[random.nextInt(faces.length)] + " of " + suit[random.nextInt(suit.length)];

			// Showing the final result
			System.out.println("Selected card: " + card);
		}
	}
}