package de.mohadipe.dynastie.karte;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.RandomService;

public class KartenGenerator {

	private final RandomService randomService;

	public KartenGenerator(RandomService randomService) {
		this.randomService = randomService;
	}

	public void generiereKarte(Konfiguration spielKonfiguration) {
		Karte karte = new MinFuenfMalFuenfKarte();
		((MinFuenfMalFuenfKarte) karte).setMaxX(spielKonfiguration.getSpielFeldMaxX());
		((MinFuenfMalFuenfKarte) karte).setMaxY(spielKonfiguration.getSpielFeldMaxY());
		((MinFuenfMalFuenfKarte) karte).init(randomService);
		spielKonfiguration.setKarte(karte);
	}
}
