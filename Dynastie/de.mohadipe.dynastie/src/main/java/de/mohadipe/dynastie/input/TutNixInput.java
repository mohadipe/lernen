package de.mohadipe.dynastie.input;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Koordinate;

/**
 * Default Implementierung, sorgt dafür das speziellere Klassen nicht alle
 * Methoden implementieren müssen. Und man die Methoden aber bereits aufrufen
 * kann also kompatibilität in Klassen welche keinen Input gesetzt bekommen.
 * 
 * @author Mohadipe
 */
public class TutNixInput implements Input {

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
