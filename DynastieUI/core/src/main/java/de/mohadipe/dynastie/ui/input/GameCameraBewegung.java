package de.mohadipe.dynastie.ui.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class GameCameraBewegung {
    private OrthographicCamera camera;
    private float rotationSpeed = 0.5f;

    public GameCameraBewegung(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void handleInput(InputProcessor processor) {
        if (processor.isZoomingOut()) {
            camera.zoom += 0.02;
            processor.outputConsole("Zoom: " + camera.zoom);
        }
        if (processor.isZoomingIn()) {
            camera.zoom -= 0.02;
            processor.outputConsole("Zoom: " + camera.zoom);
        }
        if (processor.isGameCamMoveLeft()) {
            camera.translate(-3, 0, 0);
        }
        if (processor.isGameCamMoveRight()) {
            camera.translate(3, 0, 0);
        }
        if (processor.isGameCamMoveDown()) {
            camera.translate(0, -3, 0);
        }
        if (processor.isGameCamMoveUp()) {
            camera.translate(0, 3, 0);
        }
        if (processor.isGameCamRotateAgainstClock()) {
            camera.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (processor.isGameCamRotateWithClock()) {
            camera.rotate(rotationSpeed, 0, 0, 1);
        }
    }
}
