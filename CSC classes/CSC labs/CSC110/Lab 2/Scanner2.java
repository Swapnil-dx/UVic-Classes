import java.util.Scanner;
public class Scanner2 {
	public static void main(String[] args){
		Scanner userInput = new Scanner(System.in);
		System.out.println("Type any number");
		int x= userInput.nextInt();
		for (int i=1; i<=x; i++){
			System.out.print(i + " ");
		}	
	}
}