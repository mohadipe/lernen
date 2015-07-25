package de.mohadipe.dynastie.bewegen;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.mohadipe.dynastie.DummyRandomServiceImpl;
import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.input.DummyZielX2Y1Koordinaten;
import de.mohadipe.dynastie.karte.Feld;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.karte.KartenGenerator;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.sieg.SpielKonfiguration;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.spieler.MenschSpieler;
import de.mohadipe.dynastie.spieler.Spieler;

public class EinheitBewegenTest {

	private Karte fuenfMalFuenfKarte;
	private Einheit einheit;
	private Konfiguration konfiguration;

	@Before
	public void setBevorAll() {
		konfiguration = new SpielKonfiguration();
		KartenGenerator kartenGenerator = new KartenGenerator(new DummyRandomServiceImpl());
		kartenGenerator.generiereKarte(konfiguration);
		fuenfMalFuenfKarte = konfiguration.getKarte();
		einheit = new Infanterie();

	}

	@Test
	public void bewegeInfantrieAlsComputer() {
		ComputerSpieler computerSpieler = new ComputerSpieler();
		einheit.setSpieler(computerSpieler);
		Koordinate koordinate = new ZweiDimensionaleKoordinate(1, 1);
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(koordinate, einheit);

		Bewegung bewegung = EinheitenBewegen.creaeInstanceFuerSpieler(computerSpieler, null);
		bewegung.setSpieler(computerSpieler);
		bewegung.setKarte(fuenfMalFuenfKarte);
		bewegung.bewegen();

		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = fuenfMalFuenfKarte.getEinheitenMitKoordinatenVonSpieler(computerSpieler);
		Assert.assertEquals(1, einheitenMitKoordinatenVonSpieler.size());
		Feld feld = einheitenMitKoordinatenVonSpieler.get(koordinate);
		Assert.assertNull("An diesen Koordinaten sollte keine Einheit sein.", feld);
	}

	@Test
	public void bewegenInfantrieAlsMensch() {
		Spieler menschSpieler = new MenschSpieler();
		einheit.setSpieler(menschSpieler);
		Koordinate koordinate = new ZweiDimensionaleKoordinate(1, 1);
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(koordinate, einheit);

		Bewegung bewegung = EinheitenBewegen.creaeInstanceFuerSpieler(menschSpieler, new DummyZielX2Y1Koordinaten());
		bewegung.setSpieler(menschSpieler);
		bewegung.setKarte(fuenfMalFuenfKarte);
		bewegung.bewegen();

		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = fuenfMalFuenfKarte.getEinheitenMitKoordinatenVonSpieler(menschSpieler);
		Assert.assertEquals(1, einheitenMitKoordinatenVonSpieler.size());
		Feld feld = einheitenMitKoordinatenVonSpieler.get(koordinate);
		Assert.assertNull("An diesen Koordinaten sollte keine Einheit sein.", feld);
		Feld feldMitEinheit = einheitenMitKoordinatenVonSpieler.get(new ZweiDimensionaleKoordinate(2, 1));
		Assert.assertNotNull("An diesen Koordinaten sollte eine Einheit sein.", feldMitEinheit);
		Assert.assertTrue("Auf diesem Feld sollte eine Einheit des Spielers sein.", feldMitEinheit.hasEinheitOf(menschSpieler));
	}
}
