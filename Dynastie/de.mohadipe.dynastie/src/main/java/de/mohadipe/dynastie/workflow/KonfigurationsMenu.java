package de.mohadipe.dynastie.workflow;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.input.Input;

public class KonfigurationsMenu implements Schritt {
	private Konfiguration spielKonfiguration;
	private Input input;

	public KonfigurationsMenu(Konfiguration konfiguration, Input konfigMenu) {
		this.spielKonfiguration = konfiguration;
		this.input = konfigMenu;
	}

	@Override
	public void anzeigen() {
		input.setKonfiguration(spielKonfiguration);
		input.frageInputAb();
	}

	@Override
	public int naechsterSchritt() {
		return input.getInputFromMenu();
	}
}
