package de.mohadipe.dynastie.bewegen;

import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.plausi.AufKartePlausi;
import de.mohadipe.dynastie.plausi.EinheitenKollisionPlausi;

public class KIBewegung extends AbstractBewegung {

	protected void bewegeEinheitVon(Koordinate standort, Einheit einheit) {
		EinheitenKollisionPlausi einheitenKollisionPlausi = new EinheitenKollisionPlausi(karte);
		AufKartePlausi aufKartePlausi = new AufKartePlausi(karte);
		int bewegungsReichweite = einheit.getBewegungsReichweite();
		int standortX = standort.getX();
		int standortY = standort.getY();
		Koordinate ziel = diagonaleBewegung(bewegungsReichweite, standortX, standortY);
		if (einheitenKollisionPlausi.isKeineEinheitAnZiel(ziel)) {
			karte.bewegeEinheitVonNach(einheit, standort, ziel);
			return;
		}
		ziel = vertikaleYBewegung(bewegungsReichweite, standortY, standort.getX(), aufKartePlausi);
		if (einheitenKollisionPlausi.isKeineEinheitAnZiel(ziel)) {
			karte.bewegeEinheitVonNach(einheit, standort, ziel);
			return;
		}
		ziel = vertikaleXBewegung(bewegungsReichweite, standortX, aufKartePlausi, standort.getY());
		if (einheitenKollisionPlausi.isKeineEinheitAnZiel(ziel)) {
			karte.bewegeEinheitVonNach(einheit, standort, ziel);
			return;
		}
	}

	private Koordinate vertikaleXBewegung(int bewegungsReichweite, int standortX, AufKartePlausi aufKartePlausi, int zielY) {
		int zielX = xDiagonaleBewegung(bewegungsReichweite, standortX, aufKartePlausi);
		Koordinate ziel = new ZweiDimensionaleKoordinate(zielX, zielY);
		return ziel;
	}

	private Koordinate vertikaleYBewegung(int bewegungsReichweite, int standortY, int zielX, AufKartePlausi aufKartePlausi) {
		int zielY = yDiagonaleBewegung(bewegungsReichweite, standortY, aufKartePlausi);
		Koordinate ziel = new ZweiDimensionaleKoordinate(zielX, zielY);
		return ziel;
	}

	private Koordinate diagonaleBewegung(int bewegungsReichweite, int standortX, int standortY) {
		AufKartePlausi aufKartePlausi = new AufKartePlausi(karte);
		int zielY = yDiagonaleBewegung(bewegungsReichweite, standortY, aufKartePlausi);
		int zielX = xDiagonaleBewegung(bewegungsReichweite, standortX, aufKartePlausi);
		Koordinate ziel = new ZweiDimensionaleKoordinate(zielX, zielY);
		return ziel;
	}

	private int xDiagonaleBewegung(int bewegungsReichweite, int standortX, AufKartePlausi aufKartePlausi) {
		if (aufKartePlausi.isXAufKarte(standortX + bewegungsReichweite)) {
			return standortX + bewegungsReichweite;
		} else {
			if (aufKartePlausi.isXAufKarte(standortX - bewegungsReichweite)) {
				return standortX - bewegungsReichweite;
			}
		}
		throw new RuntimeException("Keine gueltige Bewegung auf X Koordinate moeglich.");
	}

	private int yDiagonaleBewegung(int bewegungsReichweite, int standortY, AufKartePlausi aufKartePlausi) {
		if (aufKartePlausi.isYAufKarte(standortY + bewegungsReichweite)) {
			return standortY + bewegungsReichweite;
		} else {
			if (aufKartePlausi.isYAufKarte(standortY - bewegungsReichweite)) {
				return standortY - bewegungsReichweite;
			}
		}
		throw new RuntimeException("Keine gueltige Bewegung auf Y Koordinate moeglich.");
	}
}
