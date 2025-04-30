package HexOust;

import GameController.utils.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static GameController.utils.HexUtil.isValidMove;

/**
 * Game Controller Class
 * for 'game-view.fxml'
 */
public class GameUIController {


    @FXML
    Pane boardPane; // The container holding all hexagons
    @FXML
    Pane turnPane; // The container holding the turns and the text "To Make a Move"
    @FXML
    private Button restartButton;

    @FXML
    List<Polygon> hexagons;

    public final GameController gameController = new GameController();
    private GraphicsUtil graphicsUtil;
    GraphicsUtil hover;


    @FXML
    public void initialize() { // Called by the FXMLLoader when initialization is complete
        gameController.turn = new TurnUtil(turnPane);
        hover = new GraphicsUtil(boardPane, gameController.turn);
        gameController.turn.displayTurn(); // Display turns, RED to make a move first

        // Hexagon instances where values are injected by FXMLLoader
        hexagons = boardPane.getChildren()
                .filtered(node -> node instanceof Polygon)
                .stream()
                .map(node -> (Polygon) node)
                .collect(Collectors.toList());

        gameController.boardPane = boardPane;
        gameController.hexagons = hexagons;

    }

    /**
     * Method to allow the user to place a stone on the base-7 hexagonal grid
     *
     * @param event : Mouse event triggered by clicking on a hexagon.
     */
    @FXML
    public void placeStone(MouseEvent event) {

        Polygon hexagon = (Polygon) event.getSource();
        String hexID = hexagon.getId();

        if (gameController.showErrorMessage(hexagon) || hexagon.isDisabled()) {
            return; // Preventing placement on disabled/ invalid hexagons
        }


        //Display stone to board
        boardPane.getChildren().add(gameController.createStone(hexagon));

        gameController.playMove(hexID, hexagon);
    }

    /**
     * Method to restart the game by clearing the board and removing all stones
     */
@FXML
    public void restartGame() {

        gameController.turn.resetTurn(); // Reset turn to begin from RED
        boardPane.getChildren().removeIf(node -> node instanceof Circle); // Remove all stones from the board
        boardPane.getChildren().forEach(node -> {
            if (node instanceof Polygon) {
                node.setDisable(false); // Enable all the hexagons
            }
        });

        // Erasing Lists of placed stones for RED and BLUE
        gameController.getRedHexagons().clear();
        gameController.getBlueHexagons().clear();
        gameController.turn.displayTurn(); // Display turn from beginning
    }


    /**
     * Method to show the red X on the board when hovering on a hexagon.
     *
     * @param event : Mouse event triggered by cursor hovering on the hexagon.
     */
    @FXML
    public void showX(MouseEvent event) {
        Polygon hexagon = (Polygon) event.getSource();

        // Show the X on hover if its invalid
        if (!isValidMove(hexagon.getId())) {
            hover.createX(hexagon);
        } else {
            hover.createTick(hexagon);
        }

    }

    /**
     * Method to remove the red X and green tick from the board after hovering
     */
    @FXML
    public void removeX() {
        hover.removeX();
        hover.removeTick();
    }




}
