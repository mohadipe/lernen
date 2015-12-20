package de.mohadipe.ui.test.robot.aufgabe;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class WiederholeAufgabe extends AbstractAufgabe {

	@Override
	public void ausfuehren() {
		final long start = new Date().getTime();
		long aktuelleZeit = new Date().getTime();
		int wiederholungenCount = 0;
		do {
			wiederholeAufgaben();
			wiederholungenCount++;
			setDaten(AufgabeDaten.ANZAHL_WIEDERHOLUNGEN, wiederholungenCount);
			aktuelleZeit = new Date().getTime();
		} while (notTimeOut(start, aktuelleZeit));
	}

	private void wiederholeAufgaben() {
		Map<AufgabenArten, Aufgabe> abhaengigeAufgaben = getAbhaengigeAufgaben();
		Set<Entry<AufgabenArten,Aufgabe>> entrySet = abhaengigeAufgaben.entrySet();
		for (Entry<AufgabenArten, Aufgabe> entry : entrySet) {
			entry.getValue().ausfuehren();
		}
	}

	private boolean notTimeOut(long start, long aktuelleZeit) {
		long dauer = aktuelleZeit - start;
		final long timeOut = ((Long)getDaten(AufgabeDaten.TIMEOUT)).longValue();
		return (dauer < timeOut);
	}

	@Override
	public boolean isErfolgreich() {
		return true;
	}

	@Override
	public AufgabenArten getArt() {
		return AufgabenArten.WIEDERHOLE_AUFGABEN;
	}

}
