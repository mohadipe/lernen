package de.mohadipe.dynastie;

import java.util.List;

import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.spieler.Spieler;

public interface Konfiguration {

	Siegbedingung getSieg();

	Integer getEinheitenPunkte();

	Spieler getSpieler(int i);

	int getAnzSpieler();

	List<Spieler> getAlleSpieler();

	void setKarte(Karte karte);

	Karte getKarte();

}