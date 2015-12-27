package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.image.BufferedImage;

import de.mohadipe.ui.test.robot.IconFinden;
import de.mohadipe.ui.test.robot.IconNotFoundException;
import de.mohadipe.ui.test.robot.Koordinaten2D;

public class FindeGrafikInGrafik extends AbstractAufgabe {
	private BufferedImage zuDurchsuchende;
	private BufferedImage zuFindende;
	private boolean isErfolgreich;

	public FindeGrafikInGrafik(final BufferedImage zuFindende) {
		this.zuFindende = zuFindende;
	}

	private boolean isIn() {
		boolean gefunden = false;
		IconFinden iconFinden = new IconFinden();
		gefunden = findeGrafik(iconFinden);
		if (gefunden) {
			return gefunden;
		}
		gefunden = findeFarbe(iconFinden);
		return gefunden;
	}

	private boolean findeFarbe(IconFinden iconFinden) {
		if (iconFinden.getKoordinaten() == null) {
			try {
				Integer farbe = (Integer) getDaten(AufgabeDaten.ZU_FINDENDE_FARBE);
				if (farbe == null) {
					return false;
				}
				iconFinden.findeFarbeAufScreen(zuDurchsuchende, farbe.intValue());
				super.setDaten(AufgabeDaten.KOORDINATE,
						iconFinden.getKoordinaten());
				return true;
			} catch (IconNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}

	private boolean findeGrafik(IconFinden iconFinden) {
		if (zuFindende != null) {
			try {
				iconFinden.findeIcon(zuFindende, zuDurchsuchende);
				super.setDaten(AufgabeDaten.KOORDINATE,
						iconFinden.getKoordinaten());
				return true;
			} catch (IconNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}

	public Koordinaten2D getKoordinaten() {
		return (Koordinaten2D) super.getDaten(AufgabeDaten.KOORDINATE);
	}

	@Override
	public void ausfuehren() {
		System.out.println("Aufgabe FindeGrafikInGrafik wird ausgefuehrt.");
		System.out.println((String) getDaten(AufgabeDaten.NAME));
		this.zuDurchsuchende = (BufferedImage) getDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK);
		if (this.zuDurchsuchende == null) {
			this.zuDurchsuchende = takeScreenShot();
		}
		this.isErfolgreich = isIn();
	}

	@Override
	public boolean isErfolgreich() {
		return this.isErfolgreich;
	}

	@Override
	public AufgabenArten getArt() {
		return AufgabenArten.GRAFIK_IN_GRAFIK_FINDEN;
	}
}
