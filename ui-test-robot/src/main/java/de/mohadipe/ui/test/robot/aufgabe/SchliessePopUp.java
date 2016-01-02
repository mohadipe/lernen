package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.image.BufferedImage;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.foe.FarbBloecke;
import de.mohadipe.ui.test.robot.util.BilderLaden;

public class SchliessePopUp extends AbstractAufgabe {

	private final AufgabenAusfuehren ausfuehren = new AufgabenAusfuehren();
	
	@Override
	public void ausfuehren() {
		ausfuehren.clearAufgaben();
		System.out.println("Schliesse PopUp");
		BufferedImage x3 = getBilderLaden().ladeX3();
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(x3);
		findeGrafikInGrafik.setRobot(this.getRobot());
//		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, FarbBloecke.POPUP.getFarbBlock());
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
		return AufgabenArten.SCHLIESSE_POPUP;
	}

}
