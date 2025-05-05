package HexOust;

import java.util.*;

import GameController.utils.*;

import static GameController.utils.HexUtil.*;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import javax.swing.*;

/**
 * GameController handles core game state logic for HexOust.
 * It manages moves, captures, turns, and win conditions, separate from UI rendering.
 * Used by GameUIController to manage gameplay.
 */
public class GameController {

    private static List<String> redHexagons = new ArrayList<>();   // Stores RED player's hex IDs
    private static List<String> blueHexagons = new ArrayList<>();  // Stores BLUE player's hex IDs

    Pane boardPane;
    List<Polygon> hexagons;
    static TurnUtil turn;


    /**
     * Determines if the current move leads to a win (i.e., one player has no pieces).
     *
     * @return true if game is over, false otherwise.
     */
    protected boolean isWinningMove() {
        return getRedHexagons().isEmpty() || getBlueHexagons().isEmpty();
    }

    public static List<String> getBlueHexagons() {
        return blueHexagons;
    }

    public static List<String> getRedHexagons() {
        return redHexagons;
    }

    public static List<String> getCurrentPlayerHexes() {
        return turn.isRedTurn() ? getRedHexagons() : getBlueHexagons();
    }

    public static List<String> getOpponentPlayerHexes() {
        return turn.isRedTurn() ? getBlueHexagons() : getRedHexagons();
    }

    /**
     * Executes a move by logging and processing it, then possibly skipping turn.
     *
     * @param hexID   ID of the hexagon where the move is made.
     * @param hexagon Polygon representing the hexagon.
     */
    public void playMove(String hexID, Polygon hexagon) {
        logStonePlacement(hexID);
        processMove(hexID);
        skipTurn();
    }

    /**
     * Creates a stone with color and border matching current player's turn.
     *
     * @param hexagon The hexagon where the stone will be placed.
     * @return Circle representing the stone.
     */
    public Circle createStone(Polygon hexagon) {
        Circle stone = new Circle(12, turn.isRedTurn() ? Color.RED : Color.BLUE);
        stone.setStroke(turn.isRedTurn() ? Color.MAROON : Color.NAVY);
        stone.setStrokeWidth(4);

        stone.setLayoutX(hexagon.getLayoutX());
        stone.setLayoutY(hexagon.getLayoutY());

        hexagon.setDisable(true); // Prevent reusing a hex already occupied

        return stone;
    }

    /**
     * Records the move in the current player's list.
     *
     * @param hexID ID of the hex placed.
     */
    public void logStonePlacement(String hexID) {
        getCurrentPlayerHexes().add(hexID);
    }

    /**
     * Removes captured stones from the board and updates opponent's state.
     *
     * @param capturedStones List of hex IDs to be removed.
     */
    public void removeStones(List<String> capturedStones) {

        for (String hexID : capturedStones) {
            Polygon hex = (Polygon) boardPane.lookup("#" + hexID);
            if (hex != null) {
                boardPane.getChildren().removeIf(node -> node instanceof Circle && (node.getLayoutX() == hex.getLayoutX() && node.getLayoutY() == hex.getLayoutY()));
                hex.setDisable(false);
            }
            getOpponentPlayerHexes().removeAll(capturedStones);

        }
    }

    /**
     * Processes a move, checks for captures and win conditions, then switches turn.
     *
     * @param hexID ID of the hex where the move occurred.
     */
    public void processMove(String hexID) {

        List<String> capturedStones = capturedStones(hexID);
        if (capturedStones != null) {
            removeStones(capturedStones);

            if (isWinningMove()) {
                showWinner(turn.isRedTurn() ? "RED WINS" : "BLUE WINS");
                restartGame();
                return;
            }
            turn.switchTurn();
        }
        // Always switch turn after processing
        turn.switchTurn();
    }


    /**
     * Skips the turn if no valid moves are available.
     */
    public void skipTurn() {
        List<String> availableHexes = new ArrayList<>();

        for (Polygon p : hexagons) {
            availableHexes.add(p.getId());
        }
        availableHexes.removeAll(getRedHexagons());
        availableHexes.removeAll(getBlueHexagons());

        for (String hexID : availableHexes) {
            if (isValidMove(hexID)) {
                return; // Valid move found, do not skip
            }
        }

        // No moves available, skip turn
        turn.switchTurn();
    }

    /**
     * Displays a dialog box showing the winner.
     *
     * @param winner String to show in the dialog.
     */
    private void showWinner(String winner) {
        JDialog dialog = MessageUtil.showWinner(winner);
        dialog.setVisible(true);
    }

    /**
     * Restarts the game by resetting state and clearing the board.
     */
    public void restartGame() {

        turn.resetTurn();
        boardPane.getChildren().removeIf(node -> node instanceof Circle); // Remove all stones from the board
        boardPane.getChildren().forEach(node -> {
            if (node instanceof Polygon) {
                node.setDisable(false);
            }
        });

        getRedHexagons().clear();
        getBlueHexagons().clear();
        turn.displayTurn();
    }


    /**
     * Shows an error message for invalid moves.
     *
     * @param hexagon The polygon that was clicked.
     * @return true if move is invalid and message is shown, false otherwise.
     */
    public boolean showErrorMessage(Polygon hexagon) {

        if (!isValidMove(hexagon.getId())) {
            GraphicsUtil.showErrorMessage();

            return true;
        }
        return false;
    }


}
