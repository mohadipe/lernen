package de.mohadipe.dynastie.ui.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Monk extends Einheit {
    public Monk() {
        setTextureAtlas(new TextureAtlas("ui/monk.atlas"));
        set(new Sprite(new Texture("ui/monk.png")));
    }
}
