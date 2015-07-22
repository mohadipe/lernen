package de.mohadipe.dynastie.spieler;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.input.Input;
import de.mohadipe.dynastie.karte.Karte;

public interface Spieler {

	void stelleEinheitenAuf(Karte fuenfMalFuenfKarte, Konfiguration spielKonfiguration, Input aufstellenInput);

	boolean isMensch();

	void setIdentitaet(String string);

}
