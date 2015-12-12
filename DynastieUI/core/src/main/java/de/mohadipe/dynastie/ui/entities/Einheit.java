package de.mohadipe.dynastie.ui.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public abstract class Einheit extends Sprite implements IEinheit {

    private TextureAtlas textureAtlas;
    private boolean isAktiv;

    @Override
    public void draw(Batch batch) {
//        draw(textureRegion, x?, y?, x, y, width, height, scaleX, scaleY, rotation)
//        rotation = eine art grad Angabe
//        width/height = mapWidth * tileWidth
        batch.draw(getAngezeigteRegion(), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    protected void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    protected TextureAtlas getTextureAtlas() {
        return this.textureAtlas;
    }

    protected abstract TextureRegion getAngezeigteRegion();

    @Override
    public void setPosition() {
        throw new UnsupportedOperationException();
    }

    public void setAktiv() {
        this.isAktiv = true;
    }

    @Override
    public boolean isAktiv() {
        return isAktiv;
    }

    @Override
    public void setInAktiv() {
        this.isAktiv = false;
    }

    @Override
    public Vector3 getKoordinaten() {
        return new Vector3(getX(), getY(), 0);
    }
}
