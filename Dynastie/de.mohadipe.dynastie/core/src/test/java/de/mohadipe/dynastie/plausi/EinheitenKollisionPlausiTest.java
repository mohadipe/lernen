package de.mohadipe.dynastie.plausi;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.mohadipe.dynastie.DummyRandomServiceImpl;
import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.SpielKonfiguration;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.KartenGenerator;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Koordinate;
import de.mohadipe.dynastie.spieler.ComputerSpieler;

public class EinheitenKollisionPlausiTest {
	EinheitenKollisionPlausi plausi;

	@Before
	public void setUp() {
		Konfiguration spielKonfiguration = new SpielKonfiguration();
		KartenGenerator kartenGenerator = new KartenGenerator(new DummyRandomServiceImpl());
		kartenGenerator.generiereKarte(spielKonfiguration);
		Karte karte = spielKonfiguration.getKarte();
		Einheit einheit = new Infanterie();
		((Infanterie)einheit).setSpieler(new ComputerSpieler());
		karte.platziereEinheitInStartZone(einheit);
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
