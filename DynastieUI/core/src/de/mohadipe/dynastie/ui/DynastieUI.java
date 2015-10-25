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
import de.mohadipe.dynastie.ui.screens.external.ISplash;
import de.mohadipe.dynastie.ui.screens.external.ScreensModul;

public class DynastieUI extends Game {
	public static final String TITLE = "Dynastie - Spiel der Meister";

	public OrthographicCamera camera;
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
		camera = new OrthographicCamera();
		viewport = new FillViewport(100,100, camera);
		viewport.apply();
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("fonts/white.fnt"), false);
		injector = Guice.createInjector(new ScreensModul());
		ISplash splash = injector.getInstance(ISplash.class);
		splash.setGame(this);
		setScreen(splash);
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
}
