public class OnlineBank {
	//INSTANCE VARIABLES:
	private String accountName;
	private double savings;
	private static double transactionFee = 2.50;
	
	//CONSTRUCTORS:
	public OnlineBank(String accountName) {
		this.accountName = accountName;
		savings = 500.0;
	}
	
	public OnlineBank(String accountName, double amount) {
		this.accountName = accountName;
		savings = amount;
	}
	
	//INSTANCE METHODS:
	public String getAccountName() {
		return accountName;
	}
	
	public double getSavings() {
		return savings;	
	}
	
	public void deposit(double amount) {
		if (amount > 0) {
			savings += (amount - transactionFee);
		} else {
			System.out.println("Must deposit a positive amount of money");	
		}
	}
	
	public void withdraw(double amount) {
		savings -= (amount + transactionFee);
	}
	
	public static void changeFee(double newFee) {
		transactionFee = newFee;
	}
	
	//TOSTRING:
	public String toString() {
		String details = "Account Holder: " + accountName + "\n";
		details = details +"Avaible funds: $" + savings + "\n";
		return details;	
	}
}