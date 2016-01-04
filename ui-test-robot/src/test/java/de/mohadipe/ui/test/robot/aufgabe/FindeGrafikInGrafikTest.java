package de.mohadipe.ui.test.robot.aufgabe;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.FarbBlock;
import de.mohadipe.ui.test.robot.Koordinaten2D;
import de.mohadipe.ui.test.robot.foe.FarbBloecke;
import de.mohadipe.ui.test.robot.util.BilderLaden;
import de.mohadipe.ui.test.robot.util.ImageToFarbBlockUtil;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindeGrafikInGrafikTest {

	private BilderLaden bilder = new BilderLaden(null);
	
	@Test
	public void findeVerknuepfungRotesIcon() {
		BufferedImage zuDurchsuchende = new BilderLaden(null).ladeScreenShotFoEVerknuepfung();
		BufferedImage zuFindende = new BilderLaden(null).ladeRotesIcon();
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(zuFindende);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, zuDurchsuchende);
		findeGrafikInGrafik.ausfuehren();
		
		Koordinaten2D koordinaten = (Koordinaten2D) findeGrafikInGrafik.getDaten(AufgabeDaten.KOORDINATE);
		Koordinaten2D expected = new Koordinaten2D();
		expected.x = 1417; expected.y = 232;
		Assert.assertEquals(expected, koordinaten);
	}
	
	@Test
	public void findeMuenzeHerausgezoomt() {
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(null);
		BufferedImage zuDurchsuchende = new BilderLaden(null).screenHerausgezoomt02();
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, zuDurchsuchende);
		FarbBlock farbBlock = ImageToFarbBlockUtil.erstelleFarbBlock(bilder.ladeBildByName("Wiederholen11.bmp"));
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, farbBlock);
		
		findeGrafikInGrafik.ausfuehren();
		
		Assert.assertTrue(findeGrafikInGrafik.isErfolgreich());
	}
	
	@Test
	public void findeWerkzeugeHerausgezoomt() {
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(null);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_DURCHSUCHENDE_GRAFIK, new BilderLaden(null).screenHerausgezoomt());
		FarbBlock farbBlock = ImageToFarbBlockUtil.erstelleFarbBlock(bilder.ladeBildByName("Wiederholen12.bmp"));
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, farbBlock);
		
		findeGrafikInGrafik.ausfuehren();
		
		Assert.assertTrue(findeGrafikInGrafik.isErfolgreich());		
	}
	
	@Test
	public void findeWerkzeug() {
		BufferedImage bildMitWerkzeuge = new BilderLaden(null).ladeScreenshotWerkzeugeAbholbereit();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitWerkzeuge);
		
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(null);
		FarbBlock farbBlock = ImageToFarbBlockUtil.erstelleFarbBlock(bilder.ladeBildByName("Wiederholen12.bmp"));
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, farbBlock);
		findeGrafikInGrafik.setRobot(robot);
		
		findeGrafikInGrafik.ausfuehren();
		
		Assert.assertNotNull(findeGrafikInGrafik.getKoordinaten());
	}
	
	@Test
	public void produziereWerkzeug() {
		BufferedImage bildMitFreierProd = new BilderLaden(null).ladeScreenshotWerkzeugeProduzierbar();
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitFreierProd);
		
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(null);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, FarbBloecke.SCHLAFEN.getFarbBlock());
		findeGrafikInGrafik.setRobot(robot);
		
		findeGrafikInGrafik.ausfuehren();
		
		Assert.assertNotNull(findeGrafikInGrafik.getKoordinaten());
	}
	
	@Test
	public void keinOffenesPopup() {
		BufferedImage bildMitFreierProd = new BilderLaden(null).ladeBildByName("LeereStadt.bmp");
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitFreierProd);
		
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(null);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, FarbBloecke.POPUP.getFarbBlock());
		findeGrafikInGrafik.setRobot(robot);
		
		findeGrafikInGrafik.ausfuehren();
		
		Assert.assertNull(findeGrafikInGrafik.getKoordinaten());
	}
	
	@Test
	public void findeFarbBlockWerkzeug01() {
		BufferedImage bildMitFreierProd = new BilderLaden(null).ladeBildByName("Werkzeug01.bmp");
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitFreierProd);
		
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(null);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, FarbBloecke.WERKZEUG.getFarbBlock());
		findeGrafikInGrafik.setRobot(robot);
		
		findeGrafikInGrafik.ausfuehren();
		
		Assert.assertNotNull(findeGrafikInGrafik.getKoordinaten());
	}
	
	@Test
	public void findeFarbBlockWerkzeug02() {
		BufferedImage bildMitFreierProd = new BilderLaden(null).ladeBildByName("Werkzeug02.bmp");
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitFreierProd);
		
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(null);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, FarbBloecke.WERKZEUG.getFarbBlock());
		findeGrafikInGrafik.setRobot(robot);
		
		findeGrafikInGrafik.ausfuehren();
		
		Assert.assertNotNull(findeGrafikInGrafik.getKoordinaten());
	}
	
	@Test
	public void findeFarbBlockWerkzeug03() {
		BufferedImage bildMitFreierProd = new BilderLaden(null).ladeBildByName("Werkzeug03.bmp");
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitFreierProd);
		
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(null);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, FarbBloecke.WERKZEUG.getFarbBlock());
		findeGrafikInGrafik.setRobot(robot);
		
		findeGrafikInGrafik.ausfuehren();
		
		Assert.assertNotNull(findeGrafikInGrafik.getKoordinaten());
	}
	
	@Test
	public void findeFarbBlockWerkzeug04() {
		BufferedImage bildMitFreierProd = new BilderLaden(null).ladeBildByName("Werkzeug04.bmp");
		Robot robot = mock(Robot.class);
		when(robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()))).thenReturn(bildMitFreierProd);
		
		FindeGrafikInGrafik findeGrafikInGrafik = new FindeGrafikInGrafik(null);
		findeGrafikInGrafik.setDaten(AufgabeDaten.ZU_FINDENDEN_FARBBLOCK, FarbBloecke.WERKZEUG.getFarbBlock());
		findeGrafikInGrafik.setRobot(robot);
		
		findeGrafikInGrafik.ausfuehren();
		
		Assert.assertNotNull(findeGrafikInGrafik.getKoordinaten());
	}
}
