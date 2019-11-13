public class FirstCalculator{
	public static void main(String[] args){
		int[] arr= {1,6,2,8,3,4};
		printSub(arr);
	}
	
	public static void printSub(int[] arr){
		for(int i=0;i<arr.length;i++){
			int minPos= i;
			int j=i+1;
			while(j<arr.length){
				if(arr[j]<arr[minPos]){
					minPos=j;
				}
				j++;
			}
			if(minPos!=i){
				int temp=arr[i];
				arr[i]=arr[minPos];
				arr[minPos]=temp;

				System.out.println("swapping");
				for(int k=0; k<arr.length;k++){
				System.out.print(arr[k]+",");
				}
				System.out.println();
			}
			for(int k=0; k<arr.length;k++){
				System.out.print(arr[k]+",");
			}
			System.out.println();
		}
		
	}
}