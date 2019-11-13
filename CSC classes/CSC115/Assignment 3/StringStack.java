/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 6/12/2016
* Filename: StringStack.java
* Details: CSC115 Assignment 3
*/

public class StringStack {

	private Node head;

	public StringStack(){
		head = null;
	}
	
	/**
	 * Checks if the stack is empty
	 * @return True if empty, false if not.
	 */
	public boolean isEmpty() {
		if(head == null){
			return true;
		} 
		return false;
	
	}
	
	/**
	 * Returns the item on top of the stack and removes item from stack.
	 * @return String item
	 * @throws StackEmptyException if the stack is empty
	 */
	public String pop() {
		if(head==null){
			throw new StackEmptyException("Can't pop from an empty stack");
		}
		String item = head.item;
		head = head.next;
		return item;
	}
	
	/**
	 * Checks and returns the item on top of stack but doesnt not remove it.
	 * @return String item
	 * @throws StackEmptyException if the stack is empty
	 */
	public String peek() {
		if(isEmpty()){
			throw new StackEmptyException("Can't peek from an empty stack");
		}
		return head.item;
	}
	
	/**
	 * Adds the item on top of the stack.
	 * @param String item
	 */
	public void push(String item) {
		Node temp = new Node(item, head);
		head = temp;
	}
	
	/**
	 * Removes all items from stack.
	 */
	public void popAll() {
		head = null;
	}
	
	public static void main(String args[]){
		StringStack myStack= new StringStack();
		System.out.println(myStack.isEmpty());
		myStack.push("Lab");
		myStack.push("CSC");
		myStack.push("Hello");
		myStack.push("Why");
		System.out.println(myStack.peek());
		myStack.pop();
		System.out.println(myStack.peek());
		System.out.println(myStack.isEmpty());
		myStack.popAll();
		System.out.println(myStack.isEmpty());
		try{
			myStack.pop();
		} catch (StackEmptyException e){
			System.out.println(e);
		}
		
	}
}
