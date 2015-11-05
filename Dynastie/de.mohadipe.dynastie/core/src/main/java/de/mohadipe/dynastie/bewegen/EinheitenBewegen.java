package de.mohadipe.dynastie.bewegen;

import de.mohadipe.dynastie.input.Input;
import de.mohadipe.dynastie.spieler.Spieler;

public abstract class EinheitenBewegen {

	public static Bewegung creaeInstanceFuerSpieler(Spieler spieler, Input input) {
		if (spieler.isMensch()) {
			return new MenschBewegung(input);
		} else {
			return new KIBewegung();
		}
	}

}
