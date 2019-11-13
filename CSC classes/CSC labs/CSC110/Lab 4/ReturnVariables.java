import java.util.*;
public class ReturnVariables{
	public static void main(String[] args){
	Scanner y=new Scanner(System.in);
//	System.out.print("Type any number: ");
//	int result= sumToN(y.nextInt());
//	System.out.println("The answer is: " + result);
	int answer= Exponent(y.nextInt(),y.nextInt());
	System.out.println("Your answer is:" + answer);
	}
	public static int sumToN(int x){
		int sum=0;
		for (int i=1; i<=x; i++){
			sum=sum+i;
		}
		return sum;
	}
	public static int Exponent(int num,int power){
		int result=1;
		for (int i=0; i<power; i++){
			result*=num;
		}
		return result;
	}
}