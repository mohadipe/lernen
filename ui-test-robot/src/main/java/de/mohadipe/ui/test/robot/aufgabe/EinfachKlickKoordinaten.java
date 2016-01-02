package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.Robot;
import java.awt.event.InputEvent;

import de.mohadipe.ui.test.robot.Koordinaten2D;

public class EinfachKlickKoordinaten extends AbstractAufgabe {

	private boolean isErfolgreich;

	@Override
	public void ausfuehren() {
		System.out.println("Einfach Klicken");
		Aufgabe aufgabe = getAbhaengigeAufgabeByArt(AufgabenArten.GRAFIK_IN_GRAFIK_FINDEN);
		Integer abweichungY = ((Integer) getDaten(AufgabeDaten.ABWEICHUNG_Y));
		if (aufgabe != null && aufgabe.isErfolgreich()) {
			System.out.println("Gefundene Koordinaten klicken.");
			Koordinaten2D koordinaten = (Koordinaten2D) getDaten(AufgabeDaten.KOORDINATE);
			if (koordinaten == null) {
				System.out.println("Koordinaten aus AbhängigerAufgabe klicken");
				koordinaten = (Koordinaten2D) aufgabe.getDaten(AufgabeDaten.KOORDINATE);
			}
			klickeKoordinatenAn(abweichungY, koordinaten);
			isErfolgreich = true;	
		} else {
			isErfolgreich = false;
		}
	}

	private void klickeKoordinatenAn(Integer abweichungY,
			Koordinaten2D koordinaten) {
		Robot robot = getRobot();
		int mouseOnX = koordinaten.x;
		int mouseOnY = koordinaten.y;
		System.out.println("Zu klickende Koordinaten X: " + mouseOnX + " Y: " + mouseOnY);
		if (abweichungY != null) {
			mouseOnY = koordinaten.y + abweichungY.intValue();
		}
		System.out.println("Mit Abweichung X: " + mouseOnX + " Y: " + mouseOnY);
		robot.mouseMove(mouseOnX, mouseOnY);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
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
