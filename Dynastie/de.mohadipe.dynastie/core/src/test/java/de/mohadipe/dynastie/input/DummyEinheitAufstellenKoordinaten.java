package de.mohadipe.dynastie.input;

import de.mohadipe.dynastie.logik.model.Koordinate;

public class DummyEinheitAufstellenKoordinaten extends TutNixInput {

	private Koordinate inputKoordinate;

	public DummyEinheitAufstellenKoordinaten(Koordinate startKoordinate) {
		this.inputKoordinate = startKoordinate;
	}

	@Override
	public Koordinate getInputKoordinate() {
		return this.inputKoordinate;
	}
}
