public class Loop1 {
	public static void main(String[] args){
		for (int i=0; i<5; i++){
			for (int j=1; j<4; j++){
				System.out.print(j + ", ");
			}
			System.out.println("Stop!");
		}
	}
}