package de.mohadipe.dynastie.ui.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import de.mohadipe.dynastie.ui.DynastieUI;

public class InputProcessor extends Stage {
    private boolean zoomingOut;
    private boolean zoomingIn;
    private boolean gameCamMoveLeft;
    private boolean gameCamMoveRight;
    private boolean gameCamMoveDown;
    private boolean gameCamMoveUp;
    private boolean gameCamRotateAgainstClock;
    private boolean gameCamRotateWithClock;
    private boolean leftMouseClicked;
    private Vector2 clickedMousePosition;
    private boolean rightMouseClicked;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) {
            this.zoomingOut = true;
        }
        if (keycode == Input.Keys.Q) {
            this.zoomingIn = true;
        }
        if (keycode == Input.Keys.LEFT) {
            this.gameCamMoveLeft = true;
        }
        if (keycode == Input.Keys.RIGHT) {
            this.gameCamMoveRight = true;
        }
        if (keycode == Input.Keys.DOWN) {
            this.gameCamMoveDown = true;
        }
        if (keycode == Input.Keys.UP) {
            this.gameCamMoveUp = true;
        }
        if (keycode == Input.Keys.W) {
            this.gameCamRotateAgainstClock = true;
        }
        if (keycode == Input.Keys.E) {
            this.gameCamRotateWithClock = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keyCode) {
        if (keyCode == Input.Keys.A) {
            this.zoomingOut = false;
        }
        if (keyCode == Input.Keys.Q) {
            this.zoomingIn = false;
        }
        if (keyCode == Input.Keys.LEFT) {
            this.gameCamMoveLeft = false;
        }
        if (keyCode == Input.Keys.RIGHT) {
            this.gameCamMoveRight = false;
        }
        if (keyCode == Input.Keys.DOWN) {
            this.gameCamMoveDown = false;
        }
        if (keyCode == Input.Keys.UP) {
            this.gameCamMoveUp = false;
        }
        if (keyCode == Input.Keys.W) {
            this.gameCamRotateAgainstClock = false;
        }
        if (keyCode == Input.Keys.E) {
            this.gameCamRotateWithClock = false;
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == 0) {
            leftMouseClicked = true;
            getClickedMousePosition().x = screenX;
            getClickedMousePosition().y = screenY;
        }
        if (button == 1) {
            rightMouseClicked = true;
            getClickedMousePosition().x = screenX;
            getClickedMousePosition().y = screenY;
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    public boolean isZoomingOut() {
        return zoomingOut;
    }

    public boolean isZoomingIn() {
        return zoomingIn;
    }

    public boolean isGameCamMoveLeft() {
        return gameCamMoveLeft;
    }

    public boolean isGameCamMoveRight() {
        return gameCamMoveRight;
    }

    public boolean isGameCamMoveDown() {
        return gameCamMoveDown;
    }

    public boolean isGameCamMoveUp() {
        return gameCamMoveUp;
    }

    public boolean isGameCamRotateAgainstClock() {
        return gameCamRotateAgainstClock;
    }

    public boolean isGameCamRotateWithClock() {
        return gameCamRotateWithClock;
    }

    public boolean isLeftMouseClicked() {
        return leftMouseClicked;
    }

    public Vector2 getClickedMousePosition() {
        if (clickedMousePosition == null) {
            clickedMousePosition = new Vector2();
        }
        return clickedMousePosition;
    }

    public boolean isRightMouseClicked() {
        return rightMouseClicked;
    }

    public void resetLeftMouseClick() {
        leftMouseClicked = false;
    }

    public void resetRightMouseClick() {
        rightMouseClicked = false;
    }
}
