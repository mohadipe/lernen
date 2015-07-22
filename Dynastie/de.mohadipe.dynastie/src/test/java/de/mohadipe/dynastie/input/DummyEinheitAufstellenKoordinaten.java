package de.mohadipe.dynastie.input;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Koordinate;

public class DummyEinheitAufstellenKoordinaten implements Input {

	private Koordinate inputKoordinate;

	public DummyEinheitAufstellenKoordinaten(Koordinate startKoordinate) {
		this.inputKoordinate = startKoordinate;
	}

	@Override
	public Koordinate getInputKoordinate() {
		return this.inputKoordinate;
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
