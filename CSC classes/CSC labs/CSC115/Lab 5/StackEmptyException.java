/**
 * An exception thrown when trying to retrieve the top item from an empty stack.
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
