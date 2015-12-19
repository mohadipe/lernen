package de.mohadipe.ui.test.robot.protokoll;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.aufgabe.Aufgabe;
import de.mohadipe.ui.test.robot.aufgabe.FindeGrafikInGrafik;

public class ProtokollTest {
	@Test
	public void aufgabeErfolgreichAusgefuehrt() {
		Protokoll protokoll = new Protokoll();
		Aufgabe erfolgreicheAufgabe = mock(FindeGrafikInGrafik.class);
		when(erfolgreicheAufgabe.isErfolgreich()).thenReturn(Boolean.TRUE);
		
		protokoll.addAusgefuehrteAufgabe(erfolgreicheAufgabe);
		Assert.assertTrue(protokoll.isAufgabeAusgefuehrt(erfolgreicheAufgabe));
		Assert.assertTrue(protokoll.isAufgabeErfolgreichAusgefuehrt(erfolgreicheAufgabe));
	}
	
	@Test
	public void aufgabeNichtErfolgreichAusgefuehrt() {
		Protokoll protokoll = new Protokoll();
		Aufgabe erfolgreicheAufgabe = mock(FindeGrafikInGrafik.class);
		when(erfolgreicheAufgabe.isErfolgreich()).thenReturn(Boolean.FALSE);
		
		protokoll.addAusgefuehrteAufgabe(erfolgreicheAufgabe);
		Assert.assertTrue(protokoll.isAufgabeAusgefuehrt(erfolgreicheAufgabe));
		Assert.assertFalse(protokoll.isAufgabeErfolgreichAusgefuehrt(erfolgreicheAufgabe));
	}
	
	@Test
	public void aufgabeNichtAusgefuehrt() {
		Protokoll protokoll = new Protokoll();
		Aufgabe erfolgreicheAufgabe = mock(FindeGrafikInGrafik.class);
		Assert.assertFalse(protokoll.isAufgabeAusgefuehrt(erfolgreicheAufgabe));
	}
	
	@Test
	public void resetAusgefuehrteAufgaben() {
		Protokoll protokoll = new Protokoll();
		Aufgabe erfolgreicheAufgabe = mock(FindeGrafikInGrafik.class);
		protokoll.addAusgefuehrteAufgabe(erfolgreicheAufgabe);
		Assert.assertTrue(protokoll.isAufgabeAusgefuehrt(erfolgreicheAufgabe));
		
		protokoll.reset();
		
		Assert.assertFalse(protokoll.isAufgabeAusgefuehrt(erfolgreicheAufgabe));
	}
}
