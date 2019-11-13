public class QuickSort {
    
    public static void quicksort(Comparable[] array, int lo, int hi) {
	if(hi <= lo) return;
	int j = partition(array, lo, hi);
	quicksort(array, lo, j - 1);
	quicksort(array, j + 1, hi);
    }

    
    private static int partition(Comparable[] array, int lo, int hi) {
	int i = lo, j = hi + 1;
	Comparable v = array[lo];
	while(true) {
	    i++;
	    while(less(array[i], v)) {
		if(i == hi) break;
		i++;
	    }
	    
	    j--;
	    while(greater(array[j], v)) {
		if(j == lo)
		    break;
		j--;
	    }
	    
	    if(i >= j) {
		break;
	    }
	    swap(array, i, j);
	}
	swap(array, lo, j);  // this puts the pivot in a[j], its correct position
	return j;  // j will satisfy a[lo ... j-1] <= a[j] <= [j+1 ... hi]
    }

    
    private static void swap(Comparable[] array, int a, int b){
	int tmp = array[a];
	array[a] = array[b];
	array[b] = tmp;
    }
}
