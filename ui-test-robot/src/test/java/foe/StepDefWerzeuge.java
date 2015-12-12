package foe;

import java.awt.AWTException;
import java.awt.image.BufferedImage;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.mohadipe.ui.test.robot.foe.FoEUIRobot;
import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;
import de.mohadipe.util.BilderLaden;

public class StepDefWerzeuge {
	
	private BufferedImage screenShot;
	private long werkzeugeKlickCount = 0;
	
	@Given("^Die Werkzeugproduktion einer Schmiede ist fertig\\.$")
	public void dieWerkzeugproduktionEinerSchmiedeIstFertig() {
		screenShot = new BilderLaden().ladeScreenshotWerkzeugeAbholbereit();
		Assert.assertNotNull(screenShot);
	}
	
	@Given("^Die Werkzeugproduktion einer Schmiede mit Stern ist fertig\\.$")
	public void dieWerkzeugproduktionEinerSchmideMitSternIstFertig() {
	}

	@When("^Nach fertigen Werkzeugproduktionen gesucht wird\\.$")
	public void nachFertigenWerkzeugproduktionenGesuchtWird() {
		try {
			FoEUIRobot foEUIRobot = new FoEUIRobot();
			foEUIRobot.setScreenCapture(screenShot);
			foEUIRobot.setGrafikPathService(new GrafikDateiPfadeService(true));
			foEUIRobot.holeWerkzeugAb();
			werkzeugeKlickCount = foEUIRobot.getWerkzeugeKlickCount();
		} catch (AWTException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Then("^Die fertige Werkzeugproduktion gefunden\\.$")
	public void dieFertigeWerkzeugproduktionGefunden() {
		Assert.assertEquals(1, werkzeugeKlickCount);
	}
}
