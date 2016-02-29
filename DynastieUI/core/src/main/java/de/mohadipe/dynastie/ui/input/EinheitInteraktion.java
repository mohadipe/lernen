package de.mohadipe.dynastie.ui.input;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.List;

import de.mohadipe.dynastie.logik.DynastieLogik;
import de.mohadipe.dynastie.logik.adapter.DynastieLogikAdapter;
import de.mohadipe.dynastie.ui.DynastieUI;
import de.mohadipe.dynastie.ui.controller.EinheitAuswaehlenController;
import de.mohadipe.dynastie.ui.controller.EinheitenController;
import de.mohadipe.dynastie.ui.entities.Einheit;
import de.mohadipe.dynastie.ui.map.Feld;
import de.mohadipe.dynastie.ui.map.KoordinatenSystem;
import de.mohadipe.dynastie.ui.menu.SpielMenu;

public class EinheitInteraktion {

    private final KoordinatenSystem koordinatenSystem;
    private DynastieUI game;

    public EinheitInteraktion(DynastieUI game, KoordinatenSystem koordinatenSystem) {
        this.game = game;
        this.koordinatenSystem = koordinatenSystem;
    }

    public void handleInput(InputProcessor processor) {
        EinheitenController einheitenController = game.getEinheitenController();
        if (processor.isLeftMouseClicked()) {
            Vector2 screenKoords = processor.getClickedMousePosition();
            Vector3 worldKoords = koordinatenSystem.getWorldKoords(game.gameCamera, screenKoords);
            // Herausfinden ob eine Einheit angeklickt wurde.
            List<Einheit> einheits = einheitenController.getEinheitenDesSpielers(game.getAktiveSpieler());
            EinheitAuswaehlenController einheitAuswaehlenController = new EinheitAuswaehlenController(einheits, worldKoords);
            if (einheitAuswaehlenController.isEinheitAusgeweahlt()) {
                // Einheit ist "Aktiv"
                // Ist an der geclickten Position eine Einheit wird diese aktiviert.
                einheitenController.setAktiveEinheit(einheitAuswaehlenController.getGecklickteEinheit());
            } else {
                // Wenn erneut geklickt wird und eine Einheit aktiv ist soll sich die aktive Einheit dort hin bewegen.
                if (einheitenController.existAktiveEinheit()) {
                    // ermittle geklicktes Feld
                    Feld zielFeld = koordinatenSystem.getFeldByWorldKoords(worldKoords);
                    Vector2 possitionFuerFeld = koordinatenSystem.getPossitionFuerFeld(zielFeld);
//                    Feld aktuellesFeld = koordinatenSystem.getFeldByWorldKoords(aktiveEinheit.getKoordinaten());
//                    de.mohadipe.dynastie.logik.model.Einheit logikEinheit = DynastieLogikAdapter.convertToLogikEinheit(aktiveEinheit);
//                    de.mohadipe.dynastie.logik.model.Feld logikZielFeld = DynastieLogikAdapter.convertToLogikFeld(zielFeld);
//                    de.mohadipe.dynastie.logik.model.Feld logikAktuellesFeld = DynastieLogikAdapter.convertToLogikFeld(aktuellesFeld);
//                    DynastieLogik dynastieLogik = DynastieLogikAdapter.getInstance();
//                    if (dynastieLogik.isBewegungErlaubt(logikEinheit, logikZielFeld, logikAktuellesFeld)) {
                        // positioniere Einheit in Feld
                    if (possitionFuerFeld != null) {
                        einheitenController.bewegeAktiveEinheit(possitionFuerFeld);
                    }
//                    } else {
//                         TODO Hinweis Reichweite der Einheit überschritten.
//                    }

                }
            }
            processor.resetLeftMouseClick();
        }
        if (processor.isRightMouseClicked()) {
            einheitenController.keineAktiveEinheit();
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
