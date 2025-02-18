package comp20050.hexagonalboard;

/**
 * Sample Skeleton for 'hello-view.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class HelloController {
    boolean turn = true; // true for red, false for blue

    public boolean switchTurn () {
        turn = !turn;
        displayTurn(); //display next turn
        return turn;
    }

    @FXML
    private Pane boardPane; // The container holding all hexagons

    @FXML
    private Pane turnPane; // The container holding the turns and the test "To Make a Move"

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="hex1"
    private Polygon hex1; // Value injected by FXMLLoader

    @FXML // fx:id="hex10"
    private Polygon hex10; // Value injected by FXMLLoader

    @FXML // fx:id="hex11"
    private Polygon hex11; // Value injected by FXMLLoader

    @FXML // fx:id="hex12"
    private Polygon hex12; // Value injected by FXMLLoader

    @FXML // fx:id="hex13"
    private Polygon hex13; // Value injected by FXMLLoader

    @FXML // fx:id="hex14"
    private Polygon hex14; // Value injected by FXMLLoader

    @FXML // fx:id="hex15"
    private Polygon hex15; // Value injected by FXMLLoader

    @FXML // fx:id="hex16"
    private Polygon hex16; // Value injected by FXMLLoader

    @FXML // fx:id="hex17"
    private Polygon hex17; // Value injected by FXMLLoader

    @FXML // fx:id="hex18"
    private Polygon hex18; // Value injected by FXMLLoader

    @FXML // fx:id="hex19"
    private Polygon hex19; // Value injected by FXMLLoader

    @FXML // fx:id="hex2"
    private Polygon hex2; // Value injected by FXMLLoader

    @FXML // fx:id="hex3"
    private Polygon hex3; // Value injected by FXMLLoader

    @FXML // fx:id="hex4"
    private Polygon hex4; // Value injected by FXMLLoader

    @FXML // fx:id="hex5"
    private Polygon hex5; // Value injected by FXMLLoader

    @FXML // fx:id="hex6"
    private Polygon hex6; // Value injected by FXMLLoader

    @FXML // fx:id="hex7"
    private Polygon hex7; // Value injected by FXMLLoader

    @FXML // fx:id="hex8"
    private Polygon hex8; // Value injected by FXMLLoader

    @FXML // fx:id="hex9"
    private Polygon hex9; // Value injected by FXMLLoader

    @FXML
    void getHexID(MouseEvent event) {
        Polygon hexagon = (Polygon) event.getSource();
        hexagon.setDisable(true);

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
        if(!turn)
        {
            boardPane.getChildren().add(blueStone);

        }
        else if(turn){
            boardPane.getChildren().add(redStone);
        }
        switchTurn();


    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        displayTurn(); // display turns, red to make a move first
        assert hex1 != null : "fx:id=\"hex1\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex10 != null : "fx:id=\"hex10\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex11 != null : "fx:id=\"hex11\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex12 != null : "fx:id=\"hex12\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex13 != null : "fx:id=\"hex13\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex14 != null : "fx:id=\"hex14\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex15 != null : "fx:id=\"hex15\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex16 != null : "fx:id=\"hex16\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex17 != null : "fx:id=\"hex17\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex18 != null : "fx:id=\"hex18\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex19 != null : "fx:id=\"hex19\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex2 != null : "fx:id=\"hex2\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex3 != null : "fx:id=\"hex3\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex4 != null : "fx:id=\"hex4\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex5 != null : "fx:id=\"hex5\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex6 != null : "fx:id=\"hex6\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex7 != null : "fx:id=\"hex7\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex8 != null : "fx:id=\"hex8\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert hex9 != null : "fx:id=\"hex9\" was not injected: check your FXML file 'hello-view.fxml'.";

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

    void displayTurn(){

            //create a red circle
            Circle redStone = new Circle(12, Color.RED);
            redStone.setStroke(Color.MAROON);
            redStone.setStrokeWidth(4);
            Circle stone = new Circle(12, turn? Color.RED : Color.BLUE);
            stone.setStroke(turn? Color.MAROON : Color.NAVY);
            stone.setStrokeWidth(4);
            stone.setCenterX(-30);
            stone.setCenterY(-9);
            turnPane.getChildren().add(stone);

    }

}
