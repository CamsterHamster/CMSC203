/*
 * Class: CMSC203 CRN 46622
 * Program: Assignment 2
 * Instructor: Grigoriy Grinberg
 * Summary of Description: The objective of the assignment is to create a 
 * driver class for a given utility class. The driver class will use 
 * methods from the utility class to ask the user to guess a random number 
 * generated from the Random class and count the number of guess and 
 * continuously ask the user if they would like to keep guessing for a number.
 * Due Date: 07/03/2022 
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Camilo Trejos
*/

package Assignment2Package;

import java.util.Scanner; //Importing the Scanner class

public class RandomNumberGuesser 
{
	
	// Create an object from the RNG class and use randObject to reference object 
	
	RNG randObject = new RNG(); 
	
	// Create Scanner object that can be referenced in every method
	
	private static Scanner input =  new Scanner(System.in); 
	
	/* Main method contains code to repeat call to a method that will allow the
	 * user to guess a number between 0 and 100.
	 * @param continueGame - sentinel boolean variable that is used to repeat program
	 * @param randNum - Reference variable that obtains a random number from rand() method
	 * 
	 */
	public static void main(String[] args) 
	{
		
		System.out.println("Welcome to my Random Number Guesser!\n"); // Header main
		
		String continueGame = "yes"; 
		
		while (continueGame.equalsIgnoreCase("yes"))
		{
			
			/* using the rand() method from the RNG class, randNum receives
			 * a random number 
			 */
			
			int randNum = RNG.rand(); 
			
			/* continueGame variable stores the return value from the playGame 
			 * method that takes randNum as argument 
			 */
			
			continueGame = playGame(randNum); 
			
			/* Handles if user enters anything other than yes or no when asked if 
			 * they would like to play again
			 */
			
			while (!(continueGame.equalsIgnoreCase("yes")))
			{
				if (continueGame.equalsIgnoreCase("no"))
					break;
				else
				{
					System.out.println("Incorrect entry.\nPlease "
					+ "choose either yes or no");
					continueGame = input.nextLine();
				}
			}
		}
		
		System.out.println("Thanks for playing..."); // Ending message
		
		System.out.println("\nMade by Camilo Trejos"); // Programmer
		
	}
	
	/* playGame method takes an int argument and will compare that value 
	 * with a value entered by the user until the user guesses the 
	 * random number
	 * @param guesses - int variable tallies number of guesses per number
	 * @param nextGuess - int variable that holds current guess
	 * @param highGuess - int variable that holds current upper bound guess
	 * @param lowGuess - int variable that holds current lower bound guess
	 * @param response - sentinel variable that records user's yes or no 
	 * @return user's response as a string
	 */
	
	public static String playGame(int num)
	{
		
		int guesses, nextGuess, highGuess = 100, lowGuess = 0;
		
		String response = ""; 
		
		/* Calls the resetCount() method from RNG class to reset the amount 
		 * of guesses 
		 */
		
		RNG.resetCount();
		
		System.out.println("Enter your first guess: ");
		
		nextGuess = input.nextInt();
		
		 /* Calls inputValidation() method from RNG class to check if user 
		  * has entered a number not in the range of lowGuess to HighGuess 
		  */
		
		boolean betweenRange = RNG.inputValidation(nextGuess, 
		lowGuess, highGuess); 
		
		/* Calls the getCount() method from RNG class to check how many guesses 
		 * have been made 
		 */
		
		guesses = RNG.getCount(); 
		
		System.out.println("Number of guesses is " + guesses);
		
		/* After the first guess, the program will keep asking user to guess
		 * the random number until the guess equals the random number. While
		 * loop will handle if number is not between the current range 
		 */
		
		while (!betweenRange)
		{
			nextGuess = input.nextInt();
			betweenRange = RNG.inputValidation(nextGuess, 
			lowGuess, highGuess);
			
			guesses = RNG.getCount();
			
			System.out.println("Number of guesses is " + guesses);
		}
			
		if (nextGuess == num) // User guesses number
		{
			System.out.println("Congratulations, you guessed "
			+ "correctly\nTry again? (yes or no) ");
			input.nextLine();
			response = input.nextLine();
		}
		else if (nextGuess < num) // If guess is less than random number
		{
			lowGuess = nextGuess;
			
			System.out.println("Your guess is too low\n" 
			+ "Enter your guess between " + lowGuess + " and "
			+ highGuess);
			
			while (nextGuess != num)
			{
				
				nextGuess = input.nextInt();
			
				betweenRange = RNG.inputValidation(nextGuess, 
				lowGuess, highGuess);
				
				guesses = RNG.getCount();
				
				System.out.println("Number of guesses is " + guesses);
						
				while (!betweenRange)
				{
					nextGuess = input.nextInt();
					betweenRange = RNG.inputValidation(nextGuess, 
					lowGuess, highGuess);
					guesses = RNG.getCount();
					
					System.out.println("Number of guesses is " + guesses);
				}
				
				if (nextGuess == num)
				{
					System.out.println("Congratulations, you guessed "
					+ "correctly\nTry again? (yes or no) ");
					input.nextLine();
					response = input.nextLine();
				}
				
				else if (nextGuess < num)
				{
					lowGuess = nextGuess;
					
					System.out.println("Your guess is too low\n" 
					+ "Enter your guess between " + lowGuess + " and "
					+ highGuess);
					
				}
				else if (nextGuess > num)
				{
					highGuess = nextGuess;
					System.out.println("Your guess is too high\n" 
					+ "Enter your guess between " + lowGuess + " and "
					+ highGuess);
					
				}
			}
			
		}
		else if (nextGuess > num) // If guess is greater than random number
		{
			highGuess = nextGuess;
			
			System.out.println("Your guess is too high\n" 
			+ "Enter your guess between " + lowGuess + " and "
			+ highGuess);
			
			while (nextGuess != num)
			{
				nextGuess = input.nextInt();
				
				betweenRange = RNG.inputValidation(nextGuess, 
				lowGuess, highGuess);
				
				guesses = RNG.getCount();
				
				System.out.println("Number of guesses is " + guesses);
								
				while (!betweenRange)
				{
					nextGuess = input.nextInt();
					betweenRange = RNG.inputValidation(nextGuess, 
					lowGuess, highGuess);
					guesses = RNG.getCount();
					
					System.out.println("Number of guesses is " + guesses);
				}
				
				if (nextGuess == num)
				{
					System.out.println("Congratulations, you guessed "
					+ "correctly\nTry again? (yes or no) ");
					input.nextLine();
					response = input.nextLine();
				}
				else if (nextGuess < num)
				{
					lowGuess = nextGuess;
					System.out.println("Your guess is too low\n" 
					+ "Enter your guess between " + lowGuess + " and "
					+ highGuess);
					
				}
				
				else if (nextGuess > num)
				{
					highGuess = nextGuess;
					System.out.println("Your guess is too high\n" 
					+ "Enter your guess between " + lowGuess + " and "
					+ highGuess);
					
				}
				
			}
		}
		
		// Returns string to be used to check if continueGame either equals yes or no
		
		return response;
	}
	
}
