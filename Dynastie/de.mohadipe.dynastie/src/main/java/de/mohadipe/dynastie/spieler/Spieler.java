package de.mohadipe.dynastie.spieler;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.input.Input;

public interface Spieler {

	void stelleEinheitenAuf(Konfiguration spielKonfiguration, Input aufstellenInput);

	boolean isMensch();

	void setIdentitaet(String string);

}
