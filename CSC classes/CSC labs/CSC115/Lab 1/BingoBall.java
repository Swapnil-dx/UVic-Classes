public class BingoBall {

// NOTE TO STUDENT
// We challenge you to use this constant array as a lookup table to convert a number
// value to its appropriate letter.
// HINT: It can be done with with a single expression that converts the number
// 	to an index position in the following array.

	private static char[] bingo = {'B','I','N','G','O'};

	private int number;
	private char letter;
// NOTE TO STUDENT
// This constructor is partially done for you.
// This example is meant to demonstrate the use of Exception Handling in Java.
	public BingoBall(int value) {
		if (value > 0 && value < 76) {
			number= value;
			setLetter();
		} else {
			throw new IllegalArgumentException("number must be between 1 and 75; it was "+value);
		}
	}

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
		
	public int getValue() {
		return number;
	}

	public char getLetter() {
		return letter; 
	}

	public void setValue(int value) {
		number=value;
	}

	public boolean equals(BingoBall other) {
		if(number==other.getValue()){
			return true;
		}
		return false;
	}

	public String toString() {
		return letter+""+number;
	}

// NOTE TO STUDENT:
// The following code is provided as an example of the testing
// that is required in each class that is created.
// Since this testing is done internally, any private methods you
// create can also be tested. 
	public static void main(String[] args) {
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
