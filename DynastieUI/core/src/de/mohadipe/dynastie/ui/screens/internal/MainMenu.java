package de.mohadipe.dynastie.ui.screens.internal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.mohadipe.dynastie.ui.DynastieUI;
import de.mohadipe.dynastie.ui.screens.listener.ExitButtonClickListener;
import de.mohadipe.dynastie.ui.screens.listener.OpenScreenButtonClickListener;

public class MainMenu implements de.mohadipe.dynastie.ui.screens.external.IMainMenu {
    private DynastieUI game;
    private Stage stage;
    private TextureAtlas textureAtlas;
    private Skin skin;
    private Table table;
    private TextButton exitButton;
    private TextButton playButton;
    private TextButton spielSetupButton;
    private Label heading;

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        textureAtlas = new TextureAtlas("ui/button.pack");
        skin = new Skin(textureAtlas);
        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        exitButton = createButton("EXIT", new ExitButtonClickListener());
        de.mohadipe.dynastie.ui.screens.external.ISpielSetupScreen spielSetup = game.injector.getInstance(de.mohadipe.dynastie.ui.screens.external.ISpielSetupScreen.class);
        spielSetup.setGame(game);
        spielSetupButton = createButton("Setup Game", new OpenScreenButtonClickListener(spielSetup));

        createHeading();

        table.add(heading);
        table.getCell(heading).spaceBottom(100);
        table.row();
        table.add(spielSetupButton);
        table.getCell(spielSetupButton).spaceBottom(20);
        table.row();
        table.add(exitButton);
        table.debug();
        stage.addActor(table);
    }

    private void createHeading() {
        Label.LabelStyle labelStyle = new Label.LabelStyle(game.font, Color.WHITE);
        heading = new Label(DynastieUI.TITLE, labelStyle);
        heading.setFontScale(2);
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

        game.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        stage.act(delta);
        stage.draw();
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
        stage.dispose();
        textureAtlas.dispose();
        skin.dispose();
    }

    @Override
    public void setGame(DynastieUI game) {
        this.game = game;
    }
}
