package de.mohadipe.dynastie.karte;

import java.util.Map;

import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.spieler.Spieler;

public interface Karte {

	int getMaxX();

	int getMaxY();

	String getSysOutFeld(ZweiDimensionaleKoordinate zweiDimensionaleKoordinate);

	boolean hasSpielerEinheiten(Spieler tmp);

	public abstract void fuegeEinheitVonSpielerHinzu(Koordinate koordinate, Einheit einheit);

	Map<Koordinate, Feld> getEinheitenMitKoordinatenVonSpieler(Spieler spieler);

	void bewegeEinheitVonNach(Einheit einheit, Koordinate standort, Koordinate ziel);

	Map<Koordinate, Feld> getEinheitenMitKoordinatenVonAnderenSpielern(Spieler spieler);

	Map<Koordinate, Feld> getFelderMap();

	int getMinX();

	int getMinY();

	void platziereEinheitAnStartKoordinate(Einheit einheit);

	boolean isKeineEinheitAnKoordinate(Koordinate ziel);

}
