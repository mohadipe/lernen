package de.mohadipe.dynastie.workflow;

import de.mohadipe.dynastie.input.Input;

public class StartMenu implements Schritt {
	private Input input;
	private int naechsterSchritt;

	public StartMenu(Input startMenuInput) {
		this.input = startMenuInput;
	}

	private void startMaenu() {
		input.frageInputAb();
		naechsterSchritt = input.getInputFromMenu();
	}

	@Override
	public void anzeigen() {
		startMaenu();
	}

	@Override
	public int naechsterSchritt() {
		return naechsterSchritt;
	}
}
