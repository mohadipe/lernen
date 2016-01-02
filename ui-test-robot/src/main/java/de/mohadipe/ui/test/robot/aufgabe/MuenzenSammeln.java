package de.mohadipe.ui.test.robot.aufgabe;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.foe.FarbBloecke;

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
		System.out.println("Sammle Muenzen");
		ausfuehren.fuehreAufgabenAus();
		isErfolgreich = ausfuehren.getProtokoll().alleAufgabenErfolgreich();
	}

	@Override
	public boolean isErfolgreich() {
		return isErfolgreich;
	}

	private void setup() {
		ausfuehren.clearAufgaben();
		FindeGrafikInGrafik muenze = new FindeGrafikInGrafik(null);
		muenze.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, FarbBloecke.MUENZE.getFarbBlock());
		muenze.setRobot(this.getRobot());
		ausfuehren.addAufgabe(muenze);
		EinfachKlickKoordinaten einfachKlickKoordinaten = new EinfachKlickKoordinaten();
		einfachKlickKoordinaten.setRobot(this.getRobot());
		einfachKlickKoordinaten.setDaten(AufgabeDaten.ABWEICHUNG_Y,
				Integer.valueOf(60));
		einfachKlickKoordinaten.addAbhaengigkeit(muenze);
		ausfuehren.addAufgabe(einfachKlickKoordinaten);
	}
}
