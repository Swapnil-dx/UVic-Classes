/* 

   Rahnuma Islam Nishat - 08/02/2014
   -edited by Swapnil Daxini
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class  KMP 
{
    private static String pattern;
	private static int[][] dfa;

	/* Constructor creates DFA for a particular pattern
	*/
    public KMP(String pattern){  
		this.pattern = pattern;
		int M = pattern.length();
		int R = 4;
		dfa = new int[R][M];
		dfa[pattern.charAt(0)][0] = 1;
		for (int X = 0, j = 1; j < M; j++){
			for (int c = 0; c < R; c++){
				dfa[c][j] = dfa[c][X];
			}
			dfa[pattern.charAt(j)][j] = j+1;
			X = dfa[pattern.charAt(j)][X];
		} 
	}
    
	/* Method that use KMP algorithm to search for a paticular pattern.
	*/
    public static int search(String txt){  
		int N = txt.length();
		int i, j;
		for(i = 0, j = 0; i < N && j < pattern.length(); i++) {
			j = dfa[txt.charAt(i)][j];
		}
		if( j == pattern.length()){
			return (i - pattern.length());
		} else {
			return N;
		}
    }
    
    
    public static void main(String[] args) throws FileNotFoundException{
	Scanner s;
	if (args.length > 0){
	    try{
		s = new Scanner(new File(args[0]));
	    } catch(java.io.FileNotFoundException e){
		System.out.println("Unable to open "+args[0]+ ".");
		return;
	    }
	    System.out.println("Opened file "+args[0] + ".");
	    String text = "";
	    while(s.hasNext()){
		text += s.next() + " ";
	    }
	    
	    for(int i = 1; i < args.length; i++){
		KMP k = new KMP(args[i]);
		int index = search(text);
		if(index >= text.length())System.out.println(args[i] + " was not found.");
		else System.out.println("The string \"" + args[i] + "\" was found at index " + index + ".");
	    }
	    
	    //System.out.println(text);
	    
	}
	else{
	    System.out.println("usage: java SubstringSearch <filename> <pattern_1> <pattern_2> ... <pattern_n>.");
	}
	
	
    }
}
