public class PrefixCodeHarder
{
	public static void main(String[] args){
		char[] characters = {'a', 'b', 'c', 'd', 'r', '!'};
		String[] encodings = {"0", "111", "1011", "1010", "110", "100"};
		BTNode treeRoot = createTree(characters, encodings);
		System.out.println(decodeString(treeRoot, "10110"));
		System.out.println(decodeString(treeRoot, "0111110010110101001111100100"));
	}
	
	//For example, if character 'c' has encoding 1011, we make sure that the root
	//has a right child which has a left child which has a right child which has a right child whose label is 'c'.
	public static BTNode createTree(char[] characters, String[] encodings){
		BTNode root = new BTNode();
		//...
		return root;
	}
	
	//If our input string is 10110, we start at root and go right, left, right, right, left,
	//but whenever we hit a leaf we add a character to the output string and re-start at root.
	public static String decodeString(BTNode treeRoot, String input){
		String output = "";
		//...
		return output;
	}
}

//A simple binary tree node that can have two children, which are null by default.
//It can also have a single-character label.
class BTNode
{
	Character label = null;
	BTNode leftChild = null;
	BTNode rightChild = null;
}