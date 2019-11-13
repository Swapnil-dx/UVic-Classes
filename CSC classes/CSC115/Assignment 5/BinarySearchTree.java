import java.util.Iterator;

/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 10/07/2016
* Details: CSC115 Assignment 5
*/

/**
 * BinarySearchTree is an ordered binary tree, where the element in each node
 * comes after all the elements in the left subtree rooted at that node
 * and before all the elements in the right subtree rooted at that node.
 * For this assignment, we can assume that there are no elements that are
 * identical in this tree. 
 * A small modification will allow duplicates.
 */
public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {

	// the root is inherited from BinaryTree.

	/**
	 * Create an empty BinarySearchTree.
	 */
	public BinarySearchTree() {
		super();
	}

	/**
	 * Creates a BinarySearchTree with a single item.
	 * @param item The single item in the tree.
	 */
	public BinarySearchTree(E item) {
		super(item);
	}

	/**
 	 * <b>This method is not allowed in a BinarySearchTree.</b>
	 * It's description from the subclass:<br>
	 * <br>
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException if this method is invoked.
	 */
	public void attachLeftSubtree(BinaryTree<E> left) {
		throw new UnsupportedOperationException();
	}

	/**
 	 * <b>This method is not allowed in a BinarySearchTree.</b>
	 * It's description from the subclass:<br>
	 * <br>
	 * {@inheritDoc}
	 * @throws UnsupportedOperationException if this method is invoked.
	 */
	public void attachRightSubtree(BinaryTree<E> right) {
		throw new UnsupportedOperationException();
	}
	
	/**
 	 * This is a public method which calls a recursive private
	 * method to insert a new item
	 * @param E item to be inserted
	 */
	public void insert(E item) {
		if(root==null){
			TreeNode<E> newNode= new TreeNode<E>(item);
			root= newNode;
		}else{
			insert(item, root);
		}
	}
	
	/**
 	 * This is a private method which uses recursion to insert a new item
	 * similar to a BinarySearchTree.
	 * @param E item to be inserted and node to begin from.
	 * @return TreeNode<E> with the inserted item and its position
	 */
	private TreeNode<E> insert(E item, TreeNode<E> node){
		if(node == null){
			TreeNode<E> newNode = new TreeNode<E>(item);
			return newNode;
		} else {
			if(item.compareTo(node.item)<0){
				node.left = insert(item, node.left);
			} else{
				node.right = insert(item, node.right);
			}
			return node;
		}
	}
	
	/**
 	 * This is a public method which calls a recursive private
	 * method to retrieve a new item
	 * @param E item to be retrieved
	 */
	public E retrieve(E item) {
		return retrieve(item, root);
	}
	
	/**
 	 * This is a private method which uses recursion to retrieve a new item
	 * @param E item to be retrieved and node to begin from.
	 * @return item E if the searched item is present, null otherwise.
	 */
	private E retrieve(E item, TreeNode<E> node){
		E treeItem;
		if(node==null){
			treeItem = null;
		} else{
			if(item.compareTo(node.item) == 0){
				treeItem = node.item;
			} else if(item.compareTo(node.item)<0){
				treeItem= retrieve(item, node.left);
			} else{
				treeItem = retrieve(item, node.right);
			}
		}
		return treeItem;
	}
	
	/**
 	 * This is a public method which calls a recursive private
	 * method to delete a new item
	 * @param E item to be deleted
	 */
	public E delete(E item) {
		E toBeDeleted = retrieve(item);
		TreeNode<E> node = delete(root, item);
		return toBeDeleted;
	}
	
	/**
 	 * This is a private method which uses recursion to delete an item
	 * @param E item to be deleted and node to begin search from.
	 * @return item E if the searched item is present, null otherwise.
	 */
	private TreeNode<E> delete(TreeNode<E> node, E item) {
		if (node == null) {
			return null;
		}
		int h= height();
		if(h == 1 && item.compareTo(node.item) == 0){
			root= null;
			return null;
		}
		if (item.compareTo(node.item) == 0) { 
	    	if (node.left == null) { 
				return node.right;
	   	 	} else if (node.right == null) { 
				return node.left;
	    	} else {
				node.item = getLeftmost(node.right);
            	node.right = delete(node.right, node.item);
	    	}
		} else {		
	    	if (item.compareTo(node.item) < 0) {
				node.left = delete(node.left, item);
	    	} else {		
				node.right = delete(node.right, item);
	    	}
		}
		return node;
	}
	
	/**
 	 * This is a private method which uses recursion to find the leftmost successor of a tree.
	 * @param node to begin search from.
	 * @return item E the leftmost successor
	 */
	private E getLeftmost(TreeNode<E> node) {
		TreeNode<E> left = node.left;
		if (left == null) {
			return node.item;
		} else {
			return getLeftmost(left);
		}
	} 

	/**
	 * Internal test harness.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		// NOTE TO STUDENT: something to get you started.
		BinarySearchTree<String> tree = new BinarySearchTree<String>();
		String s1 = new String("fifa 16");
		String s3 = new String("ark Survival");
		String s2 = new String("battlefield");
		String s4 = new String("pes 16");
		String s5 = new String("call of duty Modern warfare 2");
		String s6 = new String("minecraft");
		String s7 = new String("shellshock live");
		String s8 = new String("impossible creatures");
		tree.insert(s1);
		tree.insert(s2);
		tree.insert(s3);
		tree.insert(s4);
		tree.insert(s5);
		tree.insert(s6);
		tree.insert(s7);
		tree.insert(s8);
		String test = tree.retrieve("battlefield");
		if (test != null && !test.equals("")) {
			System.out.println("retrieving the node that contains "+s2);
			if (test.equals(s2)) {
				System.out.println("Confirmed");
			} else {
				System.out.println("retrieve returns the wrong item");
			}
		} else {
			System.out.println("retrieve returns nothing when it should not");
		}	

		Iterator<String> it = tree.inorderIterator();
		System.out.println("printing out the contents of the tree in sorted order:");
		while (it.hasNext()) {
			System.out.print(it.next()+", ");
		}
		System.out.println();
		System.out.println();
		
		tree.delete("shellshock live");
		
		it = tree.preorderIterator();
		System.out.println("printing out the contents of the tree in preorder:");
		while (it.hasNext()) {
			System.out.print(it.next()+", ");
		} 
		System.out.println();
		System.out.println();
		
		tree.delete("battlefield");
		
		it = tree.postorderIterator();
		System.out.println("printing out the contents of the tree in postorder:");
		while (it.hasNext()) {
			System.out.print(it.next()+", ");
		}		
		System.out.println();
		String deleted= tree.delete("fifa 16");
		System.out.println("The deleted string was " + deleted);
		DrawableBTree<String> dbt = new DrawableBTree<String>(tree);
		dbt.showFrame();
	}
}

	

	
