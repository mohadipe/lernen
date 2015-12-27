package de.mohadipe.ui.test.robot.aufgabe;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.foe.Farben;

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
	
	private void setup() {
		FindeGrafikInGrafik muenze = new FindeGrafikInGrafik(null);
		muenze.setDaten(AufgabeDaten.ZU_FINDENDE_FARBE, Integer.valueOf(Farben.MUENZE.getFarbe()));
		muenze.setRobot(this.getRobot());
		ausfuehren.addAufgabe(muenze);
		EinfachKlickKoordinaten einfachKlickKoordinaten = new EinfachKlickKoordinaten();
		einfachKlickKoordinaten.setRobot(this.getRobot());
		einfachKlickKoordinaten.setDaten(AufgabeDaten.ABWEICHUNG_Y, Integer.valueOf(60));
		einfachKlickKoordinaten.addAbhaengigkeit(muenze);
		ausfuehren.addAufgabe(einfachKlickKoordinaten);
	}
}
