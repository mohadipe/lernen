package de.mohadipe.dynastie.sieg;

import java.util.ArrayList;
import java.util.List;

import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.spieler.Spieler;

public class Vernichtung implements CheckSieg {

	private Karte karte;
	private List<Spieler> spieler = new ArrayList<Spieler>();
	private Sieger sieger = null;

	@Override
	public void auswerten() {
		Spieler hatSpielerKeineEinheiten = null;
		Spieler siegerSpieler = null;
		for (Spieler tmp : spieler) {
			if (!karte.hasSpielerEinheiten(tmp)) {
				if (hatSpielerKeineEinheiten == null) {
					hatSpielerKeineEinheiten = tmp;
				}
			} else {
				if (siegerSpieler != null) {
					siegerSpieler = null;
				} else {
					siegerSpieler = tmp;
				}
			}
		}
		if (siegerSpieler != null) {
			sieger = new Sieger();
			sieger.setIsEindeutig(siegerSpieler);
		} else if (hatSpielerKeineEinheiten != null) {
			sieger = new Sieger();
			sieger.setIsPatt();
		}
	}

	@Override
	public void setKarte(Karte karte) {
		this.karte = karte;
	}

	@Override
	public void setSpieler(List<Spieler> beteiligteSpieler) {
		if (beteiligteSpieler == null) {
			throw new RuntimeException(ES_MUESSEN_SPIELER_VORHANDEN_SEIN);
		}
		this.spieler.addAll(beteiligteSpieler);
	}

	@Override
	public boolean isSpielBeendet() {
		return sieger != null;
	}

	@Override
	public Sieger getSieger() {
		return sieger;
	}

}
