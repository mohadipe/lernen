package de.mohadipe.dynastie.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SysoStartMenu extends SysoTutNixInput {
	private int naechsterSchritt;

	@Override
	public void frageInputAb() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Willkommen bei Dynastie");
		System.out.println("10 Neues Spiel starten");
		System.out.println("2 Beenden");
		try {
			naechsterSchritt = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Integer getInputFromMenu() {
		return naechsterSchritt;
	}
}
