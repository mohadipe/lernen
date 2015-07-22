package de.mohadipe.dynastie.plausi;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.mohadipe.dynastie.RandomServiceImpl;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.karte.FuenfMalFuenfKarte;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.sieg.SpielKonfiguration;
import de.mohadipe.dynastie.spieler.ComputerSpieler;

public class EinheitenKollisionPlausiTest {
	EinheitenKollisionPlausi plausi;

	@Before
	public void setUp() {
		Karte karte = new FuenfMalFuenfKarte(new SpielKonfiguration(), new RandomServiceImpl());
		Einheit einheit = new Infanterie();
		einheit.setSpieler(new ComputerSpieler());
		karte.platziereEinheitAnStartKoordinate(einheit);
		plausi = new EinheitenKollisionPlausi(karte);
	}

	@Test
	public void keineEinheitAnZiel() {
		Koordinate ziel = new ZweiDimensionaleKoordinate(3, 3);
		Assert.assertTrue(plausi.isKeineEinheitAnZiel(ziel));
	}

	@Test
	public void einheitAnZiel() {
		Koordinate ziel = new ZweiDimensionaleKoordinate(1, 1);
		Assert.assertFalse(plausi.isKeineEinheitAnZiel(ziel));
	}
}
