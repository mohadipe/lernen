package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.image.BufferedImage;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;

public class SchliesseBrowser extends AbstractAufgabe {

	private final AufgabenAusfuehren ausfuehren = new AufgabenAusfuehren();
	
	@Override
	public void ausfuehren() {
		ausfuehren.clearAufgaben();
		BufferedImage browserClose = getBilderLaden().ladeBrowserClose();
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(browserClose);
		findeGrafikInGrafik.setRobot(this.getRobot());
		ausfuehren.addAufgabe(findeGrafikInGrafik);
		EinfachKlickKoordinaten einfachKlickKoordinaten = new EinfachKlickKoordinaten();
		einfachKlickKoordinaten.setRobot(this.getRobot());
		einfachKlickKoordinaten.addAbhaengigkeit(findeGrafikInGrafik);
		ausfuehren.addAufgabe(einfachKlickKoordinaten);
		ausfuehren.fuehreAufgabenAus();
	}
	
	@Override
	public boolean isErfolgreich() {
		return ausfuehren.getProtokoll().alleAufgabenErfolgreich();
	}

	@Override
	public AufgabenArten getArt() {
		return AufgabenArten.SCHLIESSE_BROWSER;
	}

}
