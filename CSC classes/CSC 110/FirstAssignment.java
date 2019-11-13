/*	Name: Swapnil Daxini
	ID: V00861672
	Assignment 1
	Program Name: FirstAssignment
*/
public class FirstAssignment {
	public static void main (String[] args) {
		System.out.println("Welcome!");
		char s=12;
		System.out.println(s);
//		printTotemPole(); // For printing Totem pole
//		calcSurfaceArea(); // To calculate surface area of cylinder
	}
	
	public static void printBat() {
		System.out.println("   mm");
		System.out.println("/^(  )^\\");
		System.out.println("\\,(..),/");
		System.out.println("  V~~V  ");
	}

	public static void printFrog() {
		System.out.println("  @..@");
		System.out.println(" (----)");
		System.out.println("( >__< )");
		System.out.println("\"\"    \"\"");
	}
	
	public static void printTotemPole() {
		printBat();
		System.out.println();
		printFrog();
		System.out.println();
		printLine();
		printFrog();
		printLine();
		printBat();
		printLine();
		printFrog();
		printLine();
		printBat();
		printLine();
		printLine();
	}

	public static void printLine() {
		System.out.println("/~~~~~~\\"); // Added extra method to draw Totem pole
	}
	
	public static void calcSurfaceArea() {
		int h=8; // h= Height
		double d=3; // d= Diameter
		double c; // c= Cirumference
		double a; // c= Area of top Circle
		double areaOfWalls; // Surface area of cylinder walls
		double totalArea; // Final Answer
		double pi=3.141592654;
		c= 2 * pi * (d/2); // Circumference= 2*pi*radius(r) where r=d/2
		a= pi * ((d/2)*(d/2)); // Area of circle= pi*radius squared
		areaOfWalls= h * c; // a= height*circumference
		totalArea= areaOfWalls + (2*a); // Total Area= area of walls + area of top circle + area of bottom circle
		System.out.printf("Total surface area is: %.2f", totalArea); // printf is used to display only 2 decimal point
	}	
}