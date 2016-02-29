package de.mohadipe.dynastie.ui;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import de.mohadipe.dynastie.ui.entities.Spieler;

public class DynastieUITest {

    private DynastieUI dynastieUI = new DynastieUI();

    @Before
    public void setUp() {
        dynastieUI.initSpieler();
    }

    @Test
    public void naechsterSpieler() {
        dynastieUI.nextTurn();
        Spieler aktSpieler = dynastieUI.getAktiveSpieler();
        Assert.assertNotNull(aktSpieler);
    }

    @Test
    public void naechsterSpielerNichtDerGleiche() {
        dynastieUI.nextTurn();
        Spieler aktSpieler = dynastieUI.getAktiveSpieler();
        Assert.assertNotNull(aktSpieler);
        dynastieUI.nextTurn();
        Spieler naechsterSpieler = dynastieUI.getAktiveSpieler();
        Assert.assertNotEquals(aktSpieler, naechsterSpieler);
    }

    @Test
    public void naechsterSpielerVonVorn() {
        dynastieUI.nextTurn();
        Spieler aktSpieler = dynastieUI.getAktiveSpieler();
        Assert.assertNotNull(aktSpieler);
        dynastieUI.nextTurn();
        Spieler naechsterSpieler = dynastieUI.getAktiveSpieler();
        Assert.assertNotEquals(aktSpieler, naechsterSpieler);
        dynastieUI.nextTurn();
        Spieler ersterSpieler = dynastieUI.getAktiveSpieler();
        Assert.assertEquals(aktSpieler, ersterSpieler);
    }
}
