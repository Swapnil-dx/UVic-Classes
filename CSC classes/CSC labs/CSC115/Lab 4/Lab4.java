public class Lab4{
	public static void main(String args[]){
		printDownBy2(7);
		printDownBy2(12);
	}
	
	public static void printDownBy2(int n){
		if (n<=2){
			System.out.println(n);
			return;
		}
		System.out.print(n+" ");
		printDownBy2(n-2);
	}
}