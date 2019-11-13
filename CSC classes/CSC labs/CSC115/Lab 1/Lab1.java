public class Lab1{
	public static void main(String[] args){
		int[] nums= {7, 3, 4, 8, 5};
		printArray(nums);
		int sumOdds= sumOdds(nums);
		System.out.println("Sum of odds in array= " + sumOdds);
	}
	
	public static void printArray(int[] x){
		for(int i=0; i<x.length-1; i++){
			System.out.print(x[i] + ", ");
		}
		System.out.println(x[x.length-1]);
	}
	
	public static int sumOdds(int[] x){
		int sum=0;
		for(int i=0; i<x.length; i++){
			int temp= x[i];
			if((temp%2)==1){
				sum+=x[i];
			}
		}
		return sum;
	}
}