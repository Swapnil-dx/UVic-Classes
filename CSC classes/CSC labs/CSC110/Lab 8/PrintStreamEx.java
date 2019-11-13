import java.io.*;
import java.util.*;

public class PrintStreamEx {
	public static void main(String[] args) {
		File outputFile= new File("output.txt");
		
		try{
			PrintStream printer= new PrintStream(outputFile);
			for(int i=0; i<=3; i++) {
				printer.println("i is: " + i);
			}
			
		} catch (FileNotFoundException x) {
			System.out.println("Can't print on that file!");
		}
	}
}