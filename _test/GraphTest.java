package _test;


import java.util.Vector;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.GraphWithViewer;
import gui.GUI;
import linear.List;
import linear.ListWithViewer;


public class GraphTest {

	// Attribut, in dem die Karte gespeichert wird.
	public GraphWithViewer karte;

	// Rahmenmethode zum testen
	public List<Vertex> tiefendurchlaufVonKassel(){
		karte.setAllVertexMarks(false);
		karte.setAllEdgeMarks(false);
		Vertex vKassel = karte.getVertex("Kassel");
		List<Vertex> v = new ListWithViewer<>();
		v.concat(tiefendurchlauf(vKassel));
		return v;
	}
	
	// rekursive Methode
	private List<Vertex> tiefendurchlauf(Vertex pVertex) {
		List<Vertex> ergebnis = new List<>();
		List<Vertex> n = karte.getNeighbours(pVertex);
		for (n.toFirst();n.hasAccess();n.next()) {
			if (!n.getContent().isMarked()) {
				ergebnis.append(n.getContent());
				n.getContent().setMark(true);
				ergebnis.concat(tiefendurchlauf(n.getContent()));
			}
		}
		
		return ergebnis;
	}
	public List<Vertex> ereichbarInVonKassel(int pKm) {
		karte.setAllVertexMarks(false);
		karte.setAllEdgeMarks(false);
		Vertex kassel = karte.getVertex("Kassel");
		kassel.setMark(true);
		List<Vertex> v = new ListWithViewer<>();
		v.concat(ereichbarIn(kassel, pKm));
		return v;
	}
		
	
	public List<Vertex> ereichbarIn(Vertex pVertex, int pKm) {
		List<Vertex> ergebnis = new List<>();
		if (pKm<0) {
			return ergebnis;
		}
		List<Vertex> n = karte.getNeighbours(pVertex);
		
		for (n.toFirst();n.hasAccess();n.next()) {
			int abs = (int) (pKm - karte.getEdge(pVertex, n.getContent()).getWeight());
			
			if (!n.getContent().isMarked() && abs>0) {
				ergebnis.append(n.getContent());
				n.getContent().setMark(true);
				ergebnis.concat(ereichbarIn(n.getContent(), abs));
			}
		}
		return ergebnis;
		
	}
	
	
	public Vertex haveEqual(List<Vertex> p1, List<Vertex> p2) {
		for (p1.toFirst();p1.hasAccess();p1.next()) {
			for (p2.toFirst();p2.hasAccess();p2.next()) {
				if (p1.getContent().equals(p2.getContent())){
					return p1.getContent();
				}
			}
		}
		return null;
	}
	
	public List<Vertex> kuerzesteRundreise(String pStart){
		List<Vertex> erg = new ListWithViewer<>();
		Vertex vStart = karte.getVertex(pStart);
		erg.append(vStart);
		vStart.setMark(false);
		kuerzesteRundreise(vStart, erg);
		return erg;
	}
	
	public int len(List<Vertex> pL) {
		int i = 0;
		for (pL.toFirst();pL.hasAccess();pL.next()) {
			i++;
		}
		return i;
	}
	
	public void kuerzesteRundreise(Vertex pVertex, List<Vertex> erg){
		List<Vertex> n = karte.getNeighbours(pVertex);
		for (n.toFirst();n.hasAccess();n.next()) {
			erg.toLast();
			
			if(!n.getContent().isMarked()) {
				n.getContent().setMark(true);
				if(erg.hasAccess() && n.getContent().equals(erg.getContent()) && len(erg)!= 1) {
					pVertex.setMark(true);
					erg.append(pVertex);
					return;
				}
				kuerzesteRundreise(n.getContent(), erg);
			}
		}
		if (!erg.isEmpty()) {
			erg.append(pVertex);
		}
		return;
	
	}
	
	

	// Rahmenmethode zum testen
	public List<Vertex> breitendurchlaufVonKassel(){
		
		karte.setAllVertexMarks(false);
		karte.setAllEdgeMarks(false);
		Vertex vKassel = karte.getVertex("Kassel");
		return breitendurchlauf(vKassel);
	}
	
	// NICHT-rekursive Methode
	private List<Vertex> breitendurchlauf(Vertex pVertex) {
		List<Vertex> ergebnis = new ListWithViewer<>();
		ergebnis.append(pVertex);
		pVertex.setMark(true);
		for (ergebnis.toFirst();ergebnis.hasAccess();ergebnis.next()) {
			List<Vertex> n = karte.getNeighbours(ergebnis.getContent());
			for (n.toFirst();n.hasAccess();n.next()) {
				if (!n.getContent().isMarked()) {
					ergebnis.append(n.getContent());
					n.getContent().setMark(true);
					
				}
			}
		}
		return ergebnis;
	}
	
