public class Tester {
	public static void main(String[] args) {
		TreeNode<Integer> zero = new TreeNode<Integer>(0);
		TreeNode<Integer> one = new TreeNode<Integer>(1);
		TreeNode<Integer> two = new TreeNode<Integer>(2);
		TreeNode<Integer> three = new TreeNode<Integer>(3);
		TreeNode<Integer> four = new TreeNode<Integer>(4);
		TreeNode<Integer> five = new TreeNode<Integer>(5);
		TreeNode<Integer> six = new TreeNode<Integer>(6);
		TreeNode<Integer> seven = new TreeNode<Integer>(7);
		TreeNode<Integer> eight = new TreeNode<Integer>(8);
		TreeNode<Integer> nine = new TreeNode<Integer>(9);
		TreeNode<Integer> ten = new TreeNode<Integer>(10);
		
		/*
		BinaryTree<Integer> myTree = new BinaryTree<Integer>(5);
		DrawableBTree<Integer> draw = new DrawableBTree<Integer>(myTree);
		draw.showFrame();
		System.out.println(myTree.height());
		*/
		
		/*
		BinaryTree<Integer> myTree = new BinaryTree<Integer>(5);
		myTree.root.left = three;
		myTree.root.right = eight;
		DrawableBTree<Integer> draw = new DrawableBTree<Integer>(myTree);
		draw.showFrame();
		System.out.println(myTree.height());
		*/
		
		
		BinarySearchTree<Integer> myTree = new BinarySearchTree<Integer>(5);
		two.left = zero;
		two.right = three;
		three.right = four;
		nine.right = ten;
		nine.left = seven;
		myTree.root.left = two;
		myTree.root.right = nine;		
		myTree.insert(1);
		myTree.insert(15);
		myTree.insert(8);
		System.out.println(myTree.retrieve(123));
		DrawableBTree<Integer> draw = new DrawableBTree<Integer>(myTree);
		draw.showFrame();
		System.out.println(myTree.height());
		
			
	}

}