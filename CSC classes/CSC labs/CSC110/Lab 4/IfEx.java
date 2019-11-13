import java.util.*;
public class IfEx {
	public static void main(String[] args){
		Scanner input= new Scanner(System.in);
		System.out.println("Type any number");
		int userinput= input.nextInt();
		if (userinput>0 && userinput<11){
			System.out.println("The number is between 1 and 10");
		}
		else if (userinput<0 || userinput>100){
			System.out.println("The number is less than 0 or greater than 100");
		}
		else {
			System.out.println("The number is between 10 and 100");
		}
	}
}