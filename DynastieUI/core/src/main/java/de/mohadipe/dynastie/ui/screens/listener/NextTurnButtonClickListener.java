package de.mohadipe.dynastie.ui.screens.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import de.mohadipe.dynastie.ui.DynastieUI;

public class NextTurnButtonClickListener extends ClickListener {
    private DynastieUI game;
    public NextTurnButtonClickListener(DynastieUI game) {
        this.game = game;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        this.game.nextTurn();
    }
}
