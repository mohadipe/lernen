package foe;

import java.awt.image.BufferedImage;
import java.nio.file.Paths;

import org.hamcrest.Matchers;
import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import de.mohadipe.ui.test.robot.IconFinden;
import de.mohadipe.ui.test.robot.IconNotFoundException;
import de.mohadipe.ui.test.robot.Koordinaten2D;
import de.mohadipe.util.BilderLaden;

public class StepDefFoE {
	private BufferedImage screenShotFoEVerknuepfung;
	private Koordinaten2D geklickteKoordinaten;
	private static final String ide_main_icon_pfad = "\\src\\main\\resources\\icons\\";
	private String ausgangspunkt = Paths.get("").toAbsolutePath().toString();;
	private String klick01 = ausgangspunkt + Paths.get("").resolve(ide_main_icon_pfad).resolve(IconFinden.KLICK_01).toString();
	private String klick02 = ausgangspunkt + Paths.get("").resolve(ide_main_icon_pfad).resolve(IconFinden.KLICK_02).toString();
	private String klick03 = ausgangspunkt + Paths.get("").resolve(ide_main_icon_pfad).resolve(IconFinden.KLICK_03).toString();

	
	@Given("^Ein Screenshot mit FoE-Verknüpfung\\.$")
	public void einScreenshotMitFoEVerknuepfung() {
		screenShotFoEVerknuepfung = new BilderLaden().ladeScreenShotFoEVerknuepfung();
		Assert.assertNotNull(screenShotFoEVerknuepfung);
	}

	@Given("^Die Münzproduktion eines Wohnhauses ist fertig\\.$")
	public void dieMuenzenproduktionEinesWohnhausesIstFertig() {
		// TODO
	}

	@Given("^Die Münzproduktion eines Wohnhauses mit Stern ist fertig\\.$")
	public void dieMuenzproduktionEinesWohnhausesMitSternIstFertig() {
		// TODO
	}

	@Given("^Die Startseite von Forge of Empire ist im Browser offen\\.$")
	public void dieStartseiteVonForgeOfEmpireIstImBrowserOffen() {
		screenShotFoEVerknuepfung = new BilderLaden().ladeStartseiteFoE();
		Assert.assertNotNull(screenShotFoEVerknuepfung);
	}

	@When("^Nach fertigen Münzproduktionen gesucht wird\\.$")
	public void nachFertigenMuenzproduktionenGesuchtWird() {
		// TODO
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
			screenShotFoEVerknuepfung = new BilderLaden().waehleServerRugnir();
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
		// TODO
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
}
