/* NinePuzzle.java
   CSC 225 - Spring 2017
   Assignment 4 - Template for the 9-puzzle
   
   This template includes some testing code to help verify the implementation.
   Input boards can be provided with standard input or read from a file.
   
   To provide test inputs with standard input, run the program with
	java NinePuzzle
   To terminate the input, use Ctrl-D (which signals EOF).
   
   To read test inputs from a file (e.g. boards.txt), run the program with
    java NinePuzzle boards.txt
	
   The input format for both input methods is the same. Input consists
   of a series of 9-puzzle boards, with the '0' character representing the 
   empty square. For example, a sample board with the middle square empty is
   
    1 2 3
    4 0 5
    6 7 8
   
   And a solved board is
   
    1 2 3
    4 5 6
    7 8 0
   
   An input file can contain an unlimited number of boards; each will be 
   processed separately.
  
   B. Bird    - 07/11/2014
   M. Simpson - 11/07/2015
*/

import java.util.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;

public class NinePuzzle{

	//The total number of possible boards is 9! = 1*2*3*4*5*6*7*8*9 = 362880
	public static final int NUM_BOARDS = 362880;


	/*  SolveNinePuzzle(B)
		Given a valid 9-puzzle board (with the empty space represented by the 
		value 0),return true if the board is solvable and false otherwise. 
		If the board is solvable, a sequence of moves which solves the board
		will be printed, using the printBoard function below.
	*/
	
	public static class Graph {
		private final int V;
		private LinkedList<Integer>[] adj;
		
		public Graph(int V){
			this.V = V;
			adj = (LinkedList<Integer>[]) new LinkedList[V];
			for (int v = 0; v < V; v++){
				adj[v] = new LinkedList<Integer>();
			}
		}
		
		public void addEdge(int v, int w){
			adj[v].add(w);
			adj[w].add(v);
		}
		
		public LinkedList<Integer> adj(int v){
			return adj[v];
		}
	}
	
	public static class BFSPath{
		private boolean[] marked;
		private int[] edgeTo;
		private int sol;
		private int s;
		
		public BFSPath(Graph G, int s, int sol){
			marked = new boolean[NUM_BOARDS];
			edgeTo = new int[NUM_BOARDS];
			this.sol = sol;
			this.s = s;
			
			bfs(G, s);
		}
		
		private void bfs(Graph G, int s){
			Queue<Integer> q = new LinkedList<Integer>();
			marked[s] = true;
			q.add(s);

			while (!q.isEmpty()) {
				int v = q.remove();
				for (int w : G.adj(v)) {
					if (!marked[w]) {
						edgeTo[w] = v;
						marked[w] = true;
						q.add(w);
					}
				}	
			}
		}
		
		public boolean hasPath(int v){
			return marked[v];
		}
		
		public Stack<Integer> Solvable(){
			if(hasPath(sol)){
				Stack<Integer> path = new Stack<Integer>();
				for (int x = sol; x != s; x = edgeTo[x]){
					path.push(x);
				}
				path.push(s);
				return path;
			} else {
				return null;
			}
		}
	}
	
	private static void copyBoard(int[][] B, int[][] altB){
		for (int m = 0; m < 3; m++) {
			for (int n = 0; n < 3; n++) {
				altB[m][n] = B[m][n];
		}
    }
	}
	
	private static void buildGraph(Graph G, int N){
		int n = (int) Math.sqrt(N);
		
		for(int v = 0; v < NUM_BOARDS; v++){
			int[][] B = getBoardFromIndex(v);
			int i = 0,j = 0;
			
			for(int s = 0; s < n; s++){
				for(int t = 0; t < n; t++){
					if(B[s][t] == 0){
						i = s;
						j = t;
						break;
					}
				}
			}
			
			if(i > 0){
				int[][] altB = new int[3][3];
				copyBoard(B, altB);
				altB[i][j] = altB[i-1][j];
				altB[i-1][j] = 0;
				int u = getIndexFromBoard(altB);
				
				G.addEdge(u, v);
			}
			
			if(i < n-1){
				int[][] altB = new int[3][3];
				copyBoard(B, altB);
				altB[i][j] = altB[i+1][j];
				altB[i+1][j] = 0;
				int u = getIndexFromBoard(altB);
				
				G.addEdge(u, v);
			}
			
			if(j > 0){
				int[][] altB = new int[3][3];
				copyBoard(B, altB);
				altB[i][j] = altB[i][j-1];
				altB[i][j-1] = 0;
				int u = getIndexFromBoard(altB);
				
				G.addEdge(u, v);
			}
			
			if(j < n-1){
				int[][] altB = new int[3][3];
				copyBoard(B, altB);
				altB[i][j] = altB[i][j+1];
				altB[i][j+1] = 0;
				int u = getIndexFromBoard(altB);
				
				G.addEdge(u, v);
			}
		}
	}
	
