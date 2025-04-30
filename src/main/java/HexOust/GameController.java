package HexOust;

import java.util.*;

import GameController.utils.*;

import static GameController.utils.HexUtil.*;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import javax.swing.*;

/**
 * Handles game state and logic (not the JavaFX UI).
 * Used by GameUIController to manage gameplay.
 */

public class GameController {


    private final List<String> redHexagons = new ArrayList<>(); // List to store RED players moves
    private final List<String> blueHexagons = new ArrayList<>(); // List to store BLUE players moves
    Pane boardPane;
    List<Polygon> hexagons;
    TurnUtil turn;

    private boolean isWinningMove() {
        return getRedHexagons().isEmpty() || getBlueHexagons().isEmpty();
    }


    public List<String> getBlueHexagons() {
        return blueHexagons;
    }

    public List<String> getRedHexagons() {
        return redHexagons;
    }

    public List<String> getCurrentPlayerHexes() {
        return turn.isRedTurn() ? getRedHexagons() : getBlueHexagons();
    }

    public List<String> getOpponentPlayerHexes() {
        return turn.isRedTurn() ? getBlueHexagons() : getRedHexagons();
    }


    public void playMove(String hexID, Polygon hexagon) {
        logStonePlacement(hexID);
        processMove(hexID);
        skipTurn();
    }


    public Circle createStone(Polygon hexagon) {
        //Creating blue or red stone depending on whose turn it is
        Circle stone = new Circle(12, turn.isRedTurn() ? Color.RED : Color.BLUE);

        //Setting the stone border colour and width
        stone.setStroke(turn.isRedTurn() ? Color.MAROON : Color.NAVY);
        stone.setStrokeWidth(4);

        //Setting the stone position
        stone.setLayoutX(hexagon.getLayoutX());
        stone.setLayoutY(hexagon.getLayoutY());

        hexagon.setDisable(true); // Disabling hexagons with stones to prevent placement on them

        return stone;
    }

    /**
     * @param hexID
     */

    public void logStonePlacement(String hexID) {

        getCurrentPlayerHexes().add(hexID);
    }

    /**
     * Removes all given stones from the board and updates state lists.
     *
     * @param capturedStones
     */


    public void removeStones(List<String> capturedStones) {

        for (String hexID : capturedStones) {
            Polygon hex = (Polygon) boardPane.lookup("#" + hexID);
            if (hex != null) {
                boardPane.getChildren().removeIf(node -> node instanceof Circle &&
                        (node.getLayoutX() == hex.getLayoutX() &&
                                node.getLayoutY() == hex.getLayoutY()));
                hex.setDisable(false);
            }
            getOpponentPlayerHexes().removeAll(capturedStones);

        }
    }

    /**
     * @param hexID
     */
    public void processMove(String hexID) {

        List<String> capturedStones = capturedStones(hexID, getCurrentPlayerHexes(),
                getOpponentPlayerHexes());
        if (capturedStones != null) {
            removeStones(capturedStones);

            if (isWinningMove()) {
                if (turn.isRedTurn()) {
                    showWinner("RED WINS");
                } else {
                    showWinner("BLUE WINS");
                }
                restartGame();
                return;
            }
            turn.switchTurn();
        }
        // Switch player's turn
        turn.switchTurn();
    }


    public void skipTurn() {
        //getting list of all empty hexes for possible moves
        List<String> availableHexes = new ArrayList<>();

        for (Polygon p : hexagons) {
            availableHexes.add(p.getId());
        }
        availableHexes.removeAll(getRedHexagons());
        availableHexes.removeAll(getBlueHexagons());

        for (String hexID : availableHexes) {
            if (isValidMove(turn.isRedTurn() ? getRedHexagons() : getBlueHexagons(),
                    turn.isRedTurn() ? getBlueHexagons() : getRedHexagons(), hexID)) {
                //if there is a valid move, function is exited immediately
                return;
            }
        }

        //if it reaches here it means there are no possible moves so turn is skipped
        turn.switchTurn();
    }


    private void showWinner(String winner) {
        //  Display pop-up message to display winner
        JDialog dialog = MessageUtil.showWinner(winner);
        dialog.setVisible(true);
    }

    /**
     * Method to restart the game by clearing the board and removing all stones
     */

    public void restartGame() {

        turn.resetTurn(); // Reset turn to begin from RED
        boardPane.getChildren().removeIf(node -> node instanceof Circle); // Remove all stones from the board
        boardPane.getChildren().forEach(node -> {
            if (node instanceof Polygon) {
                node.setDisable(false); // Enable all the hexagons
            }
        });

        // Erasing Lists of placed stones for RED and BLUE
        getRedHexagons().clear();
        getBlueHexagons().clear();
        turn.displayTurn(); // Display turn from beginning
    }


    /**
     * Method to show error message if user attempts to place a stone in an invalid hex.
     *
     * @param hexagon
     * @return True if error message should be displayed and false if it shouldn't
     */
    public boolean showErrorMessage(Polygon hexagon) {

        if (!isValidMove(turn.isRedTurn() ? getRedHexagons() : getBlueHexagons(),
                turn.isRedTurn() ? getBlueHexagons() : getRedHexagons(), hexagon.getId())) {
            GraphicsUtil.showErrorMessage(); // Display error message

            return true;
        }
        return false;
    }


}
