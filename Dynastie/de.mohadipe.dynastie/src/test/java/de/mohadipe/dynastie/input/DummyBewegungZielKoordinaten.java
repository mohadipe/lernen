package de.mohadipe.dynastie.input;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;

public class DummyBewegungZielKoordinaten implements Input {

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
	public void frageInputAb() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getInputFromMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setKonfiguration(Konfiguration spielKonfiguration) {
		// TODO Auto-generated method stub

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