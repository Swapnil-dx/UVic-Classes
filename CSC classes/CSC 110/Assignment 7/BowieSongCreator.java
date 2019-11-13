/*	Name: Swapnil Daxini
	ID: V00861672
	Assignment 7
	Program Name: BowieSongCreator
	Program Description: This program reads text files which contain the lyrics of some of David Bowie's famous songs and converts them into an array
	of objects which are then sorted into the correct order. Finally, the program also uses his cut up technique to create new songs using lyrics
	from his old songs
 */
import java.util.*;
import java.io.*;

public class BowieSongCreator {
	public static void main(String[] args) {
		Scanner console= new Scanner(System.in);
		Scanner fileReader= null; //Scanner object initiated outside due to scope
		boolean fileStatus= false;
		while (!fileStatus){
			try{
				System.out.print("What is the name of the input file? ");
				String fileName =  console.nextLine();
				fileReader = new Scanner(new File(fileName));
				fileStatus = true;
			} catch (FileNotFoundException x) {
				System.out.println("Error - can't read that file, try another file name.");
			}
		}
		SongLine[] arr= makeArray(fileReader);
		System.out.print("What keyword would you like to filter the songs by? ");
		String filterWord= console.next();
		listSongsByKeyword(arr, filterWord);
		makeSong(arr);
	}
//This method converts lines of lyrics of a certain song to an array of objects.	
	public static SongLine[] makeArray(Scanner fileReader) {
		SongLine[] arr= new SongLine[fileReader.nextInt()];
		for(int i=0; i<arr.length; i++){
			String keyword= fileReader.next();
			int lineNum= fileReader.nextInt();
			String words= fileReader.nextLine().trim();
			SongLine temp= new SongLine(keyword, lineNum, words);
			arr[i]= temp;
		}
		return arr;
	}
//This method prints out the array of lyrics	
	public static void printArray(SongLine[] arr){
		for(int i=0; i<arr.length; i++){
			System.out.println(arr[i].getLineNumber() + ":\t" + arr[i].getWords());
		}
	}
//This method sorts the lyrics in the array of objects numerically.	
	public static void sortByLineNumber(SongLine[] arr){
		for (int i=0; i<arr.length; i++){
			swapIndex(arr, i, findMinIndex(arr, i));
		}
		System.out.println();
		printArray(arr);
	}
//This method sorts the lyrics numerically of a certain song
	public static void listSongsByKeyword(SongLine[] arr, String filterWord){
		int arraySize=0;
		for(int i=0; i<arr.length; i++){
			String temp= arr[i].getKeyword();
			if(temp.equals(filterWord)){
				arraySize++;
			}
		}
		SongLine[] filtered= new SongLine[arraySize];
		int num=0;
		int count=0;
		while(count<arr.length){
			String temp= arr[count].getKeyword();
			if(temp.equals(filterWord)){
				filtered[num]=arr[count];
				num++;
			}
			count++;
		}
		sortByLineNumber(filtered);
	}
//This creates a new 21-line song from the lyrics of David Bowies' songs Hero and Dance
	public static void makeSong(SongLine[] arr){
		for(int i=0; i<7; i++){
			printRandomWords(arr, "Dance");
			printRandomWords(arr, "Dance");
			printRandomWords(arr, "Hero");
		}
	}
//Additional method to sort the array. Finds the index of the minimum value in the array from a certain point
	public static int findMinIndex (SongLine[] arr, int start){
		int min=arr[start].getLineNumber();
		int minIndex= start;
		for(int i=start+1; i<arr.length; i++){
			if(arr[i].getLineNumber()<min){
				min= arr[i].getLineNumber();
				minIndex=i;
			}
		}
		return minIndex;
	}
//Additional method to sort array. Swaps two objects in an array	
	public static void swapIndex(SongLine[] arr, int a, int b){
		SongLine temp= new SongLine(arr[a].getKeyword(), arr[a].getLineNumber(), arr[a].getWords());
		arr[a]=arr[b];
		arr[b]= temp;
	}
//Addiditional method used in the makeSong method. Finds random words from a certain David Bowie song.
	public static void printRandomWords(SongLine[] arr, String keyword){
		Random r= new Random();
		String s="";
		int randomIndex=0;
		do{
			randomIndex= r.nextInt(arr.length);
			s= arr[randomIndex].getKeyword();
		} while(!(s.equals(keyword)));
		System.out.println(arr[randomIndex].getWords());
	}
}