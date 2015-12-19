package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.Robot;

public interface Aufgabe {

	void ausfuehren();

	boolean isErfolgreich();

	int getPositionAusfuehrung();

	void setPositionAusfuehrung(int reihenfolge);

	void addAbhaengigkeit(Aufgabe grafikFinden);

	Object getDaten(AufgabeDaten koordinate);

	AufgabenArten getArt();

	void setRobot(Robot robot);

	void setDaten(AufgabeDaten key, Object value);

}
