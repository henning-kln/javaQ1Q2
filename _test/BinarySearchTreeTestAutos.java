package _test;
import gui.GUI;
import sonstiges.Auto;
import sonstiges.Autos;
import linear.List;
import linear.ListWithViewer;
import baeume.BinarySearchTree;
import baeume.BinaryTree;
import baeume.TreeViewer;


public class BinarySearchTreeTestAutos {
	//TODO BinarySearchTree parametrisieren!
	public BinarySearchTree<Auto> autosBaum;
	
	
	public BinarySearchTreeTestAutos(){
		autosBaum = new BinarySearchTree<Auto>();
		ListWithViewer<Auto> autosList = Autos.autoList();
		this.einfuegenListe(autosList);
		TreeViewer.showTree(autosBaum, 1000, 400);
	}
	
	/**
	 * fuegt die Celebrities aus der Liste in autosBaum ein
	 * @param pautosListe
	 */
	private void einfuegenListe(List<Auto> pList){
		System.out.println("einfuegenListe(pList)");
		for(pList.toFirst();pList.hasAccess();pList.next()){
			Auto a = pList.getContent();
			autosBaum.insert(a);

		}
	}
	
	public void einfuegen(String marke, String farbe, int ps, int preis, double tankGroesse){
		Auto a = new Auto(marke, farbe, ps, preis, tankGroesse);
		autosBaum.insert(a);
	}
	
	public Auto finde(int pPs){
		Auto ergebnis = null;
		Auto a = new Auto("", "", pPs, 0, 0);
		ergebnis = autosBaum.search(a);
		return ergebnis;
	}

	public Auto finde(String marke){
		Auto erg = null;
		Auto a = new Auto(marke, "", 0, 0, 0);
		erg = autosBaum.search(a);

		return erg;
	}
	
	public void loeschen(int pPs) {
		Auto a = new Auto("", "", pPs, 0, 0);
		autosBaum.remove(a);
	}
	

	/**
	 * gibt die Elemente von autosBaum sortiert zurueck
	 * @return
	 */
	public List<Auto> sortiert(){
		List<Auto> ergebnis = new ListWithViewer<Auto>();
		BinaryTree<Auto> b = autosBaum.getBinaryTree();
		ergebnis.concat(sortiert(b));
		return ergebnis;
	}


	public List<Auto> sortiert(BinaryTree<Auto> pTree){
		List<Auto> erg = new List<Auto>();
		if(pTree.isEmpty()){
			return erg;
		}

		List<Auto> l = sortiert(pTree.getLeftTree());
		List<Auto> r = sortiert(pTree.getRightTree());
		erg.concat(l);
		erg.append(pTree.getContent());
		erg.concat(r);

		return erg;
	}
	
	public static void main(String[] args) {
		BinarySearchTreeTestAutos bstta = new BinarySearchTreeTestAutos();
		new GUI(bstta);
	}
}
