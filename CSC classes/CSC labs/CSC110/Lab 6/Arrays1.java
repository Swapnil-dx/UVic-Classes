import java.util.*;

public class Arrays1 {
	public static void main(String[] args){
		int x=3;
		int[] a= {256,654,78,8,6,3,8,1};
		printArray(a);
		findMin(a);
		doStuff(x,a);
		printArray(a);
		System.out.println(x);
	}
	
	public static void printArray(int[] a){
		for(int i=0; i<a.length; i++){
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	public static void findMin(int[] b){
		int minValue=b[0];
		for(int i=0; i<b.length; i++){
			if(b[i]<=minValue){
				minValue=b[i];
			}
		}
		System.out.println("The smallest element is " + minValue);
	}
	
	public static void doStuff(int x, int[] a) {
		x*=2;
		a[1]*=2;
	}
}