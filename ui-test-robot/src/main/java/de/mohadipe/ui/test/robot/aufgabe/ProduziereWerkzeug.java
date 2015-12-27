package de.mohadipe.ui.test.robot.aufgabe;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.Koordinaten2D;
import de.mohadipe.ui.test.robot.foe.Farben;

public class ProduziereWerkzeug extends AbstractAufgabe {

	private final AufgabenAusfuehren ausfuehren = new AufgabenAusfuehren();
	private boolean isErfolgreich;
	
	@Override
	public void ausfuehren() {
		setup();
		ausfuehren.fuehreAufgabenAus();
		isErfolgreich = ausfuehren.getProtokoll().alleAufgabenErfolgreich();
	}

	private void setup() {
		FindeGrafikInGrafik schlafendeProd = new FindeGrafikInGrafik(null);
		schlafendeProd.setDaten(AufgabeDaten.ZU_FINDENDE_FARBE, Integer.valueOf(Farben.SCHLAFEN.getFarbe()));
		ausfuehren.addAufgabe(schlafendeProd);
		EinfachKlickKoordinaten klickSchlafendeProd = new EinfachKlickKoordinaten();
		klickSchlafendeProd.setRobot(getRobot());
		klickSchlafendeProd.setDaten(AufgabeDaten.ABWEICHUNG_Y, Integer.valueOf(60));
		klickSchlafendeProd.addAbhaengigkeit(schlafendeProd);
		ausfuehren.addAufgabe(klickSchlafendeProd);
		EinfachKlickKoordinaten klickProduktion = new EinfachKlickKoordinaten();
		klickProduktion.setRobot(getRobot());
		Koordinaten2D koordinaten = new Koordinaten2D(200, 500);
		schlafendeProd.setDaten(AufgabeDaten.KOORDINATE, koordinaten);
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
