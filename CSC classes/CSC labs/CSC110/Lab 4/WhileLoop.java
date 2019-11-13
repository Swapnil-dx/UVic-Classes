import java.util.*;
public class WhileLoop{
	public static void main(String[] args){
		Scanner console= new Scanner(System.in);
		System.out.println("Enter a positive number less than 10.");
		int input= 2;
		while (input>0 && input<=10){
			System.out.print("Enter a number:");
			input=console.nextInt();
		}
		System.out.println(input + " isnt a valid number");
	}
}