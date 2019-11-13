public class PrefixCode
{
	public static void main(String[] args){
		char[] characters = {'a', 'b', 'c', 'd', 'r', '!'};
		String[] encodings = {"L", "RRR", "RLRR", "RLRL", "RRL", "RLL"};
		BTNode treeRoot = createTree(characters, encodings);
		System.out.println(decodeString(treeRoot, "RLRRL"));
		System.out.println(decodeString(treeRoot, "LRRRRRLLRLRRLRLRLLRRRRRLLRLL"));
	}
	
	//For example, if character 'c' has encoding RLRR, we make sure that the root
	//has a right child which has a left child which has a right child which has a right child, whose label is 'c'.
	public static BTNode createTree(char[] characters, String[] encodings){
		BTNode root = new BTNode();
		int numChars = characters.length;
		
		for(int i = 0; i < numChars; i++){
			String encoding = encodings[i];
			int j = 0;
			BTNode curr = root;
			while(j < encoding.length()){
				if(encoding.charAt(j) == 'L'){
					curr = curr.leftChild();
				} else {
					curr = curr.rightChild();
				}
			}
			curr.label = characters[i];
		}
		return root;
	}
	
	//If our input string is RLRRL, we start at root and go right, left, right, right, left,
	//but whenever we hit a leaf we add a character to the output string and re-start at root.
	public static String decodeString(BTNode treeRoot, String input){
		String output = "";
		BTNode curr = treeRoot;
		for(int i = 0; i < input.length(); i++){
			if(input.charAt(i) == 'L'){
				curr = curr.leftChild();
			} else{
				curr =curr.rightChild();
			}
			if(curr.label != null){
				char label = curr.label;
				curr = treeRoot;
				output+= label;
			}
		}
		return output;
	}
}

//A simple binary tree node that can have two children, which are null by default.
//It can also have a single-character label.
class BTNode
{
	Character label = null;
	private BTNode leftChild = null;
	private BTNode rightChild = null;
	//Creates a left child if there isn't one, then returns it.
	public BTNode leftChild(){if(leftChild == null){leftChild = new BTNode();} return leftChild;}
	public BTNode rightChild(){if(rightChild == null){rightChild = new BTNode();} return rightChild;}
}