import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Random;
import java.io.*;

public class Wordle {

	public static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		
		FileReader fileName = new FileReader("wordList.txt");	
		Scanner wordList = new Scanner(fileName);
		
		/*************************
		 * Determine answer word *
		 ************************/
		System.out.println("Would you like to choose a word or generate a random answer word?");
		System.out.print("[C] = choose, [R] = random: ");
		String choice = userInput.next();
		System.out.println();
		String answer = generateWord(choice, wordList);
		
		System.out.println("Answer word has been set.");
		System.out.print("Would you like to see the instructions? [y] or [n] ");
		if(userInput.next().equalsIgnoreCase("y")) {
			System.out.println("\nLetters that are in the word but NOT in the right position will "
					+ "be printed in UPPERCASE");
			System.out.println("Letters that are in the word AND in the right position will be "
					+ "printed in LOWERCASE");
			System.out.println("Letters not in the word will be shown as an UNDERSCORE \n");
		}
		
		/*********************************
		 * Asking for number of attempts *
		 *********************************/
		System.out.print("How many attempt does the player get? ");
		int attempts = userInput.nextInt();
		System.out.println();
		
		/***********************
		 * Letters of the word *
		 ***********************/
		String[] answerLetters = new String[5];
		for(int i = 0; i < 5; i++)
		{
			answerLetters[i] = answer.substring(i, i + 1);
		}
		
		String[] guessLetters = new String[5];
		resetLetters(guessLetters);
		
		String guessedWord = "";
		String currentLetter = "";
		
		for(int guessNum = 0; guessNum < attempts; guessNum++)
		{
			System.out.print("Guess #" + (guessNum + 1) + ": ");
			String guess = userInput.next();
			guessedWord = "";
			resetLetters(guessLetters);
			
			for(int letter = 0; letter < 5; letter++)
			{
				currentLetter = guess.substring(letter, letter + 1);
				for(int answerLetter = 0; answerLetter < 5; answerLetter++)
				{
					if(currentLetter.equals(answerLetters[answerLetter]))
					{
						if(answerLetter == letter)
						{
							guessLetters[letter] = currentLetter;
							break;
						}
						else
						{
							guessLetters[letter] = currentLetter.toUpperCase();
						}
					}
				}
			}
			
			for(int guessLetter = 0; guessLetter < 5; guessLetter++)
			{
				guessedWord += guessLetters[guessLetter];
			}

			System.out.println(guessedWord);
			System.out.println();
			
			if(guessedWord.equals(answer))
			{
				System.out.println("Congrats! The answer word is: " + answer);
				System.out.println("You got the word in " + (guessNum + 1) + " tries!");
				break;
			}
		}
		
		System.out.println("The answer word is: " + answer);
		
		
		
		
		
		
		wordList.close();
		userInput.close();
	}

	public static String generateWord(String choice, Scanner wordList) throws IOException {
		String answer = "";
		if (choice.equalsIgnoreCase("C"))
		{
			boolean validWord = false;
			
			System.out.print("Please enter the five letter answer word: ");
			answer = userInput.next();
			System.out.println();
						
			while(validWord == false)
			{
				for(int i = 0; i < 12947; i++) {
					if(answer.length() == 5)
					{
						validWord = true;
						break;
					}
					else
					{
						validWord = false;
					}
				}
				
				if(validWord == true)
				{
					return answer;
				}
				
				System.out.println("\"" + answer + "\" is not a five letter word.");
				System.out.print("Please enter the five letter answer word: ");
				answer = userInput.next();
				System.out.println();
			}
		}
		else if (choice.equalsIgnoreCase("R"))
		{
			Random rand = new Random();
			int lineNum = rand.nextInt(12947);

			for(int i = 0; i < lineNum; i++)
			{
				answer = wordList.nextLine();
			}
		}
		return answer;
	}
	
	public static void resetLetters(String[] word)
	{
		for(int i = 0; i < word.length; i++)
		{
			word[i] = "_";
		}
	}

}
