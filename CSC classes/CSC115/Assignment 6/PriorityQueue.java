/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 24/07/2016
* Details: CSC115 Assignment 6
*/

import java.util.NoSuchElementException;

public class PriorityQueue<E extends Comparable<E>> {
	
	private Heap<E>  heap;

	/**Initializes the heap
	*/	
	public PriorityQueue() {
		heap= new Heap<E>();
	}

	/**Inserts a new item to the queue using heap class methods
	*/
 	public void enqueue(E item){
		heap.insert(item);
	}
	
	/**Removes the root (first in line) item from the queue using heap class methods
	* @return E item removed
	*/
	public E dequeue(){
		return heap.removeRootItem();
	}
	
	/**Returns the root (first in line) item from the queue using heap class methods
	* @return E item
	*/
	public E peek(){
		return heap.getRootItem();
	}
	
	/**Checks whether queue is empty
	* @return true if empty, false otherwise.
	*/
	public boolean isEmpty(){
		return heap.isEmpty();
	}

	/**Internal testing
	* @param args Not used.
	*/
	public static void main(String[] args){
		ER_Patient John = new ER_Patient("10:01", "Chronic");
		ER_Patient Mary = new ER_Patient("10:06", "Walk-in");
		ER_Patient Tom = new ER_Patient("10:15", "Life-threatening");
		ER_Patient Throckmorten = new ER_Patient("10:07", "Major fracture");
		
		PriorityQueue<ER_Patient> line = new PriorityQueue<ER_Patient>();
		
		line.enqueue(John);
		line.enqueue(Mary);
		
		System.out.println("Next in line is " + line.peek());
		
		line.enqueue(Tom);
		
		System.out.println("Are any patients attended to? " + line.isEmpty());
		
		line.enqueue(Throckmorten);
		
		for(int i=0; i<4; i++){
			System.out.println("The next patient is " + line.dequeue());
		}
		
		System.out.println("Are any patients attended to? " + line.isEmpty());
	}
}
	
