import java.util.*;
public class ScannerEx {
	public static void main (String[] args) {
		Scanner console = new Scanner(System.in);
		
		System.out.print("Type a number: ");
		int next= console.nextInt();
		if ( next % 2 ==0){
			System.out.println("Your number is even");
		} else {
			System.out.println("Your number is odd");
		}
		
	}
}