package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.image.BufferedImage;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;
import de.mohadipe.ui.test.robot.util.BilderLaden;

public class MuenzenSammeln extends AbstractAufgabe {

	private final AufgabenAusfuehren ausfuehren = new AufgabenAusfuehren();
	private boolean isErfolgreich;

	@Override
	public AufgabenArten getArt() {
		return AufgabenArten.MUENZENSAMMELN;
	}

	@Override
	public void ausfuehren() {
		setup();
		boolean ergebnisVorletzteSchleife = false;
		do {
			ausfuehren.fuehreAufgabenAus();
			isErfolgreich = ausfuehren.getProtokoll().alleAufgabenErfolgreich();
			if (isErfolgreich) {
				ergebnisVorletzteSchleife = isErfolgreich;
			}
		} while (ausfuehren.getProtokoll().alleAufgabenErfolgreich());
		isErfolgreich = ergebnisVorletzteSchleife;
	}
	
	@Override
	public boolean isErfolgreich() {
		return isErfolgreich;
	}

	public BilderLaden getBilderLaden() {
		return new BilderLaden((GrafikDateiPfadeService) this.getDaten(AufgabeDaten.GRAFIKPFAD));
	}
	
	private void setup() {
		BufferedImage zuFindende = getBilderLaden().ladeMuenzVergleich01();
		FindeGrafikInGrafik muenze01 = new FindeGrafikInGrafik(zuFindende);
		muenze01.setRobot(this.getRobot());
		ausfuehren.addAufgabe(muenze01);
		EinfachKlickKoordinaten einfachKlickKoordinaten = new EinfachKlickKoordinaten();
		einfachKlickKoordinaten.setRobot(getRobot());
		einfachKlickKoordinaten.addAbhaengigkeit(muenze01);
		ausfuehren.addAufgabe(einfachKlickKoordinaten);
	}
}
