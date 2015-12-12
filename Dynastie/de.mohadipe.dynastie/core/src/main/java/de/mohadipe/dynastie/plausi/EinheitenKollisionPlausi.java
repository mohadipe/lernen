package de.mohadipe.dynastie.plausi;

import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.logik.model.Koordinate;

public class EinheitenKollisionPlausi {

	private Karte karte;

	public EinheitenKollisionPlausi(Karte karte) {
		this.karte = karte;
	}

	public boolean isKeineEinheitAnZiel(Koordinate ziel) {
		return this.karte.isKeineEinheitAnKoordinate(ziel);
	}

}
