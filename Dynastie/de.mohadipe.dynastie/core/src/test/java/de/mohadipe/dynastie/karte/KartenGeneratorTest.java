package de.mohadipe.dynastie.karte;

import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.dynastie.DummyRandomServiceImpl;
import de.mohadipe.dynastie.RandomService;
import de.mohadipe.dynastie.SpielKonfiguration;
import de.mohadipe.dynastie.logik.model.Feld;
import de.mohadipe.dynastie.logik.model.Koordinate;

public class KartenGeneratorTest {

	@Test
	public void fuenfMalFuenfKarteNurEbeneErzeugen() {
		int expectedX = 5;
		int expectedY = 5;
		Integer gelaende = Integer.valueOf(1);
		SpielKonfiguration spielKonfiguration = new SpielKonfiguration();
		spielKonfiguration.setSpielFeldMaxX(expectedX);
		spielKonfiguration.setSpielFeldMaxY(expectedY);
		RandomService dummyRandomServiceImpl = new DummyRandomServiceImpl();
		((DummyRandomServiceImpl) dummyRandomServiceImpl).setRandomValue(gelaende);

		KartenGenerator kartenGenerator = new KartenGenerator(dummyRandomServiceImpl);
		kartenGenerator.generiereKarte(spielKonfiguration);

		Assert.assertEquals(expectedX, spielKonfiguration.getKarte().getMaxX());
		Assert.assertEquals(expectedY, spielKonfiguration.getKarte().getMaxY());
		Set<Entry<Koordinate, Feld>> felder = spielKonfiguration.getKarte().getFelderMap().entrySet();
		for (Entry<Koordinate, Feld> feld : felder) {
			Assert.assertEquals(GelaendeBeschaffenheit.EBENE, ((FeldImpl)feld.getValue()).getGelaende());
		}
	}

	@Test
	public void sechsMalsiebenKarteNurWaldErzeugen() {
		int expectedX = 6;
		int expectedY = 7;
		Integer gelaende = Integer.valueOf(2);
		SpielKonfiguration spielKonfiguration = new SpielKonfiguration();
		spielKonfiguration.setSpielFeldMaxX(expectedX);
		spielKonfiguration.setSpielFeldMaxY(expectedY);
		RandomService dummyRandomServiceImpl = new DummyRandomServiceImpl();
		((DummyRandomServiceImpl) dummyRandomServiceImpl).setRandomValue(gelaende);

		KartenGenerator kartenGenerator = new KartenGenerator(dummyRandomServiceImpl);
		kartenGenerator.generiereKarte(spielKonfiguration);

		Assert.assertEquals(expectedX, spielKonfiguration.getKarte().getMaxX());
		Assert.assertEquals(expectedY, spielKonfiguration.getKarte().getMaxY());
		Set<Entry<Koordinate, Feld>> felder = spielKonfiguration.getKarte().getFelderMap().entrySet();
		for (Entry<Koordinate, Feld> feld : felder) {
			Assert.assertEquals(GelaendeBeschaffenheit.WALD, ((FeldImpl)feld.getValue()).getGelaende());
		}
	}

	@Test
	public void neunMalelfKarteNurHuegelErzeugen() {
		int expectedX = 9;
		int expectedY = 11;
		Integer gelaende = Integer.valueOf(3);
		SpielKonfiguration spielKonfiguration = new SpielKonfiguration();
		spielKonfiguration.setSpielFeldMaxX(expectedX);
		spielKonfiguration.setSpielFeldMaxY(expectedY);
		RandomService dummyRandomServiceImpl = new DummyRandomServiceImpl();
		((DummyRandomServiceImpl) dummyRandomServiceImpl).setRandomValue(gelaende);

		KartenGenerator kartenGenerator = new KartenGenerator(dummyRandomServiceImpl);
		kartenGenerator.generiereKarte(spielKonfiguration);

		Assert.assertEquals(expectedX, spielKonfiguration.getKarte().getMaxX());
		Assert.assertEquals(expectedY, spielKonfiguration.getKarte().getMaxY());
		Set<Entry<Koordinate, Feld>> felder = spielKonfiguration.getKarte().getFelderMap().entrySet();
		for (Entry<Koordinate, Feld> feld : felder) {
			Assert.assertEquals(GelaendeBeschaffenheit.HUEGEL, ((FeldImpl)feld.getValue()).getGelaende());
		}
	}

	@Test
	public void nieKleinerAlsFuenfMalFuenfKarteErzeugen() {
		int vierX = 4;
		int dreiY = 3;
		int expected = 5;
		Integer gelaende = Integer.valueOf(2);
		SpielKonfiguration spielKonfiguration = new SpielKonfiguration();
		spielKonfiguration.setSpielFeldMaxX(vierX);
		spielKonfiguration.setSpielFeldMaxY(dreiY);
		RandomService dummyRandomServiceImpl = new DummyRandomServiceImpl();
		((DummyRandomServiceImpl) dummyRandomServiceImpl).setRandomValue(gelaende);

		KartenGenerator kartenGenerator = new KartenGenerator(dummyRandomServiceImpl);
		kartenGenerator.generiereKarte(spielKonfiguration);

		Assert.assertEquals(expected, spielKonfiguration.getKarte().getMaxX());
		Assert.assertEquals(expected, spielKonfiguration.getKarte().getMaxY());
	}
}
