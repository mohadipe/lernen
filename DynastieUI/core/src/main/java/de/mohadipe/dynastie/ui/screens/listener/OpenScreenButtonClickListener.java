package de.mohadipe.dynastie.ui.screens.listener;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class OpenScreenButtonClickListener extends ClickListener {
    private final Screen screen;

    public OpenScreenButtonClickListener(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        ((Game) Gdx.app.getApplicationListener()).setScreen(screen);
    }
}
