package de.mohadipe.dynastie.ui.screens.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.mohadipe.dynastie.ui.DynastieUI;
import de.mohadipe.dynastie.ui.controler.EinheitAuswaehlenController;
import de.mohadipe.dynastie.ui.entities.Einheit;
import de.mohadipe.dynastie.ui.entities.Monk;
import de.mohadipe.dynastie.ui.input.GameCameraBewegung;
import de.mohadipe.dynastie.ui.input.InputProcessor;
import de.mohadipe.dynastie.ui.map.KoordinatenSystem;
import de.mohadipe.dynastie.ui.menu.SpielMenu;
import de.mohadipe.dynastie.ui.screens.external.IMapSetupScreen;
import de.mohadipe.dynastie.ui.screens.listener.ExitButtonClickListener;
import javafx.application.Platform;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

public class MapSetupScreen implements IMapSetupScreen {

    private DynastieUI game;
    private Music marschMusic;
    private TiledMap tiledMap;
    private TiledMapRenderer renderer;
    private KoordinatenSystem koordinatenSystem;

    private Stage stage;

    private float rotationSpeed;
    private int[] background = new int[] {0}, foreground = new int[] {1};
    private Einheit einheit;
    private SpielMenu menu;
    private GameCameraBewegung cameraBewegung;

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
        einheit = new Monk();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput((InputProcessor) Gdx.input.getInputProcessor());

        game.gameCamera.update();
        renderer.setView(game.gameCamera);
        renderer.render(background);
        ((OrthogonalTiledMapRenderer)renderer).getBatch().begin();
        einheit.draw(((OrthogonalTiledMapRenderer) renderer).getBatch());
        ((OrthogonalTiledMapRenderer)renderer).getBatch().end();
        renderer.render(foreground);

        game.menuCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.menuCamera.update();
        game.batch.setProjectionMatrix(game.menuCamera.combined);
        game.batch.begin();
        stage.act(delta);
        stage.draw();
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        game.gameCamera.viewportWidth = width/2;
        game.gameCamera.viewportHeight = height/2;
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

        if (processor.isLeftMouseClicked()) {
            Vector2 screenKoords = processor.getClickedMousePosition();
            menu.getDebugLabel().setText("Mouse geklickt an X: " + screenKoords.x + " und Y: " + screenKoords.y);
            // TODO gecklickte Position auf Map Koordinaten umrechnen.
            Vector3 worldKoords = koordinatenSystem.getWorldKoords(game.gameCamera, screenKoords);
            // Herausfinden ob eine Einheit angeklickt wurde.
            if (new EinheitAuswaehlenController(einheit, worldKoords).isEinheitAusgeweahlt()) {
                // Einheit ist "Aktiv"
                // Ist an der geclickten Position eine Einheit wird diese aktiviert.
                einheit.setAktiv();
            } else {
                // Wenn erneut geklickt wird und eine Einheit aktiv ist soll sich die aktive Einheit dort hin bewegen.
                // ermittle geklicktes Feld
                // positioniere Einheit in Feld
                if (einheit.isAktiv()) {
                    einheit.setX(worldKoords.x);
                    einheit.setY(worldKoords.y);
                }
                // TODO Check via Logik-Lib ob Bewegung erlaubt ist.
            }
            processor.resetLeftMouseClick();
        }
        if (processor.isRightMouseClicked()) {
            einheit.setInAktiv();
            // TODO later Kontextmenu an Einheit.
            processor.resetRightMouseClick();
        }

        // TODO Grenzen für scrollen und zoomen müssen noch implementiert werden
//        game.gameCamera.zoom = MathUtils.clamp(game.gameCamera.zoom, 0.1f, 100 / game.gameCamera.viewportWidth);

//        float effectiveViewportWidth = game.gameCamera.viewportWidth * game.gameCamera.zoom;
//        float effectiveViewportHeight = game.gameCamera.viewportHeight * game.gameCamera.zoom;
//
//        game.gameCamera.position.x = MathUtils.clamp(game.gameCamera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
//        game.gameCamera.position.y = MathUtils.clamp(game.gameCamera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }
}
