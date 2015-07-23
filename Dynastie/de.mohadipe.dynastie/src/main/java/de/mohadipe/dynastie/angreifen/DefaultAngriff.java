package de.mohadipe.dynastie.angreifen;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.RandomService;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Feld;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.output.Output;
import de.mohadipe.dynastie.output.TutNixOutput;
import de.mohadipe.dynastie.spieler.Spieler;

public class DefaultAngriff implements Angreifen {
	private Spieler spieler;
	private RandomService randomService;
	private Konfiguration konfiguration;
	private Output output = new TutNixOutput();

	@Override
	public void angreifen() {
		// Eigene Einheiten (aktueller Spieler)
		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = konfiguration.getKarte().getEinheitenMitKoordinatenVonSpieler(spieler);

		// Je Einheit Ziele ermitteln
		GegnerEinheitenErmittler gegnerEinheitenErmittler = new GegnerEinheitenErmittler(konfiguration.getKarte());
		einheitenMitKoordinatenVonSpieler.entrySet().forEach(gegnerEinheitenErmittler);
		Map<Einheit, List<Feld>> gegnerZuEinheit = gegnerEinheitenErmittler.getGegnerZuEinheit();
		output.anzeigeGegnerInReichweite(gegnerZuEinheit);
		// Je Einheit angriff auf Ziel(e) ausfuehren
		for (Entry<Einheit, List<Feld>> entry : gegnerZuEinheit.entrySet()) {
			if (entry.getValue().size() > 0) {
				Einheit angreifer = entry.getKey();
				// kein Algorithmus fuer Zielermittlung
				Feld ziel = entry.getValue().get(0);
				output.einheitBekaempfen(angreifer, ziel);
				angriffAusfuehren(angreifer, ziel);
			}
		}
	}

	private void angriffAusfuehren(Einheit angreifer, Feld ziel) {
		Kampf kampf = new Kampf(angreifer, ziel);
		kampf.setRandomService(randomService);
		kampf.setOutput(output);
		kampf.ausfuehren();
	}

	@Override
	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}

	@Override
	public void setKarte(Karte karte) {
		throw new UnsupportedOperationException("Karte wird nicht benoetigt.");
	}

	@Override
	public void setKonfiguration(Konfiguration konfiguration) {
		this.konfiguration = konfiguration;
	}

	public void setRandomService(RandomService randomService) {
		this.randomService = randomService;
	}

	@Override
	public void setOutput(Output output) {
		this.output = output;
	}
}
