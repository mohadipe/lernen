package de.mohadipe.dynastie.angreifen;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.mohadipe.dynastie.DummyRandomServiceImpl;
import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.karte.Feld;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.KartenGenerator;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.sieg.SpielKonfiguration;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.spieler.MenschSpieler;
import de.mohadipe.dynastie.spieler.Spieler;

public class GegnerEinheitenErmittlerTest {
	private Karte karte;
	private Spieler compSpieler;
	private Einheit angreifendeEinheit;

	@Before
	public void setUp() {
		angreifendeEinheit = new Infanterie();
		compSpieler = new ComputerSpieler();
		angreifendeEinheit.setEindeutigeKennung("attacker");
		angreifendeEinheit.setSpieler(compSpieler);
		Konfiguration konfiguration = new SpielKonfiguration();
		KartenGenerator kartenGenerator = new KartenGenerator(new DummyRandomServiceImpl());
		kartenGenerator.generiereKarte(konfiguration);
		karte = konfiguration.getKarte();
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(1, 1), angreifendeEinheit);

	}

	@Test
	public void keineGegnerischenEinheitenInReichweite() {
		Einheit infMen = new Infanterie();
		infMen.setSpieler(new MenschSpieler());
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(5, 5), infMen);
		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = karte.getEinheitenMitKoordinatenVonSpieler(compSpieler);

		GegnerEinheitenErmittler gegnerEinheitenErmittler = new GegnerEinheitenErmittler(karte);
		einheitenMitKoordinatenVonSpieler.entrySet().forEach(gegnerEinheitenErmittler);
		Assert.assertEquals(0, gegnerEinheitenErmittler.getGegnerZuEinheit().size());
	}

	@Test
	public void eineGegnerischeEinheitInReichweite() {
		Einheit infMen = new Infanterie();
		infMen.setSpieler(new MenschSpieler());
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(3, 1), infMen);
		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = karte.getEinheitenMitKoordinatenVonSpieler(compSpieler);

		GegnerEinheitenErmittler gegnerEinheitenErmittler = new GegnerEinheitenErmittler(karte);
		einheitenMitKoordinatenVonSpieler.entrySet().forEach(gegnerEinheitenErmittler);
		Assert.assertEquals(1, gegnerEinheitenErmittler.getGegnerZuEinheit().get(angreifendeEinheit).size());
	}

	@Test
	public void zweiGegnerischeEinheitenInReichweite() {
		Einheit infMen = new Infanterie();
		infMen.setEindeutigeKennung("1");
		infMen.setSpieler(new MenschSpieler());
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(3, 1), infMen);
		Einheit infMen2 = new Infanterie();
		infMen2.setEindeutigeKennung("2");
		infMen2.setSpieler(new MenschSpieler());
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(2, 2), infMen2);

		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = karte.getEinheitenMitKoordinatenVonSpieler(compSpieler);

		GegnerEinheitenErmittler gegnerEinheitenErmittler = new GegnerEinheitenErmittler(karte);
		einheitenMitKoordinatenVonSpieler.entrySet().forEach(gegnerEinheitenErmittler);
		Assert.assertEquals(2, gegnerEinheitenErmittler.getGegnerZuEinheit().get(angreifendeEinheit).size());
	}
}
