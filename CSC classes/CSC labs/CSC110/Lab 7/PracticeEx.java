

public class PracticeEx{
	public static void main(String[] args){
		printIfDuplicate("Hello");
		printIfDuplicate("coffee");
		printIfDuplicate("Letters");
		printIfDuplicate("FraserRiver");
		String[] words= {"Hello", "Anthony", "Letters", "FraserRiver"};
		printIndexes(words);
	}
	
	public static boolean printIfDuplicate(String word){

		for(int i=0; i<word.length()-1; i++){
			if(word.charAt(i)==word.charAt(i+1)){
				return true;
			}
		}
		return false;
	}
	
	public static void printIndexes(String[] words){
		for(int i=0; i<words.length; i++){
			String word= words[i];
			boolean test= printIfDuplicate(word);
			if(test==true){
				System.out.println("Index " + i + ", word: " + word);
			}
		}
	}
}