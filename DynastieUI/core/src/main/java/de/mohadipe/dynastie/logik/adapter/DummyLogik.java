package de.mohadipe.dynastie.logik.adapter;

import de.mohadipe.dynastie.logik.DynastieLogik;
import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Feld;

public class DummyLogik implements DynastieLogik {
    @Override
    public boolean isBewegungErlaubt(Einheit einheit, Feld feld, Feld feld1) {
        return true;
    }
}
