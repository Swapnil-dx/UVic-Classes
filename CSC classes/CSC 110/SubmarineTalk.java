/*	Name: Swapnil Daxini
	ID: V00861672
	Assignment 2
	Program Name: SubmarineTalk
*/
import java.util.*; //Import all classes
public class SubmarineTalk{
	public static void main(String[] args){ 
		theMessage(); // To print the message you type in with Scanner
		printSub(); // To print the submarine
	}
	public static void theMessage() {
		Scanner sub= new Scanner(System.in); // To create new Object within Scanner class
		System.out.print("What is the submarine message? ");
		String message="| " + sub.nextLine() + ", over and out! |"; // Using the nextLine method within Scanner to store a a line of words.
		for (int i=0; i< message.length(); i++){ //message.length() counts number of characters within string to determine the number of stars
			System.out.print("*");
		}
		System.out.println(); // To end the line
		System.out.println(message);
		for (int i=0; i< message.length(); i++){
			System.out.print("*");
		}
		System.out.println();
	}
	
	public static void printSub(){ 
		System.out.println("\t  O");
		System.out.println("\t   o");
		System.out.println("\t      _|_");
		System.out.println("\t     /~  |");
		System.out.println("   ,--------'    '--------..._/");
		System.out.println("  (  ===\t\t     _--+");
		System.out.println("   `----------------------''' \\");
	}
} 
