import java.util.*;
public class Random1{
	public static void main(String[] args){
		Random numGenerator= new Random();
		System.out.println("10 dice rolls:");
		for(int i=1; i<=10; i++){
			System.out.print("Roll #" + i + ": ");
			int pseudoRandom= numGenerator.nextInt(6);
			System.out.println(pseudoRandom + 1);
			
		}
	}	
}