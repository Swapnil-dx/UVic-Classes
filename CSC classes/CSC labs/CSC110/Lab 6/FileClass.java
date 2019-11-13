import java.util.*;
import java.io.*;

public class FileClass {
	public static void main(String[] args) throws FileNotFoundException {
		File inputfile = new File("words.txt");
		try {
			Scanner input= new Scanner(inputfile);
			System.out.println(input.next());
	} catch (FileNotFoundException x) {
		System.out.println("Oops! File not found");
	}
	}
}