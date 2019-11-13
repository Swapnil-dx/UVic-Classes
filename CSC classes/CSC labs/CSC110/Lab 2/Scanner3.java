import java.util.Scanner;
public class Scanner3 {
	public static void main(String[] args){
		Scanner userInput = new Scanner(System.in);
		System.out.println("Type any number");
		int x= userInput.nextInt();
		int sum=0;
		for (int i=1; i<=x; i++){
			sum=sum+i;
			}
		System.out.print("The sum of all numbers up to " + x + " is " + sum);
	}
}