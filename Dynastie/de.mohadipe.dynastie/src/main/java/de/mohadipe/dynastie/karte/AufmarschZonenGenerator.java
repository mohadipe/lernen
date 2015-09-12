package de.mohadipe.dynastie.karte;

import java.util.ArrayList;
import java.util.List;

public class AufmarschZonenGenerator {
	private final Karte karte;

	public AufmarschZonenGenerator(Karte karte) {
		if (karte == null) {
			throw new RuntimeException("Zum generieren von AufmarschZonen wird eine Karte benötigt.");
		}
		this.karte = karte;
	}

	public List<AufmarschZone> generiereAufmarschZonen() {
		List<AufmarschZone> aufmarschZonen = new ArrayList<AufmarschZone>();
		int minX = karte.getMinX();
		int minY = karte.getMinY();
		int maxX = karte.getMaxX();
		int maxY = karte.getMaxY();
		aufmarschZonen.add(new AufmarschZone(new ZweiDimensionaleKoordinate(minX, minY)));
		aufmarschZonen.add(new AufmarschZone(new ZweiDimensionaleKoordinate(maxX, maxY)));
		return aufmarschZonen;
	}
}
