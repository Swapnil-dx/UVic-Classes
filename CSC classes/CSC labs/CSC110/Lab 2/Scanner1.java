import java.util.Scanner;
public class Scanner1 {
	public static void main(String[] args){
		Scanner userInput = new Scanner(System.in);
		System.out.println("Type the first number you want to add");
		int x= userInput.nextInt();
		System.out.println("Type the other number you want to add");
		int y= userInput.nextInt();
		int z=x+y;
		System.out.println(x + "+" + y + "=" + z);
	}
}