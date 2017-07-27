using System;
using System.Windows.Forms;

public  class Reading {
	public static void Main() {
		Form a = new Form();
		a.ShowDialog();
	}

	public static void UsingConsole() {
		Console.Write("What is your name? ");
		String userName = Console.ReadLine();
		Console.WriteLine("Welcome, {0}!", userName);
		//
		Console.ReadKey();
	}
}