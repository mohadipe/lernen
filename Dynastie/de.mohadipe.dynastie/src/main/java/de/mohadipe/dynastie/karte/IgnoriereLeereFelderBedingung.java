package de.mohadipe.dynastie.karte;

import java.util.Map.Entry;
import java.util.function.Predicate;

public class IgnoriereLeereFelderBedingung implements Predicate<Entry<Koordinate, Feld>> {

	@Override
	public boolean test(Entry<Koordinate, Feld> entry) {
		if (entry.getValue().hasEinheit()) {
			return true;
		}
		return false;
	}

}
