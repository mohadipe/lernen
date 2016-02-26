package de.mohadipe.dynastie.ui.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.mohadipe.dynastie.ui.DynastieUI;
import de.mohadipe.dynastie.ui.entities.Einheit;
import de.mohadipe.dynastie.ui.entities.Spieler;
import de.mohadipe.dynastie.ui.map.Feld;

public class EinheitenController {
    private Einheit aktiveEinheit;
    private Map<Spieler, List<Einheit>> einheitenDerSpieler;

    public EinheitenController() {
        einheitenDerSpieler = new HashMap<>();
    }

    public void platziereEinheit(Feld freiesFeld, Class<? extends Einheit> einheitClass, Spieler sp) {
        try {
            Class<?> aClass = Class.forName(einheitClass.getName());
            Constructor<?> constructor = aClass.getConstructor(Float.class, Float.class);
            Einheit newInstance = (Einheit) constructor.newInstance((float) freiesFeld.getX(), (float) freiesFeld.getY());
            merkeEinheitDesSpielers(sp, newInstance);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void merkeEinheitDesSpielers(Spieler sp, Einheit newInstance) {
        List<Einheit> einheiten = einheitenDerSpieler.get(sp);
        if (einheiten == null) {
            einheiten = new ArrayList<>();
        }
        einheiten.add(newInstance);
        einheitenDerSpieler.put(sp, einheiten);
    }

    public void drawEinheiten(TiledMapRenderer renderer) {
        for (Spieler spieler : einheitenDerSpieler.keySet()) {
            List<Einheit> einheitenDesSpielers = einheitenDerSpieler.get(spieler);
            for (Einheit einheit : einheitenDesSpielers) {
                einheit.draw(((OrthogonalTiledMapRenderer) renderer).getBatch());
            }
        }
    }

    public List<Einheit> getEinheitenDesSpielers(Spieler aktiverSpieler) {
        return einheitenDerSpieler.get(aktiverSpieler);
    }

    public void setAktiveEinheit(Einheit aktiveEinheit) {
        this.aktiveEinheit = aktiveEinheit;
    }

    public void keineAktiveEinheit() {
        this.aktiveEinheit = null;
    }

    public boolean existAktiveEinheit() {
        return this.aktiveEinheit != null;
    }

    public void bewegeAktiveEinheit(Vector2 possitionFuerFeld) {
        this.aktiveEinheit.setX(possitionFuerFeld.x);
        this.aktiveEinheit.setY(possitionFuerFeld.y);
    }

    public void bewegungsReichweiteAktiverEinheitAnzeigen(DynastieUI game) {
        if (existAktiveEinheit()) {
            ShapeRenderer sr = new ShapeRenderer();
            sr.setProjectionMatrix(game.gameCamera.combined);
            sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(new Color(0, 0, 1, 0));
            sr.rect((aktiveEinheit.getX() - 12), (aktiveEinheit.getY() - 12), (3 * 12), (3 * 12));
            sr.end();
        }
    }
}
