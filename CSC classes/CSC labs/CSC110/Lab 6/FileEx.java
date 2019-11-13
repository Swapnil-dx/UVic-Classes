import java.io.*;
import java.util.*;

public class FileEx{
	public static void main(String[] args){
		Scanner input= new Scanner(System.in);
		System.out.print("Enter the name of file: ");
		File f= new File(input.next());
		while(!f.exists){
			try{
			Scanner fReader= new Scanner(f);
			} catch (FileNotFoundException x){
			System.out.println("File not found! Please try again: ");
			f= File(input.next());
			}
		}
	}
}