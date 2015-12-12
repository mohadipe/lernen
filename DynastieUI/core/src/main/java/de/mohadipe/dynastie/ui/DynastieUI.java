package de.mohadipe.dynastie.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.mohadipe.dynastie.ui.config.Config;
import de.mohadipe.dynastie.ui.screens.external.IMapSetupScreen;
import de.mohadipe.dynastie.ui.screens.external.ScreensModul;

public class DynastieUI extends Game {
	public static final String TITLE = "Dynastie - Spiel der Meister";

	public OrthographicCamera gameCamera;
	public OrthographicCamera menuCamera;
	public Viewport viewport;
	public SpriteBatch batch;
	public BitmapFont font;
	public Injector injector;
	private Config config;

//    private Config ladeKonfiguration() {
//        FileHandle fileHandle = Gdx.files.internal("config/konfiguration.json");
//        Json json = new Json();
//        return json.fromJson(Config.class, fileHandle.readString());
//    }
	
	@Override
	public void create () {
//        https://github.com/libgdx/libgdx/wiki/A-simple-game
		config = new Config();
		createGameCamera();
		createMenuCamera();
//		createViewPort();
//		viewport.apply();
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"), false);
		injector = Guice.createInjector(new ScreensModul());
//		ISplash screen = injector.getInstance(ISplash.class);
		IMapSetupScreen screen = injector.getInstance(IMapSetupScreen.class);
		screen.setGame(this);
		setScreen(screen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	private void createGameCamera() {
//		float w = Gdx.graphics.getWidth();
//		float h = Gdx.graphics.getHeight();
//		float aspectRatio = h / w;
//		gameCamera = new OrthographicCamera(100 * aspectRatio, 100);
//		gameCamera.translate(w/2, h/2);
		gameCamera = new OrthographicCamera();
	}

	private void createMenuCamera() {
		menuCamera = new OrthographicCamera();
	}

	private void createViewPort() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		viewport = new FillViewport(w, h, gameCamera);
		viewport.apply(true);
//		gameCamera.position.set(gameCamera.viewportWidth / 2f, gameCamera.viewportHeight / 2f, 0);
	}
}