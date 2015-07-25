package de.mohadipe.dynastie.input;


public class DummyStartMenu extends TutNixInput {

	private int inputFromStartMenu;

	public DummyStartMenu(int input) {
		this.inputFromStartMenu = input;
	}

	@Override
	public Integer getInputFromMenu() {
		return inputFromStartMenu;
	}
}
