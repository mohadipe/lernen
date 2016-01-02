package de.mohadipe.ui.test.robot.foe;

import java.awt.Color;

import de.mohadipe.ui.test.robot.FarbBlock;
import de.mohadipe.ui.test.robot.Koordinaten2D;

public enum FarbBloecke {
	MUENZE(1), WERKZEUG(2), SCHLAFEN(3), POPUP(4);
	
	private final FarbBlock farbBlock = new FarbBlock();
	
	private FarbBloecke(int block) {
		switch (block) {
		case 1:
			farbBlock.addFarbe(new Koordinaten2D(0, 0), new Color(255, 238, 136));
			farbBlock.addFarbe(new Koordinaten2D(0, 1), new Color(255, 244, 125));
			farbBlock.addFarbe(new Koordinaten2D(0, 2), new Color(255, 249, 113));
			farbBlock.addFarbe(new Koordinaten2D(1, 0), new Color(255, 244, 125));
			farbBlock.addFarbe(new Koordinaten2D(1, 1), new Color(255, 244, 119));
			farbBlock.addFarbe(new Koordinaten2D(1, 2), new Color(255, 253, 125));
			farbBlock.addFarbe(new Koordinaten2D(2, 0), new Color(255, 249, 119));
			farbBlock.addFarbe(new Koordinaten2D(2, 1), new Color(255, 238, 113));
			farbBlock.addFarbe(new Koordinaten2D(2, 2), new Color(255, 245, 113));
			break;
		case 2:
			farbBlock.addFarbe(new Koordinaten2D(0, 0), new Color(170, 102, 0));
			farbBlock.addFarbe(new Koordinaten2D(0, 1), new Color(221, 119, 0));
			farbBlock.addFarbe(new Koordinaten2D(1, 2), new Color(119, 51, 0));
			farbBlock.addFarbe(new Koordinaten2D(1, 0), new Color(136, 68, 0));
			farbBlock.addFarbe(new Koordinaten2D(1, 1), new Color(187, 102, 0));
			farbBlock.addFarbe(new Koordinaten2D(1, 2), new Color(187, 85, 0));
			farbBlock.addFarbe(new Koordinaten2D(2, 0), new Color(221, 102, 0));
			farbBlock.addFarbe(new Koordinaten2D(2, 1), new Color(136, 68, 0));
			farbBlock.addFarbe(new Koordinaten2D(2, 2), new Color(187, 102, 0));
			break;
		case 3:
			farbBlock.addFarbe(new Koordinaten2D(0, 0), new Color(204, 170, 119));
			farbBlock.addFarbe(new Koordinaten2D(0, 1), new Color(170, 153, 85));
			farbBlock.addFarbe(new Koordinaten2D(1, 2), new Color(187, 170, 102));
			farbBlock.addFarbe(new Koordinaten2D(1, 0), new Color(187, 153, 102));
			farbBlock.addFarbe(new Koordinaten2D(1, 1), new Color(187, 153, 102));
			farbBlock.addFarbe(new Koordinaten2D(1, 2), new Color(187, 153, 102));
			farbBlock.addFarbe(new Koordinaten2D(2, 0), new Color(170, 153, 102));
			farbBlock.addFarbe(new Koordinaten2D(2, 1), new Color(187, 153, 102));
			farbBlock.addFarbe(new Koordinaten2D(2, 2), new Color(187, 153, 102));
			break;
		case 4:
			farbBlock.addFarbe(new Koordinaten2D(0, 0), new Color(76, 88, 112));
			farbBlock.addFarbe(new Koordinaten2D(0, 1), new Color(74, 86, 109));
			farbBlock.addFarbe(new Koordinaten2D(1, 2), new Color(71, 82, 106));
			farbBlock.addFarbe(new Koordinaten2D(1, 0), new Color(75, 87, 111));
			farbBlock.addFarbe(new Koordinaten2D(1, 1), new Color(75, 87, 110));
			farbBlock.addFarbe(new Koordinaten2D(1, 2), new Color(69, 80, 104));
			farbBlock.addFarbe(new Koordinaten2D(2, 0), new Color(74, 86, 110));
			farbBlock.addFarbe(new Koordinaten2D(2, 1), new Color(70, 82, 105));
			farbBlock.addFarbe(new Koordinaten2D(2, 2), new Color(69, 80, 104));
			break;
		default:
			break;
		}
	}
	
	public FarbBlock getFarbBlock() {
		return farbBlock;
	}
}
