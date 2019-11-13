public class TwoDArrays{
	public static void main(String[] args){
		int[][] nums= {
			{1,	5, 6, 8, 4, 3, 6, 9, 8, 2},
			{8, 3, 3, 5, 6, 7, 4, 8, 8, 8},
			{7, 6, 4, 5, 3, 3, 2, 6, 6, 4},
			{1, 1, 1, 1, 2, 1, 2, 2, 1, 1},
			{7, 6, 5, 6, 6, 7, 2, 3, 4, 8},
			{1, 3, 4, 5, 6, 2, 2, 1, 2, 0},
		};
//		printArray(nums);
//		printMaxPerRow(nums);
		rotateUp(nums);
	}
	
	public static void printArray(int[][] nums){
		for(int i=0; i<nums.length; i++){
			for(int j=0; j<nums[i].length; j++){
				System.out.print(nums[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void printMaxPerRow(int[][] nums){
		for(int i=0; i<nums.length; i++){
			int max=0;
			for(int j=0; j<nums[i].length; j++){
				if(nums[i][j]>max){
					max=nums[i][j];
				}
			}
			System.out.println("Row " + i + " max: " + max);
		}
	}
	
	public static void rotateUp(int[][] nums){
		int[][] buffer= new int[nums.length][nums[0].length];
		for(int i=0; i<nums[0].length; i++){
			buffer[0][i]= nums[nums.length-1][i];
		}
		for(int i=0; i<nums[0].length; i++){
			buffer[buffer.length-1][i]= nums[0][i];
		}
		for(int i=1; i<nums.length; i++){
			for(int j=0; j<nums[i].length; j++){
				buffer[i-1][j]=nums[i][j];
			}
		}
		printArray(buffer);
	}
}