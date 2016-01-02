package de.mohadipe.ui.test.robot.aufgabe;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.Koordinaten2D;
import de.mohadipe.ui.test.robot.foe.FarbBloecke;

public class ProduziereWerkzeug extends AbstractAufgabe {

	private final AufgabenAusfuehren ausfuehren = new AufgabenAusfuehren();
	private boolean isErfolgreich;
	private Koordinaten2D prodFuenfMin = new Koordinaten2D(640, 450);
//	private Koordinaten2D prodFuenfZehnMin = new Koordinaten2D(744, 372);
	
	@Override
	public void ausfuehren() {
		setup();
		System.out.println("Prouziere Werkzeuge");
		ausfuehren.fuehreAufgabenAus();
		isErfolgreich = ausfuehren.getProtokoll().alleAufgabenErfolgreich();
	}

	private void setup() {
		ausfuehren.clearAufgaben();
		FindeGrafikInGrafik schlafendeProd = new FindeGrafikInGrafik(null);
		schlafendeProd.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, FarbBloecke.SCHLAFEN.getFarbBlock());
		schlafendeProd.setRobot(getRobot());
		ausfuehren.addAufgabe(schlafendeProd);
		EinfachKlickKoordinaten klickSchlafendeProd = new EinfachKlickKoordinaten();
		klickSchlafendeProd.setRobot(getRobot());
		klickSchlafendeProd.setDaten(AufgabeDaten.ABWEICHUNG_Y, Integer.valueOf(60));
		klickSchlafendeProd.addAbhaengigkeit(schlafendeProd);
		ausfuehren.addAufgabe(klickSchlafendeProd);
		EinfachKlickKoordinaten klickProduktion = new EinfachKlickKoordinaten();
		klickProduktion.setRobot(getRobot());
		klickProduktion.setDaten(AufgabeDaten.KOORDINATE, prodFuenfMin);
		schlafendeProd.setDaten(AufgabeDaten.KOORDINATE, null);
		klickProduktion.addAbhaengigkeit(schlafendeProd);
		ausfuehren.addAufgabe(klickProduktion);
	}

	@Override
	public boolean isErfolgreich() {
		return isErfolgreich;
	}

	@Override
	public AufgabenArten getArt() {
		return AufgabenArten.PRODUZIERE_WERKZEUG;
	}

}
