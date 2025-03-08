package comp20050.hexagonalboard;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

public class HoverUI {
    private final Pane boardPane;

    // Lines to display X
    // First line "/"
    private final Line line1 = new Line(-8, 8, 8, -8);
    // Second line "\"
    private final Line line2 = new Line(-8, -8, 8, 8);

    public HoverUI(Pane boardPane) {
        this.boardPane = boardPane;
        // Styling the lines to make them RED
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(4);
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(4);

        // Setting it transparent so when clicked on, error message will pop up
        line1.setMouseTransparent(true);
        line2.setMouseTransparent(true);
    }

    /**
     * Method to show the red X on the board when hovering on a hexagon.
     *
     * @param currentHex : Hexagon for which "X" is to be displayed on.
     */
    public void createX(Polygon currentHex) {
        line1.setLayoutX(currentHex.getLayoutX());
        line1.setLayoutY(currentHex.getLayoutY());

        line2.setLayoutX(currentHex.getLayoutX());
        line2.setLayoutY(currentHex.getLayoutY());


        // Add to the pane if not already added
        if (!boardPane.getChildren().contains(line1)) {
            boardPane.getChildren().addAll(line1, line2);
        }
    }

    public void removeX() {
        // Removing the line from the view of the board
        boardPane.getChildren().remove(line1);
        boardPane.getChildren().remove(line2);
    }
}

