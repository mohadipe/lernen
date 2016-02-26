package de.mohadipe.dynastie.ui.screens.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.mohadipe.dynastie.ui.DynastieUI;
import de.mohadipe.dynastie.ui.controller.EinheitenController;
import de.mohadipe.dynastie.ui.entities.Monk;
import de.mohadipe.dynastie.ui.entities.Spieler;
import de.mohadipe.dynastie.ui.input.EinheitInteraktion;
import de.mohadipe.dynastie.ui.input.GameCameraBewegung;
import de.mohadipe.dynastie.ui.input.InputProcessor;
import de.mohadipe.dynastie.ui.map.AufmarschZonen;
import de.mohadipe.dynastie.ui.map.Feld;
import de.mohadipe.dynastie.ui.map.KoordinatenSystem;
import de.mohadipe.dynastie.ui.menu.SpielMenu;
import de.mohadipe.dynastie.ui.screens.external.IMapSetupScreen;

public class MapSetupScreen implements IMapSetupScreen {

    private DynastieUI game;
    private Music marschMusic;
    private TiledMap tiledMap;
    private TiledMapRenderer renderer;
    private KoordinatenSystem koordinatenSystem;

    private Stage stage;

    private int[] background = new int[]{0}, foreground = new int[]{1};
    private SpielMenu menu;
    private GameCameraBewegung cameraBewegung;
    private EinheitInteraktion einheitInteraktion;

    @Override
    public void show() {
        stage = new InputProcessor();
        Gdx.input.setInputProcessor(stage);
        cameraBewegung = new GameCameraBewegung(game.gameCamera);
        menu = new SpielMenu(this.game);
        menu.addMenuToStage(stage);
//        marschMusic = Gdx.audio.newMusic(Gdx.files.internal(("sounds/military-march-intro2.wav")));
//
//        marschMusic.setLooping(true);
//        marschMusic.play();

        final String einfach_ortho_map = "maps/einfach_ortho_map.tmx";
        tiledMap = new TmxMapLoader().load(einfach_ortho_map);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
//        https://github.com/libgdx/libgdx/wiki/Tile-maps
//        https://www.youtube.com/watch?v=DOpqkaX9844
        MapProperties properties = tiledMap.getProperties();
        koordinatenSystem = new KoordinatenSystem(properties);
        koordinatenSystem.insertDebugInfoInto(menu.getDebugLabel());
        platziereEinheiten();
        einheitInteraktion = new EinheitInteraktion(game, menu, koordinatenSystem);
    }

    private void platziereEinheiten() {
        AufmarschZonen aufmarschZonen = new AufmarschZonen();
        aufmarschZonen.init(koordinatenSystem);
        EinheitenController einheitenController = game.getEinheitenController();
        for (Spieler sp : game.getSpielerListe()) {
            aufmarschZonen.verbindeFreieZoneMitSpieler(sp);
            Feld freiesFeld = aufmarschZonen.getFreiesFeld(sp);
            einheitenController.platziereEinheit(freiesFeld, Monk.class, sp);
            Feld freiesFeld2 = aufmarschZonen.getFreiesFeld(sp);
            einheitenController.platziereEinheit(freiesFeld2, Monk.class, sp);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // http://badlogicgames.com/forum/viewtopic.php?f=11&t=3638
        // Fog of War
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glDepthFunc(GL20.GL_ALWAYS);
        Gdx.gl.glDepthMask(true);

        handleInput((InputProcessor) Gdx.input.getInputProcessor());

        game.gameCamera.update();
        renderer.setView(game.gameCamera);
        renderer.render(background);
        ((OrthogonalTiledMapRenderer) renderer).getBatch().begin();
        game.getEinheitenController().drawEinheiten(renderer);
        ((OrthogonalTiledMapRenderer) renderer).getBatch().end();
        renderer.render(foreground);

        game.menuCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.menuCamera.update();
        game.batch.setProjectionMatrix(game.menuCamera.combined);
        game.batch.begin();
        stage.act(delta);
        stage.draw();
        game.batch.end();

        game.getEinheitenController().bewegungsReichweiteAktiverEinheitAnzeigen(game);
    }

    @Override
    public void resize(int width, int height) {
        game.gameCamera.viewportWidth = width / 2;
        game.gameCamera.viewportHeight = height / 2;
        Vector2 position = koordinatenSystem.getMitteDerMap();
        game.gameCamera.position.set(position.x, position.y, 0);
        game.gameCamera.update();
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

    private void handleInput(InputProcessor processor) {
        cameraBewegung.handleInput(processor);

        einheitInteraktion.handleInput(processor);
    }
}
