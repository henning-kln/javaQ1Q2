package _test;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.GraphWithViewer;


public class GraphTestMatrix {

	public GraphWithViewer karte;

	private String[] staedte = {
			   "Koeln",
			   "Bonn",
			   "Koenigswinter",
			   "Bad Honnef",
			   "Unkel",
			   "Godesberg",
			   "Barcelona",
			   "Avalon"
	};
	private Vertex[] staedteVertices;

	private int[][] entfernungen = {
			{ 0,29,40,-1,-1,-1,500,-1},  //Koeln
			{29, 0,10,-1,-1, 6,529,-1},  //Bonn
			{40,10, 0, 5,-1, 4,-1,-1},  //Koenigswinter
			{-1,-1, 5, 0, 8,-1,-1,-1},  //Bad Honnef
			{-1,-1,-1, 8, 0,-1,-1,-1},  //Unkel
			{-1, 6, 4,-1,-1, 0,-1,-1},  //Godesberg
			{500,529,-1,-1,-1,-1,0,-1}, //Barcelona
			{-1,-1,-1,-1,-1,-1,-1, 0}	 //Avalon
	};
	
	public GraphTestMatrix(){
		karte = new GraphWithViewer();
		staedteVertices = new Vertex[staedte.length];
		for(int i=0;i<entfernungen.length;i++) {
			Vertex v1 = staedteVertices[i];
			if (v1==null) {
				v1 = new Vertex(staedte[i]);
				staedteVertices[i] = v1;
				karte.addVertex(v1);
			}
			for(int e=i+1;e<entfernungen[i].length;e++) {
				Vertex v2 = staedteVertices[e];
				if (v2==null) {
					v2 = new Vertex(staedte[e]);
					staedteVertices[e] = v2;
					karte.addVertex(v2);
				}
				if (entfernungen[i][e] >0) {
					karte.addEdge( new Edge(v1,v2, entfernungen[i][e]));					
				}
			}
		}
		karte.switchToISOMLayout();
	}

	public static void main(String[] args) {
		GraphTestMatrix gtm = new GraphTestMatrix();
	}

}
