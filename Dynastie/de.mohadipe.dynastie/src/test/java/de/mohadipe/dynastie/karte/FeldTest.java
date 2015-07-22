package de.mohadipe.dynastie.karte;

import org.junit.Assert;
import org.junit.Test;

public class FeldTest {
	@Test
	public void initialisiereEbeneFeld() {
		FeldImpl feldImpl = new FeldImpl(GelaendeBeschaffenheit.EBENE);
		Assert.assertEquals("E", feldImpl.toString());
	}
}
