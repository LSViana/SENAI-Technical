package execution;

import java.io.IOException;
import java.io.PrintStream;

import business.Dog;
import business.Person;

public class Start {
	public static void main(String[] args) throws Exception {
		// Brief introduction to declaring variables and using methods
		// WorkingWithDogs();

		// Creating some examples as an exercise
		//WorkingWithPeople();
	}

	private static void WorkingWithPeople() {
		// #1 First Object
		Person franca = new Person();
		franca.name = "França";
		franca.age = 17;
		franca.showAge();
		franca.birthday();
		// #2 Second object
		Person juscelino = new Person();
		juscelino.name = "Juscelino";
		juscelino.age = 35;
		juscelino.showAge();
		// Showing the difference of age between the two objects
		franca.ageDifference(juscelino);
	}

	private static void WorkingWithDogs() {
		// Defining a new 'Dog' variable
		Dog franca = new Dog();
		// Defining new values to instance variables
		franca.name = "França";
		franca.breed = "Rottweiler";
		franca.height = 48;
		// Invoking accessible methods on that instance
		franca.bark();

		// Defining another new 'Dog' variable
		Dog erika = new Dog();
		erika.name = "Érika";
		erika.breed = "Boxer";
		erika.height = 32;

		// Invoking the same method from two different objects of the same type, and
		// they will have distinct outputs, possible, because there are two different
		// objects at memory
		franca.shower();
		erika.shower();

		// Using Reflection to execute private methods under an instance
		// Method info = franca.getClass().getDeclaredMethod("bark");
		// info.setAccessible(true);
		// info.invoke(franca);
	}
}
