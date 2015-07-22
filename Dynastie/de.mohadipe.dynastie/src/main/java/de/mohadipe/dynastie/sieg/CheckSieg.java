package de.mohadipe.dynastie.sieg;

import java.util.List;

import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.spieler.Spieler;

public interface CheckSieg {
	public static final String ES_MÜSSEN_SPIELER_VORHANDEN_SEIN = "Es müssen Spieler vorhanden sein.";

	void auswerten();

	void setKarte(Karte karte);

	void setSpieler(List<Spieler> beteiligteSpieler);

	boolean isSpielBeendet();

	Sieger getSieger();

}
