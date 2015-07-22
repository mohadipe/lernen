package de.mohadipe.dynastie.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Koordinate;

public class SysoEinheitenAufstellenMenu implements Input {

	private int naechsterSchritt;

	@Override
	public Koordinate getInputKoordinate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void frageInputAb() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("20 Um die Spielzuege zu starten.");
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

	@Override
	public void setKonfiguration(Konfiguration spielKonfiguration) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setZuBewegendeEinheit(Einheit einheit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAusgangsKoordinateFuerBewegung(Koordinate standort) {
		// TODO Auto-generated method stub

	}
}
