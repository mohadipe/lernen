package de.mohadipe.dynastie.ui.screens.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

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
import de.mohadipe.dynastie.ui.screens.listener.ExitButtonClickListener;
import de.mohadipe.dynastie.ui.screens.listener.NextTurnButtonClickListener;

public class MapSetupScreen implements IMapSetupScreen {

    private DynastieUI game;
    private Music marschMusic;
    private TiledMap tiledMap;
    private TiledMapRenderer renderer;
    private KoordinatenSystem koordinatenSystem;

    private Skin skin;
    private Stage stage;

    private int[] background = new int[]{0}, foreground = new int[]{1};
//    private SpielMenu menu;
    private GameCameraBewegung cameraBewegung;
    private EinheitInteraktion einheitInteraktion;

    @Override
    public void show() {
//        marschMusic = Gdx.audio.newMusic(Gdx.files.internal(("sounds/military-march-intro2.wav")));
//
//        marschMusic.setLooping(true);
//        marschMusic.play();
//        https://github.com/libgdx/libgdx/wiki/Tile-maps
//        https://www.youtube.com/watch?v=DOpqkaX9844
        TextureAtlas buttonTextAtlas = new TextureAtlas("ui/button.pack");
        skin = new Skin(buttonTextAtlas);

        stage = new InputProcessor();
        stage.setViewport(new ScreenViewport());
        stage.setDebugAll(true);
        cameraBewegung = new GameCameraBewegung(game.gameCamera);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        game.gameCamera.setToOrtho(false, (w / (h - 40)) * 10, 10);
        game.gameCamera.update();
        Gdx.input.setInputProcessor(stage);

        final String einfach_ortho_map = "maps/einfach_ortho_map.tmx";
        tiledMap = new TmxMapLoader().load(einfach_ortho_map);
        float unitScale = 1/32f;
        renderer = new OrthogonalTiledMapRenderer(tiledMap); //, unitScale);
        MapProperties properties = tiledMap.getProperties();
        // https://github.com/libgdx/libgdx/wiki/Tile-maps
        // Mit unitScale kann ich mir vielleicht das umgerechne sparen.
        koordinatenSystem = new KoordinatenSystem(properties);
        platziereEinheiten();
        einheitInteraktion = new EinheitInteraktion(game, koordinatenSystem);

        Table main = getMainMenu();

        stage.addActor(main);

    }

    private Table getMainMenu() {
        Table main = new Table(skin);
        main.setFillParent(true);
        main.debug();

        TextureAtlas buttonTextAtlas = new TextureAtlas("ui/button.pack");
        Skin skin = new Skin(buttonTextAtlas);
        TextButton exitButton = createButton("Exit", new ExitButtonClickListener(), skin);
        TextButton nextTurnButton = createButton("Next Turn", new NextTurnButtonClickListener(game), skin);

        Table t = new Table(skin);
        Label.LabelStyle labelStyle = new Label.LabelStyle(game.font, Color.WHITE);
        t.add(nextTurnButton);
        t.add(new Label("Label2", labelStyle));
        main.add(t).expandX().height(20);
        main.row();
        main.add().expand();
        main.row();
        Table b = new Table(skin);
        b.add(new Label("Label3", labelStyle));
        b.add(exitButton);
        main.add(b).expandX().height(20);
        return main;
    }

    private TextButton createButton(String text, EventListener buttonclickListener, Skin skin) {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up.patch");
        textButtonStyle.down = skin.getDrawable("button.down.patch");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = game.font;
        textButtonStyle.fontColor = Color.BLACK;
        TextButton tmpButton = new TextButton(text, textButtonStyle);
        tmpButton.addListener(buttonclickListener);
        tmpButton.pad(20);
        return tmpButton;
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
//        Gdx.gl.glClearColor(100f / 255f, 100f / 255f, 250f / 255f, 1f); // blauer Hintergrund
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // http://badlogicgames.com/forum/viewtopic.php?f=11&t=3638
        // Fog of War
//        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
//        Gdx.gl.glDepthFunc(GL20.GL_ALWAYS);
//        Gdx.gl.glDepthMask(true);

        handleInput((InputProcessor) Gdx.input.getInputProcessor());

        // Prepare for stage drawing by updating the viewport
//        game.menuCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        game.menuCamera.update();
//        game.batch.setProjectionMatrix(game.gameCamera.combined);
//        game.batch.begin();
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.act();
        stage.draw();
//        game.batch.end();

        // Prepare for embedded map drawing by applying the desired viewport for the map
        Gdx.gl.glViewport(0, 20, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight() - 40);
        game.gameCamera.update();
        renderer.setView(game.gameCamera);
        renderer.render();
        renderer.render(background);
        ((OrthogonalTiledMapRenderer) renderer).getBatch().begin();
        game.getEinheitenController().drawEinheiten(renderer);
        ((OrthogonalTiledMapRenderer) renderer).getBatch().end();
        renderer.render(foreground);

        game.getEinheitenController().bewegungsReichweiteAktiverEinheitAnzeigen(game);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
//        game.gameCamera.viewportWidth = width / 2;
//        game.gameCamera.viewportHeight = height / 2;
//        Vector2 position = koordinatenSystem.getMitteDerMap();
//        game.gameCamera.position.set(position.x, position.y, 0);
//        game.gameCamera.update();
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
