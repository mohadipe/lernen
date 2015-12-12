package de.mohadipe.dynastie.logik;

import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Feld;

public interface DynastieLogik {
    boolean isBewegungErlaubt(Einheit einheit, Feld zielFeld, Feld aktuellesFeld);
}
