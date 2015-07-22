package de.mohadipe.dynastie.karte;

import junit.framework.Assert;

import org.junit.Test;

public class GelaendeBeschaffenheitTest {
	
	@Test
	public void testValues() {
		Assert.assertEquals(3, GelaendeBeschaffenheit.values().length);
	}
}
