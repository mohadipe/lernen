package de.mohadipe.dynastie.ui.map;

import java.util.ArrayList;
import java.util.List;

import de.mohadipe.dynastie.ui.entities.Spieler;

public class AufmarschZone {

    private List<Feld> felder = new ArrayList<>();
    private List<Feld> besetzteFelder = new ArrayList<>();
    private Spieler gehoert;
    private boolean frei = true;

    public void addFeld(FeldImpl feld) {
        felder.add(feld);
    }

    public boolean isFrei() {
        return frei;
    }

    public void verbindeMit(Spieler spieler) {
        this.gehoert = spieler;
        this.frei = false;
    }

    public boolean isSpielerZone(Spieler spieler) {
        return this.gehoert.equals(spieler);
    }

    public Feld getFreiesFeld() {
        for (Feld feld : felder) {
            if (!besetzteFelder.contains(feld)) {
                besetzteFelder.add(feld);
                return feld;
            }
        }
        throw new RuntimeException("Keine freien Felder vorhanden.");
    }
}
