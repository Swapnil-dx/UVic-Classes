/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 6/12/2016
* Filename: StringEmptyException.java
* Details: CSC115 Assignment 3
*/

/**
 * An exception thrown when trying to retreive the top item from an empty stack
 */ 
public class StackEmptyException extends RuntimeException {

	/**
	 * Creates an exception.
	 * @param msg The message to the calling program.
	 */
	public StackEmptyException(String msg) {
		super(msg);
	}

	/**
	 * Creates an exception without a message.
	 */
	public StackEmptyException() {
		super();
	}
}
