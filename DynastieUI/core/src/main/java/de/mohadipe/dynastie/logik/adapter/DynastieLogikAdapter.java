package de.mohadipe.dynastie.logik.adapter;

import de.mohadipe.dynastie.logik.DynastieLogik;
import de.mohadipe.dynastie.logik.model.Einheit;
import de.mohadipe.dynastie.logik.model.Feld;

public class DynastieLogikAdapter {
    public static DynastieLogik getInstance() {
        // TODO Anbindung der DynastieLogik-Bibliothek
        return new DummyLogik();
    }

    public static Einheit convertToLogikEinheit(de.mohadipe.dynastie.ui.entities.Einheit uiEinheit) {
        return null;
    }

    public static Feld convertToLogikFeld(de.mohadipe.dynastie.ui.map.Feld feld) {
        return null;
    }
}
