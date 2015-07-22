package de.mohadipe.dynastie.workflow;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.RandomService;
import de.mohadipe.dynastie.input.Input;
import de.mohadipe.dynastie.karte.FuenfMalFuenfKarte;
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
		// Karte anzeigen
		// Siegmarkierungen auf Karte anzeigen
		FuenfMalFuenfKarte fuenfMalFuenfKarte = new FuenfMalFuenfKarte(spielKonfiguration, randomService);
		fuenfMalFuenfKarte.anzeigen();
		// Computer-Einheiten auf Karte anzeigen
		Spieler player1 = spielKonfiguration.getSpieler(0);
		player1.stelleEinheitenAuf(fuenfMalFuenfKarte, spielKonfiguration, aufstellenInput);
		// Spieler stellt Einheiten auf
		Spieler player2 = spielKonfiguration.getSpieler(1);
		player2.stelleEinheitenAuf(fuenfMalFuenfKarte, spielKonfiguration, aufstellenInput);
		// nächster Schritt -> Beginn der SpielZüge
		fuenfMalFuenfKarte.anzeigen();
		input.frageInputAb();
		spielKonfiguration.setKarte(fuenfMalFuenfKarte);
	}

	@Override
	public int naechsterSchritt() {
		return input.getInputFromMenu();
	}
}
