package _wiederholungEF;

import gui.GUI;



public class Kasse{
    private double summe = .0;
    private double tagesSumme = .0;
    private double mwst = 0.19;

    public Kasse(){

    }
    public void produktHinzufügen(double pBetrag){
        summe += pBetrag; 
    }

    public void kassieren(){
    	System.out.println("|--------------------------------|");
        System.out.println("Summe: +"+summe+"€");
        System.out.println("MwSt: +"+summe*mwst+"€");
        summe += summe*mwst; 
        System.out.println("Gesamtsumme: "+summe+"€");
        System.out.println("|--------------------------------|");
        System.out.println("");
        tagesSumme += summe;
        summe = 0;
    }

    public void leeren(){
    	System.out.println("|--------------------------------|");
        System.out.println("Heute umgesetzt: "+tagesSumme+"€");
        System.out.println("|--------------------------------|");
        System.out.println("");
        tagesSumme = 0;
    }
    
    public static void main(String[] args) {
    
    	new GUI(new Kasse());
    }
}
