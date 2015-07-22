package de.mohadipe.dynastie.einheiten;

import de.mohadipe.dynastie.spieler.Spieler;

public interface Einheit {

	void setSpieler(Spieler spieler);

	boolean isFromSpieler(Spieler spieler);

	int getBewegungsReichweite();

	Einheit copy();

	Spieler getSpieler();

	int getLebenspunkte();

	int getStaerke();

	void erleideTrefferStaerke(int staerke);

	int getAngriffsReichweite();

	void setEindeutigeKennung(String eindeutigeKennung);

	boolean isAmLeben();
}
