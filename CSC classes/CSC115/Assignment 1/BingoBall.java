/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 5/15/2016
* Filename: BingoBall.java
* Details: CSC115 Assignment 1
*/
public class BingoBall {
	private static char[] bingo = {'B','I','N','G','O'};

	private int number;
	private char letter;

/**
*Constructor Creates a BingoBall with the given value. The appropriate letter is assigned.
*@throws java.lang.IllegalArgumentException - if the value is not between 1 and 75.
*/	
	public BingoBall(int value) {
		if (value > 0 && value < 76) {
			number= value;
			setLetter();
		} else {
			throw new IllegalArgumentException("number must be between 1 and 75; it was "+value);
		}
	}

/**
*Chooses a letter depending on the value of the ball
*/	
	private void setLetter() {
		if(number<16){
			letter='B';
		} else if(number<31){
			letter='I';
		} else if(number<46){
			letter='N';
		} else if(number<61){
			letter='G';
		} else{
			letter='O';
		}
	}

/**
*@return int the number value of the BingoBall.
*/		
	public int getValue() {
		return number;
	}

/**
*@return the letter value of the BingoBall.
*/	
	public char getLetter() {
		return letter; 
	}

/**
*Updates the number value of the BingoBall. The letter is automatically adjusted to the new value.
*@param value - The new value.
*@throws java.lang.IllegalArgumentException - if the value is not between 1 and 75.
*/	
	public void setValue(int value) {
		if (value > 0 && value < 76) {
			number= value;
			setLetter();
		} else {
			throw new IllegalArgumentException("number must be between 1 and 75; it was "+value);
		}
	}

/**
*Determines if this BingoBall is equivalent to another BingoBall. 
*Two BingoBalls are equivalent if their number value is the same.
*@param other - The BingoBall that is compared to this one.
*@return True if the balls are equivalent.
*/	
	public boolean equals(BingoBall other) {
		if(number==other.getValue()){
			return true;
		}
		return false;
	}

/**
*@return a String representation of the ball as a letter immediately followed by an integer. 
*/	
	public java.lang.String toString() {
		return letter+""+number;
	}

/**
*Used mainly as a unit tester for the class. 
*/	
	public static void main(java.lang.String[] args) {
		BingoBall b = new BingoBall(42);
		System.out.println("Created a BingoBall: "+b);
		System.out.println("The number is "+b.getValue());
		System.out.println("The letter is "+b.getLetter());
		BingoBall c = null;
		try {
			c = new BingoBall(76);
		} catch (Exception e) {
			System.out.println("Correctly caught the exception");
		}
		System.out.println("Created a second BingoBall: "+c);
		c = new BingoBall(14);
		if (!b.equals(c)) {
			System.out.println("The two balls are not equivalent");
		}
		c.setValue(42);
		System.out.println("The second ball has been changed to "+c);
		if (b.equals(c)) {
			System.out.println("They are now equivalent");
		}
		c.setValue(74);
		System.out.println("The second bingo ball has been changed to "+c);
	}
}
