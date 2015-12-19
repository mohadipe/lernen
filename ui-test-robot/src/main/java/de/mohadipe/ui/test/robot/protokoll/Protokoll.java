package de.mohadipe.ui.test.robot.protokoll;

import java.util.ArrayList;
import java.util.List;

import de.mohadipe.ui.test.robot.aufgabe.Aufgabe;

public class Protokoll {

	private List<Aufgabe> ausgefuehrteAufgaben = new ArrayList<Aufgabe>();
	
	public boolean isAufgabeAusgefuehrt(Aufgabe checkAufgabe) {
		return this.ausgefuehrteAufgaben.contains(checkAufgabe);
	}

	public void addAusgefuehrteAufgabe(Aufgabe aufgabe) {
		this.ausgefuehrteAufgaben.add(aufgabe);
	}

	public void reset() {
		this.ausgefuehrteAufgaben.clear();
	}

	public boolean isAufgabeErfolgreichAusgefuehrt(
			Aufgabe erfolgreicheAufgabe) {
		if (this.ausgefuehrteAufgaben.contains(erfolgreicheAufgabe)) {
			Aufgabe aufgabe = this.ausgefuehrteAufgaben.get(this.ausgefuehrteAufgaben.indexOf(erfolgreicheAufgabe));
			return aufgabe.isErfolgreich();
		}
		return false;
	}

	public int wannWurdeAufgabeAusgefuehrt(Aufgabe finde) {
		Aufgabe aufgabe = this.ausgefuehrteAufgaben.get(this.ausgefuehrteAufgaben.indexOf(finde));
		return aufgabe.getPositionAusfuehrung();
	}

	public boolean alleAufgabenErfolgreich() {
		for (Aufgabe aufgabe : ausgefuehrteAufgaben) {
			if (!aufgabe.isErfolgreich()) {
				return false;
			}
		}
		return true;
	}
}
