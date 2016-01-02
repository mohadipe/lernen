package de.mohadipe.ui.test.robot.aufgabe;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.foe.FarbBloecke;

public class WerkzeugeSammeln extends AbstractAufgabe {

	private final AufgabenAusfuehren ausfuehren = new AufgabenAusfuehren();
	private boolean isErfolgreich;

	@Override
	public AufgabenArten getArt() {
		return AufgabenArten.WERKZEUGESAMMELN;
	}

	@Override
	public void ausfuehren() {
		setup();
		System.out.println("Sammle Werkzeuge");
		ausfuehren.fuehreAufgabenAus();
		isErfolgreich = ausfuehren.getProtokoll().alleAufgabenErfolgreich();
	}

	@Override
	public boolean isErfolgreich() {
		return isErfolgreich;
	}

	private void setup() {
		ausfuehren.clearAufgaben();
		FindeGrafikInGrafik werkzeug = new FindeGrafikInGrafik(null);
		werkzeug.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK,
				FarbBloecke.WERKZEUG.getFarbBlock());
		werkzeug.setRobot(this.getRobot());
		ausfuehren.addAufgabe(werkzeug);
		EinfachKlickKoordinaten einfachKlickKoordinaten = new EinfachKlickKoordinaten();
		einfachKlickKoordinaten.setRobot(getRobot());
		einfachKlickKoordinaten.setDaten(AufgabeDaten.ABWEICHUNG_Y,
				Integer.valueOf(58));
		einfachKlickKoordinaten.addAbhaengigkeit(werkzeug);
		ausfuehren.addAufgabe(einfachKlickKoordinaten);
	}
}
