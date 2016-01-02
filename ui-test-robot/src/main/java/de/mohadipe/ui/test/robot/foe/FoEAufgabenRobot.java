package de.mohadipe.ui.test.robot.foe;

import java.awt.AWTException;
import java.awt.Robot;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.aufgabe.AbstractAufgabe;
import de.mohadipe.ui.test.robot.aufgabe.AufgabeDaten;
import de.mohadipe.ui.test.robot.aufgabe.FoEAufRugnirStarten;
import de.mohadipe.ui.test.robot.aufgabe.MuenzenSammeln;
import de.mohadipe.ui.test.robot.aufgabe.ProduziereWerkzeug;
import de.mohadipe.ui.test.robot.aufgabe.SchliesseBrowser;
import de.mohadipe.ui.test.robot.aufgabe.SchliessePopUp;
import de.mohadipe.ui.test.robot.aufgabe.WerkzeugeSammeln;
import de.mohadipe.ui.test.robot.aufgabe.WiederholeAufgabe;
import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;

public class FoEAufgabenRobot extends Robot {

	private AufgabenAusfuehren ausfuehren;
	private GrafikDateiPfadeService grafikPfad;
	private long timeOut;
	
	public FoEAufgabenRobot() throws AWTException {
		super();
	}

	
	public void sammleGoldUndWerkzeuge() {
		ausfuehren = new AufgabenAusfuehren();
		fuegeAufgabenHinzu();
		ausfuehren.fuehreAufgabenAus();
	}

	private void fuegeAufgabenHinzu() {
		FoEAufRugnirStarten foEAufRugnirStarten = new FoEAufRugnirStarten();
		foEAufRugnirStarten.setRobot(this);
		foEAufRugnirStarten.setDaten(AufgabeDaten.GRAFIKPFAD, grafikPfad);
		foEAufRugnirStarten.setDaten(AufgabeDaten.SPIEL_LADEN_TIMEOUT, 25000L);
		ausfuehren.addAufgabe(foEAufRugnirStarten);
		MuenzenSammeln muenzenSammeln = new MuenzenSammeln();
		muenzenSammeln.setRobot(this);
		muenzenSammeln.setDaten(AufgabeDaten.GRAFIKPFAD, grafikPfad);
		WerkzeugeSammeln werkzeugeSammeln = new WerkzeugeSammeln();
		werkzeugeSammeln.setRobot(this);
		werkzeugeSammeln.setDaten(AufgabeDaten.GRAFIKPFAD, grafikPfad);
		ProduziereWerkzeug produziereWerkzeug = new ProduziereWerkzeug();
		produziereWerkzeug.setRobot(this);
		SchliessePopUp schliessePopUp = new SchliessePopUp();
		schliessePopUp.setRobot(this);
		WiederholeAufgabe wiederholeAufgabe = new WiederholeAufgabe();
		wiederholeAufgabe.addAbhaengigkeit(muenzenSammeln);
		wiederholeAufgabe.addAbhaengigkeit(werkzeugeSammeln);
		wiederholeAufgabe.addAbhaengigkeit(produziereWerkzeug);
		wiederholeAufgabe.addAbhaengigkeit(schliessePopUp);
		wiederholeAufgabe.setDaten(AufgabeDaten.TIMEOUT, Long.valueOf(timeOut));
		ausfuehren.addAufgabe(wiederholeAufgabe);
	}


	public void setGrafikPathService(
			GrafikDateiPfadeService grafikDateiPfadeService) {
		this.grafikPfad = grafikDateiPfadeService;
	}


	public void setLaufZeitTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}


	public boolean schliesseBrowser() {
		ausfuehren = new AufgabenAusfuehren();
		AbstractAufgabe schliesseBrowser = new SchliesseBrowser();
		schliesseBrowser.setRobot(this);
		schliesseBrowser.setDaten(AufgabeDaten.GRAFIKPFAD, this.grafikPfad);
		ausfuehren.addAufgabe(schliesseBrowser);
		ausfuehren.fuehreAufgabenAus();
		return ausfuehren.getProtokoll().alleAufgabenErfolgreich();
	}
}