	public List<Vertex> breitendurchlaufVonBerlin(){
		
		karte.setAllVertexMarks(false);
		karte.setAllEdgeMarks(false);
		Vertex vKassel = karte.getVertex("Berlin");
		return breitendurchlauf2(vKassel);
	}
	
	private List<Vertex> breitendurchlauf2(Vertex pVertex) {
		List<Vertex> erg = new ListWithViewer<>();
		pVertex.setMark(true);
		erg.append(pVertex);
		for (erg.toFirst();erg.hasAccess();erg.next()) {
			List<Vertex> n = karte.getNeighbours(erg.getContent());
			for (n.toFirst();n.hasAccess();n.next()) {
				if (!n.getContent().isMarked()) {
					karte.getEdge(erg.getContent(), n.getContent()).setMark(true);;
					erg.append(n.getContent());
					n.getContent().setMark(true);
				}
			}
		}
		return erg;
	}


	public GraphTest(){
		karte = new GraphWithViewer();
		Vertex dortmund = new Vertex("Dortmund");
		karte.addVertex(dortmund);
		Vertex koeln = new Vertex("Koeln");
		karte.addVertex(koeln);
		Vertex frankfurt = new Vertex("Frankfurt");
		karte.addVertex(frankfurt);
		Vertex kassel = new Vertex("Kassel");
		karte.addVertex(kassel);
		Vertex wuerzburg = new Vertex("Wuerzburg");
		karte.addVertex(wuerzburg);

		Edge kassel_dortmund = new Edge(kassel, dortmund, 160);
		karte.addEdge(kassel_dortmund);
		Edge dortmund_koeln = new Edge(dortmund, koeln, 93);
		karte.addEdge(dortmund_koeln);
		Edge frankfurt_kassel = new Edge(frankfurt, kassel, 193);
		karte.addEdge(frankfurt_kassel);
		Edge kassel_wuerzburg = new Edge(kassel, wuerzburg, 209);
		karte.addEdge(kassel_wuerzburg);
		Edge wuerzburg_frankfurt = new Edge(wuerzburg, frankfurt, 119);
		karte.addEdge(wuerzburg_frankfurt);
		Edge frankfurt_koeln = new Edge(frankfurt, koeln, 189);
		karte.addEdge(frankfurt_koeln);

		// *** weitere Vertices und Edges! ***
		
		Vertex hamburg = new Vertex("Hamburg");
		karte.addVertex(hamburg);
		Vertex berlin = new Vertex("Berlin");
		karte.addVertex(berlin);
		Vertex bremen = new Vertex("Bremen");
		karte.addVertex(bremen);
		Vertex hannover = new Vertex("Hannover");
		karte.addVertex(hannover);
		Vertex leipzig = new Vertex("Leipzig");
		karte.addVertex(leipzig);
		Vertex nuernberg = new Vertex("Nuernberg");
		karte.addVertex(nuernberg);
		Vertex stuttgart = new Vertex("Stuttgart");
		karte.addVertex(stuttgart);
		Vertex muenchen = new Vertex("Muenchen");
		karte.addVertex(muenchen);
		Vertex karlsruhe = new Vertex("Karlsruhe");
		karte.addVertex(karlsruhe);
		Vertex aachen = new Vertex("Aachen");
		karte.addVertex(aachen);

		Edge e = new Edge(berlin, hamburg, 289);
		karte.addEdge(e);
		Edge hamburg_bremen = new Edge(hamburg, bremen, 119);
		karte.addEdge(hamburg_bremen);
		Edge bremen_hannover = new Edge(bremen, hannover, 122);
		karte.addEdge(bremen_hannover);
		Edge hannover_hamburg = new Edge(hannover, hamburg, 150);
		karte.addEdge(hannover_hamburg);
		Edge berlin_hannover = new Edge(berlin, hannover, 290);
		karte.addEdge(berlin_hannover);
		Edge berlin_leipzig = new Edge(berlin, leipzig, 188);
		karte.addEdge(berlin_leipzig);
		Edge hannover_kassel = new Edge(hannover, kassel, 167);
		karte.addEdge(hannover_kassel);
		Edge leipzig_kassel = new Edge(leipzig, kassel, 250);
		karte.addEdge(leipzig_kassel);
		Edge dortmund_bremen = new Edge(dortmund, bremen, 234);
		karte.addEdge(dortmund_bremen);
		Edge dortmund_hannover = new Edge(dortmund, hannover, 210);
		karte.addEdge(dortmund_hannover);
		Edge leipzig_nuernberg = new Edge(leipzig, nuernberg, 278);
		karte.addEdge(leipzig_nuernberg);
		Edge wuerzburg_nuernberg = new Edge(wuerzburg, nuernberg, 110);
		karte.addEdge(wuerzburg_nuernberg);
		Edge nuernberg_muenchen = new Edge(nuernberg, muenchen, 166);
		karte.addEdge(nuernberg_muenchen);
		Edge muenchen_stuttgart = new Edge(muenchen, stuttgart, 223);
		karte.addEdge(muenchen_stuttgart);
		Edge nuernberg_stuttgart = new Edge(nuernberg, stuttgart, 208);
		karte.addEdge(nuernberg_stuttgart);
		Edge stuttgart_karlsruhe = new Edge(stuttgart, karlsruhe, 82);
		karte.addEdge(stuttgart_karlsruhe);
		Edge karlsruhe_frankfurt = new Edge(karlsruhe, frankfurt, 147);
		karte.addEdge(karlsruhe_frankfurt);
		Edge aachen_koeln = new Edge(aachen, koeln, 68);
		karte.addEdge(aachen_koeln);

		// auf ein geeignetes Layout umstellen
		karte.switchToISOMLayout();
	}
	
