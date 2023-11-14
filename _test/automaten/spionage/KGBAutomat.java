package _test.automaten.spionage;

import gui.GUI;

public class KGBAutomat {
	public int zustand;
	private char[] alphabet = {'0','1','2','3','4','5','6','7','8','9'};
	
	public boolean imAlphabet(char pZeichen){
		//TODO
		return false;
	}
	
	public boolean teste(String pEingabe){
		zustand = 0;
		for(int i=0;i<pEingabe.length();i++){
			char x = pEingabe.charAt(i);
			switch(zustand){
				case 2:{
					switch (x){
						case '0':{
							zustand = 2;
						}
						case '7': {
							zustand = 3;
						}
						default:{
							zustand = 0;
						}
					}
				}
				case 1:{
					switch (x){
						case '0':{
							zustand = 2;
						}
						default: {
							zustand = 0;
						}
					}
				}
				case 0:{
					switch (x){
						case '0':zustand = 1;
						default:zustand = 0;
					}
				}
				
				
			}
		}
		System.out.println(zustand);
		return zustand==3;
	}
	
	public static void main(String[] args) {
		new GUI(new KGBAutomat());
	}
	
}
