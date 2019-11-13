/*
 * The shell of the class, to be completed as part of the CSC115 Assignment 3 : Calculator.
 */

/*
 * NOTE TO STUDENT:
 * Fill in any of the parts that have the comment:
	*******COMPLETE*******
 * Do not change method headers or code that has been supplied.
 * All methods must be properly commented.
 * Please delete all messages to you, including this one, before submitting.
 */
public class StringStack {
	
	public StringStack(){
		head = null;
	}
	
	private Node head;

	public boolean isEmpty() {
	if( head == null) return true;
	else return false;
	}

	public String pop() {
	if (isEmpty()){
		throw new StackEmptyException("Can't pop from an empty stack.");
	}
	String popped = head.item;
	head = head.next;
	return popped; 
	}

	public String peek() {
	if (isEmpty()){
		throw new StackEmptyException("Can't pop from an empty stack.");
	}
		return head.item;
	}

	public void push(String item) {
		Node add = new Node(item, head);
		head = add;
	
	}

	public void popAll() {
	head = null;
	}

	public static void main(String[] args){
		StringStack myStack = new StringStack();
		myStack.push("cscs");
		System.out.println(myStack.peek());
		myStack.push("1");
		System.out.println(myStack.peek());
		myStack.push("2");
		System.out.println(myStack.peek());
		myStack.push("3");
		System.out.println(myStack.peek());
		myStack.push("4");
		System.out.println(myStack.peek());
		myStack.push("5");
		System.out.println(myStack.peek());
		try{
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		System.out.println(myStack.pop());
		} 
		catch(StackEmptyException e){
			System.out.println(e);
		}
	}
}