	public void printMatrix() {
		int anzahlStaedte = 0;
		Vector<String> staedte = new Vector<String>();
		String staedteString = "staedte = [";
		List<Vertex> vertices = karte.getVertices();
		for(vertices.toFirst(); vertices.hasAccess(); vertices.next()) {
			String stadt = vertices.getContent().getID();
			staedteString += "'"+stadt+"'"+",";
			staedte.add(stadt);
			anzahlStaedte++;
		}
		staedteString = staedteString.substring(0,staedteString.length()-1);
		staedteString += "]";
		System.out.println(staedteString);
		int[][] entfernungen = new int[anzahlStaedte][anzahlStaedte];
		List<Edge> edges = karte.getEdges();
		for(edges.toFirst(); edges.hasAccess(); edges.next()) {
			Edge e = edges.getContent();
			double weight = e.getWeight();
			Vertex v0 = e.getVertices()[0];
			Vertex v1 = e.getVertices()[1];
			int index0 = staedte.indexOf(v0.getID());		
			int index1 = staedte.indexOf(v1.getID());
			entfernungen[index0][index1] = (int)weight;
			entfernungen[index1][index0] = (int)weight;
		}
		System.out.println();
		System.out.println("entfernungen = []");
		for(int[] zeile:entfernungen) {
			String z = "entfernungen.add([";
			for(int zelle:zeile) {
				z += zelle + ",";
			}
			z = z.substring(0,z.length()-1);
			z += "])";
			System.out.println(z);
			
		}
		
	}
	public List<Vertex> kuersesteVerbindungVonKasaselNachBerlin(){
		karte.setAllVertexMarks(false);
		Vertex b = karte.getVertex("Berlin");
		Vertex k = karte.getVertex("Kassel");
		return kuerstesteVerbindung(k,b);
	}
	

	
	public List<Vertex> kuerstesteVerbindung(Vertex pStart, Vertex pEnde) {
		List<Vertex> erg = new ListWithViewer<Vertex>();
		List<Double> strecke = new ListWithViewer<Double>();
		double min = 1000000.0;
		erg.append(pStart);
		pStart.setMark(true);
		strecke.append(0.0);
		for(erg.toFirst(),strecke.toFirst();erg.hasAccess()&&strecke.hasAccess();erg.next(),strecke.next()) {
			Vertex v = erg.getContent();
			double entfernungBisV = strecke.getContent();
			List<Vertex> nachbarn = karte.getNeighbours(v);
			for(nachbarn.toFirst();nachbarn.hasAccess();nachbarn.next()) {
				Vertex n = nachbarn.getContent();
				Edge e = karte.getEdge(v, n);
				double gewicht = e.getWeight();
				if(!n.isMarked() || entfernungBisV+gewicht < min) {
					if (entfernungBisV+gewicht < min) {
						// TODO Abfrage nur für Berlin
						min = entfernungBisV+gewicht;
					}
					n.setMark(true);
					erg.append(n);
					strecke.append(entfernungBisV+gewicht);
				}
			}
			
			
		}
		
		
		return erg;
 		
	}
	public static void main(String[] args) {
		GraphTest gt = new GraphTest();
		new GUI(gt);
	}

}
