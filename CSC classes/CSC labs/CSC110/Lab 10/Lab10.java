public class Lab10 {
	public static void main(String[] args) {
		int[] arr= {8,5,4,3,0,1,567,34,56,2,-8};
		printArray(arr);
		sortSelection(arr);
	}
	
	public static int findMin(int[] arr) {
		int min= arr[0];
		int index=0;
		for(int i=0; i<arr.length; i++) {
			if(arr[i]<min){
				min=arr[i];
				index= i;
			}
		}
		return min;
	}
	
	public static int findMinwithIndex(int[] arr, int start) {
		int min= arr[start];
		int index=start;
		for(int i=start; i<arr.length; i++) {
			if(arr[i]<min){
				min=arr[i];
				index= i;
			}
		}
		return index;
	}
	
	public static void swap(int[] arr, int pos1, int pos2){
		int buffer= arr[pos1];
		arr[pos1]=arr[pos2];
		arr[pos2]=buffer;
	}
	
	public static void printArray(int[] arr){
		for(int i=0; i<arr.length-1; i++) {
			System.out.print(arr[i] + (", "));
		}
		System.out.println(arr[arr.length-1]);
	}
	
	public static void sortSelection(int[] arr) {
		for(int i=0; i<arr.length; i++){
			int minIndex= findMinwithIndex(arr, i);
			swap(arr,i,minIndex);
		}
		printArray(arr);
	}
}