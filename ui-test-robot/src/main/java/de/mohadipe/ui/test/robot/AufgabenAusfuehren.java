package de.mohadipe.ui.test.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import de.mohadipe.ui.test.robot.aufgabe.Aufgabe;
import de.mohadipe.ui.test.robot.aufgabe.AufgabeDaten;
import de.mohadipe.ui.test.robot.protokoll.Protokoll;

public class AufgabenAusfuehren {
	
	private List<Aufgabe> aufgaben = new ArrayList<Aufgabe>();
	private Protokoll protokoll = new Protokoll();
	private Logger logger;

	
	public void addAufgabe(Aufgabe aufgabe) {
		aufgaben.add(aufgabe);
	}

	public Protokoll getProtokoll() {
		return protokoll;
	}

	public void fuehreAufgabenAus() {
		int reihenfolge = 0;
		protokoll.reset();
		for (Aufgabe aufgabe : aufgaben) {
			aufgabe.setDaten(AufgabeDaten.LOGGER, this.logger);
			aufgabe.ausfuehren();
			aufgabe.setPositionAusfuehrung(reihenfolge);
			protokoll.addAusgefuehrteAufgabe(aufgabe);
			reihenfolge++;
		}
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
}
