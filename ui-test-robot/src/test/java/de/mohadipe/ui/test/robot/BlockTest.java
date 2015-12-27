package de.mohadipe.ui.test.robot;

import org.junit.Assert;
import org.junit.Test;

import de.mohadipe.ui.test.robot.aufgabe.Block;

public class BlockTest {

	@Test
	public void koordinatenLiegenNebeneinander() throws GehoertNichtZuBlockException {
		Block block = new Block(2);
		block.addKoordinate(1, 1);
		block.addKoordinate(1, 2);
	}
	
	@Test(expected=GehoertNichtZuBlockException.class)
	public void koordinatenLiegenNichtNebeneinander() throws GehoertNichtZuBlockException {
		Block block = new Block(2);
		block.addKoordinate(1, 1);
		block.addKoordinate(1, 3);
	}
	
	@Test
	public void blockIstVoll() throws GehoertNichtZuBlockException {
		Block block = new Block(1);
		block.addKoordinate(1, 1);
		Assert.assertTrue(block.isBlockVoll());
	}
}
