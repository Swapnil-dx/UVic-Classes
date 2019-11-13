/* MST.java
   CSC 226 - Fall 2018
   Problem Set 2 - Template for Minimum Spanning Tree algorithm
   
   The assignment is to implement the mst() method below, using Kruskal's algorithm
   equipped with the Weighted Quick-Union version of Union-Find. The mst() method computes
   a minimum spanning tree of the provided graph and returns the total weight
   of the tree. To receive full marks, the implementation must run in time O(m log n)
   on a graph with n vertices and m edges.
   
   This template includes some testing code to help verify the implementation.
   Input graphs can be provided with standard input or read from a file.
   
   To provide test inputs with standard input, run the program with
       java MST
   To terminate the input, use Ctrl-D (which signals EOF).
   
   To read test inputs from a file (e.g. graphs.txt), run the program with
       java MST graphs.txt
   
   The input format for both methods is the same. Input consists
   of a series of graphs in the following format:
   
       <number of vertices>
       <adjacency matrix row 1>
       ...
       <adjacency matrix row n>
   	
   For example, a path on 3 vertices where one edge has weight 1 and the other
   edge has weight 2 would be represented by the following
   
   3
   0 1 0
   1 0 2
   0 2 0
   	
   An input file can contain an unlimited number of graphs; each will be processed separately.
   
   (originally from B. Bird - 03/11/2012)
   (tweaked by N. Mehta - 10/3/2017)
   (added to by S. Daxini - 26/10/17)
*/

import java.util.Scanner;
import java.io.File;
import java.util.*;

public class MST {


    /* mst(G)
       Given an adjacency matrix for graph G, return the total weight
       of all edges in a minimum spanning tree.
		
       If G[i][j] == 0, there is no edge between vertex i and vertex j
       If G[i][j] > 0, there is an edge between vertices i and j, and the
       value of G[i][j] gives the weight of the edge.
       No entries of G will be negative.
    */
    static int mst(int[][] G) {
		int numVerts = G.length;
		int totalWeight = 0;
		
		Queue<Edge> mst = new LinkedList<Edge>();
		
		ArrayList<Edge> pq = new ArrayList<Edge>();

		/* Find a minimum spanning tree using Kruskal's algorithm */
		/* (You may add extra functions if necessary) */
		
		for (int i = 0; i < numVerts; i++) {
			for(int j = 0; j < numVerts; j++) {
				if(G[i][j] > 0){
					Edge temp = new Edge(i,j, G[i][j]);
					pq.add(temp);
					G[j][i] = 0;
				}
			}
		}
		
		Collections.sort(pq);
		
		UF union = new UF(numVerts);
		
		for(int i = 0; i < pq.size(); i++){
			Edge temp = pq.get(i);
			int v = temp.either();
			int w = temp.other(v);
			
			if(!union.connected(v, w)){
				union.union(v,w);
				mst.add(temp);
				totalWeight += temp.weight();
			}
		}
		
		return totalWeight;
		
    }


    public static void main(String[] args) {
	/* Code to test your implementation */
	/* You may modify this, but nothing in this function will be marked */

		int graphNum = 0;
		Scanner s;

		if (args.length > 0) {
			//If a file argument was provided on the command line, read from the file
			try {
			s = new Scanner(new File(args[0]));
			}
			catch(java.io.FileNotFoundException e) {
			System.out.printf("Unable to open %s\n",args[0]);
			return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}
		else {
	    //Otherwise, read from standard input
			s = new Scanner(System.in);
			System.out.printf("Reading input values from stdin.\n");
		}
		
		//Read graphs until EOF is encountered (or an error occurs)
		while(true) {
			graphNum++;
			if(!s.hasNextInt()) {
			break;
			}
			System.out.printf("Reading graph %d\n",graphNum);
			int n = s.nextInt();
			int[][] G = new int[n][n];
			int valuesRead = 0;
			for (int i = 0; i < n && s.hasNextInt(); i++) {
			G[i] = new int[n];
			for (int j = 0; j < n && s.hasNextInt(); j++) {
				G[i][j] = s.nextInt();
				valuesRead++;
			}
		}
		if (valuesRead < n * n) {
			System.out.printf("Adjacency matrix for graph %d contains too few values.\n",graphNum);
			break;
		}
	    if (!isConnected(G)) {
			System.out.printf("Graph %d is not connected (no spanning trees exist...)\n",graphNum);
			continue;
		}
	    
		int totalWeight = mst(G);
	    System.out.printf("Graph %d: Total weight of MST is %d\n",graphNum,totalWeight);
				
		}
    }

    /* isConnectedDFS(G, covered, v)
       Used by the isConnected function below.
       You may modify this, but nothing in this function will be marked.
    */
    static void isConnectedDFS(int[][] G, boolean[] covered, int v) {
		covered[v] = true;
		for (int i = 0; i < G.length; i++) {
			if (G[v][i] > 0 && !covered[i]) {
			isConnectedDFS(G,covered,i);
			}
		}
    }
	   
    /* isConnected(G)
       Test whether G is connected.
       You may modify this, but nothing in this function will be marked.
    */
    static boolean isConnected(int[][] G) {
		boolean[] covered = new boolean[G.length];
		for (int i = 0; i < covered.length; i++) {
			covered[i] = false;
		}
		isConnectedDFS(G,covered,0);
		for (int i = 0; i < covered.length; i++) {
			if (!covered[i]) {
			return false;
			}
		}
		return true;
	}
    
}

/* Create class for an edge (similar to textbook)
*/
class Edge implements Comparable<Edge> {
	private int v;
	private int w;
	private int weight;
	
	public Edge(int v, int w, int weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int either(){
		return v;
	}
	
	public int other(int vertex){
		if(vertex == v) return w;
		else return v;
	}
	
	public int weight() {
		return weight;
	}
	
	public int compareTo(Edge that) {
        return Integer.compare(this.weight, that.weight);
    }
	
	public String toString() {
        return String.format("%d-%d, Weight: %d", v, w, weight);
    }
	
}

/* Create class for union find. Based on algorithm found in textbook.
*/
class UF {
    private int[] parent;  
    private int[] size;     
    private int count;      

   
    public UF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return count;
    }
  
    public int find(int p) {
        while (p != parent[p])
            p = parent[p];
        return p;
    }

   
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }
}
