package de.mohadipe.ui.test.robot.aufgabe;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WiederholeAufgabeTest {

//	@Test brauche ich erstmal nicht
//	public void wiederholeAufgabeZweiMal() {
//		Aufgabe zuWiederholendeAufgabe = mock(FindeGrafikInGrafik.class);
//		when(zuWiederholendeAufgabe.isErfolgreich()).thenReturn(false, true);
//		
//		WiederholeAufgabe wiederholeAufgabe = new WiederholeAufgabe();
//		wiederholeAufgabe.addAbhaengigkeit(zuWiederholendeAufgabe);
//		wiederholeAufgabe.ausfuehren();
//		
//		Integer wiederholungen = (Integer) wiederholeAufgabe.getDaten(AufgabeDaten.ANZAHL_WIEDERHOLUNGEN);
//		Assert.assertEquals(2, wiederholungen.intValue());
//	}
	
	@Test
	public void brichWiederholungWegenTimeoutAb() {
		Aufgabe zuWiederholendeAufgabe = mock(FindeGrafikInGrafik.class);
		when(zuWiederholendeAufgabe.isErfolgreich()).thenReturn(false, false, false, true);
		
		WiederholeAufgabe wiederholeAufgabe = new WiederholeAufgabe();
		wiederholeAufgabe.setDaten(AufgabeDaten.TIMEOUT, Long.valueOf(1));
		wiederholeAufgabe.addAbhaengigkeit(zuWiederholendeAufgabe);
		wiederholeAufgabe.ausfuehren();
		
		Integer wiederholungen = (Integer) wiederholeAufgabe.getDaten(AufgabeDaten.ANZAHL_WIEDERHOLUNGEN);
		Assert.assertTrue("Zu viele Wiederholungen. ", (1 <= wiederholungen.intValue()) && (wiederholungen.intValue() <= 800));
	}
	
}
