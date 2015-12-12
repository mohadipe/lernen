package de.mohadipe.dynastie.logik.model;


public interface Feld {

	void show();

	void setEinheit(Einheit einheit);

//	boolean hasEinheitOf(Spieler spieler);

	Einheit getEinheit();

	boolean hasEinheit();

//	GelaendeBeschaffenheit getGelaende();
}
