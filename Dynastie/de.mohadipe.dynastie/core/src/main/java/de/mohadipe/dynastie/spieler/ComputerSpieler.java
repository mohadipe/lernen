package de.mohadipe.dynastie.spieler;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.input.Input;
import de.mohadipe.dynastie.logik.model.Einheit;

public class ComputerSpieler implements Spieler {

	private final String kuerzel = "AI";
	private String identitaet;

	public ComputerSpieler(String identitaet) {
		this.identitaet = identitaet;
	}

	public ComputerSpieler() {
		this("KI");
	}

	@Override
	public void stelleEinheitenAuf(Konfiguration spielKonfiguration, Input input) {
		Einheit einheit = new Infanterie();
		((Infanterie)einheit).setSpieler(this);
		spielKonfiguration.getKarte().platziereEinheitInStartZone(einheit);
	}

	@Override
	public String toString() {
		return identitaet;
	}

	@Override
	public boolean isMensch() {
		return false;
	}

	public void setIdentitaet(String identitaet) {
		this.identitaet = identitaet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identitaet == null) ? 0 : identitaet.hashCode());
		result = prime * result + ((kuerzel == null) ? 0 : kuerzel.hashCode());
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
		ComputerSpieler other = (ComputerSpieler) obj;
		if (identitaet == null) {
			if (other.identitaet != null)
				return false;
		} else if (!identitaet.equals(other.identitaet))
			return false;
		if (kuerzel == null) {
			if (other.kuerzel != null)
				return false;
		} else if (!kuerzel.equals(other.kuerzel))
			return false;
		return true;
	}
}
