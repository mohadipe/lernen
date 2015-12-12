package de.mohadipe.dynastie.sieg;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.mohadipe.dynastie.DummyRandomServiceImpl;
import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.Siegbedingung;
import de.mohadipe.dynastie.SpielKonfiguration;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.KartenGenerator;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.spieler.MenschSpieler;
import de.mohadipe.dynastie.spieler.Spieler;

public class VernichtungTest {

	private Karte karte;

	@Before
	public void setUp() {
		Konfiguration konfiguration = new SpielKonfiguration();
		KartenGenerator kartenGenerator = new KartenGenerator(new DummyRandomServiceImpl());
		kartenGenerator.generiereKarte(konfiguration);
		karte = konfiguration.getKarte();
	}

	@Test
	public void pattTest() {
		List<Spieler> beteiligteSpieler = new ArrayList<Spieler>();
		beteiligteSpieler.add(new ComputerSpieler());
		beteiligteSpieler.add(new MenschSpieler());
		CheckSieg checkSieg = CheckSiegBedingungen.getInstance(Siegbedingung.VERNICHTEN);
		checkSieg.setKarte(karte);
		checkSieg.setSpieler(beteiligteSpieler);
		checkSieg.auswerten();
		Assert.assertTrue(checkSieg.getSieger().isPatt());
		Assert.assertTrue(checkSieg.isSpielBeendet());
	}

	@Test
	public void eindeutigerSiegTest() {
		ComputerSpieler computerSpieler = new ComputerSpieler();
		MenschSpieler menschSpieler = new MenschSpieler();
		Einheit humanInf = new Infanterie();
		((Infanterie)humanInf).setSpieler(menschSpieler);

		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(2, 2), humanInf);

		List<Spieler> beteiligteSpieler = new ArrayList<Spieler>();
		beteiligteSpieler.add(computerSpieler);
		beteiligteSpieler.add(menschSpieler);
		CheckSieg checkSieg = CheckSiegBedingungen.getInstance(Siegbedingung.VERNICHTEN);
		checkSieg.setKarte(karte);
		checkSieg.setSpieler(beteiligteSpieler);
		checkSieg.auswerten();
		Assert.assertTrue(checkSieg.getSieger().isEindeutig());
		Assert.assertTrue(checkSieg.isSpielBeendet());
	}

	@Test
	public void spielNochNichtBeendet() {
		ComputerSpieler computerSpieler = new ComputerSpieler();
		MenschSpieler menschSpieler = new MenschSpieler();
		Einheit humanInf = new Infanterie();
		((Infanterie)humanInf).setSpieler(menschSpieler);
		Einheit compInf = new Infanterie();
		((Infanterie)compInf).setSpieler(computerSpieler);

		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(5, 5), compInf);
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(2, 2), humanInf);

		List<Spieler> beteiligteSpieler = new ArrayList<Spieler>();
		beteiligteSpieler.add(computerSpieler);
		beteiligteSpieler.add(menschSpieler);
		CheckSieg checkSieg = CheckSiegBedingungen.getInstance(Siegbedingung.VERNICHTEN);
		checkSieg.setKarte(karte);
		checkSieg.setSpieler(beteiligteSpieler);
		checkSieg.auswerten();
		Assert.assertFalse(checkSieg.isSpielBeendet());
	}

	@Test
	public void keineBeteiligtenSpieler() {
		List<Spieler> beteiligteSpieler = null;
		CheckSieg checkSieg = CheckSiegBedingungen.getInstance(Siegbedingung.VERNICHTEN);
		try {
			checkSieg.setSpieler(beteiligteSpieler);
			Assert.fail("Bei null beteiligten Spieler wird RuntimeException erwartet.");
		} catch (RuntimeException e) {
			String actual = e.getMessage();
			Assert.assertEquals(CheckSieg.ES_MUESSEN_SPIELER_VORHANDEN_SEIN, actual);
		}
	}
}
