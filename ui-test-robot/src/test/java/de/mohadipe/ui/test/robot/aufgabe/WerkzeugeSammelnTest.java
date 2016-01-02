package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.util.BilderLaden;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WerkzeugeSammelnTest {

	@Test
	public void sammleErfolgreichWerkzeuge() {
		BufferedImage bildMitWerkzeuge = new BilderLaden(null).ladeScreenshotWerkzeugeAbholbereit();
		BufferedImage bildOhneWerkzeuge = new BilderLaden(null).ladeServerRugnirButton();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitWerkzeuge, bildOhneWerkzeuge);
		
		WerkzeugeSammeln werkzeugeSammeln = new WerkzeugeSammeln();
		werkzeugeSammeln.setRobot(robot);
		
		werkzeugeSammeln.ausfuehren();
		
		Assert.assertTrue(werkzeugeSammeln.isErfolgreich());
	}
	
	@Test
	public void keineWerkzeugeZumSammeln() {
		BufferedImage bildOhneWerkzeuge = new BilderLaden(null).ladeServerRugnirButton();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildOhneWerkzeuge);
		
		WerkzeugeSammeln werkzeugeSammeln = new WerkzeugeSammeln();
		werkzeugeSammeln.setRobot(robot);
		
		werkzeugeSammeln.ausfuehren();
		
		Assert.assertFalse(werkzeugeSammeln.isErfolgreich());
	}
}
