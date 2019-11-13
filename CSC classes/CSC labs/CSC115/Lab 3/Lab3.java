public class Lab3 {
	public static void main(String[] args) {
		
		List<String> test = new StringList();
		
		/* Task 1 - Searching for a value
		 * If the list contains the value, print out the where the value 
		 * is found, otherwise print that the value is not in the list.
		 * Example: The List contains X at position Y.
		 *			The List does not contain X.
		 * a) Search for "csc115"
		 * b) Search for "array"
		 */
		
		String word= "csc115"; 
		for(int i=0; i<test.size(); i++){
			if((test.get(i)).equals(word)){
				System.out.println("The List contains " + word + " at position " + i);
			}
		}
		
		System.out.println(test);
		int shortWordIndex=0;
		
		int shortsize=test.get(0).length();
		for(int i=0; i<test.size(); i++){
			if(test.get(i).length()<=shortsize){
				shortsize=test.get(i).length();
				shortWordIndex=i;
			}
		}
		test.remove(shortWordIndex);
		System.out.println(test);
	}

		
		/* Task 2 - Remove the shortest word in the list
		 * Identify the shortest word in the list, and then remove it
		 * without changing the order of the other elements in the list.
		 * Example: A List with the contents { "CSC115", "labs", "are", "awesome!" } the
		 *			"are" should be removed, resulting in { "CSC115", "labs", "awesome!" }
		 */
		
		// Write your code for task 2 here.

}
	