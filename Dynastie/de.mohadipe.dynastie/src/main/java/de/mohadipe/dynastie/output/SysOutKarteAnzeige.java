package de.mohadipe.dynastie.output;

import java.util.List;
import java.util.Map;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Feld;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;

public class SysOutKarteAnzeige implements Output {
	private Karte karte;

	public SysOutKarteAnzeige(Karte karte) {
		this.karte = karte;
	}

	public void anzeigen() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		int maxX = karte.getMaxX();
		int maxY = karte.getMaxY();
		String tab = "\t\t";
		String umbruch = "\n";
		// Von Links Oben nach Rechts unten
		// y:1 x:1
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Y|X").append(tab);
		for (int x = 1; x <= maxX; x++) {
			stringBuilder.append(x).append(tab);
		}
		stringBuilder.append(umbruch);
		for (int y = 1; y <= maxY; y++) {
			stringBuilder.append(y + ":").append(tab);
			for (int x = 1; x <= maxX; x++) {
				stringBuilder.append(karte.getSysOutFeld(new ZweiDimensionaleKoordinate(x, y))).append(tab);
			}
			stringBuilder.append(umbruch);
		}
		return stringBuilder.toString();
	}

	@Override
	public void anzeigeSpielZugEnde(int runde, Konfiguration konfiguration) {
		// TODO Auto-generated method stub

	}

	@Override
	public void anzeigeGegnerInReichweite(Map<Einheit, List<Feld>> gegnerZuEinheit) {
		// TODO Auto-generated method stub

	}
}
