package de.mohadipe.ui.test.robot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.aufgabe.Block;
import de.mohadipe.ui.test.robot.util.BilderLaden;

public class FindeRGBHaeufungTest {

	@Test
	public void findeBlockGleicherFarbe() {
		int rgb = -10083840;
		int anzahlUebereinstimmenderFelder = 1;
		Block block = new Block(anzahlUebereinstimmenderFelder);
		BufferedImage currentScreen = new BilderLaden(null).ladeScreenshotMuenzenAbholbereit();
		List<Block> treffer = new ArrayList<Block>();
		for (int x = 0; x < currentScreen.getWidth(); x++) {
			for (int y = 0; y < currentScreen.getHeight(); y++) {
				int cSRGB = currentScreen.getRGB(x, y);
				if (cSRGB == rgb) {
					try {
						block.addKoordinate(x, y);
					} catch (GehoertNichtZuBlockException e) {
						block = new Block(anzahlUebereinstimmenderFelder);
					}
				}
				if (block.isBlockVoll()) {
					treffer.add(block);
					block = new Block(anzahlUebereinstimmenderFelder);
				}
			}
		}
		Assert.assertEquals(21, treffer.size());
		System.out.println(treffer);
	}
	
	@Test
	public void kombiniereBloecke() throws GehoertNichtZuBlockException {
		List<Block> einzelneBloecke = new ArrayList<Block>();
		einzelneBloecke.add(new Block(1, 224, 566));
//		einzelneBloecke.add(new Block(1, 232, 581));
//		einzelneBloecke.add(new Block(1, 233, 573));
//		einzelneBloecke.add(new Block(1, 233, 579));
//		einzelneBloecke.add(new Block(1, 234, 576));
		einzelneBloecke.add(new Block(1, 235, 574));
//		einzelneBloecke.add(new Block(1, 235, 580));
		einzelneBloecke.add(new Block(1, 236, 574));
//		einzelneBloecke.add(new Block(1, 236, 580));
//		einzelneBloecke.add(new Block(1, 236, 582));
//		einzelneBloecke.add(new Block(1, 236, 586));
//		einzelneBloecke.add(new Block(1, 236, 590));
		einzelneBloecke.add(new Block(1, 237, 574));
//		einzelneBloecke.add(new Block(1, 238, 580));
//		einzelneBloecke.add(new Block(1, 238, 590));
//		einzelneBloecke.add(new Block(1, 239, 587));
//		einzelneBloecke.add(new Block(1, 239, 591));
//		einzelneBloecke.add(new Block(1, 240, 575));
//		einzelneBloecke.add(new Block(1, 241, 575));
//		einzelneBloecke.add(new Block(1, 241, 591));
//		einzelneBloecke.add(new Block(1, 242, 575));
		
		List<Block> kombinationen = kombiniereBloecke(einzelneBloecke);
		System.out.println(kombinationen);
		Assert.assertEquals(4, kombinationen.size());
	}

	private List<Block> kombiniereBloecke(List<Block> einzelneBloecke) throws GehoertNichtZuBlockException {
		List<Block> kombinationen = new ArrayList<Block>();
		for (Block block : einzelneBloecke) {
			Block tmp = new Block(block.getKantenLaenge());
			tmp.addBlock(block);
			for (Block block2 : einzelneBloecke) {
				try {
					tmp.addBlock(block2);
				} catch (GehoertNichtZuBlockException e) {
					continue;
				}
			}
			if (!kombinationen.contains(tmp)) {
				kombinationen.add(tmp);
			}
		}
		return kombinationen;
	}
}
