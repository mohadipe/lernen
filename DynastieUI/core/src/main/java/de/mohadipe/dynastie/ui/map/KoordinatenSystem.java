package de.mohadipe.dynastie.ui.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;

import de.mohadipe.dynastie.ui.entities.Spieler;

public class KoordinatenSystem {
    private int tilePixelWidth;
    private int tilePixelHeight;
    private int mapPixelWidth;
    private int mapPixelHeight;
    private int mapWidth;
    private int mapHeight;

    public KoordinatenSystem(MapProperties mapProperties) {
        mapWidth = mapProperties.get("width", Integer.class);
        mapHeight = mapProperties.get("height", Integer.class);
        tilePixelWidth = mapProperties.get("tilewidth", Integer.class);
        tilePixelHeight = mapProperties.get("tileheight", Integer.class);
        mapPixelWidth = mapWidth * tilePixelWidth;
        mapPixelHeight = mapHeight * tilePixelHeight;
    }

    public Vector2 getMitteDerMap() {
        Vector2 mitteDerMap = new Vector2();
        mitteDerMap.x = mapPixelWidth / 2;
        mitteDerMap.y = mapPixelHeight / 2;
        return mitteDerMap;
    }

    public Vector3 getWorldKoords(OrthographicCamera gameCamera, Vector2 screenKoords) {
        Vector3 positionWithZ = new Vector3();
        positionWithZ.x = screenKoords.x;
        positionWithZ.y = screenKoords.y + 10; // Die 10 wegen dem Rand oben und unten.
        positionWithZ.z = gameCamera.zoom;
        return gameCamera.unproject(positionWithZ);
    }

    public Feld getFeldByWorldKoords(Vector3 worldKoords) {
        float x = worldKoords.x / tilePixelWidth;
        float y = worldKoords.y / tilePixelHeight;
        return new FeldImpl(x, y);
    }

    public Vector2 getPossitionFuerFeld(Feld feld) {
        float x = feld.getX() * tilePixelWidth - tilePixelWidth;
        float y = feld.getY() * tilePixelHeight - tilePixelHeight;
        if (isKoordinateAufKarte(x,y)) {
            return new Vector2(x, y);
        } else {
            return null;
        }
    }

    private boolean isKoordinateAufKarte(float x, float y) {
        if ((x >= 0 && x <= mapPixelWidth)
            && (y >= 0 && y <= mapPixelHeight)) {
            return true;
        }
        return false;
    }

    public float getFeldLaengeX() {
        return tilePixelWidth;
    }

    public float getFeldLaengeY() {
        return tilePixelHeight;
    }

    public float getMaxX() {
        return mapPixelWidth;
    }

    public float getMaxY() {
        return mapPixelHeight;
    }
}
