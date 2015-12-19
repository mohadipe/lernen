package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.protokoll.Protokoll;
import de.mohadipe.ui.test.robot.util.BilderLaden;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class FoEAufRugnirStartenTest {

	@Test
	public void erfolgreichStarten() {
		FoEAufRugnirStarten foEAufRugnirStarten = new FoEAufRugnirStarten();
		Robot robot = mock(Robot.class);
		BufferedImage bild1 = new BilderLaden(null).ladeScreenShotFoEVerknuepfung();
		BufferedImage bild2 = new BilderLaden(null).ladeStartseiteFoE();
		BufferedImage bild3 = new BilderLaden(null).ladeAuswahlServerRugnir();
		when(robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bild1, bild2, bild3);
		foEAufRugnirStarten.setRobot(robot);
		foEAufRugnirStarten.ausfuehren();
		Assert.assertTrue(foEAufRugnirStarten.isErfolgreich());
		Protokoll protokoll = (Protokoll) foEAufRugnirStarten.getDaten(AufgabeDaten.PROTOKOLL);
		Assert.assertFalse(protokoll.alleAufgabenErfolgreich());
	}
}
