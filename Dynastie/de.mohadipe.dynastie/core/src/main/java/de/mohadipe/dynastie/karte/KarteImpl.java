package de.mohadipe.dynastie.karte;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Feld;
import de.mohadipe.dynastie.logik.model.Koordinate;
import de.mohadipe.dynastie.spieler.Spieler;

public class KarteImpl implements Karte {

	private Integer maxFelderX;
	private Integer maxFelderY;
	private Map<Koordinate, Einheit> einheitenAnKoordinaten = new HashMap<Koordinate, Einheit>();
	
	public KarteImpl(Integer x, Integer y) {
		this.maxFelderX = x;
		this.maxFelderY = y;
	}

	@Override
	public int getMaxX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getSysOutFeld(
			ZweiDimensionaleKoordinate zweiDimensionaleKoordinate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSpielerEinheiten(Spieler tmp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fuegeEinheitVonSpielerHinzu(Koordinate koordinate,
			Einheit einheit) {
		einheitenAnKoordinaten.put(koordinate, einheit);
	}

	@Override
	public Map<Koordinate, Feld> getEinheitenMitKoordinatenVonSpieler(
			Spieler spieler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bewegeEinheitVonNach(Einheit einheit, Koordinate standort,
			Koordinate ziel) {
		einheitenAnKoordinaten.remove(standort);
		einheitenAnKoordinaten.put(ziel, einheit);
	}

	@Override
	public Map<Koordinate, Feld> getEinheitenMitKoordinatenVonAnderenSpielern(
			Spieler spieler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Koordinate, Feld> getFelderMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMinX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void platziereEinheitInStartZone(Einheit einheit) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isKeineEinheitAnKoordinate(Koordinate ziel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Koordinate getStandortVonEinheit(Einheit einheit) {
		Set<Entry<Koordinate,Einheit>> entrySet = einheitenAnKoordinaten.entrySet();
		for (Entry<Koordinate, Einheit> entry : entrySet) {
			if (entry.getValue().equals(einheit)) {
				return entry.getKey();
			}
		}
		return null;
	}

}
