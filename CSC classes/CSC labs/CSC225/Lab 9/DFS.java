/* DFS.java
   CSC 225 - Fall 2015
   Template for Depth First Search
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java DFS
	
   To conveniently test the algorithm with a large input, create a text file
   containing one or more test graphs (in the format described below) and run
   the program with
	java DFS file.txt
   where file.txt is replaced by the name of the text file.
   
   The input consists of a series of graphs in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>
	
   An input file can contain an unlimited number of graphs; each will be 
   processed separately.


   M. Simpson & B. Bird - 07/11/2015
*/

import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.io.File;

public class DFS {

	//static int time = 0;

	/* has_cycle(G)
	   Given an adjacency matrix describing a graph, determine if the graph has a cycle
	*/
	public static void has_cycle(int[][] G){
		int n = G.length;
		int[] visited = new int[n];
		
     	boolean cycle = cDFS(G, visited, 0, 0);
     	
		if (cycle == true) {
      		System.out.println("\nG contains a cycle");
		} else {
      		System.out.println("\nG does not contain a cycle");
		}
	}

	private static boolean cDFS(int[][] G, int[] visited, int i, int p) {
     	System.out.print(i + " ");
     	visited[i] = 1;
     	
     	for(int j=0; j<visited.length; j++) {
           	if(j != p && visited[j] == 1 && G[i][j] == 1) {
                    return true;
           	} else if(visited[j] == 0 && G[i][j] == 1) {
                    return cDFS(G, visited, j, i);
           	}
     	}
     	
     	return false;
	}

	/* has_odd_cycle(G)
	   Given an adjacency matrix describing a graph, determine if the graph has an odd-length cycle
	*/
	public static void has_odd_cycle(int[][] G){
		int n = G.length;
		int[] visited = new int[n];
		
     	boolean odd_cycle = ocDFS(G, visited, 0, 0, 1);
     	
		if (odd_cycle == true) {
      		System.out.println("\nG contains an odd length cycle");
		} else {
      		System.out.println("\nG does not contain an odd length cycle");
		}
	}

	private static boolean ocDFS(int[][] G, int[] visited, int i, int p, int parity) {
     	System.out.print(i + " ");
     	visited[i] = parity;
     	parity = (parity == 1) ? 2 : 1;
     	
     	for(int j=0; j<visited.length; j++) {
           	if(j != p && visited[j] == visited[i] && G[i][j] == 1) {
                    return true;
           	} else if(visited[j] == 0 && G[i][j] == 1) {
                    return ocDFS(G, visited, j, i, parity);
           	}
     	}
     	
     	return false;
	}

	/* is_biconnected(G)
	   Given an adjacency matrix describing a graph, determine if the graph is biconnected
	*/
	public static void is_biconnected(int[][] G){
		int n = G.length;
		int[] visited = new int[n];
		int[] low = new int[n];
		int[] depth = new int[n];
		
     	bcDFS(G, visited, low, depth, 0, 0, 0);
	}

	private static void bcDFS(int[][] G, int[] visited, int[] low, int[] depth, int i, int p, int time) {
     	visited[i] = 1;
     	depth[i] = time;
     	low[i] = depth[i];
     	int children = 0;
     	
     	for (int j=0; j<visited.length; j++) {

     		if(j != p && visited[j] == 1 && G[i][j] == 1) {
     			if (depth[j] < low[i]) low[i] = depth[j];
     		} else if (visited[j] == 0 && G[i][j] == 1) {
     			children++;
                bcDFS(G, visited, low, depth, j, i, time+1);
                //System.out.println("\ni = " + i + "\tj = " + j + "\tlow[j] = " + low[j] + "\tdepth[i] = " + depth[i]);
                if (low[j] >= depth[i] && i != 0) {
                	System.out.println("G is not biconnected");
                	return;
                }
                if (low[j] < low[i]) low[i] = low[j];
           	}
     	}

     	if (i == 0 && children > 1) System.out.println("G is not biconnected");
	}
		
	/* main()
	   Contains code to test the DFS method.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		int graphNum = 0;
		double totalTimeSeconds = 0;
		
		//Read graphs until EOF is encountered (or an error occurs)
		while(true){
			graphNum++;
			if(graphNum != 1 && !s.hasNextInt())
				break;
			System.out.printf("Reading graph %d\n",graphNum);
			int n = s.nextInt();
			int[][] G = new int[n][n];
			int valuesRead = 0;
			for (int i = 0; i < n && s.hasNextInt(); i++){
				for (int j = 0; j < n && s.hasNextInt(); j++){
					G[i][j] = s.nextInt();
					valuesRead++;
				}
			}
			if (valuesRead < n*n){
				System.out.printf("Adjacency matrix for graph %d contains too few values.\n",graphNum);
				break;
			}
			long startTime = System.currentTimeMillis();
			has_cycle(G.clone());
			System.out.println("");
			has_odd_cycle(G.clone());
			System.out.println("");
			is_biconnected(G.clone());
			long endTime = System.currentTimeMillis();
			totalTimeSeconds += (endTime-startTime)/1000.0;
				
		}
		graphNum--;
		System.out.printf("Processed %d graph%s.\nAverage Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>0)?totalTimeSeconds/graphNum:0);
	}
}
