package de.mohadipe.dynastie.angreifen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Feld;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.plausi.UmkreisPlausi;
import de.mohadipe.dynastie.spieler.Spieler;

public class GegnerEinheitenErmittler implements Consumer<Entry<Koordinate, Feld>> {

	private final Karte karte;
	private Map<Einheit, List<Feld>> gegnerZuEinheit = new HashMap<Einheit, List<Feld>>();

	public GegnerEinheitenErmittler(Karte karte) {
		this.karte = karte;
	}

	@Override
	public void accept(Entry<Koordinate, Feld> entry) {
		Spieler spieler = entry.getValue().getEinheit().getSpieler();
		int angriffsReichweite = entry.getValue().getEinheit().getAngriffsReichweite();
		Koordinate standort = entry.getKey();
		Map<Koordinate, Feld> einheitenMitKoordinatenVonAnderenSpielern = karte.getEinheitenMitKoordinatenVonAnderenSpielern(spieler);
		UmkreisPlausi umkreisPlausi = new UmkreisPlausi(null, standort, angriffsReichweite);
		einheitenMitKoordinatenVonAnderenSpielern.entrySet().forEach(umkreisPlausi);
		List<Feld> gegner = umkreisPlausi.getGegner();
		if (gegner.size() > 0) {
			gegnerZuEinheit.put(entry.getValue().getEinheit(), gegner);
		}
	}

	public Map<Einheit, List<Feld>> getGegnerZuEinheit() {
		return gegnerZuEinheit;
	}
}
