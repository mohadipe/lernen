package de.mohadipe.dynastie.input;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Koordinate;

public class DummyEinheitenAufstellenMenu implements Input {

	private int inputFromMenu;

	public DummyEinheitenAufstellenMenu(int input) {
		this.inputFromMenu = input;
	}

	@Override
	public Koordinate getInputKoordinate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void frageInputAb() {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getInputFromMenu() {
		return this.inputFromMenu;
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
