/**
 * CSC115 Assignment 2 : Containers
 * MedListArrayBased.java
 * Created for use by CSC115 Summer2016 
 */

import java.util.NoSuchElementException;

/*
 * NOTE TO PROGRAMMER:
 * Do not alter this file.
 * It's purpose is to provide a comparison between a List 
 * that uses an array and a list that uses a linked data structure.
 */

/*
 * The MedListArrayBased is a list that uses an array as
 * the storage for its Medications.
 */
public class StringList implements List<String> {

	private String[] storage;
	private int count;
	private static final int INITIAL_SIZE=10;

	public StringList() {
		storage = new String[INITIAL_SIZE];
		storage[0] = "interface";
		storage[1] = "array";
		storage[2] = "linked-list";
		storage[3] = "generics";
		storage[4] = "csc115";
		storage[5] = "computers";
		count = 6;
	}

	private void growAndCopy() {
		String[] newStorage = new String[storage.length*2];
		// Move all the integers from storage into newStorage
		for (int i=0; i<count; i++) {
			newStorage[i] = storage[i];
		}
		storage = newStorage;
	}

	public void add(String k,int index) {
		if (index < 0 || index > count) {
			throw new IndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		if (count==storage.length) {
			growAndCopy();
		}
		for (int i=count; i>index; i--) {
			storage[i] = storage[i-1];
		}
		storage[index] = k;
		count++;
	}

	public String get(int index) {
		if (index < 0 || index > count-1) {
			throw new IndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		return storage[index];
	}

	public boolean isEmpty() {
		return count==0;
	}

	public int size() {
		return count;
	}

	public int indexOf(String item) {
		for (int i=0; i<count; i++) {
			if (storage[i].equals(item)) {
				return i;
			}
		}		
		return -1;
	}

	public void remove(int index) {
		if (index < 0 || index > count-1) {
			throw new IndexOutOfBoundsException("The index "+index+" is out of bounds.");
		}
		for (int i=index; i<count-1; i++) {
			storage[i] = storage[i+1];;
		}
		count--;
	}

	public void remove(String item) {
		for (int i=0; i<count; i++) {
			if (storage[i].equals(item)) {
				for (int j=i; j<count-1; j++) {
					storage[j] = storage[j+1];
				}
				count--;
				i--;
			}
		}
	}

	public void removeAll() {
		storage = new String[INITIAL_SIZE];
		count = 0;
	}

	public String toString() {
		if (count == 0) return "{}";
		if (count == 1) return "{"+storage[0]+"}";
		String details = "{";
		for (int i=0; i<count-1; i++) {
			details = details + storage[i] + ", ";
		}
		details = details+storage[count-1]+"}";
		return details;
	}

}
