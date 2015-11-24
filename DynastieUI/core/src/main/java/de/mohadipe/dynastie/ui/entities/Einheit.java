package de.mohadipe.dynastie.ui.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Einheit extends Sprite implements IEinheit {

    private TextureAtlas textureAtlas;

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
}
