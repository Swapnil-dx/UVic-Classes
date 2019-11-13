public class NewMethod {
	int a=8;
	int x=16;
	int y=4;
	int z=x/y;
	int c;
	public static void	main(String[] args) {
		int a=8;
		int x=16;
		int y=4;
		int z=x/y;
		int d;
		NewMethod obj=new NewMethod();
		d=obj.add();
		System.out.println("My name is Swapnil Daxini");
		System.out.println("My favourite number is "+a);
		System.out.println(x +" divided by "+y+" is "+ z);
		System.out.println(d);
	}
	public int add(){
		c=x+y;
		System.out.println("Sum is "+c);
		return c;
	}
}