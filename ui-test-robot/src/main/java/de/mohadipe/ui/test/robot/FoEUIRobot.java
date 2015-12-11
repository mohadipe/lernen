package de.mohadipe.ui.test.robot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FoEUIRobot extends Robot {

	private static final int abweichung_y = 60;
	private static final int EINFACHE_ABWEICHUNG = 5;
	private Path rootPath;
	private String ausgangspunkt;
	private static final String ide_icon_pfad = "\\src\\main\\resources\\icons\\";
	private String pfadZumBild = "";
	private int abweichung;
	
	public FoEUIRobot() throws AWTException {
		super();
		this.abweichung = abweichung_y;
	}

	private void doppelKlickEinfacheAbweichung(String dateiPfad) {
		BufferedImage currentScreen = this.createScreenCapture(new Rectangle(
				Toolkit.getDefaultToolkit().getScreenSize()));
		IconFinden iconFinden = new IconFinden();
		try {
			iconFinden.findeIcon(dateiPfad, currentScreen);
			int x = iconFinden.getxKoordinate();
			int y = iconFinden.getyKoordinate();
			mouseMove(x+EINFACHE_ABWEICHUNG, y+EINFACHE_ABWEICHUNG);
			mousePress(InputEvent.BUTTON1_DOWN_MASK);
			mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			mousePress(InputEvent.BUTTON1_DOWN_MASK);
			mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} catch (IconNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private boolean klickSpezielleAbweichung(String dateiPfad, int abweichungY) {
		BufferedImage currentScreen = this.createScreenCapture(new Rectangle(
				Toolkit.getDefaultToolkit().getScreenSize()));
		IconFinden iconFinden = new IconFinden();
		try {
			iconFinden.findeIcon(dateiPfad, currentScreen);
			int x = iconFinden.getxKoordinate();
			int y = iconFinden.getyKoordinate();
			mouseMove(x, y+abweichungY);
			
			mousePress(InputEvent.BUTTON1_DOWN_MASK);
			mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			return true;
		} catch (IconNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void starteFoEBrowser() {
		String pfad = ausgangspunkt + Paths.get("").resolve(pfadZumBild).resolve(IconFinden.KLICK_01).toString();
		System.out.println(pfad);
		doppelKlickEinfacheAbweichung(pfad);
	}
	
	public void spieleFoE() {
		String pfad = ausgangspunkt + Paths.get("").resolve(pfadZumBild).resolve(IconFinden.KLICK_02).toString();
		System.out.println(pfad);
		doppelKlickEinfacheAbweichung(pfad);
	}
	
	public void waehleServerRugnir() {
		String pfad = ausgangspunkt + Paths.get("").resolve(pfadZumBild).resolve(IconFinden.KLICK_03).toString();
		System.out.println(pfad);
		doppelKlickEinfacheAbweichung(pfad);
	}

	public void menuesBestaetigen() {
		String pfad = ausgangspunkt + Paths.get("").resolve(pfadZumBild).resolve(IconFinden.KLICK_04).toString();
		System.out.println(pfad);
		doppelKlickEinfacheAbweichung(pfad);
	}
	
	public void holeMuenzenAb() {
		Koordinaten2D koordinaten2d = new Koordinaten2D();
		if (findeKoordinaten(koordinaten2d, IconFinden.WIEDERHOLEN)){
			klickKoordinaten(koordinaten2d);
		} else if (findeKoordinaten(koordinaten2d, IconFinden.WIEDERHOLEN_02)) {
			klickKoordinaten(koordinaten2d);
		} else if (findeKoordinaten(koordinaten2d, IconFinden.WIEDERHOLEN_03)) {
			klickKoordinaten(koordinaten2d);
		}
	}

	private void klickKoordinaten(Koordinaten2D koordinaten2d) {
		mouseMove(koordinaten2d.x, koordinaten2d.y+abweichung);
		mousePress(InputEvent.BUTTON1_DOWN_MASK);
		mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}

	private boolean findeKoordinaten(Koordinaten2D koordinaten2d, String iconName) {
		String pfad = ausgangspunkt + Paths.get("").resolve(pfadZumBild).resolve(iconName).toString();
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
		} catch(IconNotFoundException e) {
			return false;
		}
	}

	public boolean beendePopUp() {
		String pfad = ausgangspunkt + Paths.get("").resolve(pfadZumBild).resolve(IconFinden.X).toString();
		return klickSpezielleAbweichung(pfad, 0);
	}

	public void setRootPfad(Path currentRelativePath) {
		this.rootPath = currentRelativePath;
		this.ausgangspunkt = rootPath.toAbsolutePath().toString();
		if (rootPath.toString().contains("workspace")) {
			pfadZumBild = ide_icon_pfad;
		} else {
			pfadZumBild = "\\";
		}
	}

	public void erhoeheAbweichung() {
		abweichung += 10;
	}

	public void beendeBrowser() {
		String pfad = ausgangspunkt + Paths.get("").resolve(pfadZumBild).resolve(IconFinden.BROWSER_X).toString();
		klickSpezielleAbweichung(pfad, 0);
	}

	public void resetAbweichung() {
		abweichung = abweichung_y;		
	}
}
