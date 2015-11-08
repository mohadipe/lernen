package de.mohadipe.dynastie.ui.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class Einheit extends Sprite {

    private final TextureAtlas textureAtlas;

    public Einheit(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    @Override
    public void draw(Batch batch) {
        set(new Sprite(new Texture("ui/monk.png")));
//        setRegion(textureAtlas.findRegion("monk_front"));
//        draw(textureRegion, x?, y?, x, y, width, height, scaleX, scaleY, rotation)
//        rotation = eine art grad Angabe
//        width/height = mapWidth * tileWidth
        batch.draw(textureAtlas.findRegion("monk_front"), 0, 0, 300, 0, 100, 100, 0.1f, 0.1f, 0);
    }
}
