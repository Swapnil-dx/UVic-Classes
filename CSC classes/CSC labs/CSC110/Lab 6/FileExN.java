import java.io.*;
import java.util.*;

public class FileExN{
	public static void main(String[] args){
		Scanner input= new Scanner(System.in);
		System.out.print("Enter the name of file: ");
		File f= new File(input.next());
		int sum=0;
		int totalNum=0;
		try{
			Scanner fReader= new Scanner(f);
			while (fReader.hasNextDouble()){
			sum += fReader.nextDouble();
			totalNum++;
			}
		} catch (FileNotFoundException x){
			System.out.println("File not found!");
		}
		System.out.println("The average of all numbers is " + sum/totalNum);
	}
}