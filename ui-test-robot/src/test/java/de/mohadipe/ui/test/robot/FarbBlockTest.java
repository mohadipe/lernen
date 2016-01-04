package de.mohadipe.ui.test.robot;

import java.awt.Color;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FarbBlockTest {

	private static FarbBlock zweiMalZwei = new FarbBlock();
	private static FarbBlock zweiMalZweiAnders = new FarbBlock();
	private static FarbBlock zweiMalZweiDurcheinander = new FarbBlock();
	private static FarbBlock muenze01 = new FarbBlock();
	private static FarbBlock muenze02 = new FarbBlock();
	private static FarbBlock werkzeug01 = new FarbBlock();
	private static FarbBlock werkzeug02 = new FarbBlock();
	private static FarbBlock schlafen01 = new FarbBlock();
	private static FarbBlock schlafen02 = new FarbBlock();
	private static FarbBlock popup01 = new FarbBlock();
	private static FarbBlock popup02 = new FarbBlock();
	
	@BeforeClass
	public static void beforeClass() {
		zweiMalZwei.addFarbe(new Koordinaten2D(0, 0), new Color(1, 1, 1));
		zweiMalZwei.addFarbe(new Koordinaten2D(1, 0), new Color(1, 1, 1));
		zweiMalZwei.addFarbe(new Koordinaten2D(0, 1), new Color(1, 1, 1));
		zweiMalZwei.addFarbe(new Koordinaten2D(1, 1), new Color(1, 1, 1));		
		
		zweiMalZweiAnders.addFarbe(new Koordinaten2D(0, 0), new Color(1, 1, 1));
		zweiMalZweiAnders.addFarbe(new Koordinaten2D(1, 0), new Color(1, 1, 1));
		zweiMalZweiAnders.addFarbe(new Koordinaten2D(0, 1), new Color(1, 2, 1));
		zweiMalZweiAnders.addFarbe(new Koordinaten2D(1, 1), new Color(1, 1, 1));
		
		zweiMalZweiDurcheinander.addFarbe(new Koordinaten2D(0, 0), new Color(1, 1, 1));
		zweiMalZweiDurcheinander.addFarbe(new Koordinaten2D(1, 1), new Color(1, 1, 1));
		zweiMalZweiDurcheinander.addFarbe(new Koordinaten2D(0, 1), new Color(1, 2, 1));
		zweiMalZweiDurcheinander.addFarbe(new Koordinaten2D(1, 0), new Color(1, 1, 1));
		
		muenze01.addFarbe(new Koordinaten2D(0, 0), new Color(255, 238, 136));
		muenze01.addFarbe(new Koordinaten2D(0, 1), new Color(255, 253, 123));
		muenze01.addFarbe(new Koordinaten2D(0, 2), new Color(255, 238, 130));
		muenze01.addFarbe(new Koordinaten2D(1, 0), new Color(255, 247, 123));
		muenze01.addFarbe(new Koordinaten2D(1, 1), new Color(255, 238, 119));
		muenze01.addFarbe(new Koordinaten2D(1, 2), new Color(255, 244, 130));
		muenze01.addFarbe(new Koordinaten2D(2, 0), new Color(255, 246, 115));
		muenze01.addFarbe(new Koordinaten2D(2, 1), new Color(255, 244, 119));
		muenze01.addFarbe(new Koordinaten2D(2, 2), new Color(255, 247, 123));

		muenze02.addFarbe(new Koordinaten2D(0, 0), new Color(255, 238, 136));
		muenze02.addFarbe(new Koordinaten2D(0, 1), new Color(255, 244, 125));
		muenze02.addFarbe(new Koordinaten2D(0, 2), new Color(255, 249, 113));
		muenze02.addFarbe(new Koordinaten2D(1, 0), new Color(255, 244, 125));
		muenze02.addFarbe(new Koordinaten2D(1, 1), new Color(255, 244, 119));
		muenze02.addFarbe(new Koordinaten2D(1, 2), new Color(255, 253, 125));
		muenze02.addFarbe(new Koordinaten2D(2, 0), new Color(255, 249, 119));
		muenze02.addFarbe(new Koordinaten2D(2, 1), new Color(255, 238, 113));
		muenze02.addFarbe(new Koordinaten2D(2, 2), new Color(255, 245, 113));
		
		werkzeug01.addFarbe(new Koordinaten2D(0, 0), new Color(108, 45, 0));
		werkzeug01.addFarbe(new Koordinaten2D(0, 1), new Color(153, 70, 4));
		werkzeug01.addFarbe(new Koordinaten2D(0, 2), new Color(117, 49, 0));
		werkzeug01.addFarbe(new Koordinaten2D(1, 0), new Color(181, 85, 0));
		werkzeug01.addFarbe(new Koordinaten2D(1, 1), new Color(193, 106, 0));
		werkzeug01.addFarbe(new Koordinaten2D(1, 2), new Color(130, 57, 0));
		werkzeug01.addFarbe(new Koordinaten2D(2, 0), new Color(238, 102, 0));
		werkzeug01.addFarbe(new Koordinaten2D(2, 1), new Color(165, 79, 0));
		werkzeug01.addFarbe(new Koordinaten2D(2, 2), new Color(170, 91, 0));

		werkzeug02.addFarbe(new Koordinaten2D(0, 0), new Color(113, 49, 0));
		werkzeug02.addFarbe(new Koordinaten2D(0, 1), new Color(104, 42, 4));
		werkzeug02.addFarbe(new Koordinaten2D(0, 2), new Color(102, 45, 0));
		werkzeug02.addFarbe(new Koordinaten2D(1, 0), new Color(151, 66, 0));
		werkzeug02.addFarbe(new Koordinaten2D(1, 1), new Color(179, 96, 0));
		werkzeug02.addFarbe(new Koordinaten2D(1, 2), new Color(119, 51, 0));
		werkzeug02.addFarbe(new Koordinaten2D(2, 0), new Color(221, 108, 0));
		werkzeug02.addFarbe(new Koordinaten2D(2, 1), new Color(153, 79, 0));
		werkzeug02.addFarbe(new Koordinaten2D(2, 2), new Color(187, 85, 0));

		schlafen01.addFarbe(new Koordinaten2D(0, 0), new Color(194, 160, 116));
		schlafen01.addFarbe(new Koordinaten2D(0, 1), new Color(182, 153, 108));
		schlafen01.addFarbe(new Koordinaten2D(1, 2), new Color(187, 153, 113));
		schlafen01.addFarbe(new Koordinaten2D(1, 0), new Color(187, 153, 102));
		schlafen01.addFarbe(new Koordinaten2D(1, 1), new Color(187, 153, 102));
		schlafen01.addFarbe(new Koordinaten2D(1, 2), new Color(187, 153, 102));
		schlafen01.addFarbe(new Koordinaten2D(2, 0), new Color(178, 153, 112));
		schlafen01.addFarbe(new Koordinaten2D(2, 1), new Color(187, 153, 101));
		schlafen01.addFarbe(new Koordinaten2D(2, 2), new Color(187, 153, 99));
		
		schlafen02.addFarbe(new Koordinaten2D(0, 0), new Color(193, 159, 115));
		schlafen02.addFarbe(new Koordinaten2D(0, 1), new Color(181, 153, 108));
		schlafen02.addFarbe(new Koordinaten2D(1, 2), new Color(187, 153, 113));
		schlafen02.addFarbe(new Koordinaten2D(1, 0), new Color(187, 153, 102));
		schlafen02.addFarbe(new Koordinaten2D(1, 1), new Color(187, 153, 102));
		schlafen02.addFarbe(new Koordinaten2D(1, 2), new Color(187, 153, 102));
		schlafen02.addFarbe(new Koordinaten2D(2, 0), new Color(178, 153, 113));
		schlafen02.addFarbe(new Koordinaten2D(2, 1), new Color(187, 153, 102));
		schlafen02.addFarbe(new Koordinaten2D(2, 2), new Color(187, 153, 98));
		
		popup01.addFarbe(new Koordinaten2D(0, 0), new Color(71, 86, 109));
		popup01.addFarbe(new Koordinaten2D(0, 1), new Color(62, 70, 88));
		popup01.addFarbe(new Koordinaten2D(1, 0), new Color(80, 92, 115));
		popup01.addFarbe(new Koordinaten2D(1, 1), new Color(72, 81, 103));
		
		popup02.addFarbe(new Koordinaten2D(0, 0), new Color(74, 86, 109));
		popup02.addFarbe(new Koordinaten2D(0, 1), new Color(71, 82, 106));
		popup02.addFarbe(new Koordinaten2D(1, 0), new Color(75, 87, 110));
		popup02.addFarbe(new Koordinaten2D(1, 1), new Color(69, 80, 104));
	}

	@Test
	public void equalsListeAndereReihenfolge() {
		Assert.assertTrue(zweiMalZweiAnders.equals(zweiMalZweiDurcheinander));
	}
	
	@Test
	public void maxXVonZweiMalZweiBlock() {
		Assert.assertEquals(1, zweiMalZwei.getMaxX());
	}
	
	@Test
	public void maxYVonZweiMalZweiBlock() {
		Assert.assertEquals(1, zweiMalZwei.getMaxY());
	}
	
	@Test
	public void anzahlFarbPixelZweiMalZweiBlock() {
		Assert.assertEquals(4, zweiMalZwei.getAnzahlFarbPixel());
	}
	
	@Test
	public void farbeVonNullNullKoordinate() {
		Assert.assertEquals(new Color(1, 1, 1), zweiMalZwei.getFarbe(0, 0));
	}
	
	@Test
	public void zweiVerschiedeneFarbBloecke() {
		Assert.assertFalse(zweiMalZwei.equals(zweiMalZweiAnders));
	}
	
	@Test
	public void zweiGleicheFarbBloecke() {
		Assert.assertTrue(zweiMalZwei.equals(zweiMalZwei));
	}
	
	@Test
	public void aehnlicheFarbBloeckeMuenze() {
		Assert.assertTrue(muenze01.isAehnlich(muenze02, 17));
	}
	
	@Test
	public void aehnlicheFarbBloeckeWerkzeug() {
		Assert.assertTrue(werkzeug01.isAehnlich(werkzeug02, 49));
	}
	
	@Test
	public void aehnlicheFarbBloeckeSchlafen() {
		Assert.assertTrue(schlafen01.isAehnlich(schlafen02, 5));
	}
	
	@Test
	public void aehnlicheFarbBloeckePopup() {
		Assert.assertTrue(popup01.isAehnlich(popup02, 18));
	}
}
