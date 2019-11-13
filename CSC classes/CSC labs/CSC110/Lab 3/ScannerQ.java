import java.util.*;
public class ScannerQ {
	public static void main (String[] args){
		System.out.print ("How many numbers will you enter? ");
		Scanner totalNumbers= new Scanner(System.in);
		int tn= totalNumbers.nextInt();
		int sum=0;
		for (int i=1; i<=tn; i++){
			System.out.print("Enter a number: ");
			sum= sum + totalNumbers.nextInt();
		}
	//	System.out.println();
		System.out.println("The total sum is: " + sum);
	}
}