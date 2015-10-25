package de.mohadipe.dynastie.ui.screens.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

import de.mohadipe.dynastie.ui.DynastieUI;

public class MapSetupScreen implements de.mohadipe.dynastie.ui.screens.external.IMapSetupScreen {
    private DynastieUI game;
    private Music marschMusic;
    private TiledMap tiledMap;
    private TiledMapRenderer renderer;

    @Override
    public void show() {
        marschMusic = Gdx.audio.newMusic(Gdx.files.internal(("sounds/military-march-intro2.wav")));

        marschMusic.setLooping(true);
        marschMusic.play();

        tiledMap = new TmxMapLoader().load("maps/gruen.tmx");
        renderer = new IsometricTiledMapRenderer(tiledMap);
//        https://github.com/libgdx/libgdx/wiki/Tile-maps
//        https://www.youtube.com/watch?v=DOpqkaX9844
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setView(game.camera);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
        game.camera.update();
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
        marschMusic.dispose();
        tiledMap.dispose();
    }

    @Override
    public void setGame(DynastieUI game) {
        this.game = game;
    }
}
