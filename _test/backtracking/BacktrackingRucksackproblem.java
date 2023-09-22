package _test.backtracking;


public class BacktrackingRucksackproblem {
	public int[] gewichte = {1,2,3,4,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40};
	public int maxGewicht = 400;
	
	public boolean[] dabei = new boolean[gewichte.length];
	public int erreichtesGewicht = 0;
	
	public boolean[] besteLoesung = new boolean[gewichte.length];
	public int bestesGewicht = 0;
	
	public void sucheBesteLoesung(){
		//Vorbereitung
		for(int i=0; i<dabei.length; i++){
			dabei[i] = false;
		}
		kopiereInBesteLoesung();
		// los gehts!
		sucheBesteLoesung(0);
		System.out.println("*** Beste Loesung: ***");
		ausgeben(besteLoesung);
	}

	public void sucheBesteLoesung(int pStufe) {
		erreichtesGewicht = berechneGewicht(dabei);
		if (erreichtesGewicht > bestesGewicht && erreichtesGewicht<maxGewicht) {
			bestesGewicht = erreichtesGewicht;
			kopiereInBesteLoesung();
			dabeiArrayAusgeben();
			return;
		}
		if (pStufe >= gewichte.length) {
			
			return ;
		}
		dabei[pStufe] = false;
		sucheBesteLoesung(pStufe+1);
		dabei[pStufe] = true;
		sucheBesteLoesung(pStufe+1);
		
			
			
		
	}
	
	public void ausgeben(boolean[] b){
		System.out.print(berechneGewicht(b)+": ");
		for(int i=0; i<dabei.length; i++){
			if(b[i]){
				System.out.print(gewichte[i]+",");
			}
		}
		System.out.println();
	}
	
	public void dabeiArrayAusgeben(){
		for(int i=0; i<dabei.length; i++){
			if(dabei[i]){
				System.out.print("+");
			}
			else{
				System.out.print("-");
			}
		}
		System.out.println();
	}
	
	
	
	
	public void kopiereInBesteLoesung(){
		for(int i=0; i<dabei.length; i++){
			besteLoesung[i] = dabei[i];
		}
	}
	
	public int berechneGewicht(boolean[] p){
		int ergebnis = 0;
		for(int i=0; i<p.length; i++){
			if(p[i]){
				ergebnis += gewichte[i];
			}
		}
		return ergebnis;
	}
	
	public static void main(String[] args) {
		BacktrackingRucksackproblem rp = new BacktrackingRucksackproblem();
		rp.sucheBesteLoesung();
	}
}
