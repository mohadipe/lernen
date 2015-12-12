package de.mohadipe.dynastie.angreifen;

import de.mohadipe.dynastie.RandomService;
import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Feld;
import de.mohadipe.dynastie.output.Output;
import de.mohadipe.dynastie.output.TutNixOutput;

public class Kampf {
	public static final String KEIN_RANDOM_SERVICE_DEFINIERT = "Kein RandomService definiert.";
	private static final int GRENZWERT_TREFFER = 4;
	private static final int SECHSSEITIGER_WUERFEL = 6;
	private final Einheit angreifer;
	private final Feld ziel;
	private RandomService randomService = null;
	private Output output;

	public Kampf(final Einheit angreifer, final Feld ziel) {
		this.angreifer = angreifer;
		this.ziel = ziel;
		this.output = new TutNixOutput();
	}

	public void setRandomService(RandomService randomService) {
		this.randomService = randomService;
	}

	public void ausfuehren() {
		if (randomService == null) {
			throw new RuntimeException(KEIN_RANDOM_SERVICE_DEFINIERT);
		}
		// wuerfeln ob Treffer oder nicht
		int randomNummerEinsBis = randomService.getRandomNummerEinsBis(SECHSSEITIGER_WUERFEL);
		if (randomNummerEinsBis >= GRENZWERT_TREFFER) {
			Einheit copyOfEinheit = ziel.getEinheit();
			copyOfEinheit.erleideTrefferStaerke(angreifer.getStaerke());
			if (copyOfEinheit.isAmLeben()) {
				output.einheitGetroffen(copyOfEinheit);
				ziel.setEinheit(copyOfEinheit);
			} else {
				ziel.setEinheit(null);
				output.zielZerstoert(copyOfEinheit);
			}
		} else {
			output.einheitVerfaehlt(randomNummerEinsBis);
		}
	}

	public void setOutput(Output output) {
		this.output = output;
	}
}
