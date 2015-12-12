package de.mohadipe.dynastie.plausi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;

import de.mohadipe.dynastie.logik.model.Feld;
import de.mohadipe.dynastie.logik.model.Koordinate;

public class UmkreisPlausi implements Consumer<Entry<Koordinate, Feld>> {

	public static final String KEIN_ZIEL_DEFINIERT = "Kein Ziel definiert.";
	private Koordinate ziel;
	private final Koordinate standort;
	private final int reichweite;
	private List<Feld> gegner = new ArrayList<Feld>();

	public UmkreisPlausi(final Koordinate ziel, Koordinate standort, int reichweite) {
		this.ziel = ziel;
		this.standort = standort;
		this.reichweite = reichweite;
	}

	public boolean check() {
		if (ziel == null) {
			throw new RuntimeException(KEIN_ZIEL_DEFINIERT);
		}
		// Ziel in Reichweite vom Standort ausgehend
		int reichweiteXPlus = standort.getX() + reichweite;
		int reichweiteXMinus = standort.getX() - reichweite;
		int reichweiteYPlus = standort.getY() + reichweite;
		int reichweiteYMinus = standort.getY() - reichweite;

		if (reichweiteXPlus >= ziel.getX() && reichweiteXMinus <= ziel.getX() && reichweiteYMinus <= ziel.getY() && reichweiteYPlus >= ziel.getY()) {
			return true;
		}
		return false;
	}

	public List<Feld> getGegner() {
		return gegner;
	}

	@Override
	public void accept(Entry<Koordinate, Feld> zielEntry) {
		this.ziel = zielEntry.getKey();
		if (check()) {
			gegner.add(zielEntry.getValue());
		}
	}
}
