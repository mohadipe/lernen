package de.mohadipe.ui.test.robot.foe;

import java.awt.AWTException;
import java.awt.Robot;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.aufgabe.AufgabeDaten;
import de.mohadipe.ui.test.robot.aufgabe.FoEAufRugnirStarten;
import de.mohadipe.ui.test.robot.aufgabe.MuenzenSammeln;
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
		ausfuehren.addAufgabe(foEAufRugnirStarten);
		MuenzenSammeln muenzenSammeln = new MuenzenSammeln();
		muenzenSammeln.setRobot(this);
		muenzenSammeln.setDaten(AufgabeDaten.GRAFIKPFAD, grafikPfad);
		ausfuehren.addAufgabe(muenzenSammeln);
	}


	public void setGrafikPathService(
			GrafikDateiPfadeService grafikDateiPfadeService) {
		this.grafikPfad = grafikDateiPfadeService;
	}
}
