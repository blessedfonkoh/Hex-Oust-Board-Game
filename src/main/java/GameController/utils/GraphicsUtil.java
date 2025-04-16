package GameController.utils;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import javax.swing.*;
import java.awt.*;

/**
 * This class holds all the utility methods to display objects to the user on hover
 */
public class GraphicsUtil {
    private final Pane boardPane;
    static TurnUtil turn;

    public Line getLine1() {
        return line1;
    }

    public Line getLine2() {
        return line2;
    }

    public Line getTickLine1() {
        return tickLine1;
    }

    public Line getTickLine2() {
        return tickLine2;
    }

    // Lines to display X
    // First line "/"
    private final Line line1 = new Line(-8, 8, 8, -8);
    // Second line "\"
    private final Line line2 = new Line(-8, -8, 8, 8);

    //Lines to display tick
    //First line "/"
    private final Line tickLine1 = new Line(-6, 9, 10, -6);
    // Second line " short \ "
    private final Line tickLine2 = new Line(-10, 3, -6, 9);

    public GraphicsUtil(Pane boardPane, TurnUtil turn) {
        GraphicsUtil.turn = turn;
        this.boardPane = boardPane;
        // Styling the lines to make them RED
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(4);
        line2.setStroke(Color.RED);
        line2.setStrokeWidth(4);

        // Setting it transparent so when clicked on, error message will pop up
        line1.setMouseTransparent(true);
        line2.setMouseTransparent(true);

        tickLine1.setStroke(Color.GREEN);
        tickLine1.setStrokeWidth(4);
        tickLine2.setStroke(Color.GREEN);
        tickLine2.setStrokeWidth(4);
        tickLine1.setMouseTransparent(true);
        tickLine2.setMouseTransparent(true);
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

    public void createTick(Polygon currentHex) {
        tickLine1.setLayoutX(currentHex.getLayoutX());
        tickLine1.setLayoutY(currentHex.getLayoutY());
        tickLine2.setLayoutX(currentHex.getLayoutX());
        tickLine2.setLayoutY(currentHex.getLayoutY());

        if (!boardPane.getChildren().contains(tickLine1)) {
            boardPane.getChildren().addAll(tickLine1, tickLine2);
        }
    }

    public void removeX() {
        // Removing the line from the view of the board
        boardPane.getChildren().remove(line1);
        boardPane.getChildren().remove(line2);
    }

    public void removeTick() {
        boardPane.getChildren().remove(tickLine1);
        boardPane.getChildren().remove(tickLine2);
    }

    public static void showErrorMessage() {
        JDialog dialog = MessageUtil.showErrorMessage();
        dialog.setVisible(true);
    }
}

