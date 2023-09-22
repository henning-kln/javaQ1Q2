package _test;


import java.util.Arrays;

import gui.GUI;
import linear.Stack;
import linear.Stack;
import linear.StackWithViewer;
import sonstiges.Auto;
import sonstiges.Autos;
import sonstiges.Celebrities;
import sonstiges.Celebrity;

public class StackTestHK {
	private Stack<Celebrity> celebritiesStack;
	private Stack<Celebrity> helpStack = new Stack<Celebrity>();
	private Stack<Celebrity> sorted = new Stack<Celebrity>();
	//private Stack<Auto> autoStack;
	
	public StackTestHK(){
		celebritiesStack = Celebrities.celebritiesStack();
		
		//autoStack = Autos.autoStack();
	}
	
	public void ausgeben() {
		System.out.println("*** ausgeben ***");
		//TODO
	}
	
	public int gesamtVermoegen() {
		Stack<Celebrity> helpstack = Celebrities.celebritiesStack();
		int ergebnis = 0;
		while(helpstack.isEmpty() == false) {
			ergebnis += helpstack.top().getVermoegen();
			helpstack.pop();
		}
		helpstack = null;
		System.gc();
		return ergebnis;
	}
	
	public int hoechstesVermoegen() {
		int ergebnis = 0;
		while(celebritiesStack.isEmpty() == false) {
			if(celebritiesStack.top().getVermoegen() > ergebnis) {
				ergebnis = celebritiesStack.top().getVermoegen();
			}
			celebritiesStack.pop();
		}
		return ergebnis;
	}
	
	
	public int vermoegenVon(String pName) {
		int ergebnis = 0;
		while(celebritiesStack.isEmpty() == false) {
			if(celebritiesStack.top().getName().equals(pName)) {
				ergebnis = celebritiesStack.top().getVermoegen();
				break;
			}
			celebritiesStack.pop();
		}
		return ergebnis;
	}
	
	
	public void hinzufuegen(String pName, String pVorname, int pVermoegen) {
		Celebrity c = new Celebrity(pName, pVorname, pVermoegen);
		celebritiesStack.push(c);
	}
	
	
	public int getAnzahl() {
		
		int counter = 0;
		Stack<Celebrity> helpStack = new Stack<>();
		while(!celebritiesStack.isEmpty()) {
			helpStack.push(celebritiesStack.top());
			celebritiesStack.pop();
			counter++;
		}
		while(!helpStack.isEmpty()) {
			celebritiesStack.push(helpStack.top());
			helpStack.pop();
		}
		
		return counter;
	}
	
	
	public void sortierenNachVermoegenArray() {
		Celebrity[] celeArray = new Celebrity[getAnzahl()];
		
		int index = 0;
		while(!celebritiesStack.isEmpty()) {
			
			celeArray[index] = celebritiesStack.top();
			index++;
			celebritiesStack.pop();
			
		}
		Arrays.sort(celeArray);
		
		for (int i=0;i<celeArray.length;i++) {
			celebritiesStack.push(celeArray[i]);
		}
	}

	
	
	
	public void sortierenNachVermoegen() {
		
		Celebrity old_max_c = null;
		int max_v = 0;
		Celebrity max_c = null;
		while(!celebritiesStack.isEmpty() || !helpStack.isEmpty()) {
			old_max_c = max_c;
			max_c = null;
			max_v = 0;
			while(celebritiesStack.isEmpty() == false) {
				if (celebritiesStack.top() != old_max_c) {
					helpStack.push(celebritiesStack.top());
					if (max_v < celebritiesStack.top().getVermoegen()){
						max_v = celebritiesStack.top().getVermoegen();
						max_c = celebritiesStack.top();
								
					}
				}
				
				celebritiesStack.pop();
				
			}
			sorted.push(max_c);
			old_max_c = max_c;
			max_c = null;
			max_v = 0;
			
			while(helpStack.isEmpty()==false) {
				if (helpStack.top() != old_max_c) {
					celebritiesStack.push(helpStack.top());
					if (max_v < helpStack.top().getVermoegen()){
						max_v = helpStack.top().getVermoegen();
						max_c = helpStack.top();
								
					}
				}
				
				helpStack.pop();
			}
			if(max_c != null) {
				sorted.push(max_c);
			}
		}
		while(sorted.isEmpty()==false) {
			celebritiesStack.push(sorted.top());
			sorted.pop();
		}
		
		
	}
	
	
	
	
	
	

	public static void main(String[] args) {
		StackTestHK st = new StackTestHK();
		new GUI(st);
	}
}
