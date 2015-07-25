package de.mohadipe.dynastie.input;

import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;

public class DummyBewegungZielKoordinaten extends TutNixInput {

	private Einheit einheit;
	private Koordinate standort;

	@Override
	public Koordinate getInputKoordinate() {
		int bewegungsReichweite = einheit.getBewegungsReichweite();
		int standortX = standort.getX();
		int standortY = standort.getY();
		int zielY = standortY + bewegungsReichweite;
		int zielX = standortX + bewegungsReichweite;
		return new ZweiDimensionaleKoordinate(zielX, zielY);
	}

	@Override
	public void setZuBewegendeEinheit(Einheit einheit) {
		this.einheit = einheit;
	}

	@Override
	public void setAusgangsKoordinateFuerBewegung(Koordinate standort) {
		this.standort = standort;
	}
}