package de.mohadipe.dynastie.bewegen;

import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;

import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.input.Input;
import de.mohadipe.dynastie.karte.Feld;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.plausi.UmkreisPlausi;
import de.mohadipe.dynastie.spieler.Spieler;

public class MenschBewegung implements Bewegung {
	private Karte karte;
	private Spieler spieler;
	private final Input inputZielKoordinaten;

	@Inject
	public MenschBewegung(final Input input) {
		this.inputZielKoordinaten = input;
	}

	public void setKarte(Karte karte) {
		this.karte = karte;
	}

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}

	public void bewegen() {
		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = karte.getEinheitenMitKoordinatenVonSpieler(spieler);
		for (Entry<Koordinate, Feld> entry : einheitenMitKoordinatenVonSpieler.entrySet()) {
			Feld feld = entry.getValue();
			Einheit einheit = feld.getEinheit();
			bewegeEinheitVon(entry.getKey(), einheit);
		}
	}

	private void bewegeEinheitVon(Koordinate standort, Einheit einheit) {
		int bewegungsReichweite = einheit.getBewegungsReichweite();
		Koordinate ziel = getZielKoordinaten(standort, einheit);
		while (!einheitReichweiteFuerZiel(ziel, standort, bewegungsReichweite)) {
			ziel = getZielKoordinaten(standort, einheit);
		}
		karte.bewegeEinheitVonNach(einheit, standort, ziel);
	}

	private boolean einheitReichweiteFuerZiel(Koordinate ziel, Koordinate standort, int bewegungsReichweite) {
		UmkreisPlausi bewegungsPlausi = new UmkreisPlausi(ziel, standort, bewegungsReichweite);
		return bewegungsPlausi.check();
	}

	private Koordinate getZielKoordinaten(Koordinate standort, Einheit einheit) {
		inputZielKoordinaten.setZuBewegendeEinheit(einheit);
		inputZielKoordinaten.setAusgangsKoordinateFuerBewegung(standort);
		inputZielKoordinaten.frageInputAb();
		return inputZielKoordinaten.getInputKoordinate();
	}
}
