package de.mohadipe.dynastie.ui.screens.internal;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import de.mohadipe.dynastie.ui.Assets;
import de.mohadipe.dynastie.ui.DynastieUI;
import de.mohadipe.dynastie.ui.tween.SpriteAccessor;

public class Splash implements de.mohadipe.dynastie.ui.screens.external.ISplash {

    private DynastieUI game;
    private Sprite splash;
    private TweenManager tweenManager;



    @Override
    public void show() {
        tweenManager = new TweenManager();

        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Texture texture = new Texture(Assets.SPLASH_GRAFIK);
        splash = new Sprite(texture);
        splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        float startWert = 0;
        float zielWert = 1;
        Tween.set(splash, SpriteAccessor.ALPHA).target(startWert).start(tweenManager);
        Tween.to(splash, SpriteAccessor.ALPHA, 2).target(zielWert).repeatYoyo(1, 0.5f).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                de.mohadipe.dynastie.ui.screens.external.IMainMenu mainMenu = game.injector.getInstance(de.mohadipe.dynastie.ui.screens.external.IMainMenu.class);
                mainMenu.setGame(game);
                ((Game) Gdx.app.getApplicationListener()).setScreen(mainMenu);
            }
        }).start(tweenManager);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        tweenManager.update(delta);

        game.batch.begin();
        splash.draw(game.batch);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        splash.getTexture().dispose();
    }

    @Override
    public void setGame(DynastieUI dynastieUI) {
        this.game = dynastieUI;
    }
}
