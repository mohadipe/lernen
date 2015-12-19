package foe;

import static org.mockito.Mockito.mock;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import org.hamcrest.Matchers;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.IconFinden;
import de.mohadipe.ui.test.robot.IconNotFoundException;
import de.mohadipe.ui.test.robot.Koordinaten2D;
import de.mohadipe.ui.test.robot.aufgabe.AufgabeDaten;
import de.mohadipe.ui.test.robot.aufgabe.DoppelKlickeKoordinaten;
import de.mohadipe.ui.test.robot.aufgabe.FindeGrafikInGrafik;
import de.mohadipe.ui.test.robot.aufgabe.Warten;
import de.mohadipe.ui.test.robot.foe.FoEUIRobot;
import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;
import de.mohadipe.ui.test.robot.protokoll.Protokoll;
import de.mohadipe.ui.test.robot.util.BilderLaden;

public class StepDefFoE {
	private AufgabenAusfuehren ausfuehren = new AufgabenAusfuehren();
	private BufferedImage screenShotFoEVerknuepfung;
	private Koordinaten2D geklickteKoordinaten;
	private GrafikDateiPfadeService pfadeService = new GrafikDateiPfadeService(true);
	private String klick01 = pfadeService.getPath() + IconFinden.KLICK_01;
	private String klick02 = pfadeService.getPath() + IconFinden.KLICK_02;
	private String klick03 = pfadeService.getPath() + IconFinden.KLICK_03;
	private long muenzenKlickCount;

	
	@Given("^Ein Screenshot mit FoE-Verknüpfung\\.$")
	public void einScreenshotMitFoEVerknuepfung() {
		screenShotFoEVerknuepfung = new BilderLaden(null).ladeScreenShotFoEVerknuepfung();
		Assert.assertNotNull(screenShotFoEVerknuepfung);
	}

	@Given("^Die Münzproduktion eines Wohnhauses ist fertig\\.$")
	public void dieMuenzenproduktionEinesWohnhausesIstFertig() {
		screenShotFoEVerknuepfung = new BilderLaden(null).ladeScreenshotMuenzenAbholbereit();
		Assert.assertNotNull(screenShotFoEVerknuepfung);
	}

	@Given("^Die Münzproduktion eines Wohnhauses mit Stern ist fertig\\.$")
	public void dieMuenzproduktionEinesWohnhausesMitSternIstFertig() {
		screenShotFoEVerknuepfung = new BilderLaden(null).ladeScreenshotMuenzenMitSternAbholbereit();
		Assert.assertNotNull(screenShotFoEVerknuepfung);
	}

	@Given("^Die Startseite von Forge of Empire ist im Browser offen\\.$")
	public void dieStartseiteVonForgeOfEmpireIstImBrowserOffen() {
		screenShotFoEVerknuepfung = new BilderLaden(null).ladeStartseiteFoE();
		Assert.assertNotNull(screenShotFoEVerknuepfung);
	}

	@When("^Nach fertigen Münzproduktionen gesucht wird\\.$")
	public void nachFertigenMuenzproduktionenGesuchtWird() {
		try {
			FoEUIRobot foEUIRobot = new FoEUIRobot();
			foEUIRobot.setScreenCapture(screenShotFoEVerknuepfung);
			foEUIRobot.setGrafikPathService(new GrafikDateiPfadeService(true));
			foEUIRobot.holeMuenzenAb();
			muenzenKlickCount = foEUIRobot.getMuenzenKlickCount();
		} catch (AWTException e) {
			Assert.fail(e.getMessage());
		}
	}

	@When("^Ein doppel Klick auf die Verknüpfung gemacht wird\\.$")
	public void einDoppelKlickAufDieVerknuepfungGemachtWird() {
		IconFinden iconFinden = new IconFinden();
		try {
			iconFinden.findeIcon(klick01, screenShotFoEVerknuepfung);
			geklickteKoordinaten = iconFinden.getKoordinaten();
		} catch (IconNotFoundException e) {
			Assert.fail(e.getMessage());
		}
	}

	@When("^Der Spielen Button gedrückt wird\\.$")
	public void derSpielenButtonGedruecktWird() {
		IconFinden iconFinden = new IconFinden();
		try {
			iconFinden.findeIcon(klick02, screenShotFoEVerknuepfung);
			geklickteKoordinaten = iconFinden.getKoordinaten();
			screenShotFoEVerknuepfung = new BilderLaden(null).ladeAuswahlServerRugnir();
			Assert.assertNotNull(screenShotFoEVerknuepfung);
		} catch (IconNotFoundException e) {
			Assert.fail(e.getMessage());
		}
	}

