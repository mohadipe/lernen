package de.mohadipe.dynastie.bewegen;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.mohadipe.dynastie.DummyRandomServiceImpl;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.karte.FuenfMalFuenfKarte;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.sieg.SpielKonfiguration;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.spieler.Spieler;

public class KIBewegungTest {

	Karte karte;
	StandortErmitteln standortErmitteln;

	@Before
	public void setUp() {
		karte = new FuenfMalFuenfKarte(new SpielKonfiguration(), new DummyRandomServiceImpl());
		standortErmitteln = new StandortErmitteln(karte);
	}

	@Test
	public void innerhalbDerKarteBleibenPositiveGrenze() {
		Koordinate koordinate = new ZweiDimensionaleKoordinate(5, 5);
		Spieler spieler = new ComputerSpieler();
		Einheit einheit = new Infanterie();
		einheit.setSpieler(spieler);
		karte.fuegeEinheitVonSpielerHinzu(koordinate, einheit);

		KIBewegung kiBewegung = new KIBewegung();
		kiBewegung.setKarte(karte);
		kiBewegung.setSpieler(spieler);
		kiBewegung.bewegen();
		Koordinate standort = standortErmitteln.getStandortFuer(einheit);
		Assert.assertTrue(standort.getX() > 0);
		Assert.assertTrue(standort.getY() > 0);
		Assert.assertTrue(standort.getX() < 6);
		Assert.assertTrue(standort.getY() < 6);
	}

	@Test
	public void innerhalbDerKarteBleibenNegativeGrenze() {
		Koordinate koordinate = new ZweiDimensionaleKoordinate(1, 1);
		Spieler spieler = new ComputerSpieler();
		Einheit einheit = new Infanterie();
		einheit.setSpieler(spieler);
		karte.fuegeEinheitVonSpielerHinzu(koordinate, einheit);

		KIBewegung kiBewegung = new KIBewegung();
		kiBewegung.setKarte(karte);
		kiBewegung.setSpieler(spieler);
		kiBewegung.bewegen();
		Koordinate standort = standortErmitteln.getStandortFuer(einheit);
		Assert.assertTrue(standort.getX() > 0);
		Assert.assertTrue(standort.getY() > 0);
		Assert.assertTrue(standort.getX() < 6);
		Assert.assertTrue(standort.getY() < 6);
	}

}
