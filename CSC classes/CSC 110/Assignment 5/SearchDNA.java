/* 	Name: Swapnil Daxini
	ID: V00861672
	Assignment 5
	Program Name: SearchDNA
	Program Description: This program reads DNA sequences from a text file and find the longest string, total number of mutations and find the total number
	of a specific sequence, with and without mutations.
*/


import java.util.*;
import java.io.*;

public class SearchDNA {
	public static void main (String[] args) {
		Scanner userInput= new Scanner(System.in);
		String[] test= fileToArray(userInput);
//		printArray(test);
		int mutations= countTotalMutations(test);
		System.out.println("Total mutations found: " + mutations);
		String longest= findLongest(test);
		System.out.println("The longest String is " + longest);
		System.out.println();
		System.out.print("What sequence would you like to search for? ");
		String toFind= userInput.next();
		int frequency= findFrequency(toFind, test);
		System.out.println("Number of times " + toFind + " was found: " + frequency);
		int numOfTarget= findFreqWithMutations(toFind, test);
		System.out.println("Times " + toFind + " was found including mutations: " + numOfTarget);
	}
	
	public static void printArray(String[] arr){ //Prints the entire array of DNA strings each on a separate line
		System.out.println("The array contains the following:");
		for(int i=0; i<arr.length; i++){
		System.out.println(arr[i]);
		}
	}
	
	public static String[] fileToArray(Scanner userInput){ //Changes a text file into an array of strings
		Scanner fileReader= null; //Scanner object initiated outside due to scope
		boolean fileStatus= false;
		while (!fileStatus){
			try{
				System.out.print("What is the name of the input file? ");
				String fileName =  userInput.nextLine();
				fileReader = new Scanner(new File(fileName));
				fileStatus = true;
			} catch (FileNotFoundException x) {
				System.out.println("Error - can't read that file, try another file name.");
			}
		}
		int size = fileReader.nextInt();
		String[] wordList = new String[size];
		for (int i = 0; i < size; i++) {
			wordList[i] = fileReader.next();
		}
		System.out.println("That file has " + size + " words in it");
		return wordList;
	}
	
	public static String findLongest(String[] arr){
		String longest= ""; //creating an empty string
		for(int i=0; i<arr.length; i++){
			String test=arr[i];
			if(longest.length()<test.length()){
				longest=test;
			}
		}
		return longest;
	}
	
	public static int findFrequency(String toFind, String[] arr){ //Find the number of specific DNA sequence(not including mutations)
		int numCounter=0;;
		for(int i=0; i<arr.length; i++){
			String test= arr[i];
			if(test.equals(toFind)){
				numCounter++;
			}
		}
		return numCounter;
	}
	
	public static int countTotalMutations(String[] arr){ //Counts the total number of mutations in an array
		int numOfMutations=0;
		for(int i=0; i<arr.length; i++){
			String test=arr[i];
			for(int j=0; j<test.length()-1; j++){
				boolean mutated=false;
				if (test.charAt(j)==test.charAt(j+1)){
					mutated=true;
					numOfMutations++;
					break;
				}
			}
		}
		return numOfMutations;
	}
	
	public static int findFreqWithMutations(String target, String[] arr){ ////Find the number of specific DNA sequence(including mutations)
		String[] buffer= new String[arr.length];
		for(int i=0; i<arr.length; i++){
			String test= arr[i];
			buffer[i]=""; //New array to store unmutated DNA sequences
			for(int j=0; j<test.length(); j++){ //Removes mutations of DNA sequences
				if(j==0){
					buffer[i]= buffer[i] + test.charAt(j);
				}
				else if(test.charAt(j) != test.charAt(j-1)){
					buffer[i] = buffer[i] + test.charAt(j);
				}
			}
		}
		int numOfTarget= findFrequency(target, buffer);
		return numOfTarget;
	}
}