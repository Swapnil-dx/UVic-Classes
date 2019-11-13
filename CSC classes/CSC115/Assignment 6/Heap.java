/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 24/07/2016
* Details: CSC115 Assignment 6
*/

import java.util.NoSuchElementException;
import java.util.Vector;


public class Heap<E extends Comparable<E>> {

	private Vector<E> heapArray;
	
	/** Intializes Vector heapArray and sets 1st element to be null
	*/
	public Heap() {
		heapArray = new Vector<E>();
		heapArray.add(null);
	}
 
	/**Checks whether heapArray is empty
	* @return true if empty, false otherwise.
	*/ 
 	public boolean isEmpty(){
		return heapArray.size()==1;
	}

	/** @return int size of vector
	*/	
	public int size(){
		return heapArray.size()-1;
	}
	
	/** Inserts a new item into the heap and adjusts the heap to maintain the order
	* @param item to be inserted
	*/	
	public void insert(E item){
		if(heapArray.isEmpty()){
			heapArray.add(item);
		} else{
			heapArray.add(item);
			int nodeIndex = size();
			int parentIndex = nodeIndex/2;
			while(parentIndex!=0){
				if(item.compareTo(heapArray.get(parentIndex))<0){
					swap(nodeIndex, parentIndex);
				}
				nodeIndex = parentIndex;
				parentIndex = parentIndex/2;
			}

		}
	}
	
	/** Method to swap 2 items at the indexes given
	* @param int indexes to be switched 
	*/	
	void swap(int a, int b){
		E temp = heapArray.get(a);
		heapArray.set(a, heapArray.get(b));
		heapArray.set(b, temp);
	}
	
	/** Removes the root item from the heap and adjusts the heap to maintain the order
	* @return item to be removed
	* @throws NoSuchElementException when heap is empty.
	*/	
	public E removeRootItem(){
		//get the root item
		E rootItem = null;
		
		//replace it with the current root node
		if (isEmpty()){
			throw new java.util.NoSuchElementException();
		}
		else if(!isEmpty()){
			rootItem = heapArray.get(1);
			int loc = size();
			//replace it with last element
			heapArray.set(1, heapArray.get(loc));
			
			heapArray.remove(loc);
			//Call Trickledown with the current root node
			TrickleDown(1);
		}
		return rootItem;
	}
	
	/** Private recursive method to arrange the array once the root is removed.
	* @param nodeIndex to start process
	*/	
	private void TrickleDown(int nodeIndex){
		int nodeSmallChild;
		
		if(2*nodeIndex > size()){
			return;
		} else if(2*nodeIndex+1> size()){
			nodeSmallChild= 2*nodeIndex;
		} else{
			if(heapArray.get(2*nodeIndex).compareTo(heapArray.get(2*nodeIndex+1))<0){
				nodeSmallChild = 2*nodeIndex;
			} else{
				nodeSmallChild = 2*nodeIndex+1;
			}
		}
		
		if(heapArray.get(nodeSmallChild).compareTo(heapArray.get(nodeIndex))<0){
			swap(nodeIndex, nodeSmallChild);
		}
		
		TrickleDown(nodeSmallChild);	
	}
	
	/** @return E root item
	*/
	public E getRootItem(){
		return heapArray.get(1);
	}
	
	/** Prints out array
	*/
 	public String toString() {
	 	String s = heapArray.toString();
		s = "[" + s.substring(7);
		return s;
 	}
	
	/** Used for internal testing
	* @param args Not used
	*/
	public static void main(String[] args) {
		Heap<Integer> testHeap = new Heap<Integer>();
		
		System.out.println("Is the heap empty? " + testHeap.isEmpty());
		
		testHeap.insert(4);
		testHeap.insert(6);
		testHeap.insert(2);
		
		System.out.println("Is the heap empty? " + testHeap.isEmpty());
		
		testHeap.insert(8);
		testHeap.insert(5);
		
		System.out.println("Root is " + testHeap.getRootItem());
		
		testHeap.insert(9);
		testHeap.insert(1);
		
		System.out.println("Root is " + testHeap.getRootItem());
		
		testHeap.insert(3);
		
		System.out.println(testHeap);
		
		System.out.println("Size: " + testHeap.size());
		
		testHeap.removeRootItem();
		int removed = testHeap.removeRootItem();
		
		System.out.println("The last removed integer was " + removed);
		
		System.out.println(testHeap);
		
	}
}
