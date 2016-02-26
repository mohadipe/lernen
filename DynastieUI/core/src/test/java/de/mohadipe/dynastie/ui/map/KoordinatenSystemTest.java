package de.mohadipe.dynastie.ui.map;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
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
    public void ermittleFeldAnhandWorldKoords() {
        Feld expectedFeld = new FeldImpl(38, 30);
        Vector3 worldKoords = new Vector3();
        worldKoords.x = 456;
        worldKoords.y = 357;
        worldKoords.z = 1;

        KoordinatenSystem koordinatenSystem = new KoordinatenSystem(properties);
        Feld feldAnKoordinaten = koordinatenSystem.getFeldByWorldKoords(worldKoords);
        Assert.assertThat(feldAnKoordinaten, CoreMatchers.equalTo(expectedFeld));
    }

    @Test
    public void ermittleEinheitPositionFuerFeld() {
        Vector2 erwartetePosition = new Vector2();
        erwartetePosition.x = 444;
        erwartetePosition.y = 336;
        Feld feld = new FeldImpl(38, 29);
        KoordinatenSystem koordinatenSystem = new KoordinatenSystem(properties);
        Vector2 actualPosition = koordinatenSystem.getPossitionFuerFeld(feld);
        Assert.assertThat(actualPosition, CoreMatchers.equalTo(erwartetePosition));
    }
}
