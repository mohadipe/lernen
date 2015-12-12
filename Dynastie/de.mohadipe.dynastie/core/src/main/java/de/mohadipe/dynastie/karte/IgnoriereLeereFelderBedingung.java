package de.mohadipe.dynastie.karte;

import java.util.Map.Entry;
import java.util.function.Predicate;

import de.mohadipe.dynastie.logik.model.Feld;
import de.mohadipe.dynastie.logik.model.Koordinate;

public class IgnoriereLeereFelderBedingung implements Predicate<Entry<Koordinate, Feld>> {

	@Override
	public boolean test(Entry<Koordinate, Feld> entry) {
		if (entry.getValue().hasEinheit()) {
			return true;
		}
		return false;
	}

}
