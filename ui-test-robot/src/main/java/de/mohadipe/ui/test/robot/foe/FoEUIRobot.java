package de.mohadipe.ui.test.robot.foe;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;

import de.mohadipe.ui.test.robot.IconFinden;
import de.mohadipe.ui.test.robot.IconNotFoundException;
import de.mohadipe.ui.test.robot.Koordinaten2D;
import de.mohadipe.ui.test.robot.path.GrafikDateiPfadeService;

public class FoEUIRobot extends Robot {

	private static final int abweichung_y = 60;
	private static final int EINFACHE_ABWEICHUNG = 5;
	private int abweichung;
	private BufferedImage screenCapture;
	private long muenzenKlickCount = 0;
	private long werkzeugeKlickCount = 0;
	private GrafikDateiPfadeService pfadeService;

	public FoEUIRobot() throws AWTException {
		super();
		this.abweichung = abweichung_y;
	}

	private boolean klickSpezielleAbweichung(String dateiPfad, int abweichungY) {
		BufferedImage currentScreen = this.createScreenCapture(new Rectangle(
				Toolkit.getDefaultToolkit().getScreenSize()));
		IconFinden iconFinden = new IconFinden();
		try {
			iconFinden.findeIcon(dateiPfad, currentScreen);
			int x = iconFinden.getxKoordinate();
			int y = iconFinden.getyKoordinate();
			mouseMove(x, y + abweichungY);

			mousePress(InputEvent.BUTTON1_DOWN_MASK);
			mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			return true;
		} catch (IconNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void starteFoEBrowser() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		String iconName = pfadeService.getPath() + IconFinden.KLICK_01;
		System.out.println(iconName);
		if (findeKoordinaten(koordinaten2d, iconName)) {
			doppelKlickKoordinaten(koordinaten2d, EINFACHE_ABWEICHUNG, EINFACHE_ABWEICHUNG);
		}
	}

	public void spieleFoE() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		String iconName = pfadeService.getPath() + IconFinden.KLICK_02;
		System.out.println(iconName);
		if (findeKoordinaten(koordinaten2d, iconName)) {
			doppelKlickKoordinaten(koordinaten2d, EINFACHE_ABWEICHUNG, EINFACHE_ABWEICHUNG);
		}
	}

	public void waehleServerRugnir() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		String iconName = pfadeService.getPath() + IconFinden.KLICK_03;
		System.out.println(iconName);
		if (findeKoordinaten(koordinaten2d, iconName)) {
			doppelKlickKoordinaten(koordinaten2d, EINFACHE_ABWEICHUNG, EINFACHE_ABWEICHUNG);
		}
	}

	public void menuesBestaetigen() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		String iconName = pfadeService.getPath() + IconFinden.KLICK_04;
		System.out.println(iconName);
		if (findeKoordinaten(koordinaten2d, iconName)) {
			doppelKlickKoordinaten(koordinaten2d, EINFACHE_ABWEICHUNG, EINFACHE_ABWEICHUNG);
		}
	}

	public void holeMuenzenAb() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		if (findeKoordinaten(koordinaten2d, IconFinden.WIEDERHOLEN)) {
			klickKoordinaten(koordinaten2d);
			muenzenKlickCount++;
		} else if (findeKoordinaten(koordinaten2d, IconFinden.WIEDERHOLEN_02)) {
			klickKoordinaten(koordinaten2d);
			muenzenKlickCount++;
		} else if (findeKoordinaten(koordinaten2d, IconFinden.WIEDERHOLEN_03)) {
			klickKoordinaten(koordinaten2d);
			muenzenKlickCount++;
		} else if (findeKoordinaten(koordinaten2d, IconFinden.WIEDERHOLEN_04)) {
			klickKoordinaten(koordinaten2d);
			muenzenKlickCount++;
		}
	}

	public void holeWerkzeugAb() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		if (findeKoordinaten(koordinaten2d, IconFinden.WIEDERHOLEN_05)) {
			klickKoordinaten(koordinaten2d);
			werkzeugeKlickCount++;
		}
	}

	public boolean oeffneProduktion() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		if (findeKoordinaten(koordinaten2d, IconFinden.PRODUZIEREN_01)) {
			klickKoordinaten(koordinaten2d);
			return true;
		}
		return false;
	}

	public boolean guteHufeisenProduzieren() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		if (findeKoordinaten(koordinaten2d, IconFinden.GUTES_HUFEISEN)) {
			klickKoordinaten(koordinaten2d);
			return true;
		}
		return false;
	}
	
	private void klickKoordinaten(Koordinaten2D koordinaten2d) {
		mouseMove(koordinaten2d.x, koordinaten2d.y + abweichung);
		mousePress(InputEvent.BUTTON1_DOWN_MASK);
		mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	private void doppelKlickKoordinaten(Koordinaten2D koordinaten2d, int abweichungX, int abweichungY) {
		mouseMove(koordinaten2d.x + abweichungX, koordinaten2d.y + abweichungY);
		mousePress(InputEvent.BUTTON1_DOWN_MASK);
		mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		mousePress(InputEvent.BUTTON1_DOWN_MASK);
		mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	private boolean findeKoordinaten(Koordinaten2D koordinaten2d,
			String iconName) {
		String pfad = pfadeService.getPath() + iconName;
		return existIcon(pfad, koordinaten2d);
	}

	private boolean existIcon(String dateiPfad, Koordinaten2D koordinaten2d) {
		BufferedImage currentScreen = this.createScreenCapture(new Rectangle(
				Toolkit.getDefaultToolkit().getScreenSize()));
		IconFinden iconFinden = new IconFinden();
		try {
			iconFinden.findeIcon(dateiPfad, currentScreen);
			koordinaten2d.x = iconFinden.getxKoordinate();
			koordinaten2d.y = iconFinden.getyKoordinate();
			return true;
		} catch (IconNotFoundException e) {
			return false;
		}
	}

	public boolean beendePopUp() {
		String pfad = pfadeService.getPath() + IconFinden.X;
		return klickSpezielleAbweichung(pfad, 0);
	}

	public void beendeBrowser() {
		String pfad = pfadeService.getPath() + IconFinden.BROWSER_X;
		klickSpezielleAbweichung(pfad, 0);
	}

	public void erhoeheAbweichung() {
		abweichung += 10;
	}

	public void resetAbweichung() {
		abweichung = abweichung_y;
	}

	@Override
	public synchronized BufferedImage createScreenCapture(Rectangle screenRect) {
		if (screenCapture != null) {
			return screenCapture;
		}
		return super.createScreenCapture(screenRect);
	}

	public void setScreenCapture(final BufferedImage geladenesBild) {
		this.screenCapture = geladenesBild;
	}

	public long getMuenzenKlickCount() {
		return this.muenzenKlickCount;
	}

	public void setGrafikPathService(
			GrafikDateiPfadeService grafikDateiPfadeService) {
		this.pfadeService = grafikDateiPfadeService;
	}

	public long getWerkzeugeKlickCount() {
		return this.werkzeugeKlickCount;
	}
}
