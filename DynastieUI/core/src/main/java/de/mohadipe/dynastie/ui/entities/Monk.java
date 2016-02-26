package de.mohadipe.dynastie.ui.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Monk extends Einheit {

    private TextureRegion angezeigteRegion;

    public Monk(Float x, Float y) {
        setTextureAtlas(new TextureAtlas("ui/monk.atlas"));
        set(new Sprite(new Texture("ui/monk.png")));
        angezeigteRegion = getTextureAtlas().findRegion("monk_front");
        setX(0);
        setY(0);
        setOrigin(0, 0);
        setPosition(x, y);
        setSize(12, 12);
        setScale(1f, 1f);
        setRotation(0);
    }

    @Override
    protected TextureRegion getAngezeigteRegion() {
        return angezeigteRegion;
    }
}
