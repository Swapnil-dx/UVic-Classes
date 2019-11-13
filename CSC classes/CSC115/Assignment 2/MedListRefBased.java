/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 5/29/2016
* Filename: MedListRefBased.java
* Details: CSC115 Assignment 2
*/

import java.util.NoSuchElementException;

public class MedListRefBased implements List<Medication> {

	private MedicationNode head;
	private MedicationNode tail;
	private int count;

	public MedListRefBased() {
		head = null;
		tail = null;
		count = 0;
	}
	
	public void add(Medication k,int index) {
			MedicationNode addNode= new MedicationNode(k);
			if (index < 0 || index > count) {
				throw new IndexOutOfBoundsException("The index "+index+" is out of bounds.");
			} else if(count==0){		//first element
				head= addNode;
				tail= addNode;
			} else if(count == index){	//Last element
				tail.next = addNode;
				addNode.prev=tail;
				tail=tail.next;
			} else {
				MedicationNode curr= head;
				for(int i=0; i<index; i++){
					curr=curr.next;
				}
				addNode.next= curr;
				addNode.prev=curr.prev;
				curr.prev=addNode;
				curr=addNode.prev;
				curr.next=addNode;
			}
			count++;
	}

	public Medication get(int index) {
		MedicationNode curr=head;
		if((index<0 || index>count)|| this.head==null){
			throw new IndexOutOfBoundsException("The index "+index+" is out of bounds.");
		} else{
			for(int i=0; i<index; i++){
				curr=curr.next;
			}
		}
		return curr.item;
	}

	public boolean isEmpty() {
		if(head==null){
			return true;
		} else {
			return false;
		}	

	}

	public int size() {
		return count;
	}

	public int indexOf(Medication item) {
		int index=0;
		MedicationNode curr= head;
		for(int i=0; i<count; i++){
			if((curr.item).equals(item)){
				return index;
			}
			curr=curr.next;
			index++;
		}
		return -1; //not found the medication
	}

	public void remove(int index) {	
		if (index < 0 || index > count) {
			throw new IndexOutOfBoundsException("The index "+index+" is out of bounds.");
		} else if(index==0){
			head= head.next;
			head.prev= null;
		} else if(index==count-1){
			tail=tail.prev;
			tail.next=null;
		} else{
			MedicationNode curr= head;
			
			for(int i=0; i<index-1; i++){
				curr=curr.next;
			}
			
			curr.next=curr.next.next;
			curr=curr.next;
			curr.prev=curr.prev.prev;
		}
		count--;
	}

	public void remove(Medication item) {
		MedicationNode curr= head;
		for(int i=0; i<count; i++){
			if((curr.item).equals(item)){
				this.remove(i);
				i--; //all elements shifted to the left
			}
			curr=curr.next;
		}
	}

	public void removeAll() {
		head=null;
		tail=null;
		count=0;
	}

	public String toString() {
		StringBuilder details = new StringBuilder(count*10);
		MedicationNode temp= head;
		details.append("List: {\n");
		while(temp!=null){
			details.append("\t" + temp.item+"\n");
			temp=temp.next;
		}
		details.append("}");
		return details.toString();
	}

	public static void main(String[] args) {
		Medication a= new Medication("tyenol", 500);
		Medication b= new Medication("advil", 200);
		Medication c= new Medication("med3", 200);
		
		MedListRefBased test= new MedListRefBased();
		test.add(a,0);
		test.add(b,1);
		test.add(b,2);
		test.add(c,3);
		test.add(b,4);
		System.out.println(test);
		System.out.println("The medication at index 0 is "+test.get(0));
		System.out.println("med3 is at index "+test.indexOf(c));
		System.out.println("Size of list: "+test.size());
		test.remove(b);
		System.out.println(test);
		System.out.println("Size of list: "+test.size());
		System.out.println("Is the list empty? "+test.isEmpty());
		test.remove(1);
		System.out.println(test);
		test.removeAll();
		System.out.println(test);
		System.out.println("Is the list empty? "+test.isEmpty());
	}
}
