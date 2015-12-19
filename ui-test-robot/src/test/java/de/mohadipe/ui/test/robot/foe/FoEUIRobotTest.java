package de.mohadipe.ui.test.robot.foe;

import java.awt.AWTException;
import java.awt.image.BufferedImage;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;
import de.mohadipe.ui.test.robot.util.BilderLaden;

public class FoEUIRobotTest {
	
	@Test
	public void holeMuenzeAb() throws AWTException {
		FoEUIRobot foEUIRobot = new FoEUIRobot();
		foEUIRobot.setGrafikPathService(new GrafikDateiPfadeService(true));
		BufferedImage muenzeMitSternAbholbereit = new BilderLaden(null).ladeScreenshotMuenzenAbholbereit();
		foEUIRobot.setScreenCapture(muenzeMitSternAbholbereit);
		foEUIRobot.holeMuenzenAb();
		Assert.assertEquals(1, foEUIRobot.getMuenzenKlickCount());
	}
	
	@Test
	public void holeWerkzeugAb() throws AWTException {
		FoEUIRobot foEUIRobot = new FoEUIRobot();
		foEUIRobot.setGrafikPathService(new GrafikDateiPfadeService(true));
		BufferedImage werkzeugAbholbereit = new BilderLaden(null).ladeScreenshotWerkzeugeAbholbereit();
		foEUIRobot.setScreenCapture(werkzeugAbholbereit);
		foEUIRobot.holeWerkzeugAb();
		Assert.assertEquals(1, foEUIRobot.getWerkzeugeKlickCount());
	}
	
	@Test
	public void produziereWerkzeug() throws AWTException {
		FoEUIRobot foEUIRobot = new FoEUIRobot();
		foEUIRobot.setGrafikPathService(new GrafikDateiPfadeService(true));
		BufferedImage werkzeugProduzierbar = new BilderLaden(null).ladeScreenshotWerkzeugeProduzierbar();
		foEUIRobot.setScreenCapture(werkzeugProduzierbar);
		boolean oeffneProduktion = foEUIRobot.oeffneProduktion();
		Assert.assertTrue(oeffneProduktion);
	}
}
