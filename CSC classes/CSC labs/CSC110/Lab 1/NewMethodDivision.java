public class NewMethodDivision {
	int a=8;
	int x=16;
	int y=4;
	int z=x/y;
	int c;
	double g;
	double e=3.14;
	double f=16.5;
	public static void	main(String[] args) {
		int a=8;
		int x=16;
		int y=4;
		int z=x/y;
		NewMethodDivision obj=new NewMethodDivision();
		double d=obj.division();
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
	public double division(){
		g=f/e;
		return g;
	}
}