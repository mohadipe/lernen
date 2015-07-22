package de.mohadipe.dynastie.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;

public class SysoBewegungZielKoordinaten implements Input {

	private Einheit einheit;
	private Koordinate standort;
	private Koordinate input;

	@Override
	public void frageInputAb() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Wohin soll die Einheit: " + einheit.toString() + " bewegt werden?");
		System.out.println("Aktueller Standort der Einheit: " + standort.getX() + " | " + standort.getY());
		try {
			System.out.println("Eingabe X: ");
			String x = br.readLine();
			System.out.println("Eingabe Y: ");
			String y = br.readLine();
			input = new ZweiDimensionaleKoordinate(Integer.valueOf(x), Integer.valueOf(y));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Koordinate getInputKoordinate() {
		return input;
	}

	@Override
	public Integer getInputFromMenu() {
		throw new RuntimeException("Kein Menu, nur Koordinaten abfrage.");
	}

	@Override
	public void setKonfiguration(Konfiguration spielKonfiguration) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setZuBewegendeEinheit(Einheit einheit) {
		this.einheit = einheit;
	}

	@Override
	public void setAusgangsKoordinateFuerBewegung(Koordinate standort) {
		this.standort = standort;
	}
}
