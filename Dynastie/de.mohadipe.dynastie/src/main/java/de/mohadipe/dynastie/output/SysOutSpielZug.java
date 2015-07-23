package de.mohadipe.dynastie.output;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Feld;

public class SysOutSpielZug extends TutNixOutput {

	@Override
	public void anzeigeSpielZugEnde(int runde, Konfiguration konfiguration) {
		System.out.println("Runde: " + runde + " wurde gespielt.");
		System.out.println(konfiguration.getKarte().toString());
	}

	@Override
	public void anzeigeGegnerInReichweite(Map<Einheit, List<Feld>> gegnerZuEinheit) {
		for (Entry<Einheit, List<Feld>> entry : gegnerZuEinheit.entrySet()) {
			System.out.println("In Reichweite der Einheit: " + entry.getKey().toString() + " befinden sich:");
			for (Feld feld : entry.getValue()) {
				System.out.println("Einheit: " + feld.getEinheit().toString());
			}
		}
	}

	@Override
	public void einheitBekaempfen(Einheit angreifer, Feld ziel) {
		System.out.println("Die Einheit: " + angreifer.toString() + " bekaempft: " + ziel.getEinheit().toString());
	}

	@Override
	public void einheitGetroffen(Einheit copyOfEinheit) {
		System.out.println("Die Einheit: " + copyOfEinheit.toString() + " wurde getroffen.");
	}

	@Override
	public void einheitVerfaehlt(int randomNummerEinsBis) {
		System.out.println("Ziel wurde verfaehlt. " + randomNummerEinsBis);
	}

	@Override
	public void zielZerstoert(Einheit copyOfEinheit) {
		System.out.println("Ziel wurde zerstoert: " + copyOfEinheit.toString());
	}
}
