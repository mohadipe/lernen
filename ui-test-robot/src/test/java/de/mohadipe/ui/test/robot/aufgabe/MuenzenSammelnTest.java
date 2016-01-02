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

public class MuenzenSammelnTest {

	@Test
	public void sammleErfolgreichMuenzen() {
		BufferedImage bildMitMuenzen = new BilderLaden(null).ladeScreenshotMuenzenAbholbereit();
		BufferedImage bildOhneMuenzen = new BilderLaden(null).ladeServerRugnirButton();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitMuenzen, bildOhneMuenzen);
		
		MuenzenSammeln muenzenSammeln = new MuenzenSammeln();
		muenzenSammeln.setRobot(robot);
		
		muenzenSammeln.ausfuehren();
		
		Assert.assertTrue(muenzenSammeln.isErfolgreich());
	}
	
	@Test
	public void keineMuenzenZumSammeln() {
		BufferedImage bildOhneMuenzen = new BilderLaden(null).ladeServerRugnirButton();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildOhneMuenzen);
		
		MuenzenSammeln muenzenSammeln = new MuenzenSammeln();
		muenzenSammeln.setRobot(robot);
		
		muenzenSammeln.ausfuehren();
		
		Assert.assertFalse(muenzenSammeln.isErfolgreich());
	}
}
