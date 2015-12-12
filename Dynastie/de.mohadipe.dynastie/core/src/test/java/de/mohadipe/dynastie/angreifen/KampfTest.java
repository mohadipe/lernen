package de.mohadipe.dynastie.angreifen;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.dynastie.DummyRandomServiceImpl;
import de.mohadipe.dynastie.einheiten.Infanterie;
import de.mohadipe.dynastie.einheiten.InfanterieDaten;
import de.mohadipe.dynastie.karte.FeldImpl;
import de.mohadipe.dynastie.karte.GelaendeBeschaffenheit;
import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Feld;

public class KampfTest {

	@Test
	public void angreiferTrifftZiel() {
		Einheit angreifer = new Infanterie();
		Einheit zielEinheit = new Infanterie();
		Feld ziel = new FeldImpl(GelaendeBeschaffenheit.EBENE);
		ziel.setEinheit(zielEinheit);
		DummyRandomServiceImpl dummyRandomServiceImpl = new DummyRandomServiceImpl();
		dummyRandomServiceImpl.setRandomValue(4);

		Assert.assertEquals(InfanterieDaten.standardLebenspunkte, angreifer.getLebenspunkte());
		Assert.assertEquals(InfanterieDaten.standardLebenspunkte, ziel.getEinheit().getLebenspunkte());

		Kampf kampf = new Kampf(angreifer, ziel);
		kampf.setRandomService(dummyRandomServiceImpl);
		kampf.ausfuehren();

		Assert.assertEquals(3, ziel.getEinheit().getLebenspunkte());
	}

	@Test
	public void angreiferVerfaehltZiel() {
		Einheit angreifer = new Infanterie();
		Einheit zielEinheit = new Infanterie();
		Feld ziel = new FeldImpl(GelaendeBeschaffenheit.EBENE);
		ziel.setEinheit(zielEinheit);
		DummyRandomServiceImpl dummyRandomServiceImpl = new DummyRandomServiceImpl();
		dummyRandomServiceImpl.setRandomValue(3);

		Assert.assertEquals(InfanterieDaten.standardLebenspunkte, angreifer.getLebenspunkte());
		Assert.assertEquals(InfanterieDaten.standardLebenspunkte, ziel.getEinheit().getLebenspunkte());

		Kampf kampf = new Kampf(angreifer, ziel);
		kampf.setRandomService(dummyRandomServiceImpl);
		kampf.ausfuehren();

		Assert.assertEquals(InfanterieDaten.standardLebenspunkte, ziel.getEinheit().getLebenspunkte());
	}

	@Test
	public void einheitDurchKampfZerstoert() {
		Einheit angreifer = new Infanterie();
		Einheit zielEinheit = new Infanterie();
		((Infanterie) zielEinheit).setAktLebenspunkte(1);
		Feld ziel = new FeldImpl(GelaendeBeschaffenheit.EBENE);
		ziel.setEinheit(zielEinheit);
		DummyRandomServiceImpl dummyRandomServiceImpl = new DummyRandomServiceImpl();
		dummyRandomServiceImpl.setRandomValue(4);

		Assert.assertEquals(InfanterieDaten.standardLebenspunkte, angreifer.getLebenspunkte());
		Assert.assertEquals(1, ziel.getEinheit().getLebenspunkte());

		Kampf kampf = new Kampf(angreifer, ziel);
		kampf.setRandomService(dummyRandomServiceImpl);
		kampf.ausfuehren();

		Assert.assertNull(ziel.getEinheit());
	}

	@Test
	public void kampfOhneRandomService() {
		Einheit angreifer = null;
		Feld ziel = null;
		Kampf kampf = new Kampf(angreifer, ziel);
		try {
			kampf.ausfuehren();
			Assert.fail("Ohne RandomService muss eine Exception geworfen werden.");
		} catch (RuntimeException e) {
			String actual = e.getMessage();
			Assert.assertEquals(Kampf.KEIN_RANDOM_SERVICE_DEFINIERT, actual);
		}
	}
}
