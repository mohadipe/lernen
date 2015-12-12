package de.mohadipe.dynastie.karte;

import de.mohadipe.dynastie.logik.model.Koordinate;

public class AufmarschZone {

	private Koordinate koordiante;
	private boolean isNichtBesetzt = true;

	public AufmarschZone(Koordinate zweiDimensionaleKoordinate) {
		this.koordiante = zweiDimensionaleKoordinate;
	}

	public Koordinate freieKoordinate() {
		if (isNichtBesetzt) {
			isNichtBesetzt = false;
			return this.koordiante;
		}
		return null;
	}

}
