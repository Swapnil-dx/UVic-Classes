public class Parameters1 {
	public static void main( String[] args){
		int x =7;
		System.out.println("x is " + x);
		printNumbers(2);
		System.out.println("x is " + x);
		printNumbers(x);
		System.out.println("x is " + x);
	}
	
	public static void printNumbers (int x) {
		System.out.println("x is " + x);
		x*=4;
		System.out.println("x is " + x);
	}
}