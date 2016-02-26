 package de.mohadipe.dynastie.ui.controller;

import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

import de.mohadipe.dynastie.ui.entities.Einheit;

public class EinheitAuswaehlenController {

    private Einheit gecklickteEinheit = null;
    private List<Einheit> einheitList;
    private final Vector3 gecklicktePosition;

    public EinheitAuswaehlenController(Einheit einheit, Vector3 gecklicktePosition) {
        einheitList = new ArrayList<>();
        einheitList.add(einheit);
        this.gecklicktePosition = gecklicktePosition;
        initGecklickteEinheit();
    }

    public EinheitAuswaehlenController(List<Einheit> einheiten, Vector3 gecklicktePosition) {
        this.gecklicktePosition = gecklicktePosition;
        this.einheitList = einheiten;
        initGecklickteEinheit();
    }

    private void initGecklickteEinheit() {
        for (Einheit einheit : einheitList) {
            if (einheit.getX() == gecklicktePosition.x &&
                    einheit.getY() == gecklicktePosition.y) {
                this.gecklickteEinheit = einheit;
                break;
            }
            if (einheit.getX() <= gecklicktePosition.x &&
                    gecklicktePosition.x <= (einheit.getX() + einheit.getWidth()) &&
                    einheit.getY() <= gecklicktePosition.y &&
                    gecklicktePosition.y <= (einheit.getY() + einheit.getHeight())) {
                this.gecklickteEinheit = einheit;
                break;
            }
        }
    }

    public boolean isEinheitAusgeweahlt() {
        return gecklickteEinheit != null;
    }

    public Einheit getGecklickteEinheit() {
        return gecklickteEinheit;
    }
}
