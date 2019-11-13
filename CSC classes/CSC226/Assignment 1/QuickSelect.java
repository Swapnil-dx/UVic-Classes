/**
 *
 * @author Rahnuma Islam Nishat, edited by Swapnil Daxini
 * September 20, 2017
 * CSC 226 - Fall 2017
 */
public class QuickSelect {
	/* Variation of QuickSort used to find the kth smallest integer in an
	array in linear time.
	@param Takes in an arr and the int k.
	@return Index of kth smallest integer in the array
	*/
    public static int QuickSelect(int[] arr, int k){
        if(k > arr.length || k <= 0){
			return -1;
		}
		
		return QuickSelect(arr, 0, arr.length-1 , k);
    }
	
	/* Recursive helper function used find the kth smallest integer in
	an array.
	@param Takes in an array with boundary indices and k.
	@return Index of kth smallest integer in the array
	*/
    private static int QuickSelect(int[] arr, int lo, int hi, int k){
        if(hi <= lo) return arr[lo];
		
		int p = pickPivot(arr, lo, hi);
		int j = partition(arr, lo, hi, p);
		
		if(k <= j){
			return QuickSelect(arr, lo, j-1, k);
		} else if (k == j+1){
			return arr[j];
		} else {
			return QuickSelect(arr, j+1, hi, k);
		}
    }
	
	/* Helper function which partitions the array based on a given pivot.
	@param Takes in an array and its index boundaries, as well as the pivot.
	@return Index of pivot which partitions the array
	*/
	private static int partition(int[] arr, int lo, int hi, int p) {
		swap(arr, p, lo);
		int i = lo, j = hi + 1;
		int curr = arr[lo];
		
		while(true) {
			i++;
			
			while(arr[i] <= curr) {
				if(i == hi) break;
				i++;
			}
	    
			j--;
			
			while(arr[j] >= curr) {
				if(j == lo) break;
				j--;
			}
	    
			if(i >= j) {
				break;
			}
			swap(arr, i, j);
		}
		
		swap(arr, lo, j); 
		return j;  
    }
    
	/* Helper function used to find the median of medians of an array.
	Splits the array into subgroups of 5 and recursively find the median
	@param Takes in an array and the index boundaries to consider
	*/
	private static int pickPivot(int[] A, int lo, int hi){
		int[] arr = new int[A.length-1];
		for(int i = 0;  i < arr.length; i++){
			arr[i] = A[i];
		}
		
		if((hi - lo) < 5){
			return findMedian(arr, lo, hi);
		}
		
		int index = lo;
		for(int i = lo; i < hi; i += 5){
			int rightEnd = i + 4;
			
			if(rightEnd > hi){
				rightEnd = hi;
			}
			
			int median = findMedian(arr, i, rightEnd);
			swap(arr, median, index);
			index++;
		}
		
		return pickPivot(arr, lo, (lo + (int)Math.ceil((hi-lo)/5)));
	}
	
	/* Helper function used to find the median of an array. It uses insertion
	sort to sort the array then find the index of median.
	@param Takes in an array and the index boundaries.
	@return Returns the index of the median.
	*/
	private static int findMedian(int[] arr, int lo, int hi){
		for(int i = lo + 1; i < hi; i++) {
			int temp = arr[i];
			int j = lo;
			
			while((j >= lo) && (arr[j] > temp)) {
				arr[j+1] = arr[j];
				j--;
			}
			arr[j+1] = temp;
		}
		
		int median = (int)Math.ceil((hi - lo)/2);
		return lo + median;
	}
	/* Helper function used to swap two elements
	@param Takes in an array and the two indices to be swapped
	*/
	private static void swap(int[] A, int a, int b){
		int temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
	
    public static void main(String[] args) {
        int[] A = {52, 73, 12, 45, 89, 1, 2, 6, 13, 14, 67};
        System.out.println("The 5th smallest element is " + QuickSelect(A, 5));
    }
    
}
