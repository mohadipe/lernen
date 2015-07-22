package de.mohadipe.dynastie.output;

import java.util.List;
import java.util.Map;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Feld;

public class SysOutSpielZug implements Output {

	@Override
	public void anzeigeSpielZugEnde(int runde, Konfiguration konfiguration) {
		System.out.println("Runde: " + runde + " wurde gespielt.");
		System.out.println(konfiguration.getKarte().toString());
	}

	@Override
	public void anzeigeGegnerInReichweite(Map<Einheit, List<Feld>> gegnerZuEinheit) {

	}

}
