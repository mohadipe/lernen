package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.junit.Test;
import org.mockito.internal.verification.VerificationModeFactory;

import de.mohadipe.ui.test.robot.Koordinaten2D;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class KlickeKoordinatenTest {
	@Test
	public void klickeKoordinatenAbhaengigVonFindeGrafik() throws AWTException {
		Aufgabe klickeKoordinaten = new DoppelKlickeKoordinaten();
		Aufgabe grafikFinden = mock(FindeGrafikInGrafik.class);
		when(grafikFinden.getArt()).thenReturn(AufgabenArten.GRAFIK_IN_GRAFIK_FINDEN);
		when(grafikFinden.getDaten(AufgabeDaten.KOORDINATE)).thenReturn(new Koordinaten2D());
		klickeKoordinaten.addAbhaengigkeit(grafikFinden);
		klickeKoordinaten.setRobot(mock(Robot.class));

		klickeKoordinaten.ausfuehren();
		
		verify(grafikFinden).getDaten(AufgabeDaten.KOORDINATE);
	}
	
	@Test
	public void robotKlicktKoordinaten() {
		Aufgabe klickeKoordinaten = new DoppelKlickeKoordinaten();
		Robot robot = mock(Robot.class);
		Aufgabe grafikFinden = mock(FindeGrafikInGrafik.class);
		when(grafikFinden.getArt()).thenReturn(AufgabenArten.GRAFIK_IN_GRAFIK_FINDEN);
		Koordinaten2D koordinate = new Koordinaten2D();
		koordinate.x = 1;
		koordinate.y = 2;
		when(grafikFinden.getDaten(AufgabeDaten.KOORDINATE)).thenReturn(koordinate);
		klickeKoordinaten.setRobot(robot);
		klickeKoordinaten.addAbhaengigkeit(grafikFinden);
		
		klickeKoordinaten.ausfuehren();
		
		verify(robot).mouseMove(1,2);
		verify(robot, VerificationModeFactory.times(2)).mousePress(InputEvent.BUTTON1_DOWN_MASK);
		verify(robot, VerificationModeFactory.times(2)).mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
}
