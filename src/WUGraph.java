//Prims Algorithm
//Matthew Twigg
// DS & ALGOs
//Tompsett
import java.util.*;
import java.io.*;
public class WUGraph {
	private int[][] adjMatrix;
	private int numVerts;
	
	public static void main(String[] args) {
		System.out.println("---------------");
		System.out.println("GRAPH 1:");
		System.out.println("---------------");
		
		WUGraph g = new WUGraph("src/graph1.txt", 7);
		g.Prims();
		g.printGraph();
		
		System.out.println("---------------");
		
		System.out.println("GRAPH 2:");
		
		System.out.println("---------------");
		
		WUGraph g2 = new WUGraph("src/graph2.txt", 7);
		g2.Prims();
		g2.printGraph();
		
		System.out.println("---------------");
		
	
	}
	
	public WUGraph(String fileName, int verts) {
		File f = new File(fileName);
		numVerts = verts;
		adjMatrix = new int[verts][verts];
		try {
			Scanner in = new Scanner(f);
			for (int i = 0; i < verts; i++) {
				for (int j = 0; j < verts; j++) {
					adjMatrix[i][j] = in.nextInt();
				}
			}
			in.close();
		}
		catch(Exception E) { System.out.println(E); }
	}
	
	
	public void printGraph() {
		for (int i = 0; i < numVerts; i++) {
			for (int j = 0; j < numVerts; j++) {
				System.out.print(adjMatrix[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public int[][] Prims() { 
		int[] key = new int[numVerts]; //key value
		
		int[] parentIndices = new int[numVerts];
		
		int[][] finalMatrix = new int[numVerts][numVerts];
		
		boolean[] checked = new boolean[numVerts];// boolean that stores wheter or not a value has been checked or not
		
		//initializes variables
		for (int i = 0; i < numVerts; i++) {
			checked[i] = false;
			key[i] = Integer.MAX_VALUE;
		}
		
		key[0] = 0;
		
		parentIndices[0] = -1;
		
		
		
		for (int i = 0; i < numVerts; i++) { //nested forloop that goes through the mst to find the minkey
			int minKeyFinderVal = -1;
			
			int minKeyFinder = 1000000000;
			
			
			for (int j = 0; j < numVerts; j++) {
				
				if (checked[j] == false) {// only checking ones that habve not been checked yet
					
					if (key[j] < minKeyFinder) {
						
						minKeyFinder= key[j];
						
						minKeyFinderVal = j;
					}	
				}
			}
			checked[minKeyFinderVal] =  true;//storing the fact it has been checked
			
			for (int n = 0; n < numVerts; n++) {
				if (adjMatrix[minKeyFinderVal][n] != 0) {
					
					if(checked[n] == false) 
					{
						if (adjMatrix[minKeyFinderVal][n] < key[n]) {
							parentIndices[n] = minKeyFinderVal;
							
							key[n] = adjMatrix[minKeyFinderVal][n];
						}
					}
				}
			}
		}
		
//nested forloops that create a matrix 
		for (int i = 1; i < numVerts; i++) {
			
			for (int j = 0; j < numVerts; j++) {
				
				for (int n = 0; n < numVerts; n++) {
					
					if (j == parentIndices[i]){
						
						if( n == i) {
							
							finalMatrix[j][n] = adjMatrix[i][parentIndices[i]];
							
							finalMatrix[n][j] = adjMatrix[i][parentIndices[i]];
						}
						
					}
					if(j == i) {
						
						if(n == parentIndices[i]) {
							
							finalMatrix[j][n] = adjMatrix[i][parentIndices[i]];
							
							finalMatrix[n][j] = adjMatrix[i][parentIndices[i]];
						}
					}
				}
			}
		}
		adjMatrix = finalMatrix;
		return adjMatrix;
		
	}

	
}