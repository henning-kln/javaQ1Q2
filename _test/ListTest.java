package _test;

import gui.GUI;
import linear.List;
import linear.ListWithViewer;
import sonstiges.Autos;
import sonstiges.Celebrities;
import sonstiges.Celebrity;
import sonstiges.Person;
import sonstiges.Auto;

public class ListTest {
	private List<Celebrity> celebritiesList;
	//private List<Auto> autoList;
	
	public ListTest(){
		celebritiesList = Celebrities.celebritiesList();
		//autoList = Autos.autoList(); 
	}
	
	public void ausgeben() {
		System.out.println("*** ausgeben ***");
		celebritiesList.toFirst();
		while (celebritiesList.hasAccess()) {
			System.out.println(celebritiesList.getContent());
			celebritiesList.next();
		}
	}
	
	
	
	public Celebrity derReichste() {
		
		celebritiesList.toFirst();
		Celebrity r = celebritiesList.getContent();
		celebritiesList.next();
		while(celebritiesList.hasAccess()) {
			if (celebritiesList.getContent().getVermoegen() > r.getVermoegen()) {
				r = celebritiesList.getContent();
			}
			celebritiesList.next();
		}
		
		return r;
	}
	
	public void loeschen(Celebrity c) {
		celebritiesList.toFirst();
		while (celebritiesList.hasAccess()) {
			if(celebritiesList.getContent() == c) {
				celebritiesList.remove();
				break;
			}
			celebritiesList.next();
		}
	}
	
	
	public void insertionSort() {
		List<Celebrity> l = new List<>();
		
		celebritiesList.toFirst();
		l.insert(celebritiesList.getContent());
		celebritiesList.remove();
		
		
		while (celebritiesList.hasAccess()) {
			Celebrity c = celebritiesList.getContent();
			celebritiesList.remove();
			l.toFirst();
			
			
			
			// setze c zum richtigen platz
			while (l.hasAccess()) {
				if (l.getContent().getVermoegen() < c.getVermoegen()) {
					l.insert(c);
					break;
				}
				l.next();	
			}
		}
		
		
		// Return to list
		l.toFirst();
		while (l.hasAccess()) {
			celebritiesList.append(l.getContent());
			l.remove();
		}
	}

	
	
	
	public static void main(String[] args) {
		ListTest lt = new ListTest();
		new GUI(lt);
	}
}
