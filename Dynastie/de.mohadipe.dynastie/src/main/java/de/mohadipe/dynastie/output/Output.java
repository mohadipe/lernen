package de.mohadipe.dynastie.output;

import java.util.List;
import java.util.Map;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Feld;

public interface Output {

	void anzeigeSpielZugEnde(int runde, Konfiguration konfiguration);

	void anzeigeGegnerInReichweite(Map<Einheit, List<Feld>> gegnerZuEinheit);

	void einheitBekaempfen(Einheit angreifer, Feld ziel);

	void zielZerstoert(Einheit copyOfEinheit);

	void einheitVerfaehlt(int randomNummerEinsBis);

	void einheitGetroffen(Einheit copyOfEinheit);

}
