package de.mohadipe.dynastie.ui.screens.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;

import de.mohadipe.dynastie.ui.DynastieUI;
import de.mohadipe.dynastie.ui.screens.external.IMapSetupScreen;

public class MapSetupScreen implements IMapSetupScreen {

    private DynastieUI game;
    private Music marschMusic;
    private TiledMap tiledMap;
    private TiledMapRenderer renderer;

    private float rotationSpeed;

    @Override
    public void show() {
        rotationSpeed = 0.5f;

//        marschMusic = Gdx.audio.newMusic(Gdx.files.internal(("sounds/military-march-intro2.wav")));
//
//        marschMusic.setLooping(true);
//        marschMusic.play();

//        game.camera.setToOrtho(false, 2000, 1000);

        tiledMap = new TmxMapLoader().load("maps/gruen.tmx");
        renderer = new IsometricTiledMapRenderer(tiledMap);
//        https://github.com/libgdx/libgdx/wiki/Tile-maps
//        https://www.youtube.com/watch?v=DOpqkaX9844
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();
        game.camera.update();

        renderer.setView(game.camera);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {
//        game.viewport.update(width, height);
        game.camera.viewportWidth = width/2;
        game.camera.viewportHeight = height/2;
        game.camera.position.set(400, 0, 0);
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

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            game.camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            game.camera.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            game.camera.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            game.camera.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            game.camera.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            game.camera.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            game.camera.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            game.camera.rotate(rotationSpeed, 0, 0, 1);
        }

        // TODO Grenzen für scrollen und zoomen müssen noch implementiert werden
//        game.camera.zoom = MathUtils.clamp(game.camera.zoom, 0.1f, 100 / game.camera.viewportWidth);

//        float effectiveViewportWidth = game.camera.viewportWidth * game.camera.zoom;
//        float effectiveViewportHeight = game.camera.viewportHeight * game.camera.zoom;
//
//        game.camera.position.x = MathUtils.clamp(game.camera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
//        game.camera.position.y = MathUtils.clamp(game.camera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }
}
