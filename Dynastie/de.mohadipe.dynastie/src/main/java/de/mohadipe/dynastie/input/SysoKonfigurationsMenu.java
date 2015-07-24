package de.mohadipe.dynastie.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.spieler.ComputerSpieler;
import de.mohadipe.dynastie.spieler.MenschSpieler;
import de.mohadipe.dynastie.spieler.Spieler;

public class SysoKonfigurationsMenu extends SysoTutNixInput {

	private int naechsterSchritt;
	private Konfiguration spielKonfiguration;

	@Override
	public void frageInputAb() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Spiel konfigurieren");
		System.out.println("Wieviele Punkte?");
		System.out.println("/ " + spielKonfiguration.getEinheitenPunkte());
		System.out.println("Welche Siegbedingungen?");
		System.out.println("/ " + spielKonfiguration.getSieg());
		System.out.println("EinzelSpieler-Modus gegen Computer");
		System.out.println("Ihr Name:");
		try {
			String nameMensch = br.readLine();
			Spieler menschSpieler = new MenschSpieler();
			menschSpieler.setIdentitaet(nameMensch);
			Spieler computerSpieler = new ComputerSpieler();
			spielKonfiguration.getAlleSpieler().add(menschSpieler);
			spielKonfiguration.getAlleSpieler().add(computerSpieler);
			System.out.println("15 Weiter");
			System.out.println("2 Beenden");
			naechsterSchritt = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Integer getInputFromMenu() {
		return naechsterSchritt;
	}

	@Override
	public void setKonfiguration(Konfiguration spielKonfiguration) {
		this.spielKonfiguration = spielKonfiguration;
	}
}
