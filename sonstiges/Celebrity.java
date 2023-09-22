package sonstiges;

import baeume.ComparableContent;

public class Celebrity extends Person implements Comparable{
	private int vermoegen;
	
	public Celebrity(String pName, String pVorname, int pVermoegen){
		super(pName, pVorname);
		this.vermoegen = pVermoegen;
	}

	
	public int getVermoegen() {
		return vermoegen;
	}

	public void setVermoegen(int pVermoegen) {
		this.vermoegen = pVermoegen;
	}
	
	public String toString(){
		return (super.toString()+", "+this.vermoegen);
	}

	public boolean istAlphabetischNach(Celebrity pPerson) {
		return this.getName().compareTo(pPerson.getName()) > 0;
	}


	@Override
	public int compareTo(Object o) {
		Celebrity c = (Celebrity) o;
		if (c.getVermoegen() < this.getVermoegen()) {
			return 1;
		}else if (c.getVermoegen() == this.getVermoegen()) {
			return 0;
			
		}else if(c.getVermoegen() > this.getVermoegen()) {
			return -1;
		}else {
			return 0
					;
		}
	}
	



}