	@When("^Der Server Rugnir ausgewählt wird\\.$")
	public void derServerRugnirAusgewaehltWird() {
		IconFinden iconFinden = new IconFinden();
		try {
			iconFinden.findeIcon(klick03, screenShotFoEVerknuepfung);
			geklickteKoordinaten = iconFinden.getKoordinaten();
		} catch (IconNotFoundException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Then("^Die fertige Münzproduktion gefunden\\.$")
	public void dieFertigeMuenzproduktionGefunden() {
		Assert.assertEquals(1, muenzenKlickCount);
	}

	@Then("^Öffnet sich ein Browser mit der Startseite von Forge of Empire\\.$")
	public void oeffnetSichEinBrowserMitDerStartseiteVonForgeOfEmpire() {
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 1417;
		expected.y = 232;
		Assert.assertThat(geklickteKoordinaten, Matchers.equalTo(expected));
	}

	@Then("^Öffnet sich die Auswahl des Servers\\.$")
	public void oeffnetSichDieAuswahlDesServers() {
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 1181;
		expected.y = 510;
		Assert.assertThat(geklickteKoordinaten, Matchers.equalTo(expected));
	}

	@Then("^Die Stadt des Spielers erscheint\\.$")
	public void dieStadtDesSpielersErscheint() {
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 884;
		expected.y = 355;
		Assert.assertThat(geklickteKoordinaten, Matchers.equalTo(expected));
	}
	
	@Given("^Die FoE Verknüpfung muss geklickt werden\\.$") 
	public void dieFoEVerknuepfungMussGeklicktWerden() throws AWTException {
		BufferedImage zuFindende = new BilderLaden(null).ladeRotesIcon();
		BufferedImage zuDurchsuchende = new BilderLaden(null).ladeScreenShotFoEVerknuepfung();
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(zuFindende);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, zuDurchsuchende);
		ausfuehren.addAufgabe(findeGrafikInGrafik);
		DoppelKlickeKoordinaten doppelKlickeKoordinaten = new DoppelKlickeKoordinaten();
		Robot robot = new FoEUIRobot();
		doppelKlickeKoordinaten.setRobot(robot);
		doppelKlickeKoordinaten.addAbhaengigkeit(findeGrafikInGrafik);
		ausfuehren.addAufgabe(doppelKlickeKoordinaten);
		ausfuehren.addAufgabe(new Warten(1));
	}

	@Given("^Der Spielen Button muss geklickt werden\\.$")
	public void derSpielenButtonMussGeklicktWerden() throws AWTException {
		BufferedImage zuFindende = new BilderLaden(null).ladeSpielenButton();
		BufferedImage zuDurchsuchende = new BilderLaden(null).ladeStartseiteFoE();
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(zuFindende);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, zuDurchsuchende);
		ausfuehren.addAufgabe(findeGrafikInGrafik);
		DoppelKlickeKoordinaten doppelKlickeKoordinaten = new DoppelKlickeKoordinaten();
		Robot robot = new FoEUIRobot();
		doppelKlickeKoordinaten.setRobot(robot);
		doppelKlickeKoordinaten.addAbhaengigkeit(findeGrafikInGrafik);
		ausfuehren.addAufgabe(doppelKlickeKoordinaten);
		ausfuehren.addAufgabe(new Warten(1));
	}

	@Given("^Der Server Rugnir muss ausgewählt werden\\.$")
	public void derServerRugnirMussAusgewaehltWerden() throws AWTException {
		Robot robotMock = mock(Robot.class);
		BufferedImage zuFindende = new BilderLaden(null).ladeServerRugnirButton();
		BufferedImage zuDurchsuchende = new BilderLaden(null).ladeAuswahlServerRugnir();
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(zuFindende);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, zuDurchsuchende);
		ausfuehren.addAufgabe(findeGrafikInGrafik);
		DoppelKlickeKoordinaten doppelKlickeKoordinaten = new DoppelKlickeKoordinaten();
		doppelKlickeKoordinaten.setRobot(robotMock);
		doppelKlickeKoordinaten.addAbhaengigkeit(findeGrafikInGrafik);
		ausfuehren.addAufgabe(doppelKlickeKoordinaten);
		ausfuehren.addAufgabe(new Warten(1));
	}

	@When("^Der Bot gestartet wird\\.$")
	public void derBotGestartetWird() {
		ausfuehren.fuehreAufgabenAus();
	}

	@Then("^Alle Aufgaben sind erfolgreich ausgeführt worden\\.$")
	public void istNachKurzemWartenDieStadtArrakeenGeladen() {
		Protokoll protokoll = ausfuehren.getProtokoll();
		Assert.assertTrue(protokoll.alleAufgabenErfolgreich());
	}
}
