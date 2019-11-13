/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 6/26/2016
* Filename: CellDeque.java
* Details: CSC115 Assignment 4
*/
public class CellDeque{
	private CellNode head;
	private CellNode tail;
	private int count;
	
	/* Intializes the variables
	*/
	public CellDeque(){
		head = null;
		tail = null;
		count = 0;
	}
	
	/* Inserts a cell at the start of Deque. 
	* @param Cell to be inserted
	*/
	public void insertFirst(Cell p){
		CellNode temp = new CellNode(p);
		if (isEmpty()){
			head = temp;
			tail = temp;
		} else{
			temp.next = head;
			head.prev = temp;
			head = temp;
		}
		
		count++;
	}
	
	/*Inserts a cell at the end of the Deque.
	* @param Cell to be inserted.
	*/
	public void insertLast(Cell p){
		CellNode temp= new CellNode(p);
		if (head==null && tail == null){
			head = temp;
			tail = temp;
		} else{
			tail.next= temp;
			temp.prev= tail;
			tail= temp;
		}
		count++;
	}
	
	/*
	Removes first cell from the deque. 
	@throws DequeEmptyException when deque is empty.
	*/
	public Cell removeFirst(){
		if(isEmpty()){
			throw new DequeEmptyException("The deque is empty");
		}
		if(count==1){
			Cell first= this.first();
			head=null;
			tail=null;
			count=0;
			return first;
		} else{
			Cell first= this.first();
			head=head.next;
			head.prev=null;
			count--;
			return first;
		}
	}
	
	/*
	Removes last cell from the deque.
	@throws DequeEmptyException when deque is empty
	@return Cell
	*/
	public Cell removeLast(){
		if(isEmpty()){
			throw new DequeEmptyException("The deque is empty");
		}
		if(count==1){
			Cell last= this.last();
			tail=null;
			head=null;
			count=0;
			return last;
		} else{
			Cell last= this.last();
			tail= tail.prev;
			tail.next= null;
			count--;
			return last;
		}
	}
	
	/*
	Returns the first cell of the deque without removing it
	*/
	public Cell first(){
		if(isEmpty()){
			throw new DequeEmptyException("The deque is empty");
		}
		return head.item;
	}
	
	/*
	Returns the last cell of the deque without removing it
	*/
	public Cell last(){
		if(isEmpty()){
			throw new DequeEmptyException("The deque is empty");
		}
		return tail.item;
	}
	
	/*
	Checks if the deque is empty.
	@return true if empty, false otherwise.
	@return true if empty, false otherwise.
	*/
	public boolean isEmpty(){
		if(head==null && tail==null){
			return true;
		} else{
			return false;
		}
	}
	
	/*
	Empties the deque.
	*/
	public void makeEmpty(){
		head=null;
		tail=null;
		count=0;
	}
	
	/*
	Main method for testing.
	*/
	public static void main(String[] args){
		Cell a = new Cell(1,2);
		Cell b = new Cell(1,1);
		Cell c = new Cell(2,1);
		Cell d = new Cell(2,2);
		Cell e = new Cell(3,2);
		CellDeque pathList = new CellDeque();
		
		pathList.insertFirst(a);
		pathList.insertFirst(b);
		pathList.insertFirst(c);
		pathList.insertFirst(e);
		
		pathList.insertFirst(d);
		Cell first= pathList.first();
		System.out.println(first);
		Cell last= pathList.last();
		System.out.println(last);


		
		Cell removed= pathList.removeFirst();
		System.out.println(removed);
		
		System.out.println(pathList.isEmpty());
		pathList.makeEmpty();
		System.out.println(pathList.isEmpty());
		
	}
}