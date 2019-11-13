public class Scopes {
	public static void main (String[] args) {
		int x=5;
		System.out.println("x is " + x);
		Scopes obj= new Scopes();
		int y=obj.printNumbers();
		System.out.println("x is " + y);
	}
	
	public int printNumbers() {
		int x =2;
		System.out.println("x is " + x);
		x*= 4;
		System.out.println("x is " + x);
		return x;
	}
}