package de.mohadipe.dynastie.input;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;

public class DummyZielX2Y1Koordinaten implements Input {
	private Koordinate input = new ZweiDimensionaleKoordinate(2, 1);

	@Override
	public Koordinate getInputKoordinate() {
		return input;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void setAusgangsKoordinateFuerBewegung(Koordinate standort) {
		// TODO Auto-generated method stub

	}

}
