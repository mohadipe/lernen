package de.mohadipe.dynastie.karte;

import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.spieler.Spieler;

public interface Feld {

	void show();

	void setEinheit(Einheit einheit);

	boolean hasEinheitOf(Spieler spieler);

	Einheit getEinheit();

	boolean hasEinheit();

	GelaendeBeschaffenheit getGelaende();
}
