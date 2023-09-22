package _test.linear.buero;

import linear.Queue;
import linear.QueueWithViewer;

public class Buero {

	private Chef[] dieChefs;
	private Sachbearbeiter[] dieSachbearbeiter;
	private Queue<Auftrag> ablage;
	private Queue<Auftrag> dringende_ablage; 

	public Buero() {
		ablage = new QueueWithViewer<>();
		dringende_ablage = new QueueWithViewer<>();
		// die Chefs erzeugen
		dieChefs = new Chef[2];
		dieChefs[0] = new Chef("Sabine", this);    
		dieChefs[1] = new Chef("Otto", this);
		
		
		// die Sachbearbeiter erzeugen
		dieSachbearbeiter = new Sachbearbeiter[3];
		dieSachbearbeiter[0] = new Sachbearbeiter("Georg", this);
		dieSachbearbeiter[1] = new Sachbearbeiter("Luise", this);
		dieSachbearbeiter[2] = new Sachbearbeiter("Christoph", this);
	}
	
	public void erstelleAuftrag(Auftrag pAuftrag) {
		if (pAuftrag.isDringend()) {
			dringende_ablage.enqueue(pAuftrag);
		}else {
			ablage.enqueue(pAuftrag);
		}
	}
	
	public Auftrag getAuftrag() {
		if (!dringende_ablage.isEmpty()) {
			Auftrag a = dringende_ablage.front();
			dringende_ablage.dequeue();
			return a;
		}else if (!ablage.isEmpty()) {
			Auftrag a = ablage.front();
			ablage.dequeue();
			return a;
		}else {
			return null;
		}
	}
}
