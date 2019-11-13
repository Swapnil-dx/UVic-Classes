import java.util.*;

public class Median3{
	public static void main(String[] args){
		int num1, num2, num3;
		Scanner userInput= new Scanner(System.in);
		System.out.println("Enter a number: ");
		num1= userInput.nextInt();
		
		System.out.println("Enter a number: ");
		num2= userInput.nextInt();
		
		System.out.println("Enter a number: ");
		num3= userInput.nextInt();
		
		int[] arr= {num1, num2, num3};
		
		Arrays.sort(arr);
	
		System.out.println("The median of the 3 numbers is "+arr[1]);
	}
}