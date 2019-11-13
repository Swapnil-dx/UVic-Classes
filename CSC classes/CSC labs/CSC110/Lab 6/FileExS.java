import java.io.*;
import java.util.*;

public class FileExS{
	public static void main(String[] args){
		Scanner input= new Scanner(System.in);
		System.out.print("Enter the name of file: ");
		File f= new File(input.next());
		int numWords=0;
		Scanner fReader= null;
		try{
			fReader= new Scanner(f);
		} catch (FileNotFoundException x){
			System.out.println("File not found!");
		}
		while (fReader.hasNext()){
			String word= fReader.next();
			System.out.print(word + " ");
			numWords++;
		}
		System.out.println();
		System.out.println("The number of words are " + numWords);
	}
}