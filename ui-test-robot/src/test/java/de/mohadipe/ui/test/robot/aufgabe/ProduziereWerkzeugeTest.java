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

public class ProduziereWerkzeugeTest {

	@Test
	public void starteProduktionErfolgreich() {
		BufferedImage bildMitFreierProd = new BilderLaden(null).ladeScreenshotWerkzeugeProduzierbar();
		BufferedImage produktionsArt = new BilderLaden(null).ladeScreenshotSchmiedeProduktion();
		BufferedImage bildOhneFreieProd = new BilderLaden(null).ladeServerRugnirButton();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitFreierProd, produktionsArt, bildOhneFreieProd);
		
		ProduziereWerkzeug produziereWerkzeug = new ProduziereWerkzeug();
		produziereWerkzeug.setRobot(robot);
		
		produziereWerkzeug.ausfuehren();
		
		Assert.assertTrue(produziereWerkzeug.isErfolgreich());
	}
	
	@Test
	public void starteProduktionNichtErfolgreich() {
		BufferedImage bildOhneFreieProd = new BilderLaden(null).ladeServerRugnirButton();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildOhneFreieProd);
		
		ProduziereWerkzeug produziereWerkzeug = new ProduziereWerkzeug();
		produziereWerkzeug.setRobot(robot);
		
		produziereWerkzeug.ausfuehren();
		
		Assert.assertFalse(produziereWerkzeug.isErfolgreich());
	}
}
