package de.mohadipe.dynastie.ui;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import de.mohadipe.dynastie.ui.entities.Spieler;

public class DynastieUITest {

    @Test
    public void naechsterSpieler() {
        DynastieUI dynastieUI = new DynastieUI();
        Spieler spieler = new Spieler(1);
        Spieler spieler2 = new Spieler(2);
        List<Spieler> spielerList = new ArrayList<>();
        spielerList.add(spieler);
        spielerList.add(spieler2);
        dynastieUI.setAktivenSpieler(spieler);
        dynastieUI.setSpielerListe(spielerList);
        dynastieUI.nextTurn();
        Spieler aktSpieler = dynastieUI.getAktiveSpieler();
        Assert.assertEquals(spieler2, aktSpieler);
    }

    @Test
    public void naechsterSpielerVonVorn() {
        DynastieUI dynastieUI = new DynastieUI();
        Spieler spieler = new Spieler(1);
        Spieler spieler2 = new Spieler(2);
        List<Spieler> spielerList = new ArrayList<>();
        spielerList.add(spieler);
        spielerList.add(spieler2);
        dynastieUI.setAktivenSpieler(spieler2);
        dynastieUI.setSpielerListe(spielerList);
        dynastieUI.nextTurn();
        Spieler aktSpieler = dynastieUI.getAktiveSpieler();
        Assert.assertEquals(spieler, aktSpieler);
    }
}
