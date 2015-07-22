package de.mohadipe.dynastie.angreifen;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.RandomService;
import de.mohadipe.dynastie.karte.Karte;
import de.mohadipe.dynastie.output.Output;
import de.mohadipe.dynastie.spieler.Spieler;

public interface Angreifen {

	void angreifen();

	void setSpieler(Spieler spieler);

	void setKarte(Karte karte);

	void setKonfiguration(Konfiguration konfiguration);

	void setRandomService(RandomService randomService);

	void setOutput(Output output);

}
