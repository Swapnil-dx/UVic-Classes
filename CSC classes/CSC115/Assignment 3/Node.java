/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 6/12/2016
* Filename: Node.java
* Details: CSC115 Assignment 3
*/

public class Node{
	String item;
	Node next;
	
	public Node(String item){
		this.item=item;
		next= null;
	}
	
	public Node(String item, Node next){
		this.item=item;
		this.next=next;
	}
}