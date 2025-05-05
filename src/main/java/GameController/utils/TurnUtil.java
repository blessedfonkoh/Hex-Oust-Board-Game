package GameController.utils;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Utility class for managing player turns in the game.
 * Handles switching turns and updating the UI with current player's color.
 */
public class TurnUtil {

    private boolean redTurn = true; // True for red, False for blue
    private final Pane turnPane; // The container holding the turns and the text "To Make a Move"

    public TurnUtil(Pane turnPane) {
        this.turnPane = turnPane;
    }

    public Pane getTurnPane() {
        return turnPane;
    }

    public void switchTurn() {
        redTurn = !redTurn;
        displayTurn();
    }

    public boolean isRedTurn() {
        return redTurn;
    }

    public void displayTurn() {

        Circle stone = new Circle(12, isRedTurn() ? Color.RED : Color.BLUE);
        stone.setStroke(isRedTurn() ? Color.MAROON : Color.NAVY);
        stone.setStrokeWidth(4);
        stone.setCenterX(-30);
        stone.setCenterY(-9);

        getTurnPane().getChildren().add(stone);
    }

    public void resetTurn() {
        redTurn = true;
        displayTurn();
    }
}
