package de.mohadipe.dynastie.ui.controler;

import com.badlogic.gdx.math.Vector3;

import de.mohadipe.dynastie.ui.entities.Einheit;

public class EinheitAuswaehlenController {

    private final Einheit einheit;
    private final Vector3 gecklicktePosition;

    public EinheitAuswaehlenController(Einheit einheit, Vector3 gecklicktePosition) {
        this.einheit = einheit;
        this.gecklicktePosition = gecklicktePosition;
    }

    public boolean isEinheitAusgeweahlt() {
        if (einheit.getX() == gecklicktePosition.x &&
                einheit.getY() == gecklicktePosition.y) {
            return true;
        }
        if (einheit.getX() <= gecklicktePosition.x &&
                gecklicktePosition.x <= (einheit.getX() + einheit.getWidth()) &&
                einheit.getY() <= gecklicktePosition.y &&
                gecklicktePosition.y <= (einheit.getY() + einheit.getHeight())) {
            return true;
        }
        return false;
    }
}
