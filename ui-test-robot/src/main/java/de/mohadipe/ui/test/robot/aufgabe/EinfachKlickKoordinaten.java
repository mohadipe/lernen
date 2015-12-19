package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.Robot;
import java.awt.event.InputEvent;

import de.mohadipe.ui.test.robot.Koordinaten2D;

public class EinfachKlickKoordinaten extends AbstractAufgabe {

	private boolean isErfolgreich;

	@Override
	public void ausfuehren() {
		Aufgabe aufgabe = getAbhaengigeAufgabeByArt(AufgabenArten.GRAFIK_IN_GRAFIK_FINDEN);
		if (aufgabe.isErfolgreich()) {
			Koordinaten2D koordinaten = (Koordinaten2D) aufgabe
					.getDaten(AufgabeDaten.KOORDINATE);
			Robot robot = getRobot();
			robot.mouseMove(koordinaten.x, koordinaten.y);
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			isErfolgreich = true;
		} else {
			isErfolgreich = false;
		}
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
