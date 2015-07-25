package de.mohadipe.dynastie.input;


public class DummyKonfigurationsMenu extends TutNixInput {

	private int inputFromStartMenu;

	public DummyKonfigurationsMenu(int input) {
		this.inputFromStartMenu = input;
	}

	@Override
	public Integer getInputFromMenu() {
		return this.inputFromStartMenu;
	}
}
