package _wiederholungEF;

import gui.GUI;


public class Fahrkartenautomat {
	private int preisstufe = 1;
    private int anzahl = 1;
    private double preisProKarte = 1.20;
    private double eingeworfen = 0;
    private double insgesamtEingeworfen = 0;
    private String passwort = "xyz";
    private FahrkartenDrucker fDrucker = new FahrkartenDrucker();
 
 
    public Fahrkartenautomat() {
    	new GUI(this);
        System.out.println("Geladen!!!");
    }
 
    public void geldeingeworfen(double pBetrag){
    	if(pBetrag > 0) {
    		insgesamtEingeworfen += pBetrag;
        	eingeworfen += pBetrag;
    	}
    }
 
    public double rueckgabe(){
        double g = eingeworfen;
        insgesamtEingeworfen -= g;
        eingeworfen = 0;
        return g;
    }
 
    public double geldAusgabe(String pPasswort){
        if(passwort.equals(pPasswort)){
            double r = insgesamtEingeworfen;
            insgesamtEingeworfen = 0;
            return r;
        }else {
        	return .0;
        }
    }
 
    public void preisstufeWaehlen(int pPreisstufe){
    	if (pPreisstufe > 0 && pPreisstufe < 4) {
    		preisstufe = pPreisstufe;
    	}
    }
    public void anzahlWaehlen(int pAnzahl){
    	if (pAnzahl > 0) {
    		anzahl = pAnzahl;
    	}
    }
 
    public double drucken(){
        if (preisstufe*preisProKarte*anzahl < eingeworfen){
        	for (int i=0;i<anzahl;i++) {
        		boolean result = fDrucker.drucken("Fahrkarte");
        		System.out.println("");
        		if (result == false) {
        			System.out.println("Fehler!!!");
        			anzahl -= i;
        			break;
        		}
        	}
            
            eingeworfen -= preisstufe*preisProKarte*anzahl;
            return this.rueckgabe();
        }else if(preisstufe*preisProKarte*anzahl == eingeworfen){
        	for (int i=0;i<anzahl;i++) {
        		boolean result = fDrucker.drucken("Fahrkarte");
        		System.out.println("");
        		if (result == false) {
        			System.out.println("Fehler!!!");
        			anzahl -= i;
        			break;
        		}
        	}
            eingeworfen -= preisstufe*preisProKarte*anzahl;
            return .0;
        }else{
            System.out.println("Zu wenig geld!");
            return .0;
        }
    }
 

	public static void main(String[] args) {
		new Fahrkartenautomat();
	}
}
