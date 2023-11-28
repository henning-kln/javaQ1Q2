package _test.automaten;

import java.util.HashMap;
import java.util.TreeSet;

import graph.Edge;
import graph.Graph;
import graph.GraphWithViewer;
import graph.Vertex;
import linear.List;
import linear.ListWithViewer;

public class PotenzmengenKonstruktion {
	private Automat NEA;
	private Automat ergebnisAutomat;
	
	public PotenzmengenKonstruktion() {
		init();		
		potenzmengenKonstruktion();
		System.out.println();
		
		System.out.println("*** Deterministischer endlicher Automat (DEA) ***");
		ergebnisAutomat.print();
	}
	
	public void init() {
		NEA = new Automat();
		NEA.startzustandSetzen("q0");
		
		NEA = new Automat();
		NEA.uebergangHinzufuegen("q0", "a", "q0,q1");
		NEA.uebergangHinzufuegen("q0", "b", "q0");
		NEA.uebergangHinzufuegen("q0", "x", "q0");
		
		NEA.uebergangHinzufuegen("q1", "a", "q2");
		NEA.uebergangHinzufuegen("q1", "b", "q2");

		NEA.uebergangHinzufuegen("q2", "a", "q3");
		
		NEA.uebergangHinzufuegen("q3", "a", "q3");
		NEA.uebergangHinzufuegen("q3", "b", "q3");
		NEA.uebergangHinzufuegen("q3", "x", "q3");
		
		NEA.startzustandSetzen("q0");
		String[] endzustaende = {"q3"};
		NEA.endzustaendeSetzen(endzustaende);		
		System.out.println();
		System.out.println("*** Nicht Deterministischer Endlicher Automat (NEA): ***");
		System.out.println();
		NEA.print();
		
	}
	
	public void potenzmengenKonstruktion() {
		System.out.println("Potenzmengenkonstruktion wird durchgefuehrt");
		ergebnisAutomat = new Automat();
		Graph graph = new GraphWithViewer();
		Automat pruefAutomat = new Automat();
		String startzustand = NEA.startzustand();
		ergebnisAutomat.startzustandSetzen(startzustand);
		List<String> zustandsListeErgebnis= new ListWithViewer<>();
		String[] alphabet = NEA.alphabet();
		zustandsListeErgebnis.append(NEA.startzustand());
		Vertex v = new Vertex(startzustand);
		graph.addVertex(v);
		pruefAutomat.zustandHinzufuegen(startzustand);
		for(zustandsListeErgebnis.toFirst();zustandsListeErgebnis.hasAccess();zustandsListeErgebnis.next()){
			Vertex from_v = graph.getVertex(zustandsListeErgebnis.getContent());
			String[] zustaendeAufgeiteilt = NEA.aufteilen(zustandsListeErgebnis.getContent());
			for(int k=0; k<alphabet.length; k++){
				String neuerLinkerEintrag = "";
				for(int i=0;i<zustaendeAufgeiteilt.length;i++){
					String folgeZustaendeVonAktuell = NEA.folgeZustaende(zustaendeAufgeiteilt[i], alphabet[k]);
					neuerLinkerEintrag+=folgeZustaendeVonAktuell+",";
				}
				String gesauebert = NEA.alphabetischOrdnenUndTrennzeichenSaeubern(neuerLinkerEintrag);
				ergebnisAutomat.uebergangHinzufuegen(zustandsListeErgebnis.getContent(), alphabet[k],gesauebert);
				Vertex to_v = new Vertex(gesauebert);
				graph.addVertex(to_v);
				graph.addEdge(new Edge(from_v, to_v, k));
				if(ergebnisAutomat.zustandVorhanden(gesauebert)){
					continue;
				}
				zustandsListeErgebnis.append(gesauebert);
				ergebnisAutomat.zustandHinzufuegen(gesauebert);
			}
			
			
		}
	}

	
	public static void main(String[] args) {
		new PotenzmengenKonstruktion();
		
	}
	
}