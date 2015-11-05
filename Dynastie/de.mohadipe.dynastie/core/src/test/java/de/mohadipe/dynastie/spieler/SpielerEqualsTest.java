package de.mohadipe.dynastie.spieler;

import junit.framework.Assert;

import org.junit.Test;

public class SpielerEqualsTest {
	@Test
	public void computerMenschUngleich() {
		Spieler cs = new ComputerSpieler();
		Spieler ms = new MenschSpieler();
		Assert.assertFalse(cs.equals(ms));
	}

	@Test
	public void computerMenschTrotzIdentitaetUngleich() {
		Spieler cs = new ComputerSpieler();
		cs.setIdentitaet("cs");
		Spieler ms = new MenschSpieler();
		ms.setIdentitaet("cs");
		Assert.assertFalse(cs.equals(ms));
	}

	@Test
	public void menschMenschGleich() {
		Spieler ms1 = new MenschSpieler();
		Spieler ms2 = new MenschSpieler();
		Assert.assertTrue(ms1.equals(ms2));
	}

	@Test
	public void menschMenschUnGleich() {
		Spieler ms1 = new MenschSpieler();
		ms1.setIdentitaet("Bob");
		Spieler ms2 = new MenschSpieler();
		ms2.setIdentitaet("Tim");
		Assert.assertFalse(ms1.equals(ms2));
	}

	@Test
	public void computerComputerGleich() {
		Spieler cs1 = new ComputerSpieler();
		Spieler cs2 = new ComputerSpieler();
		Assert.assertTrue(cs1.equals(cs2));
	}

	@Test
	public void computerComputerUnGleich() {
		Spieler cs1 = new ComputerSpieler();
		cs1.setIdentitaet("Bob");
		Spieler cs2 = new ComputerSpieler();
		cs2.setIdentitaet("Tim");
		Assert.assertFalse(cs1.equals(cs2));
	}
}
