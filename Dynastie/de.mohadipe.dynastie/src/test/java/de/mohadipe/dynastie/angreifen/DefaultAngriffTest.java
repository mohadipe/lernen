package de.mohadipe.dynastie.angreifen;

import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.mohadipe.dynastie.DummyRandomServiceImpl;
import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.RandomService;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.einheiten.InfanterieDaten;
import de.mohadipe.dynastie.karte.Feld;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.KartenGenerator;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.output.SysOutSpielZug;
import de.mohadipe.dynastie.sieg.SpielKonfiguration;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.spieler.MenschSpieler;
import de.mohadipe.dynastie.spieler.Spieler;

public class DefaultAngriffTest {

	private Karte karte;
	private Konfiguration konfiguration;
	private RandomService randomService;
	private Spieler aktSpieler;

	@Before
	public void setUp() {
		this.konfiguration = new SpielKonfiguration();
		this.aktSpieler = new ComputerSpieler();
		this.aktSpieler.setIdentitaet("KI");
		this.randomService = new DummyRandomServiceImpl();
		KartenGenerator kartenGenerator = new KartenGenerator(this.randomService);
		kartenGenerator.generiereKarte(konfiguration);
		karte = konfiguration.getKarte();

		Infanterie compInf = new Infanterie();
		compInf.setSpieler(aktSpieler);
		compInf.setEindeutigeKennung("KI-Inf");

		this.karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(1, 1), compInf);
		konfiguration.setKarte(karte);
	}

	public class AssertVolleLebenspuntke implements Predicate<Entry<Koordinate, Feld>> {

		@Override
		public boolean test(Entry<Koordinate, Feld> entry) {
			if (InfanterieDaten.standardLebenspunkte == entry.getValue().getEinheit().getLebenspunkte()) {
				return true;
			}
			return false;
		}

	}

	@Test
	public void keinGegnerInReichweite() {
		Infanterie menInf1 = new Infanterie();
		menInf1.setSpieler(new MenschSpieler());
		menInf1.setEindeutigeKennung("1");
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(4, 4), menInf1);

		DefaultAngriff defaultAngriff = new DefaultAngriff();
		defaultAngriff.setKonfiguration(konfiguration);
		defaultAngriff.setSpieler(aktSpieler);
		defaultAngriff.setOutput(new SysOutSpielZug());
		defaultAngriff.angreifen();

		// keine Veraenderung
		// Alle Einheiten bei voller Staerke
		Map<Koordinate, Feld> einheitenMitKoordinatenVonAnderenSpielern = karte.getEinheitenMitKoordinatenVonAnderenSpielern(aktSpieler);
		Assert.assertTrue("Es sollte mindestens eine Einheit vorhanden sein.", einheitenMitKoordinatenVonAnderenSpielern.size() > 0);

		Predicate<? super Entry<Koordinate, Feld>> assertVolleLebenspunkte = new AssertVolleLebenspuntke();
		Assert.assertTrue("Vorhandene Einheiten sollten bei vollen Lebenspunkten sein.", einheitenMitKoordinatenVonAnderenSpielern.entrySet().stream().allMatch(assertVolleLebenspunkte));
	}

	@Test
	public void einGegnerInReichweite() {
		Infanterie menInf1 = new Infanterie();
		menInf1.setSpieler(new MenschSpieler());
		menInf1.setEindeutigeKennung("1");
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(3, 3), menInf1);

		Angreifen defaultAngriff = new DefaultAngriff();
		defaultAngriff.setKonfiguration(konfiguration);
		defaultAngriff.setSpieler(aktSpieler);
		defaultAngriff.setOutput(new SysOutSpielZug());
		((DummyRandomServiceImpl) this.randomService).setRandomValue(4);
		defaultAngriff.setRandomService(this.randomService);
		defaultAngriff.angreifen();

		Map<Koordinate, Feld> einheitenMitKoordinatenVonAnderenSpielern = karte.getEinheitenMitKoordinatenVonAnderenSpielern(aktSpieler);
		Assert.assertTrue("Es sollte mindestens eine Einheit vorhanden sein.", einheitenMitKoordinatenVonAnderenSpielern.size() > 0);

		Predicate<? super Entry<Koordinate, Feld>> assertVolleLebenspunkte = new AssertVolleLebenspuntke();
		Assert.assertFalse("Vorhandene Einheiten sollte nicht bei vollen Lebenspunkten sein.", einheitenMitKoordinatenVonAnderenSpielern.entrySet().stream().allMatch(assertVolleLebenspunkte));
	}

	@Test
	public void zweiGleicheGegnerInReichweite() {
		Infanterie menInf1 = new Infanterie();
		menInf1.setSpieler(new MenschSpieler());
		menInf1.setEindeutigeKennung("1");
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(3, 3), menInf1);
		Infanterie menInf2 = new Infanterie();
		menInf2.setSpieler(new MenschSpieler());
		menInf2.setEindeutigeKennung("2");
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(3, 1), menInf2);

		Angreifen defaultAngriff = new DefaultAngriff();
		defaultAngriff.setKonfiguration(konfiguration);
		defaultAngriff.setSpieler(aktSpieler);
		((DummyRandomServiceImpl) this.randomService).setRandomValue(4);
		defaultAngriff.setRandomService(this.randomService);
		defaultAngriff.setOutput(new SysOutSpielZug());
		defaultAngriff.angreifen();

		Map<Koordinate, Feld> einheitenMitKoordinatenVonAnderenSpielern = karte.getEinheitenMitKoordinatenVonAnderenSpielern(aktSpieler);
		Assert.assertTrue("Es sollte mindestens eine Einheit vorhanden sein.", einheitenMitKoordinatenVonAnderenSpielern.size() > 0);

		Predicate<? super Entry<Koordinate, Feld>> assertVolleLebenspunkte = new AssertVolleLebenspuntke();
		Assert.assertFalse("Nicht Alle vorhandene Einheiten sollten bei vollen Lebenspunkten sein.", einheitenMitKoordinatenVonAnderenSpielern.entrySet().stream().allMatch(assertVolleLebenspunkte));
		Assert.assertTrue("Nicht Alle vorhandene Einheiten sollten bei vollen Lebenspunkten sein.", einheitenMitKoordinatenVonAnderenSpielern.entrySet().stream().anyMatch(assertVolleLebenspunkte));
	}

	@Test
	public void einheitDurchAngriffVernichtet() {
		Infanterie menInf1 = new Infanterie();
		Spieler mensch = new MenschSpieler();
		mensch.setIdentitaet("lost");
		menInf1.setSpieler(mensch);
		menInf1.setEindeutigeKennung("1");
		menInf1.setAktLebenspunkte(1);
		karte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(3, 3), menInf1);
		Assert.assertTrue(karte.hasSpielerEinheiten(mensch));
		Assert.assertTrue(karte.hasSpielerEinheiten(aktSpieler));

		Angreifen defaultAngriff = new DefaultAngriff();
		defaultAngriff.setKonfiguration(konfiguration);
		defaultAngriff.setSpieler(aktSpieler);
		((DummyRandomServiceImpl) this.randomService).setRandomValue(4);
		defaultAngriff.setRandomService(this.randomService);
		defaultAngriff.setOutput(new SysOutSpielZug());
		defaultAngriff.angreifen();

		Assert.assertTrue(karte.hasSpielerEinheiten(aktSpieler));
		Assert.assertFalse(karte.hasSpielerEinheiten(mensch));
	}
}
