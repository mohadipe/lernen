package de.mohadipe.dynastie.input;


public class DummyEinheitenAufstellenMenu extends TutNixInput {

	private int inputFromMenu;

	public DummyEinheitenAufstellenMenu(int input) {
		this.inputFromMenu = input;
	}

	@Override
	public Integer getInputFromMenu() {
		return this.inputFromMenu;
	}
}
