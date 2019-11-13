/*
* Name: Swapnil Daxini
* ID: V00861672
* Date: 5/15/2016
* Filename: BingoCalls.java
* Details: CSC115 Assignment 1
*/
public class BingoCalls {
	private BingoBall[] arr;
	private int counter=0; //variable to keep track of number of balls inserted
	
/**
*Constructor for the array of BingoBall objects.
*/
	public BingoCalls() {
		arr= new BingoBall[5];
	}

/**
*Adds a bingo ball to the array of BingoBall objects. If array is full,
*calls the private method arraySize to double the size of the array.
*/	
	public void insert(BingoBall ball){
		if(counter>=arr.length){
			arraySize();
		}
		arr[counter]= ball;
		counter++;
	}

/**
*Private method used to double the size of the array.
*@param tempArr Temporary array that is double the size of original array.
*/	
	private void arraySize(){
		BingoBall[] tempArr= new BingoBall[arr.length*2];
		for(int i=0; i<arr.length; i++) {
			tempArr[i]=arr[i];
		}
		arr= tempArr;
	}

/**
*@return int Returns the numbers of balls inserted into array.
*/	
	public int numBallsCalled(){
		return counter;
	}

/**
*Removes a ball, if it was called, and re sorts the array in a sequential order.
*@param ballPosition Keeps track of the index of the ball to be removed.
*/	
	public void remove(BingoBall ball){
		if(wasCalled(ball)){
			int ballPosition=0;
			for(int i=0; i<counter; i++){
				if(ball.getValue()==arr[i].getValue()){
					arr[i]=null;
					ballPosition=i;
				}
			}
			for(int i=ballPosition; i<counter-1; i++){
				arr[i]=arr[i+1];
			}
			arr[counter-1]=null;
			counter--;
		}
	}

/**
*Checks if a ball passed into the method was previously called.
*@return boolean
*/	
	public boolean wasCalled(BingoBall ball){
		for(int i=0; i<counter; i++){
			if(ball.getValue()==arr[i].getValue()){
				return true;
			}
		}
		return false;
	}

/**
*Empties the array of BingoBall objects and resets the counter to zero.
*/	
	public void makeEmpty(){
		for(int i=0; i<arr.length; i++){
			arr[i]=null;
		}
		counter=0;
	}

/**
*Formats a string representing the container of called BingoBalls. 
*If there are no balls in the container, then the string is "{}". 
*If there are balls, then they are formated inside the curly braces, 
*with a comma followed by a space between each ball. For example: "{B14, I16, O75, G50}"
*@return String
*/	
	public java.lang.String toString(){
		String s= "{";
		if(counter!=0){
			for(int i=0; i<counter-1; i++){
			s= s + arr[i] +", ";
			}
			s= s + arr[counter-1] + "}";
		} else{
			s= s + "}";
		}
		return s;
	}

/**
*Used primarily as a unit tester for the class.
*/	
	public static void main(java.lang.String[] args){
		BingoCalls game1= new BingoCalls(); //Tests constructor
		BingoBall a = new BingoBall(42);
		BingoBall b = new BingoBall(8);
		BingoBall c = new BingoBall(48);
		BingoBall d = new BingoBall(74);
		BingoBall e = new BingoBall(2);
		BingoBall f = new BingoBall(22); //more than five balls inserted to test arraySize method
		BingoBall g = new BingoBall(75);
		BingoBall h = new BingoBall(32);
		game1.insert(a);
		game1.insert(b);
		game1.insert(c);
		game1.insert(d);
		game1.insert(e);
		game1.insert(f);
		game1.insert(g);
		System.out.println(game1);
		game1.remove(b); 
		System.out.println(game1.wasCalled(c));
		System.out.println(game1.wasCalled(h));
		System.out.println(game1);
		System.out.println(game1.numBallsCalled());
		game1.makeEmpty();
		System.out.println(game1);
		BingoBall i = new BingoBall(68);
		game1.insert(i);
		System.out.println(game1);
	}
}