import java.util.*;
public class Lab5{
	public static void main (String[] args){
		Scanner userInput= new Scanner(System.in);
//		int totalSum= sumToNumber(userInput);
		System.out.println("Number of names? ");
		String initials = getInitials(userInput, userInput.nextInt());
		System.out.println(initials);
	}
	
	public static int sumToNumber(Scanner userInput){
		int sum=0;
		System.out.print("Type a number: ");
		int num=userInput.nextInt();
		for(int i=1; i<=num ;i++){
			sum=sum+i;
		}
		return sum;
	}
	
	public static String getInitials(Scanner userInput, int numOfNames){
		int i=0;
		String initials="";
		while (i<numOfNames){
			System.out.print("Enter name: ");
			String s= userInput.next();
			initials= initials + (s.charAt(0) + ".");
			i++;
		}
		return initials;
	}
}
