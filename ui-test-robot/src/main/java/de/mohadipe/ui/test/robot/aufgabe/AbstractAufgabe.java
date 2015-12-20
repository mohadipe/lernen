package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAufgabe implements Aufgabe {

	private Map<AufgabeDaten, Object> daten = new HashMap<AufgabeDaten, Object>();
	private Map<AufgabenArten, Aufgabe> abhaengigkeiten = new HashMap<AufgabenArten, Aufgabe>();

	@Override
	public int getPositionAusfuehrung() {
		return ((Integer) daten.get(AufgabeDaten.REIHENFOLGE)).intValue();
	}

	@Override
	public void setPositionAusfuehrung(int reihenfolge) {
		setDaten(AufgabeDaten.REIHENFOLGE, reihenfolge);
	}

	public void addAbhaengigkeit(Aufgabe aufgabe) {
		this.abhaengigkeiten.put(aufgabe.getArt(), aufgabe);
	}

	public Object getDaten(AufgabeDaten datenEnum) {
		return daten.get(datenEnum);
	}

	@Override
	public void setDaten(AufgabeDaten datenEnum, Object object) {
		daten.put(datenEnum, object);
	}

	public Aufgabe getAbhaengigeAufgabeByArt(AufgabenArten aufgabenArt) {
		return abhaengigkeiten.get(aufgabenArt);
	}

	public void setRobot(Robot robot) {
		setDaten(AufgabeDaten.ROBOT, robot);
	}

	public Robot getRobot() {
		return (Robot) getDaten(AufgabeDaten.ROBOT);
	}

	protected BufferedImage takeScreenShot() {
		return getRobot().createScreenCapture(
				new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	}

	protected Map<AufgabenArten, Aufgabe> getAbhaengigeAufgaben() {
		return abhaengigkeiten;
	}
}
