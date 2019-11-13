public class Loop4 {
	public static void main (String[] args){
		for(int i=0; i<=2; i++){
			for (int j=i; j<=(4+i); j++) {
				System.out.print(j + ", ");
			}
			System.out.println();
		}
	}
}
