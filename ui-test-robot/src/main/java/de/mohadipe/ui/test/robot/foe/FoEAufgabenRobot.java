package de.mohadipe.ui.test.robot.foe;

import java.awt.AWTException;
import java.awt.Robot;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.aufgabe.AufgabeDaten;
import de.mohadipe.ui.test.robot.aufgabe.FoEAufRugnirStarten;
import de.mohadipe.ui.test.robot.aufgabe.MuenzenSammeln;
import de.mohadipe.ui.test.robot.aufgabe.WiederholeAufgabe;
import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;

public class FoEAufgabenRobot extends Robot {

	private final AufgabenAusfuehren ausfuehren;
	private GrafikDateiPfadeService grafikPfad;
	
	public FoEAufgabenRobot() throws AWTException {
		super();
		ausfuehren = new AufgabenAusfuehren();
	}

	
	public void sammleGoldUndWerkzeuge() {
		fuegeAufgabenHinzu();
		fuehreAufgabenAus();
	}


	private void fuehreAufgabenAus() {
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
		WiederholeAufgabe wiederholeAufgabe = new WiederholeAufgabe();
		wiederholeAufgabe.addAbhaengigkeit(muenzenSammeln);
		wiederholeAufgabe.setDaten(AufgabeDaten.TIMEOUT, Long.valueOf(120000));
		ausfuehren.addAufgabe(wiederholeAufgabe);
	}


	public void setGrafikPathService(
			GrafikDateiPfadeService grafikDateiPfadeService) {
		this.grafikPfad = grafikDateiPfadeService;
	}
}
