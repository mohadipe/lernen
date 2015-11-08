package de.mohadipe.dynastie.ui.map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

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

    public void insertDebugInfoInto(Label debug) {
        debug.setText("MapWith: " + mapHeight + " MapHeight: " + mapHeight + "\n TilePixelWidth: " + tilePixelWidth + " TilePixelHeight: " + tilePixelHeight
                + "\n MapPixelWidth: " + mapPixelWidth + " MapPixelHeight: " + mapPixelHeight);
    }
}
