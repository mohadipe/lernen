package de.mohadipe.dynastie.ui.map;

import java.util.ArrayList;
import java.util.List;

import de.mohadipe.dynastie.ui.entities.Spieler;

public class AufmarschZonen {
    private int anzahlZonen;
    private List<AufmarschZone> aufmarschzonen = new ArrayList<>();

    public void init(KoordinatenSystem koordinatenSystem) {
        float feldLaengeX = koordinatenSystem.getFeldLaengeX();
        float feldLaengeY = koordinatenSystem.getFeldLaengeY();
        float maxX = koordinatenSystem.getMaxX();
        float maxY = koordinatenSystem.getMaxY();
        AufmarschZone aufmarschZone = new AufmarschZone();
        aufmarschZone.addFeld(new FeldImpl(0, 0));
        aufmarschZone.addFeld(new FeldImpl(0, feldLaengeX));
        aufmarschzonen.add(aufmarschZone);
        AufmarschZone aufmarschZone2 = new AufmarschZone();
        aufmarschZone2.addFeld(new FeldImpl(maxX - feldLaengeX, maxY - feldLaengeY));
        aufmarschZone2.addFeld(new FeldImpl(maxX - feldLaengeX, maxY - feldLaengeY*2));
        aufmarschzonen.add(aufmarschZone2);
    }

    public int getAnzahlZonen() {
        return aufmarschzonen.size();
    }

    public void verbindeFreieZoneMitSpieler(Spieler spieler) {
        for (AufmarschZone zone : aufmarschzonen) {
            if(zone.isFrei()) {
                zone.verbindeMit(spieler);
                return;
            }
        }
        throw new RuntimeException("Keine freie AufmarschZone zum verbinden mit Spieler vorhanden.");
    }

    public Feld getFreiesFeld(Spieler spieler) {
        for (AufmarschZone zone : aufmarschzonen) {
            if (zone.isSpielerZone(spieler)) {
                return zone.getFreiesFeld();
            }
        }
        throw new RuntimeException("Der Spieler hat keine Aufmarschzone.");
    }
}
