package de.mohadipe.dynastie.spieler;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.input.Input;
import de.mohadipe.dynastie.logik.model.Einheit;

public class MenschSpieler implements Spieler {

	private final String kuerzel = "P";
	private String identitaet;

	@Override
	public void stelleEinheitenAuf(Konfiguration spielKonfiguration, Input aufstellenInput) {
		aufstellenInput.setKonfiguration(spielKonfiguration);
		aufstellenInput.frageInputAb();
		Einheit einheit1 = new Infanterie();
		einheit1.setEindeutigeKennung("1");
		((Infanterie)einheit1).setSpieler(this);
		spielKonfiguration.getKarte().fuegeEinheitVonSpielerHinzu(aufstellenInput.getInputKoordinate(), einheit1);
		aufstellenInput.frageInputAb();
		Einheit einheit2 = new Infanterie();
		einheit2.setEindeutigeKennung("2");
		((Infanterie)einheit2).setSpieler(this);
		spielKonfiguration.getKarte().fuegeEinheitVonSpielerHinzu(aufstellenInput.getInputKoordinate(), einheit2);

	}

	@Override
	public String toString() {
		return kuerzel;
	}

	@Override
	public boolean isMensch() {
		return true;
	}

	@Override
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
		MenschSpieler other = (MenschSpieler) obj;
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
