package de.mohadipe.dynastie.ui.entities;

import com.badlogic.gdx.math.Vector3;

public interface IEinheit {

    public void setPosition();

    void setAktiv();

    boolean isAktiv();

    void setInAktiv();

    Vector3 getKoordinaten();
}
