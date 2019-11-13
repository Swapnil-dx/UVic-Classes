/* ShortestPaths.java
   CSC 226 - Fall 2017
      
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java ShortestPaths
	
   To conveniently test the algorithm with a large input, create a text file
   containing one or more test graphs (in the format described below) and run
   the program with
	java ShortestPaths file.txt
   where file.txt is replaced by the name of the text file.
   
   The input consists of a series of graphs in the following format:
   
    <number of vertices>
	<adjacency matrix row 1>
	...
	<adjacency matrix row n>
	
   Entry A[i][j] of the adjacency matrix gives the weight of the edge from 
   vertex i to vertex j (if A[i][j] is 0, then the edge does not exist).
   Note that since the graph is undirected, it is assumed that A[i][j]
   is always equal to A[j][i].
	
   An input file can contain an unlimited number of graphs; each will be 
   processed separately.


   B. Bird - 08/02/2014
   Edited by Swapnil Daxini - 09/11/2017
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.*;


//Do not change the name of the ShortestPaths class

class Data implements Comparable<Data> {
		public final int index;
		public final int priority;

		public Data(int index, int priority) {
			this.index = index;
			this.priority = priority;
		}

		public int compareTo(Data other) {
			return Integer.valueOf(priority).compareTo(other.priority);
		}
	
		public boolean equals(Data other) {
			return priority == other.priority;
		}

	}

public class ShortestPaths{

    //TODO: Your code here   
        public static int numVerts;
		public static int[] distance;
		public static LinkedList<Integer>[] list;
	/* ShortestPaths(G) 
	   Given an adjacency matrix for graph G, calculates and stores the
	   shortest paths to all the vertices from the source vertex.
		
		If G[i][j] == 0, there is no edge between vertex i and vertex j
		If G[i][j] > 0, there is an edge between vertices i and j, and the
		value of G[i][j] gives the weight of the edge.
		No entries of G will be negative.
	*/
	static void ShortestPaths(int[][] G, int source){
		numVerts = G.length;
		distance = new int[numVerts];
		list = new LinkedList[numVerts];
		
		
		
		PriorityQueue<Data> pq = new PriorityQueue<Data>();

		for (int i = 0; i < numVerts; i++){
			if (source == i) {
				distance[i] = 0;
				list[i] = new LinkedList<Integer>();
				list[i].add(source);
			} else {
				distance[i] = 10000;  //Assumed to be infinity
				Data temp = new Data(i, distance[i]);
				pq.add(temp);
				list[i] = new LinkedList<Integer>();
				list[i].add(source);
			}
		}
		
		for (int i = 0; i < numVerts; i++) {
			if(G[source][i] != 0) {
				if ((distance[source] + G[source][i]) < distance[i]) {
					int temp = distance[i];
					list[i].add(i);
					distance[i] = distance[source] + G[source][i];
					Data old = new Data(i, temp);
					pq.remove(old);
					Data updated = new Data(i, distance[i]);
					pq.add(updated);
				}
			}
		}
		
		while (pq.peek() != null) {
			Data v = pq.poll();
			
			for(int i = 0; i < numVerts; i++){
				if(G[v.index][i] != 0) {
					if(distance[v.index] + G[v.index][i] < distance[i]) {
						int temp = distance[i];
						list[i].add(v.index);
						distance[i] = distance[v.index] + G[v.index][i];
						Data old = new Data(i, temp);
						pq.remove(old);
						Data updated = new Data(i, distance[i]);
						pq.add(updated);
						
					}
				}
			}
		}
		
		for(int i = 0; i < numVerts; i++) {
			if(G[source][i] == 0){
				list[i].add(i);
			}
		}
	}
        
        static void PrintPaths(int source){
			for(int i = 0; i < numVerts; i++) {
				System.out.printf("The path from %d to %d is:", source, i);
				
				for(int j = 0; j < list[i].size()-1; j++) {
					System.out.printf("%d--> ", list[i].get(j));
				}
				System.out.printf("%d and the total distance is: %d\n", list[i].getLast(), distance[i]);
			}
        }
        
		
	/* main()
	   Contains code to test the ShortestPaths function. You may modify the
	   testing code if needed, but nothing in this function will be considered
	   during marking, and the testing process used for marking will not
	   execute any of the code below.
	*/
	public static void main(String[] args) throws FileNotFoundException{
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
			
			ShortestPaths(G, 0);
            PrintPaths(0);
			long endTime = System.currentTimeMillis();
			totalTimeSeconds += (endTime-startTime)/1000.0;
			
			//System.out.printf("Graph %d: Minimum weight of a 0-1 path is %d\n",graphNum,distance[1]);
		}
		graphNum--;
		System.out.printf("Processed %d graph%s.\nAverage Time (seconds): %.2f\n",graphNum,(graphNum != 1)?"s":"",(graphNum>0)?totalTimeSeconds/graphNum:0);
	}
	
	
}
