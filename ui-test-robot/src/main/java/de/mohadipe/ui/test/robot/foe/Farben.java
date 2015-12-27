package de.mohadipe.ui.test.robot.foe;

public enum Farben {
	WERKZEUG(-6732800), MUENZE(-10083840), SCHLAFEN(-4482714);
	
	private final int farbe;
	
	private Farben(int farbe) {
		this.farbe = farbe;
	}
	
	public int getFarbe() {
		return this.farbe;
	}
}
