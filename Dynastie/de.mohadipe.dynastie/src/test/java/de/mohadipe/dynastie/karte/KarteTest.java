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
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.sieg.SpielKonfiguration;
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
		infanterie.setSpieler(computerSpieler);
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
		infanterie.setSpieler(computerSpieler);
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(new ZweiDimensionaleKoordinate(1, 2), infanterie);
		Einheit infanterie2 = new Infanterie();
		MenschSpieler menschSpieler = new MenschSpieler();
		infanterie2.setSpieler(menschSpieler);
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
		einheit.setSpieler(computerSpieler);
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
		einheit.setSpieler(computerSpieler);
		felderMap.get(createKoordinate(1, 2)).setEinheit(einheit);

		Einheit einheit2 = new Infanterie();
		einheit2.setSpieler(new MenschSpieler());
		felderMap.get(createKoordinate(1, 3)).setEinheit(einheit2);

		KoordinateEinheitBedingung koordinateEinheitBedingung = new KoordinateEinheitBedingung(computerSpieler);
		Predicate<? super Entry<Koordinate, Feld>> ignoriereLeereFelder = new IgnoriereLeereFelderBedingung();
		Map<Koordinate, Feld> feldMitEinheit = felderMap.entrySet().stream().filter(koordinateEinheitBedingung.negate()).filter(ignoriereLeereFelder).collect(Collectors.toMap(k -> k.getKey(), f -> f.getValue()));

		Assert.assertEquals(1, feldMitEinheit.size());
	}

	@Test
	public void zugriffAufEinheitenAufKarte() {
		ComputerSpieler computerSpieler = new ComputerSpieler();
		einheit.setSpieler(computerSpieler);
		Koordinate koordinate = new ZweiDimensionaleKoordinate(1, 1);
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(koordinate, einheit);

		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = fuenfMalFuenfKarte.getEinheitenMitKoordinatenVonSpieler(computerSpieler);
		Assert.assertEquals(1, einheitenMitKoordinatenVonSpieler.size());
		Assert.assertTrue(einheitenMitKoordinatenVonSpieler.get(koordinate).hasEinheitOf(computerSpieler));
	}

	@Test
	public void bewegeEinheit() {
		ComputerSpieler computerSpieler = new ComputerSpieler();
		einheit.setSpieler(computerSpieler);
		Koordinate koordinate = new ZweiDimensionaleKoordinate(1, 1);
		fuenfMalFuenfKarte.fuegeEinheitVonSpielerHinzu(koordinate, einheit);

		Koordinate ziel = new ZweiDimensionaleKoordinate(2, 2);
		fuenfMalFuenfKarte.bewegeEinheitVonNach(einheit, koordinate, ziel);
		Map<Koordinate, Feld> einheitenMitKoordinatenVonSpieler = fuenfMalFuenfKarte.getEinheitenMitKoordinatenVonSpieler(computerSpieler);
		Assert.assertEquals(1, einheitenMitKoordinatenVonSpieler.size());
		Assert.assertNull("An diesen Koordinaten sollte keine Einheit mehr sein.", einheitenMitKoordinatenVonSpieler.get(koordinate));
		Assert.assertTrue("An diesen Koordinaten sollte eine Einheit sein.", einheitenMitKoordinatenVonSpieler.get(ziel).hasEinheitOf(computerSpieler));
	}

	private Feld createValue(GelaendeBeschaffenheit gelaende) {
		return new FeldImpl(gelaende);
	}

	private Koordinate createKoordinate(int i, int j) {
		return new ZweiDimensionaleKoordinate(i, j);
	}
}
