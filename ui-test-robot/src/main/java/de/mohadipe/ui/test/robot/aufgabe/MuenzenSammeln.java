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
		BufferedImage zuFindende01 = getBilderLaden().ladeMuenzVergleich01();
		muenzeFindenUndKlicken(zuFindende01);
		BufferedImage zuFindende02 = getBilderLaden().ladeMuenzVergleich02();
		muenzeFindenUndKlicken(zuFindende02);
		BufferedImage zuFindende03 = getBilderLaden().ladeMuenzVergleich03();
		muenzeFindenUndKlicken(zuFindende03);
		BufferedImage zuFindende04 = getBilderLaden().ladeMuenzVergleich04();
		muenzeFindenUndKlicken(zuFindende04);
		BufferedImage zuFindende06 = getBilderLaden().ladeMuenzVergleich06();
		muenzeFindenUndKlicken(zuFindende06);
	}

	private void muenzeFindenUndKlicken(BufferedImage zuFindende) {
		FindeGrafikInGrafik muenze = new FindeGrafikInGrafik(zuFindende);
		muenze.setRobot(this.getRobot());
		ausfuehren.addAufgabe(muenze);
		EinfachKlickKoordinaten einfachKlickKoordinaten = new EinfachKlickKoordinaten();
		einfachKlickKoordinaten.setRobot(getRobot());
		einfachKlickKoordinaten.setDaten(AufgabeDaten.ABWEICHUNG_Y, Integer.valueOf(60));
		einfachKlickKoordinaten.addAbhaengigkeit(muenze);
		ausfuehren.addAufgabe(einfachKlickKoordinaten);
	}
}
