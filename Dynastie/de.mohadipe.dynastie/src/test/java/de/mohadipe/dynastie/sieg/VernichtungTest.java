package de.mohadipe.dynastie.sieg;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.dynastie.RandomServiceImpl;
import de.mohadipe.dynastie.Siegbedingung;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.karte.FuenfMalFuenfKarte;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.spieler.MenschSpieler;
import de.mohadipe.dynastie.spieler.Spieler;

public class VernichtungTest {

	@Test
	public void pattTest() {
		Karte karte = new FuenfMalFuenfKarte(new SpielKonfiguration(), new RandomServiceImpl());
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
		Karte karte = new FuenfMalFuenfKarte(new SpielKonfiguration(), new RandomServiceImpl());
		Einheit humanInf = new Infanterie();
		humanInf.setSpieler(menschSpieler);

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
		Karte karte = new FuenfMalFuenfKarte(new SpielKonfiguration(), new RandomServiceImpl());
		Einheit humanInf = new Infanterie();
		humanInf.setSpieler(menschSpieler);
		Einheit compInf = new Infanterie();
		compInf.setSpieler(computerSpieler);

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
		} catch (Exception e) {
			String actual = e.getMessage();
			Assert.assertEquals(CheckSieg.ES_MUESSEN_SPIELER_VORHANDEN_SEIN, actual);
		}
	}
}
