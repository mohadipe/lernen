package de.mohadipe.dynastie.input;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Koordinate;

public class DummyStartMenu implements Input {

	private int inputFromStartMenu;

	public DummyStartMenu(int input) {
		this.inputFromStartMenu = input;
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
		return inputFromStartMenu;
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
