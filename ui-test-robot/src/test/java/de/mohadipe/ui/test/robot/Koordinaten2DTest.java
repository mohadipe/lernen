package de.mohadipe.ui.test.robot;

import org.junit.Assert;
import org.junit.Test;

public class Koordinaten2DTest {

	@Test
	public void sichSelbst() {
		Koordinaten2D ausgangspunkt = new Koordinaten2D();
		ausgangspunkt.x = 2;
		ausgangspunkt.y = 2;
		Koordinaten2D kandidat = new Koordinaten2D();
		kandidat.x = 2;
		kandidat.y = 2;
		
		boolean ergebnis = ausgangspunkt.isNachbarVon(kandidat);
		
		Assert.assertFalse(ergebnis);
	}
	
	@Test
	public void xGleichYEinsGroesser() {
		Koordinaten2D ausgangspunkt = new Koordinaten2D();
		ausgangspunkt.x = 2;
		ausgangspunkt.y = 2;
		Koordinaten2D kandidat = new Koordinaten2D();
		kandidat.x = 2;
		kandidat.y = 3;
		
		boolean ergebnis = ausgangspunkt.isNachbarVon(kandidat);
		
		Assert.assertTrue(ergebnis);
	}
	
	@Test
	public void xEinsGroesserYGleich() {
		Koordinaten2D ausgangspunkt = new Koordinaten2D();
		ausgangspunkt.x = 2;
		ausgangspunkt.y = 2;
		Koordinaten2D kandidat = new Koordinaten2D();
		kandidat.x = 3;
		kandidat.y = 2;
		
		boolean ergebnis = ausgangspunkt.isNachbarVon(kandidat);
		
		Assert.assertTrue(ergebnis);
	}
	
	@Test
	public void xEinsGroesserYEinsGroesser() {
		Koordinaten2D ausgangspunkt = new Koordinaten2D();
		ausgangspunkt.x = 2;
		ausgangspunkt.y = 2;
		Koordinaten2D kandidat = new Koordinaten2D();
		kandidat.x = 3;
		kandidat.y = 3;
		
		boolean ergebnis = ausgangspunkt.isNachbarVon(kandidat);
		
		Assert.assertTrue(ergebnis);
	}
	
	@Test
	public void xEinsGroesserYEinsKleiner() {
		Koordinaten2D ausgangspunkt = new Koordinaten2D();
		ausgangspunkt.x = 2;
		ausgangspunkt.y = 2;
		Koordinaten2D kandidat = new Koordinaten2D();
		kandidat.x = 3;
		kandidat.y = 1;
		
		boolean ergebnis = ausgangspunkt.isNachbarVon(kandidat);
		
		Assert.assertTrue(ergebnis);
	}
	
	@Test
	public void xEinsKleinerYEinsGroesser() {
		Koordinaten2D ausgangspunkt = new Koordinaten2D();
		ausgangspunkt.x = 2;
		ausgangspunkt.y = 2;
		Koordinaten2D kandidat = new Koordinaten2D();
		kandidat.x = 1;
		kandidat.y = 3;
		
		boolean ergebnis = ausgangspunkt.isNachbarVon(kandidat);
		
		Assert.assertTrue(ergebnis);
	}
	
	@Test
	public void xEinsKleinerYGleich() {
		Koordinaten2D ausgangspunkt = new Koordinaten2D();
		ausgangspunkt.x = 2;
		ausgangspunkt.y = 2;
		Koordinaten2D kandidat = new Koordinaten2D();
		kandidat.x = 1;
		kandidat.y = 2;
		
		boolean ergebnis = ausgangspunkt.isNachbarVon(kandidat);
		
		Assert.assertTrue(ergebnis);
	}
	
	@Test
	public void xGleichYEinsKleiner() {
		Koordinaten2D ausgangspunkt = new Koordinaten2D();
		ausgangspunkt.x = 2;
		ausgangspunkt.y = 2;
		Koordinaten2D kandidat = new Koordinaten2D();
		kandidat.x = 2;
		kandidat.y = 1;
		
		boolean ergebnis = ausgangspunkt.isNachbarVon(kandidat);
		
		Assert.assertTrue(ergebnis);
	}
	
	@Test
	public void xEinsKleinerYEinsKleiner() {
		Koordinaten2D ausgangspunkt = new Koordinaten2D();
		ausgangspunkt.x = 2;
		ausgangspunkt.y = 2;
		Koordinaten2D kandidat = new Koordinaten2D();
		kandidat.x = 1;
		kandidat.y = 1;
		
		boolean ergebnis = ausgangspunkt.isNachbarVon(kandidat);
		
		Assert.assertTrue(ergebnis);
	}
}
