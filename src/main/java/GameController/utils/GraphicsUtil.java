package GameController.utils;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import javax.swing.*;

/**
 * This class holds all the utility methods to display objects to the user on hover
 */
public class GraphicsUtil {

    private final Pane boardPane;
    static TurnUtil turn;

    public Line getXLine1() {
        return Xline1;
    }

    public Line getXLine2() {
        return Xline2;
    }

    public Line getTickLine1() {
        return tickLine1;
    }

    public Line getTickLine2() {
        return tickLine2;
    }

    // Lines to display X
    // First line "/"
    private final Line Xline1 = new Line(-8, 8, 8, -8);
    // Second line "\"
    private final Line Xline2 = new Line(-8, -8, 8, 8);

    //Lines to display tick
    //First line "/"
    private final Line tickLine1 = new Line(-6, 9, 10, -6);
    // Second line " short \ "
    private final Line tickLine2 = new Line(-10, 3, -6, 9);

    public GraphicsUtil(Pane boardPane, TurnUtil turn) {
        GraphicsUtil.turn = turn;
        this.boardPane = boardPane;

        // Style X lines
        Xline1.setStroke(Color.RED);
        Xline1.setStrokeWidth(4);
        Xline2.setStroke(Color.RED);
        Xline2.setStrokeWidth(4);

        // Set transparent so when clicked on, error message will pop up
        Xline1.setMouseTransparent(true);
        Xline2.setMouseTransparent(true);

        //Style tick lines
        tickLine1.setStroke(Color.GREEN);
        tickLine1.setStrokeWidth(4);
        tickLine2.setStroke(Color.GREEN);
        tickLine2.setStrokeWidth(4);

        tickLine1.setMouseTransparent(true);
        tickLine2.setMouseTransparent(true);
    }


    public void createX(Polygon currentHex) {
        Xline1.setLayoutX(currentHex.getLayoutX());
        Xline1.setLayoutY(currentHex.getLayoutY());

        Xline2.setLayoutX(currentHex.getLayoutX());
        Xline2.setLayoutY(currentHex.getLayoutY());

        // Add to the pane if not already added
        if (!boardPane.getChildren().contains(Xline1)) {
            boardPane.getChildren().addAll(Xline1, Xline2);
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
        boardPane.getChildren().remove(Xline1);
        boardPane.getChildren().remove(Xline2);
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

