using System;

public  class Reading {
	public static void Main() {
		Console.Write("What is your name? ");
		String userName = Console.ReadLine();
		Console.WriteLine("Welcome, {0}!", userName);
		//
		Console.ReadKey();
	}
}