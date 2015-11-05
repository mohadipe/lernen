package de.mohadipe.dynastie.workflow;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.Siegbedingung;
import de.mohadipe.dynastie.angreifen.Angreifen;
import de.mohadipe.dynastie.angreifen.GegnerAngreifen;
import de.mohadipe.dynastie.bewegen.Bewegung;
import de.mohadipe.dynastie.bewegen.EinheitenBewegen;
import de.mohadipe.dynastie.input.Input;
import de.mohadipe.dynastie.output.Output;
import de.mohadipe.dynastie.sieg.CheckSieg;
import de.mohadipe.dynastie.sieg.CheckSiegBedingungen;
import de.mohadipe.dynastie.spieler.Spieler;

/**
 * @author Mohadipe
 *
 *         Verwaltung/Steuerung der Spieler Spielzeuge
 *
 */
public class SpielZuege implements Schritt {

	private Konfiguration konfiguration;
	private Workflow spielWorkflow;
	private Input input;
	private Output output;
	private int spielZug;

	public SpielZuege(Konfiguration konfiguration, Workflow spielWorkflow, Input input, Output output) {
		this.spielWorkflow = spielWorkflow;
		this.konfiguration = konfiguration;
		this.input = input;
		this.output = output;
	}

	@Override
	public void anzeigen() {
		// Wie lange wechseln die Spielzuege? Bis eine Siegebdinung erreicht
		// ist.
		int nrSpieler = 0;
		CheckSieg sieg = checkSiegbedinungErfuellt();
		spielZug = 1;
		while (!sieg.isSpielBeendet()) {
			// Welcher Spieler ist an der Reihe?
			Spieler spieler = konfiguration.getSpieler(nrSpieler);
			// Ein Spielzug hat zwei Aktionen
			// 1. Bewegung aller Einheiten
			Bewegung bewegung = EinheitenBewegen.creaeInstanceFuerSpieler(spieler, input);
			bewegung.setSpieler(spieler);
			bewegung.setKarte(konfiguration.getKarte());
			bewegung.bewegen();
			// 2. Angriff fuer alle Einheiten
			Angreifen angreifen = GegnerAngreifen.createInstance();
			angreifen.setSpieler(spieler);
			angreifen.setKonfiguration(konfiguration);
			angreifen.setRandomService(spielWorkflow.getRandomService());
			angreifen.setOutput(output);
			angreifen.angreifen();
			// naechster Spieler
			sieg.auswerten();
			nrSpieler = getNaechstenSpieler(nrSpieler, spielZug);
			if (spielZug > 10000) {
				throw new RuntimeException("Vermutliche Endlosschleife. Mehr als 10000 Runden gespielt");
			}
		}
	}

	private int getNaechstenSpieler(int nrSpieler, int runde) {
		if ((konfiguration.getAnzSpieler() - 1) > nrSpieler) {
			return ++nrSpieler;
		}
		output.anzeigeSpielZugEnde(runde, konfiguration);
		spielZug++;
		return 0;
	}

	private CheckSieg checkSiegbedinungErfuellt() {
		Siegbedingung siegbedingung = konfiguration.getSieg();
		CheckSieg checker = CheckSiegBedingungen.getInstance(siegbedingung);
		checker.setKarte(konfiguration.getKarte());
		checker.setSpieler(konfiguration.getAlleSpieler());
		checker.auswerten();
		return checker;
	}

	@Override
	public int naechsterSchritt() {
		return 999;
	}

	public int getGespielteRunden() {
		return this.spielZug;
	}

}
