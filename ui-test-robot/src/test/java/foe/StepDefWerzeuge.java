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
	private boolean klickErfolgreich = false;

	@Given("^Die Werkzeugproduktion einer Schmiede ist fertig\\.$")
	public void dieWerkzeugproduktionEinerSchmiedeIstFertig() {
		screenShot = new BilderLaden().ladeScreenshotWerkzeugeAbholbereit();
		Assert.assertNotNull(screenShot);
	}

	@Given("^Die Werkzeugproduktion einer Schmiede mit Stern ist fertig\\.$")
	public void dieWerkzeugproduktionEinerSchmideMitSternIstFertig() {
	}

	@Given("^Ein Schmiede hat keine aktive Produktion\\.$")
	public void eineSchmiedeHatKeineAktiveProduktion() {
		screenShot = new BilderLaden().ladeScreenshotWerkzeugeProduzierbar();
		Assert.assertNotNull(screenShot);
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

	@When("^Die Schmiede angeklickt wird\\.$")
	public void dieSchmiedeAngeklicktWird() {
		try {
			FoEUIRobot foEUIRobot = new FoEUIRobot();
			foEUIRobot.setScreenCapture(screenShot);
			foEUIRobot.setGrafikPathService(new GrafikDateiPfadeService(true));
			klickErfolgreich = foEUIRobot.oeffneProduktion();
		} catch (AWTException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Then("^Öffnet sich das Produktionsfenster\\.$")
	public void oeffnetSichDasProduktionsfenster() {
		if (klickErfolgreich) {
			screenShot = new BilderLaden()
					.ladeScreenshotSchmiedeProduktion();
			Assert.assertNotNull(screenShot);
		} else {
			Assert.fail("ProduktionsFenster nicht geöffnet.");
		}
	}

	@When("^Gute Hufeisen produziert werden\\.$")
	public void guteHufeisenProduziertWerden() {
		try {
			FoEUIRobot foEUIRobot = new FoEUIRobot();
			foEUIRobot.setScreenCapture(screenShot);
			foEUIRobot.setGrafikPathService(new GrafikDateiPfadeService(true));
			klickErfolgreich = foEUIRobot.guteHufeisenProduzieren();
		} catch (AWTException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Then("^Schliesst sich das Produktionsfenster\\.$")
	public void schliesstSichDasProduktionsfenster() {
		Assert.assertTrue(klickErfolgreich);
	}	
}
