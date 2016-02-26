package de.mohadipe.dynastie.ui.map;

import com.badlogic.gdx.maps.MapProperties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.mohadipe.dynastie.ui.entities.Spieler;

public class AufmarschZonenTest {

    private KoordinatenSystem koordinatenSystem;

    @Before
    public void setUp() {
        MapProperties properties = new MapProperties();
        properties.put("width", Integer.valueOf(70));
        properties.put("height", Integer.valueOf(70));
        properties.put("tilewidth", Integer.valueOf(12));
        properties.put("tileheight", Integer.valueOf(12));

        this.koordinatenSystem = new KoordinatenSystem(properties);
    }

    @Test
    public void initAufmarschZonen() {
        AufmarschZonen aufmarschZonen = new AufmarschZonen();
        aufmarschZonen.init(koordinatenSystem);
        Assert.assertEquals(2, aufmarschZonen.getAnzahlZonen());
    }

    @Test
    public void verbindeSpielerMitZone() {
        AufmarschZonen aufmarschZonen = new AufmarschZonen();
        aufmarschZonen.init(koordinatenSystem);
        aufmarschZonen.verbindeFreieZoneMitSpieler(new Spieler(1));
    }

    @Test(expected = RuntimeException.class)
    public void verbindeSpielerMitZoneNichtMoeglich() {
        AufmarschZonen aufmarschZonen = new AufmarschZonen();
        aufmarschZonen.verbindeFreieZoneMitSpieler(new Spieler(1));
    }

    @Test
    public void freiesFeld() {
        AufmarschZonen aufmarschZonen = new AufmarschZonen();
        aufmarschZonen.init(koordinatenSystem);
        Spieler spieler = new Spieler(1);
        aufmarschZonen.verbindeFreieZoneMitSpieler(spieler);
        Feld freiesFeld = aufmarschZonen.getFreiesFeld(spieler);
        Assert.assertNotNull(freiesFeld);
    }

    @Test(expected = RuntimeException.class)
    public void freiesFeldKeineFreienFelder() {
        AufmarschZonen aufmarschZonen = new AufmarschZonen();
        aufmarschZonen.init(koordinatenSystem);
        Spieler spieler = new Spieler(1);
        aufmarschZonen.verbindeFreieZoneMitSpieler(spieler);
        aufmarschZonen.getFreiesFeld(spieler);
        aufmarschZonen.getFreiesFeld(spieler);
        // Kann erstmal nur zwei Felder
        aufmarschZonen.getFreiesFeld(spieler);
    }

    @Test(expected = RuntimeException.class)
    public void freiesFeldKeineAufmarschzone() {
        AufmarschZonen aufmarschZonen = new AufmarschZonen();
        aufmarschZonen.init(koordinatenSystem);
        Spieler spieler = new Spieler(1);
        aufmarschZonen.verbindeFreieZoneMitSpieler(spieler);
        Spieler spieler2 = new Spieler(2);
        aufmarschZonen.getFreiesFeld(spieler2);
    }
}
