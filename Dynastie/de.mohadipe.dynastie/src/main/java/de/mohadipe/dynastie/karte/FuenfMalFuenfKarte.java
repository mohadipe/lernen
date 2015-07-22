package de.mohadipe.dynastie.karte;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.RandomService;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.output.SysOutKarteAnzeige;
import de.mohadipe.dynastie.spieler.Spieler;

public class FuenfMalFuenfKarte implements Karte {
	private final Konfiguration konfiguration;
	Map<Koordinate, Feld> felderMap = new HashMap<Koordinate, Feld>();
	private final Koordinate start1 = new ZweiDimensionaleKoordinate(1, 1);
	private final Koordinate start2 = new ZweiDimensionaleKoordinate(5, 5);
	private final int maxX = 5;
	private final int maxY = maxX;
	private final int minX = 1;
	private final int minY = minX;

	public FuenfMalFuenfKarte(final Konfiguration spielKonfiguration, RandomService randomService) {
		this.konfiguration = spielKonfiguration;
		init(randomService);
		initSiegbedingung();
	}

	private void init(RandomService randomService) {
		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				felderMap.put(new ZweiDimensionaleKoordinate(x, y), new FeldImpl(GelaendeAdapter.getGelaende(randomService)));
			}
		}
	}

	private void initSiegbedingung() {
		switch (konfiguration.getSieg()) {
		case VERNICHTEN:
			// kein Feld der Karte ist eine Siegbedinung
			break;

		default:
			break;
		}
	}

	public void anzeigen() {
		new SysOutKarteAnzeige(this).anzeigen();
	}

	@Override
	public void fuegeEinheitVonSpielerHinzu(Koordinate koordinate, Einheit einheit) {
		Feld feld = felderMap.get(koordinate);
		feld.setEinheit(einheit);
	}

	@Override
	public String toString() {
		return new SysOutKarteAnzeige(this).toString();
	}

	@Override
	public int getMaxX() {
		return maxX;
	}

	@Override
	public int getMaxY() {
		return maxY;
	}

	@Override
	public String getSysOutFeld(ZweiDimensionaleKoordinate zweiDimensionaleKoordinate) {
		Feld feld = felderMap.get(zweiDimensionaleKoordinate);
		return feld.toString();
	}

	@Override
	public boolean hasSpielerEinheiten(Spieler tmp) {
		Predicate<Feld> predicate = new EinheitBedingung(tmp);
		return felderMap.values().stream().filter(predicate).count() > 0;
	}

	public class EinheitBedingung implements Predicate<Feld> {
		Spieler spieler;

		public EinheitBedingung(Spieler tmp) {
			this.spieler = tmp;
		}

		@Override
		public boolean test(Feld feld) {
			return feld.hasEinheitOf(this.spieler);
		}

	}

	@Override
	public Map<Koordinate, Feld> getEinheitenMitKoordinatenVonSpieler(Spieler spieler) {
		Map<Koordinate, Feld> felderMitEinheitVomSpieler = felderMap.entrySet().stream().filter(new KoordinateEinheitBedingung(spieler)).collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()));
		return felderMitEinheitVomSpieler;
	}

	@Override
	public Map<Koordinate, Feld> getEinheitenMitKoordinatenVonAnderenSpielern(Spieler spieler) {
		KoordinateEinheitBedingung koordinateEinheitBedingung = new KoordinateEinheitBedingung(spieler);
		Map<Koordinate, Feld> felderMitEinheitVomSpieler = felderMap.entrySet().stream().filter(koordinateEinheitBedingung.negate()).filter(new IgnoriereLeereFelderBedingung()).collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue()));
		return felderMitEinheitVomSpieler;
	}

	@Override
	public void bewegeEinheitVonNach(Einheit einheit, Koordinate standort, Koordinate ziel) {
		Feld feld = felderMap.get(standort);
		if (!feld.getEinheit().equals(einheit)) {
			throw new RuntimeException("Einheit auf Feld am Standort stimmt nicht mit der zu bewegenden Einheit überein.");
		}
		feld.setEinheit(null);
		felderMap.get(ziel).setEinheit(einheit);
	}

	@Override
	public Map<Koordinate, Feld> getFelderMap() {
		return felderMap;
	}

	@Override
	public int getMinX() {
		return minX;
	}

	@Override
	public int getMinY() {
		return minY;
	}

	@Override
	public void platziereEinheitAnStartKoordinate(Einheit einheit) {
		if (!felderMap.get(start1).hasEinheit()) {
			this.fuegeEinheitVonSpielerHinzu(start1, einheit);
			return;
		}
		if (!felderMap.get(start2).hasEinheit()) {
			this.fuegeEinheitVonSpielerHinzu(start2, einheit);
			return;
		}
		throw new RuntimeException("Es sind keine weiteren StartKoordinaten vorhanden.");
	}

	@Override
	public boolean isKeineEinheitAnKoordinate(Koordinate ziel) {
		return !felderMap.get(ziel).hasEinheit();
	}

}
