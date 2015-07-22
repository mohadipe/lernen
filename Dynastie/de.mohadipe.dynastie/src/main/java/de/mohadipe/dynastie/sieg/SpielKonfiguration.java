package de.mohadipe.dynastie.sieg;

import java.util.ArrayList;
import java.util.List;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.Siegbedingung;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.spieler.Spieler;

public class SpielKonfiguration implements Konfiguration {
	private int einheitenPunkte = 10;
	private Siegbedingung sieg = Siegbedingung.VERNICHTEN;
	private List<Spieler> spieler = new ArrayList<Spieler>();
	private Karte karte;

	@Override
	public Siegbedingung getSieg() {
		return sieg;
	}

	@Override
	public Integer getEinheitenPunkte() {
		return einheitenPunkte;
	}

	public void addSpieler(Spieler spieler) {
		this.spieler.add(spieler);
	}

	@Override
	public Spieler getSpieler(int i) {
		return spieler.get(i);
	}

	@Override
	public int getAnzSpieler() {
		return spieler.size();
	}

	@Override
	public List<Spieler> getAlleSpieler() {
		return spieler;
	}

	@Override
	public void setKarte(Karte karte) {
		this.karte = karte;
	}

	@Override
	public Karte getKarte() {
		return this.karte;
	}
}
