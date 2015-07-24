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
		System.out.println("1: Mensch gegen Computer");
		System.out.println("2: Mensch gegen Mensch");
		System.out.println("3: Computer gegen Computer");

		try {
			int auswahl = Integer.parseInt(br.readLine());
			switch (auswahl) {
			case 1:
				menschVsComputer(br);
				break;
			case 2:
				menschVsMensch(br);
				break;
			case 3:
				computerVsComputer(br);
				break;
			default:
				menschVsComputer(br);
				break;
			}
			System.out.println("15 Weiter");
			System.out.println("2 Beenden");
			naechsterSchritt = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void computerVsComputer(BufferedReader br) {
		spielKonfiguration.getAlleSpieler().add(new ComputerSpieler("KI1"));
		spielKonfiguration.getAlleSpieler().add(new ComputerSpieler("KI2"));
	}

	private void menschVsMensch(BufferedReader br) throws IOException {
		System.out.println("Ihr Name 1. Spieler:");
		String nameMensch1 = br.readLine();
		Spieler menschSpieler1 = new MenschSpieler();
		menschSpieler1.setIdentitaet(nameMensch1);
		System.out.println("Ihr Name 2. Spieler:");
		String nameMensch2 = br.readLine();
		Spieler menschSpieler2 = new MenschSpieler();
		menschSpieler2.setIdentitaet(nameMensch2);
		spielKonfiguration.getAlleSpieler().add(menschSpieler1);
		spielKonfiguration.getAlleSpieler().add(menschSpieler2);
	}

	private void menschVsComputer(BufferedReader br) throws IOException {
		System.out.println("Ihr Name:");
		String nameMensch = br.readLine();
		Spieler menschSpieler = new MenschSpieler();
		menschSpieler.setIdentitaet(nameMensch);
		Spieler computerSpieler = new ComputerSpieler();
		spielKonfiguration.getAlleSpieler().add(menschSpieler);
		spielKonfiguration.getAlleSpieler().add(computerSpieler);
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
