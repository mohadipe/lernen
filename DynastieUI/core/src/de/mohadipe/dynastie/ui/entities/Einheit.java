package de.mohadipe.dynastie.ui.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Einheit extends Sprite implements IEinheit {

    private TextureAtlas textureAtlas;

    @Override
    public void draw(Batch batch) {
//        set(new Sprite(new Texture("ui/monk.png")));
//        setRegion(textureAtlas.findRegion("monk_front"));
//        draw(textureRegion, x?, y?, x, y, width, height, scaleX, scaleY, rotation)
//        rotation = eine art grad Angabe
//        width/height = mapWidth * tileWidth
        batch.draw(textureAtlas.findRegion("monk_front"), 0, 0, 300, 0, 100, 100, 0.1f, 0.1f, 0);
    }

    protected void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    @Override
    public void setPosition() {
        throw new UnsupportedOperationException();
    }
}
