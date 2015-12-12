package de.mohadipe.ui.test.robot.path;

import org.junit.Assert;
import org.junit.Test;

public class GrafikDateiPfadServiceTest {
	@Test
	public void absoluterPfadZuGrafikenInnerhalbIDEermitteln() {
		String expected = "C:\\Users\\Mohadipe\\workspace\\lernen\\ui-test-robot\\grafiken\\";
		GrafikDateiPfadeService grafikDateiPfadeService = new GrafikDateiPfadeService(true);
		String actual = grafikDateiPfadeService.getPathForIDE();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void absoluterPfadZuGrafikenInnerhalbRuntimeermitteln() {
		String expected = "TODO";
		GrafikDateiPfadeService grafikDateiPfadeService = new GrafikDateiPfadeService(true);
		String actual = grafikDateiPfadeService.getPathForRuntime();
		Assert.assertEquals(expected, actual);
	}
}
