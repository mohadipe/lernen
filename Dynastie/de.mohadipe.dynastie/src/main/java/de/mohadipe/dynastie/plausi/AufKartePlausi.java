package de.mohadipe.dynastie.plausi;

import de.mohadipe.dynastie.karte.Karte;

public class AufKartePlausi {

	private Karte karte;

	public AufKartePlausi(Karte karte) {
		this.karte = karte;
	}

	public boolean isXAufKarte(int x) {
		return karte.getMaxX() >= x && karte.getMinX() <= x;
	}

	public boolean isYAufKarte(int y) {
		return karte.getMaxY() >= y && karte.getMinY() <= y;
	}
}
