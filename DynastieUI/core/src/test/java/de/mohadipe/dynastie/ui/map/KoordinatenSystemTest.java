package de.mohadipe.dynastie.ui.map;

import com.badlogic.gdx.maps.MapProperties;

import org.junit.BeforeClass;
import org.junit.Test;

public class KoordinatenSystemTest {
    private float screenWidth = 1024;
    private float screenHeight = 864;
    private static MapProperties properties;

    @BeforeClass
    public static void beforeClass() {
        properties = new MapProperties();
        properties.put("width", Integer.valueOf(70));
        properties.put("height", Integer.valueOf(70));
        properties.put("tilewidth", Integer.valueOf(12));
        properties.put("tileheight", Integer.valueOf(12));
    }

    @Test
    public void wandleCameraKoordsInMapKoords() {
        // TODO
    }
}
