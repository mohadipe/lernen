package de.mohadipe.dynastie.bewegen;

import java.util.Map;
import java.util.Map.Entry;

import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Feld;
import de.mohadipe.dynastie.logik.model.Koordinate;

public class StandortErmitteln {

	private Karte karte;

	public StandortErmitteln(Karte karte) {
		this.karte = karte;
	}

	public Koordinate getStandortFuer(Einheit einheit) {
		Map<Koordinate, Feld> felderMap = karte.getFelderMap();
		for (Entry<Koordinate, Feld> entry : felderMap.entrySet()) {
			if (entry.getValue().hasEinheit() && entry.getValue().getEinheit().equals(einheit)) {
				return entry.getKey();
			}
		}
		throw new RuntimeException("Diese Einheit: " + einheit.toString() + " ist nicht auf der Karte.");
	}

}
