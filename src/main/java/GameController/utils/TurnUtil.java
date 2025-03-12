package GameController.utils;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TurnUtil {
    private boolean redTurn = true; // True for red, False for blue
    private final Pane turnPane; // The container holding the turns and the text "To Make a Move"

    public TurnUtil(Pane turnPane) {
        this.turnPane = turnPane;
    }

    /**
     * Method to switch players turns
     */
    public void switchTurn() {
        redTurn = !redTurn;
        displayTurn(); // Calling displayTurn() method to display next turn
    }

    public boolean isRedTurn() {
        return redTurn;
    }

    /**
     * Method to display turns below the board.
     */
    public void displayTurn() {

        // Display stone depending on players turn
        Circle stone = new Circle(12, redTurn ? Color.RED : Color.BLUE);
        stone.setStroke(redTurn ? Color.MAROON : Color.NAVY);
        stone.setStrokeWidth(4);
        stone.setCenterX(-30);
        stone.setCenterY(-9);

        turnPane.getChildren().add(stone); // Add stone to the board
    }

    /**
     * Method to reset turn to red player.
     */
    public void resetTurn() {
        redTurn = true;
        displayTurn();
    }
}