	public static boolean SolveNinePuzzle(int[][] B){
		Graph G = new Graph(NUM_BOARDS); 
		buildGraph(G, 9);
		
		int curr = getIndexFromBoard(B);
		
		int[][] solution = {{1,2,3}, {4,5,6}, {7,8,0}};
		
		int solIndex = getIndexFromBoard(solution);
		
		BFSPath s = new BFSPath(G, curr, solIndex);
		
		Stack<Integer> path = new Stack<Integer>();
		
		if(s.hasPath(solIndex)){
			path = s.Solvable();
			while(!path.empty()){
				printBoard(getBoardFromIndex(path.pop()));
			}
			return true;
		} else {
			return false;
		}
		
	}
	
	/*  printBoard(B)
		Print the given 9-puzzle board. The SolveNinePuzzle method above should
		use this method when printing the sequence of moves which solves the input
		board. If any other method is used (e.g. printing the board manually), the
		submission may lose marks.
	*/
	public static void printBoard(int[][] B){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++)
				System.out.printf("%d ",B[i][j]);
			System.out.println();
		}
		System.out.println();
	}
	
	
	/* Board/Index conversion functions
	   These should be treated as black boxes (i.e. don't modify them, don't worry about
	   understanding them). The conversion scheme used here is adapted from
		 W. Myrvold and F. Ruskey, Ranking and Unranking Permutations in Linear Time,
		 Information Processing Letters, 79 (2001) 281-284. 
	*/
	public static int getIndexFromBoard(int[][] B){
		int i,j,tmp,s,n;
		int[] P = new int[9];
		int[] PI = new int[9];
		for (i = 0; i < 9; i++){
			P[i] = B[i/3][i%3];
			PI[P[i]] = i;
		}
		int id = 0;
		int multiplier = 1;
		for(n = 9; n > 1; n--){
			s = P[n-1];
			P[n-1] = P[PI[n-1]];
			P[PI[n-1]] = s;
			
			tmp = PI[s];
			PI[s] = PI[n-1];
			PI[n-1] = tmp;
			id += multiplier*s;
			multiplier *= n;
		}
		return id;
	}
		
	public static int[][] getBoardFromIndex(int id){
		int[] P = new int[9];
		int i,n,tmp;
		for (i = 0; i < 9; i++)
			P[i] = i;
		for (n = 9; n > 0; n--){
			tmp = P[n-1];
			P[n-1] = P[id%n];
			P[id%n] = tmp;
			id /= n;
		}
		int[][] B = new int[3][3];
		for(i = 0; i < 9; i++)
			B[i/3][i%3] = P[i];
		return B;
	}
	

	public static void main(String[] args){
		/* Code to test your implementation */
		/* You may modify this, but nothing in this function will be marked */

		
		Scanner s;

		if (args.length > 0){
			//If a file argument was provided on the command line, read from the file
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			//Otherwise, read from standard input
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int graphNum = 0;
		double totalTimeSeconds = 0;
		
		//Read boards until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(graphNum != 1 && !s.hasNextInt())
				break;
			System.out.printf("Reading board %d\n",graphNum);
			int[][] B = new int[3][3];
			int valuesRead = 0;
			for (int i = 0; i < 3 && s.hasNextInt(); i++){
				for (int j = 0; j < 3 && s.hasNextInt(); j++){
					B[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < 9){
				System.out.printf("Board %d contains too few values.\n",graphNum);
				break;
			}
			System.out.printf("Attempting to solve board %d...\n",graphNum);
			long startTime = System.currentTimeMillis();
			boolean isSolvable = SolveNinePuzzle(B);
			long endTime = System.currentTimeMillis();
			totalTimeSeconds += (endTime-startTime)/1000.0;
			
			if (isSolvable)
				System.out.printf("Board %d: Solvable.\n",graphNum);
			else
				System.out.printf("Board %d: Not solvable.\n",graphNum);
		}
		graphNum--;
		System.out.printf("Processed %d board%s.\n Average Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>1)?totalTimeSeconds/graphNum:0);

	}

}
