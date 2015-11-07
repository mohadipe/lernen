package de.mohadipe.dynastie.ui.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Einheit extends Sprite {

    private final TextureAtlas textureAtlas;

    public Einheit(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    @Override
    public void draw(Batch batch) {
        setPosition(50, 52);
        set(new Sprite(new Texture("ui/monk.png")));
        setRegion(textureAtlas.findRegion("monk_front"));
        setScale(0.01f,0.01f);
        super.draw(batch);
    }
}
