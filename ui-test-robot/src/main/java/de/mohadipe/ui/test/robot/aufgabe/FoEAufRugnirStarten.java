package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.image.BufferedImage;
import java.util.logging.Logger;

import de.mohadipe.ui.test.robot.AufgabenAusfuehren;
import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;
import de.mohadipe.ui.test.robot.util.BilderLaden;

public class FoEAufRugnirStarten extends AbstractAufgabe {

	private final AufgabenAusfuehren ausfuehren = new AufgabenAusfuehren();
	private Aufgabe fuerErolgRelevanteAufgabe = null;

	@Override
	public void ausfuehren() {
		setup();
		ausfuehren.setLogger((Logger) getDaten(AufgabeDaten.LOGGER));
		ausfuehren.fuehreAufgabenAus();
		setDaten(AufgabeDaten.PROTOKOLL, ausfuehren.getProtokoll());
	}

	private void setup() {
		addVerknuepfungKlicken();
		addWarten(5000);
		addSpielenButtonKlicken();
		addWarten(2000);
		addServerRugnirKlicken();
		addMenuSchliessenKlicken();
	}
	
	private BilderLaden getBilderLaden() {
		return new BilderLaden((GrafikDateiPfadeService) this.getDaten(AufgabeDaten.GRAFIKPFAD));
	}

	private void addWarten(int dauer) {
		ausfuehren.addAufgabe(new Warten(dauer));
	}
	
	private void addMenuSchliessenKlicken() {
		long spielLadenTimeOut = ((Long)getDaten(AufgabeDaten.SPIEL_LADEN_TIMEOUT)).longValue();
		BufferedImage zuFindende = getBilderLaden().ladeOkButton();
		FindeGrafikInGrafik findeGrafik = new FindeGrafikInGrafik(zuFindende);
		findeGrafik.setDaten(AufgabeDaten.NAME, "Menu Ok finden");
		findeGrafik.setRobot(this.getRobot());
		EinfachKlickKoordinaten einfachKlickKoordinaten = new EinfachKlickKoordinaten();
		einfachKlickKoordinaten.setRobot(getRobot());
		einfachKlickKoordinaten.addAbhaengigkeit(findeGrafik);
		
		WiederholeAufgabe wiederholeAufgabe = new WiederholeAufgabe();
		wiederholeAufgabe.addAbhaengigkeit(findeGrafik);
		wiederholeAufgabe.addAbhaengigkeit(einfachKlickKoordinaten);
		wiederholeAufgabe.setDaten(AufgabeDaten.TIMEOUT, spielLadenTimeOut);
		ausfuehren.addAufgabe(wiederholeAufgabe);
	}

	private void addServerRugnirKlicken() {
		BufferedImage zuFindende = getBilderLaden().ladeServerRugnirButton();
		FindeGrafikInGrafik findeGrafik = new FindeGrafikInGrafik(zuFindende);
		findeGrafik.setDaten(AufgabeDaten.NAME, "Server Rugnir finden");
		findeGrafik.setRobot(this.getRobot());
		ausfuehren.addAufgabe(findeGrafik);
		EinfachKlickKoordinaten einfachKlickKoordinaten = new EinfachKlickKoordinaten();
		einfachKlickKoordinaten.setRobot(getRobot());
		einfachKlickKoordinaten.addAbhaengigkeit(findeGrafik);
		ausfuehren.addAufgabe(einfachKlickKoordinaten);
		fuerErolgRelevanteAufgabe = einfachKlickKoordinaten;
	}

	private void addSpielenButtonKlicken() {
		BufferedImage zuFindende = getBilderLaden().ladeSpielenButton();
		FindeGrafikInGrafik findeGrafik = new FindeGrafikInGrafik(zuFindende);
		findeGrafik.setDaten(AufgabeDaten.NAME, "Spielen Button finden");
		findeGrafik.setRobot(this.getRobot());
		ausfuehren.addAufgabe(findeGrafik);
		EinfachKlickKoordinaten einfachKlickKoordinaten = new EinfachKlickKoordinaten();
		einfachKlickKoordinaten.setRobot(getRobot());
		einfachKlickKoordinaten.addAbhaengigkeit(findeGrafik);
		ausfuehren.addAufgabe(einfachKlickKoordinaten);		
	}

	private void addVerknuepfungKlicken() {
		BufferedImage zuFindende = getBilderLaden().ladeRotesIcon();
		FindeGrafikInGrafik findeGrafik = new FindeGrafikInGrafik(zuFindende);
		findeGrafik.setDaten(AufgabeDaten.NAME, "FoEVerknuepfung finden");
		findeGrafik.setRobot(this.getRobot());
		ausfuehren.addAufgabe(findeGrafik);
		DoppelKlickeKoordinaten doppelKlickKoordinaten = new DoppelKlickeKoordinaten();
		doppelKlickKoordinaten.setRobot(getRobot());
		doppelKlickKoordinaten.addAbhaengigkeit(findeGrafik);
		ausfuehren.addAufgabe(doppelKlickKoordinaten);		
	}

	@Override
	public boolean isErfolgreich() {
		return fuerErolgRelevanteAufgabe.isErfolgreich();
	}

	@Override
	public AufgabenArten getArt() {
		return AufgabenArten.FOE_AUF_RUGNIR_STARTEN;
	}

}
