package de.mohadipe.dynastie.bewegen;

import java.util.Map;
import java.util.Map.Entry;

import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Feld;
import de.mohadipe.dynastie.logik.model.Koordinate;
import de.mohadipe.dynastie.spieler.Spieler;

public abstract class AbstractBewegung implements Bewegung {

	protected Karte karte;
	private Spieler computerSpieler;

	@Override
	public void bewegen() {
		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = karte.getEinheitenMitKoordinatenVonSpieler(computerSpieler);
		for (Entry<Koordinate, Feld> entry : einheitenMitKoordinatenVonSpieler.entrySet()) {
			Feld feld = entry.getValue();
			Einheit einheit = feld.getEinheit();
			bewegeEinheitVon(entry.getKey(), einheit);
		}
	}

	protected abstract void bewegeEinheitVon(Koordinate key, Einheit einheit);

	@Override
	public void setKarte(Karte karte) {
		this.karte = karte;
	}

	@Override
	public void setSpieler(Spieler spieler) {
		this.computerSpieler = spieler;
	}

}
