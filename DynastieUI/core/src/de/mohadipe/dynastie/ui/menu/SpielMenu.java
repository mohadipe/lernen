package de.mohadipe.dynastie.ui.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import de.mohadipe.dynastie.ui.DynastieUI;
import de.mohadipe.dynastie.ui.screens.listener.ExitButtonClickListener;

public class SpielMenu {

    DynastieUI game;
    private Label debugLabel;

    public SpielMenu(DynastieUI game){
        this.game = game;
    }

    public void addMenuToStage(Stage stage) {
        TextureAtlas buttonTextAtlas = new TextureAtlas("ui/button.pack");
        Skin skin = new Skin(buttonTextAtlas);
        TextButton exitButton = createButton("Exit", new ExitButtonClickListener(), skin);
        Label.LabelStyle labelStyle = new Label.LabelStyle(game.font, Color.WHITE);
        debugLabel = new Label("Debug Debug Debug", labelStyle);

        Table table = new Table(null);
        table.setBounds(200, 0, 100, 100);
        table.add(debugLabel);
        table.row();
        table.add(exitButton);
        stage.addActor(table);
    }

    public Label getDebugLabel() {
        return debugLabel;
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
}
