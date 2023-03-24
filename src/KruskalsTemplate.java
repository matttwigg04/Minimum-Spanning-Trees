
/*
 * Kruskals Algorithm Graph
 */

//Matthew Twigg
// DS & ALGOs
//Tompsett

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class KruskalsTemplate {
	private int verts;
	private ArrayList<Edge> edges;
	private ArrayList<ArrayList<Integer>> adj;
	
	public KruskalsTemplate(int verts) {
		this.verts = verts;
		edges = new ArrayList<Edge>();
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < verts; i++) adj.add(new ArrayList<Integer>());
	}
	
	public KruskalsTemplate(String fileName, int verts) {
		File f = new File(fileName);
		this.verts = verts;
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < verts; i++) adj.add(new ArrayList<Integer>());
		edges = new ArrayList<Edge>();
		try {
			Scanner in = new Scanner(f);
			for (int i = 0; i < verts; i++) {
				for (int j = 0; j < verts; j++) {
					int weight = in.nextInt();
					if (weight != 0) {
						Edge e = new Edge(i, j, weight);
						if (!edges.contains(e)) {
							edges.add(e);
						}
						adj.get(i).add(j);
						adj.get(j).add(i);
					}
				}
			}
			in.close();
		}
		catch(Exception E) { System.out.println(E); }
	}
	
	public void printEdges() {
		for (Edge e: edges) System.out.println(e);
	}
	
	private boolean addEdge(Edge e) {
		if (!edges.contains(e)) {
			edges.add(e);
			adj.get(e.getV1()).add(e.getV2());
			adj.get(e.getV2()).add(e.getV1());
			return true;
		}
		return false;
	}
	
	private boolean removeEdge(Edge e) {
		int v1 = e.getV1();
		int v2 = e.getV2();
		adj.get(v1).remove((Integer) v2);
		adj.get(v2).remove((Integer) v1);
		return edges.remove(e);
	}
	
	private int numEdges() { return edges.size(); }
	
	private void sortEdges() {
		
		for(int i=0; i<numEdges(); i++) {
			
			for(int j=i+1; j<numEdges(); j++) {
				
				int edgesI = edges.get(i).getWeight();
				int edgesJ = edges.get(j).getWeight();
				
				if(edgesI>edgesJ) {	
					Edge myEdge = edges.get(i);
					
					edges.set(i, edges.get(j));
					
					edges.set(j, myEdge);
					
					
					
					
				}
			}
		}
	}
	
	private boolean containsCycle() {
		
		boolean[] checked = new boolean[verts];
		
		for(int i=0; i<verts; i++) {
			
			checked[i] = false;

		}
		
		for(int i=0; i<verts; i++) {
			if((checked[i])== false) { 
				
				
			
				if(adj.get(i).size()>0) {
					
					if(cycleFinder(i, checked, i-1) ==true) {
						return true;
					}
				}
				
			}
		}
		return false;
	}
	
	
	private boolean cycleFinder(int key, boolean[] checked, int lastKey) {
		
		checked[key] = true;
		
		for(int i=0; i<adj.get(key).size(); i++) {
			
			if((checked[adj.get(key).get(i)])== false) {
				if(cycleFinder(adj.get(key).get(i), checked, key) == true) {
					
					return true;
				
				}
			}
			
			else if(adj.get(key).get(i) != lastKey){
				
				return true;
			
			}
			
		}
		return false;
		
	}
	
	public KruskalsTemplate Kruskals() {
		
		KruskalsTemplate end = new KruskalsTemplate(7);
	
		for(int i=0; i<numEdges(); i++) {
			
			
			end.addEdge(edges.get(i));
			
			if(end.containsCycle() == true) {
			
				end.removeEdge(edges.get(i));
			}
			
			
			
		}
		return end;
	}
	
	public static void main(String[] args) {
		
		KruskalsTemplate kruskalsgraph = new KruskalsTemplate("graph1.txt", 7);
		
		kruskalsgraph.sortEdges();
		
		KruskalsTemplate mst = kruskalsgraph.Kruskals();
		
		System.out.println("---------------");
		System.out.println("GRAPH 1:");
		System.out.println("---------------");
		
		mst.printEdges();
		
	}
}
