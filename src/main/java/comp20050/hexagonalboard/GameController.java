package comp20050.hexagonalboard;

/**
 * Game Controller Class
 * for 'game-view.fxml'
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class GameController {
    boolean turn = true; // true for red, false for blue

    public void switchTurn() {
        turn = !turn;
        displayTurn(); //display next turn
    }

    @FXML
    private Pane boardPane; // The container holding all hexagons

    @FXML
    private Pane turnPane; // The container holding the turns and the test "To Make a Move"

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;


    /**
     * Hexagon instances
     * Values are injected by FXMLLoader
     * fx:id = variable name, i.e. "A1"
     */
    @FXML
    private Polygon A1, A2, A3, A4, A5, A6, A7;
    @FXML
    private Polygon B1, B2, B3, B4, B5, B6, B7, B8;
    @FXML
    private Polygon C1, C2, C3, C4, C5, C6, C7, C8, C9;
    @FXML
    private Polygon D1, D2, D3, D4, D5, D6, D7, D8, D9, D10;
    @FXML
    private Polygon E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11;
    @FXML
    private Polygon F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12;
    @FXML
    private Polygon G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, G11, G12, G13;
    @FXML
    private Polygon H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13;
    @FXML
    private Polygon I3, I4, I5, I6, I7, I8, I9, I10, I11, I12, I13;
    @FXML
    private Polygon J4, J5, J6, J7, J8, J9, J10, J11, J12, J13;
    @FXML
    private Polygon K5, K6, K7, K8, K9, K10, K11, K12, K13;
    @FXML
    private Polygon L6, L7, L8, L9, L10, L11, L12, L13;
    @FXML
    private Polygon M7, M8, M9, M10, M11, M12, M13;

    @FXML
    void getHexID(MouseEvent event) {
        Polygon hexagon = (Polygon) event.getSource();
        hexagon.setDisable(true);

/*

    SEE COMPACT VERSION BELOW!!!

        //create a blue stone
        Circle blueStone = new Circle(12, Color.BLUE);
        blueStone.setStroke(Color.NAVY);
        blueStone.setStrokeWidth(4);

        double centerX = hexagon.getLayoutX();
        double centerY = hexagon.getLayoutY();

        //set the circle position
        blueStone.setLayoutX(centerX);
        blueStone.setLayoutY(centerY);

        //create a red circle
        Circle redStone = new Circle(12, Color.RED);
        redStone.setStroke(Color.MAROON);
        redStone.setStrokeWidth(4);

        //set the circle position
        redStone.setLayoutX(centerX);
        redStone.setLayoutY(centerY);

        //add circle to the boardPane
        if(turn)
        {
            boardPane.getChildren().add(redStone);
        }
        else {
            boardPane.getChildren().add(blueStone);
        }
        switchTurn();
 */

        //Creating blue or red stone depending on whose turn it is
        Circle stone = new Circle(12, turn ? Color.RED : Color.BLUE);

        //Setting the stone border colour and width
        stone.setStroke(turn ? Color.MAROON : Color.NAVY);
        stone.setStrokeWidth(4);

        //Setting the stone position
        stone.setLayoutX(hexagon.getLayoutX());
        stone.setLayoutY(hexagon.getLayoutY());

        //Display stone to board
        boardPane.getChildren().add(stone);

        //Switch player's turn
        switchTurn();

    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        displayTurn(); // display turns, red to make a move first

        List<Polygon> hexagons = Arrays.asList(
                A1, A2, A3, A4, A5, A6, A7,
                B1, B2, B3, B4, B5, B6, B7, B8,
                C1, C2, C3, C4, C5, C6, C7, C8, C9,
                D1, D2, D3, D4, D5, D6, D7, D8, D9, D10,
                E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11,
                F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12,
                G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, G11, G12, G13,
                H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13,
                I3, I4, I5, I6, I7, I8, I9, I10, I11, I12, I13,
                J4, J5, J6, J7, J8, J9, J10, J11, J12, J13,
                K5, K6, K7, K8, K9, K10, K11, K12, K13,
                L6, L7, L8, L9, L10, L11, L12, L13,
                M7, M8, M9, M10, M11, M12, M13);

        for (Polygon hex : hexagons) {
            assert hex != null : "fx:id=\"" + hex.getId() + "\" was not injected: check your FXML file 'game-view.fxml'.";
        }
    }

    //fx:id="restartButton"
    public void restartGame(MouseEvent mouseEvent) {
        turn = true;
        boardPane.getChildren().removeIf(node -> node instanceof Circle);
        boardPane.getChildren().forEach(node -> {
                    if (node instanceof Polygon) {
                        node.setDisable(false);
                    }
                }
        );
        displayTurn();
    }

    void displayTurn() {

        //Display stone depending on players turn
        Circle stone = new Circle(12, turn ? Color.RED : Color.BLUE);
        stone.setStroke(turn ? Color.MAROON : Color.NAVY);
        stone.setStrokeWidth(4);
        stone.setCenterX(-30);
        stone.setCenterY(-9);
        turnPane.getChildren().add(stone);

    }

}
