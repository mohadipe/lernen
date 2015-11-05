package de.mohadipe.dynastie.plausi;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.karte.Feld;
import de.mohadipe.dynastie.karte.FeldImpl;
import de.mohadipe.dynastie.karte.GelaendeBeschaffenheit;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.spieler.MenschSpieler;

public class UmkreisPlausiTest {

	@Test
	public void zielInBewegungsreichweitePositiv() {
		Koordinate ziel = new ZweiDimensionaleKoordinate(2, 2);
		Koordinate standort = new ZweiDimensionaleKoordinate(1, 1);
		int reichweite = 1;

		UmkreisPlausi bewegungsPlausi = new UmkreisPlausi(ziel, standort, reichweite);
		Assert.assertTrue("Das Ziel sollte in Reichweite sein.", bewegungsPlausi.check());
	}

	@Test
	public void zieInBewegungsreichweiteNegativ() {
		Koordinate ziel = new ZweiDimensionaleKoordinate(2, 2);
		Koordinate standort = new ZweiDimensionaleKoordinate(3, 3);
		int reichweite = 1;

		UmkreisPlausi bewegungsPlausi = new UmkreisPlausi(ziel, standort, reichweite);
		Assert.assertTrue("Das Ziel sollte in Reichweite sein.", bewegungsPlausi.check());
	}

	@Test
	public void zielAusserhalbBewegungsreichweitePositiv() {
		Koordinate ziel = new ZweiDimensionaleKoordinate(2, 3);
		Koordinate standort = new ZweiDimensionaleKoordinate(1, 1);
		int reichweite = 1;

		UmkreisPlausi bewegungsPlausi = new UmkreisPlausi(ziel, standort, reichweite);
		Assert.assertFalse("Das Ziel sollte nicht in Reichweite sein.", bewegungsPlausi.check());
	}

	@Test
	public void zielAusserhalbBewegungsreichweiteNegativ() {
		Koordinate ziel = new ZweiDimensionaleKoordinate(2, 1);
		Koordinate standort = new ZweiDimensionaleKoordinate(3, 3);
		int reichweite = 1;

		UmkreisPlausi bewegungsPlausi = new UmkreisPlausi(ziel, standort, reichweite);
		Assert.assertFalse("Das Ziel sollte nicht in Reichweite sein.", bewegungsPlausi.check());
	}

	@Test
	public void keinZielGesetzt() {
		Koordinate standort = new ZweiDimensionaleKoordinate(3, 3);
		int reichweite = 1;

		UmkreisPlausi umkreisPlausi = new UmkreisPlausi(null, standort, reichweite);
		try {
			umkreisPlausi.check();
			Assert.fail("Ohne Ziel sollte Exception geworfen werden.");
		} catch (RuntimeException e) {
			String actual = e.getMessage();
			Assert.assertEquals(UmkreisPlausi.KEIN_ZIEL_DEFINIERT, actual);
		}
	}

	@Test
	public void einTrefferImUmkreis() {
		Map<Koordinate, Feld> feldAnKoordinate = new HashMap<Koordinate, Feld>();
		feldAnKoordinate.put(new ZweiDimensionaleKoordinate(3, 3), getFeldMitInfVonMensch());

		int reichweite = 2;
		UmkreisPlausi umkreisPlausi = new UmkreisPlausi(null, new ZweiDimensionaleKoordinate(1, 1), reichweite);
		feldAnKoordinate.entrySet().forEach(umkreisPlausi);
		Assert.assertEquals(1, umkreisPlausi.getGegner().size());
	}

	@Test
	public void zweiTrefferImUmkreis() {
		Map<Koordinate, Feld> feldAnKoordinate = new HashMap<Koordinate, Feld>();
		feldAnKoordinate.put(new ZweiDimensionaleKoordinate(2, 1), getFeldMitInfVonComputer());
		feldAnKoordinate.put(new ZweiDimensionaleKoordinate(3, 3), getFeldMitInfVonMensch());

		int reichweite = 2;
		UmkreisPlausi umkreisPlausi = new UmkreisPlausi(null, new ZweiDimensionaleKoordinate(1, 1), reichweite);
		feldAnKoordinate.entrySet().forEach(umkreisPlausi);
		Assert.assertEquals(2, umkreisPlausi.getGegner().size());
	}

	private Feld getFeldMitInfVonComputer() {
		Infanterie infanterie = new Infanterie();
		infanterie.setSpieler(new ComputerSpieler());
		FeldImpl feldImpl = new FeldImpl(GelaendeBeschaffenheit.EBENE);
		feldImpl.setEinheit(infanterie);
		return feldImpl;
	}

	private Feld getFeldMitInfVonMensch() {
		Infanterie infanterie = new Infanterie();
		infanterie.setSpieler(new MenschSpieler());
		FeldImpl feldImpl = new FeldImpl(GelaendeBeschaffenheit.EBENE);
		feldImpl.setEinheit(infanterie);
		return feldImpl;
	}
}
