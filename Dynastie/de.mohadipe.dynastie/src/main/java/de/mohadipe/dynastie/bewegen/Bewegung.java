package de.mohadipe.dynastie.bewegen;

import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.spieler.Spieler;

public interface Bewegung {

	void bewegen();
	
	void setKarte(Karte karte);
	
	void setSpieler(Spieler spieler);

}
