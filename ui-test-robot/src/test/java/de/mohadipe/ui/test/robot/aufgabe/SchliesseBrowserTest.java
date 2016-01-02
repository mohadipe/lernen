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

public class SchliesseBrowserTest {

	@Test
	public void schliesseBrowserErfolgreich() {
		BufferedImage bildMitBrowser = new BilderLaden(null).ladeScreenshotWerkzeugeProduzierbar();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitBrowser);
		
		AbstractAufgabe schliesseBrowser = new SchliesseBrowser();
		schliesseBrowser.setRobot(robot);
		
		schliesseBrowser.ausfuehren();
		
		Assert.assertTrue(schliesseBrowser.isErfolgreich());
	}
	
	@Test
	public void schliesseBrowserNichtErfolgreich() {
		BufferedImage bildOhneBrowser = new BilderLaden(null).ladeScreenShotFoEVerknuepfung();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildOhneBrowser);
		
		AbstractAufgabe schliesseBrowser = new SchliesseBrowser();
		schliesseBrowser.setRobot(robot);
		
		schliesseBrowser.ausfuehren();
		
		Assert.assertFalse(schliesseBrowser.isErfolgreich());
	}
}
