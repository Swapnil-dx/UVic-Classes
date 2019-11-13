/*	Name: Swapnil Daxini
	ID: V00861672
	Assignment 4
	Program Name: NumberGuesser
	Program Description: To create a random number and ask a user to guess the number.
	Program Input: Maximum value for secret number, Guesses for secret number.
	Program Output: Number of tries taken to guess number.
*/
import java.util.*; //Import Scanner and Random classes	
public class NumberGuesser{
	public static void main(String[] args){
		Scanner userInput= new Scanner(System.in); 
		Random numGenerator= new Random();
		int secret= createSecretNumber(numGenerator,userInput); //Pass Scanner and Random objects	
		int numTries= guessTheNumber(userInput, secret); //Pass Scanner object
		printResults(secret, numTries);
	}
	
	public static int createSecretNumber(Random numGenerator, Scanner userInput){ //Assign Scanner and Random objects
		System.out.print("What is the highest value for the number? ");
		int maxSecretValue= userInput.nextInt(); 
		int secretValue= numGenerator.nextInt(maxSecretValue) + 1; //Add 1 to random number generated so maxSecretValue is included and 0 is not
		return secretValue;
	}
	
	public static int guessTheNumber(Scanner userInput, int secret){
		int numTries= 0; //Variables initiated outside do while loop due to scope
		int guessNum=0;
		do{
			System.out.print("What is your guess? ");
			guessNum= userInput.nextInt();
			numTries++; //Add one every time do while is initiated
			if (guessNum==secret){
				System.out.println("You got it!");
			} else if (guessNum>secret){
				System.out.println("Your guess is too high!");
			} else {
				System.out.println("Your guess is too low!");
			}
		} while (!(guessNum==secret)); //do while will not end until guessed number is the secret number
		return numTries;
	}
	
	public static void printResults(int secret, int numTries){
		System.out.println("The secret number was " + secret);
		if (numTries==1){ //if statement used to display special message if numTries is 1
			System.out.println("Wow, you got it on your first try!");
		} else {
			System.out.println("It took you " + numTries + " tries to guess the number");
		}
	}
}