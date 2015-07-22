package de.mohadipe.dynastie.workflow;


public class UnbekannterSchritt implements Schritt {

	@Override
	public void anzeigen() {
		System.out.println("Nichts weiter implementiert!");
	}

	@Override
	public int naechsterSchritt() {
		return 2;
	}

}
