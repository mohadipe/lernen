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
import de.mohadipe.dynastie.ui.screens.listener.NextTurnButtonClickListener;

public class SpielMenu {

    DynastieUI game;
    private Label console;

    public SpielMenu(DynastieUI game){
        this.game = game;
    }

    public Table getMainMenu() {
        TextureAtlas buttonTextAtlas = new TextureAtlas("ui/button.pack");
        Skin skin = new Skin(buttonTextAtlas);

        Table main = new Table(skin);
        main.setFillParent(true);
        main.debug();

        TextButton exitButton = createButton("Exit", new ExitButtonClickListener(), skin);
        TextButton nextTurnButton = createButton("Next Turn", new NextTurnButtonClickListener(game), skin);

        Table t = new Table(skin);
        Label.LabelStyle labelStyle = new Label.LabelStyle(game.font, Color.WHITE);
        console = new Label("Label2", labelStyle);
        t.add(console);
        main.add(t).expandX().height(20);
        main.row();
        main.add().expand();
        main.row();
        Table b = new Table(skin);
        b.add(nextTurnButton);
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

    public Label getConsole() {
        return console;
    }
}
