package de.mohadipe.dynastie.input;

import de.mohadipe.dynastie.Konfiguration;
import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Koordinate;

public interface Input {
	public Koordinate getInputKoordinate();

	public void frageInputAb();

	public Integer getInputFromMenu();

	public void setKonfiguration(Konfiguration spielKonfiguration);

	public void setZuBewegendeEinheit(Einheit einheit);

	public void setAusgangsKoordinateFuerBewegung(Koordinate standort);
}
