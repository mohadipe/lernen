package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.Robot;
import java.awt.event.InputEvent;

import de.mohadipe.ui.test.robot.Koordinaten2D;


public class DoppelKlickeKoordinaten extends AbstractAufgabe {

	private boolean isErfolgreich;

	@Override
	public void ausfuehren() {
		System.out.println("Aufgabe DoppelKlickeKoordinaten wird ausgefuehrt.");
		Aufgabe aufgabe = getAbhaengigeAufgabeByArt(AufgabenArten.GRAFIK_IN_GRAFIK_FINDEN);
		Koordinaten2D koordinaten = (Koordinaten2D) aufgabe.getDaten(AufgabeDaten.KOORDINATE);
		System.out.println("Koordinaten anklicken: " + koordinaten);
		Robot robot = getRobot();
		robot.mouseMove(koordinaten.x, koordinaten.y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		isErfolgreich = true;
	}

	@Override
	public boolean isErfolgreich() {
		return isErfolgreich;
	}

	@Override
	public AufgabenArten getArt() {
		return AufgabenArten.KOORDINATEN_DOPPELKLICKEN;
	}
}
