package de.mohadipe.dynastie.ui.controller;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector3;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import de.mohadipe.dynastie.ui.entities.Einheit;
import de.mohadipe.dynastie.ui.entities.Monk;

public class EinheitAuswaehlenControllerTest {

    @BeforeClass
    public static void initGdx() {
        HeadlessApplicationConfiguration headlessConfig = new HeadlessApplicationConfiguration();
        ApplicationListener applicationListener = Mockito.mock(ApplicationListener.class);
        HeadlessApplication headlessApplication = new HeadlessApplication(applicationListener, headlessConfig);
        Gdx.app = headlessApplication;
        Gdx.gl = Mockito.mock(GL20.class);
    }


    @Test
    public void einheitAusgewaehltXYderEinheitGecklickt() {
        Einheit einheit = new Monk(0,0);
        Vector3 gecklicktePosition = new Vector3();
        gecklicktePosition.x = einheit.getX();
        gecklicktePosition.y = einheit.getY();
        EinheitAuswaehlenController einheitAuswaehlenController = new EinheitAuswaehlenController(einheit, gecklicktePosition);
        Assert.assertTrue(einheitAuswaehlenController.isEinheitAusgeweahlt());
    }

    @Test
    public void einheitAusgewaehltFlaecheDerEinheitGecklickt() {
        Einheit einheit = new Monk(0,0);
        Vector3 gecklicktePosition = new Vector3();
        gecklicktePosition.x = 5;
        gecklicktePosition.y = 5;
        EinheitAuswaehlenController einheitAuswaehlenController = new EinheitAuswaehlenController(einheit, gecklicktePosition);
        Assert.assertTrue(einheitAuswaehlenController.isEinheitAusgeweahlt());
    }

    @Test
    public void einheitAusgewaehltAufRandDerEinheitGecklickt() {
        Einheit einheit = new Monk(0,0);
        Vector3 gecklicktePosition = new Vector3();
        gecklicktePosition.x = 12;
        gecklicktePosition.y = 12;
        EinheitAuswaehlenController einheitAuswaehlenController = new EinheitAuswaehlenController(einheit, gecklicktePosition);
        Assert.assertTrue(einheitAuswaehlenController.isEinheitAusgeweahlt());
    }

    @Test
    public void einheitNichtAusgewaehltAufMapGecklickt() {
        Einheit einheit = new Monk(0,0);
        Vector3 gecklicktePosition = new Vector3();
        gecklicktePosition.x = 13;
        gecklicktePosition.y = 13;
        EinheitAuswaehlenController einheitAuswaehlenController = new EinheitAuswaehlenController(einheit, gecklicktePosition);
        Assert.assertFalse(einheitAuswaehlenController.isEinheitAusgeweahlt());
    }

    @Test
    public void einheitNichtAusgewaehltAusserhalbMapGecklickt() {
        Einheit einheit = new Monk(0,0);
        Vector3 gecklicktePosition = new Vector3();
        gecklicktePosition.x = -1;
        gecklicktePosition.y = -1;
        EinheitAuswaehlenController einheitAuswaehlenController = new EinheitAuswaehlenController(einheit, gecklicktePosition);
        Assert.assertFalse(einheitAuswaehlenController.isEinheitAusgeweahlt());
    }

    @Test
    public void ermittleAusgewaehlteEinheitAusListeEineEinheitGecklickt() {
        List<Einheit> einheiten = new ArrayList<>();
        einheiten.add(new Monk(0,0));
        einheiten.add(new Monk(1,1));
        einheiten.add(new Monk(23,23));
        Vector3 gecklicktePosition = new Vector3();
        gecklicktePosition.x = 0;
        gecklicktePosition.y = 0;
        EinheitAuswaehlenController einheitAuswaehlenController = new EinheitAuswaehlenController(einheiten, gecklicktePosition);
        Einheit angecklickt = einheitAuswaehlenController.getGecklickteEinheit();
        Assert.assertNotNull(angecklickt);
    }
}
