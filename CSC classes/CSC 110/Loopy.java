/*	Name: Swapnil Daxini
	ID: V00861672
	Assignment 3
	Program Name: Loopy
*/
import java.util.*;
public class Loopy {
	public static void main(String[] args){
		Scanner userInput= new Scanner(System.in); // Creating scanner object
		System.out.print("What is the size of BIGGEST shape? "); 
		drawImage(userInput.nextInt()); // Passing user input to drawImage method
/*		System.out.print("How big should the shape be? ");
		drawShape(userInput.nextInt()); */ 
	}
	
	public static void drawShape(int x){ //Draws shapes depending on the parameter being passed from user
		for (int i=0; i<x+1; i++){
			System.out.print("/");  
		}
		for (int i=0; i<x+1; i++){
			System.out.print("\\");
		}
		System.out.println(); // To end line
		System.out.print("/");
		for (int i=0; i<x; i++){
			System.out.print("**");
		}
		System.out.println("/");
		for (int i=0; i<x+1; i++){
			System.out.print("\\");
		}
		for (int i=0; i<x+1; i++){
			System.out.print("/");
		}
	}
	
	public static void drawImage(int x){ 
		for (int i=1; i<=x; i++){ //Draws all shapes between the largest shape and first shape in increasing order
			drawShape(i); // 
			System.out.println();
		}
		for (int i=x-1; i>1; i--){ // Draw all shapes between one smaller than the largest shape to the second smallest shape in decreasing order
			drawShape(i);
			System.out.println();
		}
		drawShape(1); // Last shape drawn separately to avoid fencepost problem	
	}
}