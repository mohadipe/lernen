package de.mohadipe.dynastie.karte;

import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.spieler.Spieler;

public class FeldImpl implements Feld {
	private GelaendeBeschaffenheit gelaende;
	private Einheit einheit;

	public FeldImpl(GelaendeBeschaffenheit gelaende) {
		this.gelaende = gelaende;
	}

	@Override
	public void show() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(gelaende.toString());
		if (einheit != null) {
			stringBuilder.append("|").append(einheit.toString());
		}
		return stringBuilder.toString();
	}

	@Override
	public void setEinheit(Einheit einheit) {
		this.einheit = einheit;
	}

	public Einheit getEinheit() {
		if (einheit == null) {
			return null;
		}
		return einheit.copy();
	}

	@Override
	public boolean hasEinheitOf(Spieler spieler) {
		if (this.einheit != null) {
			return einheit.isFromSpieler(spieler);
		}
		return false;
	}

	@Override
	public boolean hasEinheit() {
		if (this.einheit == null) {
			return false;
		}
		return true;
	}
}
