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
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.mohadipe.dynastie.ui.DynastieUI;
import de.mohadipe.dynastie.ui.entities.Einheit;
import de.mohadipe.dynastie.ui.screens.external.IMapSetupScreen;
import de.mohadipe.dynastie.ui.screens.listener.ExitButtonClickListener;
import javafx.application.Platform;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;

public class MapSetupScreen implements IMapSetupScreen {

    private DynastieUI game;
    private Music marschMusic;
    private TiledMap tiledMap;
    private TiledMapRenderer renderer;

    private TextureAtlas textureAtlas;
    private Skin skin;
    private TextButton exitButton;
    private Stage stage;

    private float rotationSpeed;
    private int[] background = new int[] {0}, foreground = new int[] {1};
    private Einheit einheit;
    private int tilePixelWidth;
    private int tilePixelHeight;
    private int mapPixelWidth;
    private int mapPixelHeight;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        rotationSpeed = 0.5f;
        textureAtlas = new TextureAtlas("ui/button.pack");
        skin = new Skin(textureAtlas);
        exitButton = createButton("Exit", new ExitButtonClickListener());
        Label.LabelStyle labelStyle = new Label.LabelStyle(game.font, Color.WHITE);
        Label debug = new Label("Debug Debug Debug", labelStyle);

        Table table = new Table(null);
        table.setBounds(200, 0, 100, 100);
        table.add(debug);
        table.row();
        table.add(exitButton);
        stage.addActor(table);
//        marschMusic = Gdx.audio.newMusic(Gdx.files.internal(("sounds/military-march-intro2.wav")));
//
//        marschMusic.setLooping(true);
//        marschMusic.play();

//        game.gameCamera.setToOrtho(false, 2000, 1000);
//        final String map_gruen = "maps/gruen.tmx";
        final String einfach_ortho_map = "maps/einfach_ortho_map.tmx";
        tiledMap = new TmxMapLoader().load(einfach_ortho_map);
//        renderer = new IsometricTiledMapRenderer(tiledMap);
        renderer = new OrthogonalTiledMapRenderer(tiledMap);
//        https://github.com/libgdx/libgdx/wiki/Tile-maps
//        https://www.youtube.com/watch?v=DOpqkaX9844
        MapProperties properties = tiledMap.getProperties();
        int mapWidth = properties.get("width", Integer.class);
        int mapHeight = properties.get("height", Integer.class);
        tilePixelWidth = properties.get("tilewidth", Integer.class);
        tilePixelHeight = properties.get("tileheight", Integer.class);

        mapPixelWidth = mapWidth * tilePixelWidth;
        mapPixelHeight = mapHeight * tilePixelHeight;

        debug.setText("MapWith: " + mapHeight + " MapHeight: " + mapHeight + "\n TilePixelWidth: " + tilePixelWidth + " TilePixelHeight: " + tilePixelHeight
            + "\n MapPixelWidth: " + mapPixelWidth + " MapPixelHeight: " + mapPixelHeight);
        einheit = new Einheit(new TextureAtlas("ui/monk.atlas"));
    }

    private TextButton createButton(String text, EventListener buttonclickListener) {
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


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();

        game.gameCamera.update();
        renderer.setView(game.gameCamera);
        renderer.render(background);
        ((OrthogonalTiledMapRenderer)renderer).getBatch().begin();
        einheit.draw(((OrthogonalTiledMapRenderer) renderer).getBatch());
        ((OrthogonalTiledMapRenderer)renderer).getBatch().end();
        renderer.render(foreground);

//        ShapeRenderer shapeRenderer = new ShapeRenderer();
//        shapeRenderer.setProjectionMatrix(game.gameCamera.combined);
//        shapeRenderer.begin(Line);
//        for(int x = 0; x < mapPixelWidth; x += tilePixelWidth)
//            shapeRenderer.line(x, 0, x, mapPixelHeight);
//        for(int y = 0; y < mapPixelHeight; y += tilePixelHeight)
//            shapeRenderer.line(0, y, mapPixelWidth, y);
//        shapeRenderer.end();

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
//        game.viewport.update(width, height);
        game.gameCamera.viewportWidth = width/2;
        game.gameCamera.viewportHeight = height/2;
        game.gameCamera.position.set(400, 0, 0);
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

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            game.gameCamera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            game.gameCamera.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            game.gameCamera.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            game.gameCamera.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            game.gameCamera.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            game.gameCamera.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            game.gameCamera.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            game.gameCamera.rotate(rotationSpeed, 0, 0, 1);
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
