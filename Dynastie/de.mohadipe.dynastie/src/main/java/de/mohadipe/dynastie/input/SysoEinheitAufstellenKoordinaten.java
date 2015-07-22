package de.mohadipe.dynastie.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.einheiten.Einheit;
import de.mohadipe.dynastie.karte.Koordinate;
import de.mohadipe.dynastie.karte.ZweiDimensionaleKoordinate;

public class SysoEinheitAufstellenKoordinaten implements Input {

	private Koordinate koordinate;
	private Konfiguration spielKonfiguration;

	@Override
	public Koordinate getInputKoordinate() {
		return koordinate;
	}

	@Override
	public void frageInputAb() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Sie dürfen Einheiten für " + spielKonfiguration.getEinheitenPunkte() + " Punkte aufstellen.");
		System.out.println("Geben Sie die Koordinaten für Ihre Einheit an.");
		try {
			System.out.println("X:");
			int x = Integer.parseInt(br.readLine());
			System.out.println("Y:");
			int y = Integer.parseInt(br.readLine());
			koordinate = new ZweiDimensionaleKoordinate(x, y);
		} catch (NumberFormatException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Integer getInputFromMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setKonfiguration(Konfiguration spielKonfiguration) {
		this.spielKonfiguration = spielKonfiguration;
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
