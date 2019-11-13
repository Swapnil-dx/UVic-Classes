import java.util.Scanner;
public class ScannerDemo {
	public static void main(String[] args){
		Scanner userInput = new Scanner(System.in);
		System.out.println("What is your name?");
		String userName= userInput.nextLine();
		System.out.println("Your name is " + userName);
	}
}