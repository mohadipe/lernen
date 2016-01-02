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

public class SchliessePopUpTest {

	@Test
	public void schliessePopUpErfolgreich() {
		BufferedImage bildMitPopUp = new BilderLaden(null).ladeScreenshotSchmiedeProduktion();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitPopUp);
		
		SchliessePopUp schliessePopUp = new SchliessePopUp();
		schliessePopUp.setRobot(robot);
		
		schliessePopUp.ausfuehren();
		
		Assert.assertTrue(schliessePopUp.isErfolgreich());
	}
	
	@Test
	public void schliessePopUpNichtErfolgreich() {
		BufferedImage bildMitBrowser = new BilderLaden(null).ladeScreenShotFoEVerknuepfung();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitBrowser);
		
		SchliessePopUp schliessePopUp = new SchliessePopUp();
		schliessePopUp.setRobot(robot);
		
		schliessePopUp.ausfuehren();
		
		Assert.assertFalse(schliessePopUp.isErfolgreich());
	}
}
