public class BankTester {	

	public static void main(String[] args) {
		/*	There are 4 parts to this lab activity, after you have finished one part,
			comment it out, and move on to the next part (by uncommenting it) */
		//partOne();
		//partTwo();
		//partThree();
		//partFour();
		partFive();
	}
	
	/*
		Question 1 - Object code tracing:
		What is the output of the partOne() method call?
	 	Hint: To trace the code we might have to look inside the OnlineBank.java file
	*/	
	public static void partOne() {
		OnlineBank account1 = new OnlineBank("John", 800.00);
		System.out.println(account1);
		
		String who = account1.getAccountName();
		System.out.println("Account id: " + who);
	}	
	
	/*
		Question 2 - changing how objects are output: 
		Change the OnlineBank class so instead of the output being: 
		<Name> currently has $<amount> in our system.
			
		It is changed so that the output is:
		Account holder: <Name>
		Available funds: $<amount>
	*/
	public static void partTwo() {
		OnlineBank account1 = new OnlineBank("John", 800.00);
		System.out.println(account1);	
	}	
	
	/*
		Question 3 - changing instance variable values: 
		How can we make it so that Jane's account (account2)
		has $500.00 in it? (There is more than one right answer)
	*/
	public static void partThree() {
		OnlineBank account2 = new OnlineBank("Jane");
		System.out.println(account2);
	}
	
	/*
		Question 5 - using arrays of Objects: 
		Create more OnlineBank objects and fill up the remaining spots in the array.
		Then, print out which accounts have less than $500.00 of savings.
	*/
	public static void partFour() {
		OnlineBank[] bankArray = new OnlineBank[5];
		OnlineBank account1 = new OnlineBank("John", 200.00);	
		OnlineBank account2 = new OnlineBank("Tibor", 1101.15);
		OnlineBank account3 = new OnlineBank("Bob", 2025.80);
		OnlineBank account4 = new OnlineBank("Mary", 202.80);
		OnlineBank account5 = new OnlineBank("Ilyas", 10.0);
		bankArray[0]= account1;
		bankArray[1]= account2;
		bankArray[2]= account3;
		bankArray[3]= account4;
		bankArray[4]= account5;
		
		System.out.println("Printing out information for first year courses in the array:");
		for (int i = 0; i < bankArray.length; i++) {
			if(bankArray[i].getSavings()<500) {
				System.out.println(bankArray[i].getAccountName());
			}	
		}
	}
	
	/*
		Question 6 - static variables:
		What is the output of the partFive() method call?
	*/
	public static void partFive() {
		OnlineBank account1 = new OnlineBank("John", 100);
		OnlineBank account2 = new OnlineBank("Jane", 500);
		System.out.println(account1);
		
		account1.deposit(100);
		System.out.println(account2);
		System.out.println(account1);
		
		OnlineBank.changeFee(5.00);
		System.out.println(account1);
		
		account1.deposit(10);
		account2.deposit(100);
		System.out.println(account1);
		System.out.println(account2);
	}
}