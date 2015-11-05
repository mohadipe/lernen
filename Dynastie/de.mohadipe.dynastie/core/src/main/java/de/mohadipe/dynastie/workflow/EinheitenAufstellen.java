package de.mohadipe.dynastie.workflow;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.RandomService;
import de.mohadipe.dynastie.input.Input;
import de.mohadipe.dynastie.karte.KartenGenerator;
import de.mohadipe.dynastie.spieler.Spieler;

public class EinheitenAufstellen implements Schritt {

	private Konfiguration spielKonfiguration;
	private RandomService randomService;
	private Input input;
	private Input aufstellenInput;

	public EinheitenAufstellen(Konfiguration spielKonfiguration, RandomService randomService, Input menu, Input aufstellenInput) {
		this.spielKonfiguration = spielKonfiguration;
		this.randomService = randomService;
		this.input = menu;
		this.aufstellenInput = aufstellenInput;
	}

	@Override
	public void anzeigen() {
		KartenGenerator kartenGenerator = new KartenGenerator(randomService);
		kartenGenerator.generiereKarte(spielKonfiguration);

		Spieler player1 = spielKonfiguration.getSpieler(0);
		player1.stelleEinheitenAuf(spielKonfiguration, aufstellenInput);
		Spieler player2 = spielKonfiguration.getSpieler(1);
		player2.stelleEinheitenAuf(spielKonfiguration, aufstellenInput);

		input.frageInputAb();
	}

	@Override
	public int naechsterSchritt() {
		return input.getInputFromMenu();
	}
}
