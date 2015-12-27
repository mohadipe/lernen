package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.image.BufferedImage;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.foe.Farben;
import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;
import de.mohadipe.ui.test.robot.util.BilderLaden;

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
		BufferedImage zuFindende05 = getBilderLaden().ladeWerkzeugVergleich05();
		werkzeugeFindenUndKlicken(zuFindende05);
	}

	private void werkzeugeFindenUndKlicken(BufferedImage zuFindende) {
		FindeGrafikInGrafik werkzeug = new FindeGrafikInGrafik(null);
		werkzeug.setDaten(AufgabeDaten.ZU_FINDENDE_FARBE, Integer.valueOf(Farben.WERKZEUG.getFarbe()));
		werkzeug.setRobot(this.getRobot());
		ausfuehren.addAufgabe(werkzeug);
		EinfachKlickKoordinaten einfachKlickKoordinaten = new EinfachKlickKoordinaten();
		einfachKlickKoordinaten.setRobot(getRobot());
		einfachKlickKoordinaten.setDaten(AufgabeDaten.ABWEICHUNG_Y, Integer.valueOf(60));
		einfachKlickKoordinaten.addAbhaengigkeit(werkzeug);
		ausfuehren.addAufgabe(einfachKlickKoordinaten);
	}
}
