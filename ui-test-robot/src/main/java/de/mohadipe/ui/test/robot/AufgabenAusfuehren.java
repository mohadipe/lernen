package de.mohadipe.ui.test.robot;

import java.util.ArrayList;
import java.util.List;

import de.mohadipe.ui.test.robot.aufgabe.Aufgabe;
import de.mohadipe.ui.test.robot.protokoll.Protokoll;

public class AufgabenAusfuehren {
	
	private List<Aufgabe> aufgaben = new ArrayList<Aufgabe>();
	private Protokoll protokoll = new Protokoll();
	
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
			aufgabe.ausfuehren();
			aufgabe.setPositionAusfuehrung(reihenfolge);
			protokoll.addAusgefuehrteAufgabe(aufgabe);
			reihenfolge++;
		}
	}
	
	public void clearAufgaben() {
		aufgaben.clear();
	}
}
