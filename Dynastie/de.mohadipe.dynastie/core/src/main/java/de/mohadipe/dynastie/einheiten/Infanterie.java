package de.mohadipe.dynastie.einheiten;

import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.spieler.Spieler;

public class Infanterie implements Einheit {
	private Spieler spieler;
	private int aktLebenspunkte;
	private String eindeutigeKennung;

	public Infanterie() {
		this.aktLebenspunkte = InfanterieDaten.standardLebenspunkte;
	}

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}

	public Spieler getSpieler() {
		return spieler;
	}

	@Override
	public String toString() {
		String trenner = "|";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(InfanterieDaten.kuerzel).append("-").append(eindeutigeKennung).append(trenner).append(spieler.toString()).append(trenner).append(aktLebenspunkte);
		return stringBuilder.toString();
	}

	public boolean isFromSpieler(Spieler spieler) {
		return this.spieler.equals(spieler);
	}

	@Override
	public int getBewegungsReichweite() {
		return InfanterieDaten.bewegungsReichweite;
	}

	@Override
	public Einheit copy() {
		Infanterie infanterie = new Infanterie();
		infanterie.setEindeutigeKennung(eindeutigeKennung);
		infanterie.setSpieler(this.getSpieler());
		infanterie.setAktLebenspunkte(this.aktLebenspunkte);
		return infanterie;
	}

	public void setAktLebenspunkte(int aktLebenspunkte) {
		this.aktLebenspunkte = aktLebenspunkte;
	}

	@Override
	public int getLebenspunkte() {
		return aktLebenspunkte;
	}

	@Override
	public int getStaerke() {
		return InfanterieDaten.staerke;
	}

	@Override
	public void erleideTrefferStaerke(int staerke) {
		aktLebenspunkte -= staerke;
	}

	@Override
	public int getAngriffsReichweite() {
		return InfanterieDaten.angriffsReichweite;
	}

	@Override
	public void setEindeutigeKennung(String eindeutigeKennung) {
		this.eindeutigeKennung = eindeutigeKennung;
	}

	@Override
	public boolean isAmLeben() {
		return aktLebenspunkte > 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aktLebenspunkte;
		result = prime * result + ((eindeutigeKennung == null) ? 0 : eindeutigeKennung.hashCode());
		result = prime * result + ((spieler == null) ? 0 : spieler.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Infanterie other = (Infanterie) obj;
		if (aktLebenspunkte != other.aktLebenspunkte)
			return false;
		if (eindeutigeKennung == null) {
			if (other.eindeutigeKennung != null)
				return false;
		} else if (!eindeutigeKennung.equals(other.eindeutigeKennung))
			return false;
		if (spieler == null) {
			if (other.spieler != null)
				return false;
		} else if (!spieler.equals(other.spieler))
			return false;
		return true;
	}
}
