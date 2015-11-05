package de.mohadipe.dynastie.sieg;

import de.mohadipe.dynastie.spieler.Spieler;

public class Sieger {
	private enum SiegArt {
		EINDEUTIG, PATT;
	}

	private SiegArt siegArt;
	private Spieler sieger;
	
	public void setIsPatt() {
		this.siegArt = SiegArt.PATT;
		this.sieger = null;
	}
	
	public void setIsEindeutig(Spieler spieler) {
		this.siegArt = SiegArt.EINDEUTIG;
		this.sieger = spieler;
	}
	
	public boolean isPatt() {
		return siegArt.equals(SiegArt.PATT);
	}
	
	public boolean isEindeutig() {
		return siegArt.equals(SiegArt.EINDEUTIG);
	}
	
	public Spieler getSieger() {
		return this.sieger;
	}
}
