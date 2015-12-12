package de.mohadipe.dynastie.bewegen;

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
import de.mohadipe.dynastie.spieler.Spieler;

public class KIBewegungTest {

	Karte karte;
	StandortErmitteln standortErmitteln;

	@Before
	public void setUp() {
		Konfiguration spielKonfiguration = new SpielKonfiguration();
		KartenGenerator kartenGenerator = new KartenGenerator(new DummyRandomServiceImpl());
		kartenGenerator.generiereKarte(spielKonfiguration);
		karte = spielKonfiguration.getKarte();
		standortErmitteln = new StandortErmitteln(karte);
	}

	@Test
	public void innerhalbDerKarteBleibenPositiveGrenze() {
		Koordinate koordinate = new ZweiDimensionaleKoordinate(5, 5);
		Spieler spieler = new ComputerSpieler();
		Einheit einheit = new Infanterie();
		((Infanterie)einheit).setSpieler(spieler);
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
		((Infanterie)einheit).setSpieler(spieler);
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
