package de.mohadipe.ui.test.robot;

import java.awt.AWTException;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.aufgabe.Aufgabe;
import de.mohadipe.ui.test.robot.aufgabe.FindeGrafikInGrafik;
import de.mohadipe.ui.test.robot.aufgabe.DoppelKlickeKoordinaten;
import de.mohadipe.ui.test.robot.aufgabe.Warten;
import de.mohadipe.ui.test.robot.protokoll.Protokoll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AufgabenAusfuehrenTest {
	
	@Test
	public void fuehreErfolgreicheAufgabeAus() throws AWTException {
		AufgabenAusfuehren aufgabenAusfuehren = new AufgabenAusfuehren();
		Aufgabe erfolgreicheAufgabe = mock(FindeGrafikInGrafik.class);
		when(erfolgreicheAufgabe.isErfolgreich()).thenReturn(Boolean.TRUE);
		aufgabenAusfuehren.addAufgabe(erfolgreicheAufgabe);
		
		aufgabenAusfuehren.fuehreAufgabenAus();
		
		Protokoll protokoll = aufgabenAusfuehren.getProtokoll();
		Assert.assertTrue(protokoll.isAufgabeAusgefuehrt(erfolgreicheAufgabe));
	}
	
	@Test
	public void fuehreAufgabenInReihenfolgeAus() throws AWTException{
		AufgabenAusfuehren aufgabenAusfuehren = new AufgabenAusfuehren();
		Aufgabe grafikFinden = mock(FindeGrafikInGrafik.class);
		Aufgabe warten = mock(Warten.class);

		when(grafikFinden.getPositionAusfuehrung()).thenReturn(Integer.valueOf(0));
		when(warten.getPositionAusfuehrung()).thenReturn(Integer.valueOf(1));
		aufgabenAusfuehren.addAufgabe(grafikFinden);
		aufgabenAusfuehren.addAufgabe(warten);
		
		aufgabenAusfuehren.fuehreAufgabenAus();
		
		verify(grafikFinden).setPositionAusfuehrung(0);
		verify(warten).setPositionAusfuehrung(1);
		Protokoll protokoll = aufgabenAusfuehren.getProtokoll();
		int position = protokoll.wannWurdeAufgabeAusgefuehrt(grafikFinden);
		Assert.assertEquals(0, position);
		position = protokoll.wannWurdeAufgabeAusgefuehrt(warten);
		Assert.assertEquals(1, position);
	}
	
	@Test
	public void klickeKoordinatenAbhaengigVonFindeGrafik() {
		AufgabenAusfuehren aufgabenAusfuehren = new AufgabenAusfuehren();
		Aufgabe grafikFinden = mock(FindeGrafikInGrafik.class);
		Aufgabe klickeKoordinaten = mock(DoppelKlickeKoordinaten.class);
		klickeKoordinaten.addAbhaengigkeit(grafikFinden);
		
		aufgabenAusfuehren.addAufgabe(grafikFinden);
		aufgabenAusfuehren.addAufgabe(klickeKoordinaten);
		
		aufgabenAusfuehren.fuehreAufgabenAus();
		
		verify(grafikFinden).ausfuehren();
		verify(klickeKoordinaten).ausfuehren();
	}
}
