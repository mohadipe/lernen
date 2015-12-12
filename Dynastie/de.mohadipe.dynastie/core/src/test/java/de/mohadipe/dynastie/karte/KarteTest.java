package de.mohadipe.dynastie.karte;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.mohadipe.dynastie.DummyRandomServiceImpl;
import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.SpielKonfiguration;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Feld;
import de.mohadipe.dynastie.logik.model.Koordinate;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.spieler.MenschSpieler;
import de.mohadipe.dynastie.spieler.Spieler;

public class KarteTest {

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
	public void einheitVonComputerAufKarte() {
		Einheit infanterie = new Infanterie();
		ComputerSpieler computerSpieler = new ComputerSpieler();
		((Infanterie)infanterie).setSpieler(computerSpieler);
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(1, 2), infanterie);
		boolean hasMenschSpielerEinheiten = fuenfMalFuenfKarte.hasSpielerEinheiten(new MenschSpieler());
		Assert.assertFalse("Mensch sollte keine Einheit auf Karte haben.", hasMenschSpielerEinheiten);

		boolean hasComputerSpielerEinheiten = fuenfMalFuenfKarte.hasSpielerEinheiten(computerSpieler);
		Assert.assertTrue("Computer sollte eine Einheit auf Karte haben.", hasComputerSpielerEinheiten);
	}

	@Test
	public void einheitVonMenschUndComputerAufKarte() {
		Einheit infanterie = new Infanterie();
		ComputerSpieler computerSpieler = new ComputerSpieler();
		((Infanterie)infanterie).setSpieler(computerSpieler);
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(1, 2), infanterie);
		Einheit infanterie2 = new Infanterie();
		MenschSpieler menschSpieler = new MenschSpieler();
		((Infanterie)infanterie2).setSpieler(menschSpieler);
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(2, 2), infanterie2);

		boolean hasComputerSpielerEinheiten = fuenfMalFuenfKarte.hasSpielerEinheiten(computerSpieler);
		Assert.assertTrue("Computer sollte eine Einheit auf Karte haben.", hasComputerSpielerEinheiten);
		boolean hasMenschSpielerEinheiten = fuenfMalFuenfKarte.hasSpielerEinheiten(menschSpieler);
		Assert.assertTrue("Mensch sollte eine Einheit auf Karte haben.", hasMenschSpielerEinheiten);
	}

	@Test
	public void felderMitEinheitenZuSpielerHerausfilern() {
		ComputerSpieler computerSpieler = new ComputerSpieler();
		Map<Koordinate, Feld> felderMap = new HashMap<Koordinate, Feld>();
		felderMap.put(createKoordinate(1, 1), createValue(GelaendeBeschaffenheit.EBENE));
		felderMap.put(createKoordinate(1, 2), createValue(GelaendeBeschaffenheit.EBENE));
		felderMap.put(createKoordinate(1, 3), createValue(GelaendeBeschaffenheit.WALD));

		Einheit einheit = new Infanterie();
		((Infanterie)einheit).setSpieler(computerSpieler);
		felderMap.get(createKoordinate(1, 2)).setEinheit(einheit);

		Map<Koordinate, Feld> feldMitEinheit = felderMap.entrySet().stream().filter(new KoordinateEinheitBedingung(computerSpieler)).collect(Collectors.toMap(k -> k.getKey(), f -> f.getValue()));

		Assert.assertEquals(1, feldMitEinheit.size());
	}

	@Test
	public void felderMitEinheitenZuAnderemSpielerHerausfilern() {
		Spieler computerSpieler = new ComputerSpieler();
		computerSpieler.setIdentitaet("cp1");
		Map<Koordinate, Feld> felderMap = new HashMap<Koordinate, Feld>();
		felderMap.put(createKoordinate(1, 1), createValue(GelaendeBeschaffenheit.EBENE));
		felderMap.put(createKoordinate(1, 2), createValue(GelaendeBeschaffenheit.EBENE));
		felderMap.put(createKoordinate(1, 3), createValue(GelaendeBeschaffenheit.WALD));

		Einheit einheit = new Infanterie();
		((Infanterie)einheit).setSpieler(computerSpieler);
		felderMap.get(createKoordinate(1, 2)).setEinheit(einheit);

		Einheit einheit2 = new Infanterie();
		((Infanterie)einheit2).setSpieler(new MenschSpieler());
		felderMap.get(createKoordinate(1, 3)).setEinheit(einheit2);

		KoordinateEinheitBedingung koordinateEinheitBedingung = new KoordinateEinheitBedingung(computerSpieler);
		Predicate<? super Entry<Koordinate, Feld>> ignoriereLeereFelder = new IgnoriereLeereFelderBedingung();
		Map<Koordinate, Feld> feldMitEinheit = felderMap.entrySet().stream().filter(koordinateEinheitBedingung.negate()).filter(ignoriereLeereFelder).collect(Collectors.toMap(k -> k.getKey(), f -> f.getValue()));

		Assert.assertEquals(1, feldMitEinheit.size());
	}

	@Test
	public void zugriffAufEinheitenAufKarte() {
		ComputerSpieler computerSpieler = new ComputerSpieler();
		((Infanterie)einheit).setSpieler(computerSpieler);
		Koordinate koordinate = new ZweiDimensionaleKoordinate(1, 1);
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(koordinate, einheit);

		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = fuenfMalFuenfKarte.getEinheitenMitKoordinatenVonSpieler(computerSpieler);
		Assert.assertEquals(1, einheitenMitKoordinatenVonSpieler.size());
		Assert.assertTrue(((FeldImpl)einheitenMitKoordinatenVonSpieler.get(koordinate)).hasEinheitOf(computerSpieler));
	}

	@Test
	public void bewegeEinheit() {
		ComputerSpieler computerSpieler = new ComputerSpieler();
		((Infanterie)einheit).setSpieler(computerSpieler);
		Koordinate koordinate = new ZweiDimensionaleKoordinate(1, 1);
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(koordinate, einheit);

		Koordinate ziel = new ZweiDimensionaleKoordinate(2, 2);
		fuenfMalFuenfKarte.bewegeEinheitVonNach(einheit, koordinate, ziel);
		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = fuenfMalFuenfKarte.getEinheitenMitKoordinatenVonSpieler(computerSpieler);
		Assert.assertEquals(1, einheitenMitKoordinatenVonSpieler.size());
		Assert.assertNull("An diesen Koordinaten sollte keine Einheit mehr sein.", einheitenMitKoordinatenVonSpieler.get(koordinate));
		Assert.assertTrue("An diesen Koordinaten sollte eine Einheit sein.", ((FeldImpl)einheitenMitKoordinatenVonSpieler.get(ziel)).hasEinheitOf(computerSpieler));
	}

	@Test
	public void platziereEinheitInFreierAufmarschZone() {
		ComputerSpieler computerSpieler = new ComputerSpieler("KI1");
		((Infanterie)einheit).setSpieler(computerSpieler);
		fuenfMalFuenfKarte.platziereEinheitInStartZone(einheit);
		ComputerSpieler computerSpieler2 = new ComputerSpieler("KI2");
		Infanterie infanterie = new Infanterie();
		infanterie.setSpieler(computerSpieler2);
		fuenfMalFuenfKarte.platziereEinheitInStartZone(infanterie);

		Einheit einheit2 = fuenfMalFuenfKarte.getFelderMap().get(new ZweiDimensionaleKoordinate(1, 1)).getEinheit();
		Assert.assertEquals(einheit, einheit2);

		Einheit einheit3 = fuenfMalFuenfKarte.getFelderMap().get(new ZweiDimensionaleKoordinate(5, 5)).getEinheit();
		Assert.assertEquals(infanterie, einheit3);
	}

	@Test
	public void keineFreieAufmarschZone() {
		ComputerSpieler computerSpieler = new ComputerSpieler("KI1");
		((Infanterie)einheit).setSpieler(computerSpieler);
		fuenfMalFuenfKarte.platziereEinheitInStartZone(einheit);
		ComputerSpieler computerSpieler2 = new ComputerSpieler("KI2");
		Infanterie infanterie = new Infanterie();
		infanterie.setSpieler(computerSpieler2);
		fuenfMalFuenfKarte.platziereEinheitInStartZone(infanterie);

		try {
			ComputerSpieler computerSpieler3 = new ComputerSpieler("KI3");
			Infanterie infanterie2 = new Infanterie();
			infanterie2.setSpieler(computerSpieler3);
			fuenfMalFuenfKarte.platziereEinheitInStartZone(infanterie2);
			Assert.fail("Es sollten keine AufmarschZonen mehr frei sein.");
		} catch (RuntimeException e) {
			String actual = e.getMessage();
			String expected = "Es sind keine weiteren StartKoordinaten vorhanden.";
			Assert.assertEquals(expected, actual);
		}

	}

	private Feld createValue(GelaendeBeschaffenheit gelaende) {
		return new FeldImpl(gelaende);
	}

	private Koordinate createKoordinate(int i, int j) {
		return new ZweiDimensionaleKoordinate(i, j);
	}
}
