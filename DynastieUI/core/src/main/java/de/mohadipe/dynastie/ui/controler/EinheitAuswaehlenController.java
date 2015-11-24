package de.mohadipe.dynastie.ui.controler;

import com.badlogic.gdx.math.Vector2;

import de.mohadipe.dynastie.ui.entities.Einheit;

public class EinheitAuswaehlenController {

    private final Einheit einheit;
    private final Vector2 gecklicktePosition;

    public EinheitAuswaehlenController(Einheit einheit, Vector2 gecklicktePosition) {
        this.einheit = einheit;
        this.gecklicktePosition = gecklicktePosition;
    }

    public boolean isEinheitAusgeweahlt() {

        return false;
    }
}
