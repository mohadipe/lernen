package de.mohadipe.dynastie.karte;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.dynastie.DummyRandomServiceImpl;
import de.mohadipe.dynastie.SpielKonfiguration;

public class AufmarschZonenGeneratorTest {
	@Test
	public void mindestensZweiAufmarschZonen() {
		AufmarschZonenGenerator aufmarschZonenGenerator = new AufmarschZonenGenerator(new MinFuenfMalFuenfKarte());
		Assert.assertEquals(2, aufmarschZonenGenerator.generiereAufmarschZonen().size());
	}

	@Test
	public void zehnMalZehnKarteZweiAufmarschZonen() {
		SpielKonfiguration spielKonfiguration = new SpielKonfiguration();
		spielKonfiguration.setSpielFeldMaxX(10);
		spielKonfiguration.setSpielFeldMaxY(10);
		KartenGenerator kartenGenerator = new KartenGenerator(new DummyRandomServiceImpl());
		kartenGenerator.generiereKarte(spielKonfiguration);

		AufmarschZonenGenerator aufmarschZonenGenerator = new AufmarschZonenGenerator(spielKonfiguration.getKarte());
		Assert.assertEquals(2, aufmarschZonenGenerator.generiereAufmarschZonen().size());

		List<AufmarschZone> aufMarschZonen = aufmarschZonenGenerator.generiereAufmarschZonen();
		Assert.assertEquals(2, aufMarschZonen.size());
	}
}
