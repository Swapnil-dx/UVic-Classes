import java.util.*;
public class If1{
	public static void main(String[] args){
		Scanner console= new Scanner(System.in);
		System.out.println("This program will ask you to enter x number. \nIt will output how many of the numbers are between two numbers");
		System.out.println("Type the range of your numbers first then the amount of number you want to enter");
		method1(console.nextInt(),console.nextInt(),console.nextInt());
	}
	public static void method1(int start, int end, int numbers){
		Scanner console= new Scanner(System.in);
		int count=0;
		for (int i=1; i<=numbers; i++){
			System.out.print("Enter number " + i + ":");
			int input= console.nextInt();
			if (input>start && input<end) {
				count++;
			}
		}
		System.out.println(count + " numbers are between " + start + " and " + end);
	}
	
}