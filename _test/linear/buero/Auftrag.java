package _test.linear.buero;

public class Auftrag {
	
	private String text;
	private String chefName;
	private boolean dringend;
	
	
	public Auftrag(String text, String chefName, boolean dringend) {
		super();
		this.text = text;
		this.chefName = chefName;
		this.dringend = dringend;
	}


	public String getText() {
		return text;
	}


	public String getChefName() {
		return chefName;
	}


	public boolean isDringend() {
		return dringend;
	}


	@Override
	public String toString() {
		return "[\""+text+"\"|"+dringend+"]";
	}
	

}
