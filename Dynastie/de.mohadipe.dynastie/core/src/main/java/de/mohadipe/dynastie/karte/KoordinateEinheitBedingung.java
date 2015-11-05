package de.mohadipe.dynastie.karte;

import java.util.Map.Entry;
import java.util.function.Predicate;

import de.mohadipe.dynastie.spieler.Spieler;

public class KoordinateEinheitBedingung implements Predicate<Entry<Koordinate, Feld>> {
	Spieler spieler;

	public KoordinateEinheitBedingung(Spieler spieler) {
		this.spieler = spieler;
	}

	@Override
	public boolean test(Entry<Koordinate, Feld> entry) {
		return entry.getValue().hasEinheitOf(spieler);
	}

	@Override
	public Predicate<Entry<Koordinate, Feld>> negate() {
		return Predicate.super.negate();
	}

}