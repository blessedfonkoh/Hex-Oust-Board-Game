package HexOust;

import GameController.utils.*;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

import java.util.List;
import java.util.stream.Collectors;

import static GameController.utils.HexUtil.isValidMove;

/**
 * JavaFX Controller class for 'game-view.fxml'.
 * Handles UI interaction logic and delegates game state changes to GameController.
 */
public class GameUIController {


    @FXML
    Pane boardPane; // Container for all hexagon tiles

    @FXML
    Pane turnPane; // Container for turn indicator

    @FXML
    List<Polygon> hexagons;

    public final GameController gameController = new GameController();

    GraphicsUtil hover;

    /**
     * Called automatically by the FXMLLoader after loading the FXML.
     * Initializes game state and sets up references between UI elements and controller logic.
     */
    @FXML
    public void initialize() { // Called by the FXMLLoader when initialization is complete
        gameController.turn = new TurnUtil(turnPane);
        hover = new GraphicsUtil(boardPane, gameController.turn);
        gameController.turn.displayTurn();

        hexagons = boardPane.getChildren()
                .filtered(node -> node instanceof Polygon)
                .stream()
                .map(node -> (Polygon) node)
                .collect(Collectors.toList());

        gameController.boardPane = boardPane;
        gameController.hexagons = hexagons;

    }


    /**
     * Places a stone on the board if the selected hexagon is valid.
     * Called when the user clicks on a hexagon.
     *
     * @param event MouseEvent triggered by clicking a hexagon.
     */
    @FXML
    public void placeStone(MouseEvent event) {

        Polygon hexagon = (Polygon) event.getSource();
        String hexID = hexagon.getId();

        if (gameController.showErrorMessage(hexagon) || hexagon.isDisabled()) {
            return; // Preventing placement on disabled/ invalid hexagons
        }

        boardPane.getChildren().add(gameController.createStone(hexagon));

        gameController.playMove(hexID, hexagon);
    }

    /**
     * Resets the game state and UI by clearing all stones and restoring the board to its initial state.
     */
    @FXML
    public void restartGame() {
        gameController.turn.resetTurn();

        boardPane.getChildren().removeIf(node -> node instanceof Circle);

        boardPane.getChildren().forEach(node -> {
            if (node instanceof Polygon) {
                node.setDisable(false);
            }
        });

        gameController.getRedHexagons().clear();
        gameController.getBlueHexagons().clear();
        gameController.turn.displayTurn();
    }

    /**
     * Displays a red X or green tick on a hexagon when the mouse hovers over it,
     * indicating whether it’s a valid move.
     *
     * @param event MouseEvent triggered by hovering on a hexagon.
     */
    @FXML
    public void showX(MouseEvent event) {
        Polygon hexagon = (Polygon) event.getSource();

        if (!isValidMove(hexagon.getId())) {
            hover.createX(hexagon);
        } else {
            hover.createTick(hexagon);
        }

    }

    /**
     * Removes hover indicators (X or tick) when the mouse leaves a hexagon.
     */
    @FXML
    public void removeHover() {
        hover.removeX();
        hover.removeTick();
    }
}
