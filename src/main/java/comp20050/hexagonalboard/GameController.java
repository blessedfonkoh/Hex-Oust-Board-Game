package comp20050.hexagonalboard;

/**
 * Game Controller Class
 * for 'game-view.fxml'
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class GameController {
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

    // Hexagon instances
    @FXML // fx:id="A1"
    private Polygon A1; // Value injected by FXMLLoader

    @FXML // fx:id="A2"
    private Polygon A2; // Value injected by FXMLLoader

    @FXML // fx:id="A3"
    private Polygon A3; // Value injected by FXMLLoader

    @FXML // fx:id="A4"
    private Polygon A4; // Value injected by FXMLLoader

    @FXML // fx:id="A5"
    private Polygon A5; // Value injected by FXMLLoader

    @FXML // fx:id="A6"
    private Polygon A6; // Value injected by FXMLLoader

    @FXML // fx:id="A7"
    private Polygon A7; // Value injected by FXMLLoader

    @FXML // fx:id="B1"
    private Polygon B1; // Value injected by FXMLLoader

    @FXML // fx:id="B2"
    private Polygon B2; // Value injected by FXMLLoader

    @FXML // fx:id="B3"
    private Polygon B3; // Value injected by FXMLLoader

    @FXML // fx:id="B4"
    private Polygon B4; // Value injected by FXMLLoader

    @FXML // fx:id="B5"
    private Polygon B5; // Value injected by FXMLLoader

    @FXML // fx:id="B6"
    private Polygon B6; // Value injected by FXMLLoader

    @FXML // fx:id="B7"
    private Polygon B7; // Value injected by FXMLLoader

    @FXML // fx:id="B8"
    private Polygon B8; // Value injected by FXMLLoader

    @FXML // fx:id="C1"
    private Polygon C1; // Value injected by FXMLLoader

    @FXML // fx:id="C2"
    private Polygon C2; // Value injected by FXMLLoader

    @FXML // fx:id="C3"
    private Polygon C3; // Value injected by FXMLLoader

    @FXML // fx:id="C4"
    private Polygon C4; // Value injected by FXMLLoader

    @FXML // fx:id="C5"
    private Polygon C5; // Value injected by FXMLLoader

    @FXML // fx:id="C6"
    private Polygon C6; // Value injected by FXMLLoader

    @FXML // fx:id="C7"
    private Polygon C7; // Value injected by FXMLLoader

    @FXML // fx:id="C8"
    private Polygon C8; // Value injected by FXMLLoader

    @FXML // fx:id="C9"
    private Polygon C9; // Value injected by FXMLLoader

    @FXML // fx:id="D1"
    private Polygon D1; // Value injected by FXMLLoader

    @FXML // fx:id="D2"
    private Polygon D2; // Value injected by FXMLLoader

    @FXML // fx:id="D3"
    private Polygon D3; // Value injected by FXMLLoader

    @FXML // fx:id="D4"
    private Polygon D4; // Value injected by FXMLLoader

    @FXML // fx:id="D5"
    private Polygon D5; // Value injected by FXMLLoader

    @FXML // fx:id="D6"
    private Polygon D6; // Value injected by FXMLLoader

    @FXML // fx:id="D7"
    private Polygon D7; // Value injected by FXMLLoader

    @FXML // fx:id="D8"
    private Polygon D8; // Value injected by FXMLLoader

    @FXML // fx:id="D9"
    private Polygon D9; // Value injected by FXMLLoader

    @FXML // fx:id="D10"
    private Polygon D10; // Value injected by FXMLLoader

    @FXML // fx:id="E1"
    private Polygon E1; // Value injected by FXMLLoader

    @FXML // fx:id="E2"
    private Polygon E2; // Value injected by FXMLLoader

    @FXML // fx:id="E3"
    private Polygon E3; // Value injected by FXMLLoader

    @FXML // fx:id="E4"
    private Polygon E4; // Value injected by FXMLLoader

    @FXML // fx:id="E5"
    private Polygon E5; // Value injected by FXMLLoader

    @FXML // fx:id="E6"
    private Polygon E6; // Value injected by FXMLLoader

    @FXML // fx:id="E7"
    private Polygon E7; // Value injected by FXMLLoader

    @FXML // fx:id="E8"
    private Polygon E8; // Value injected by FXMLLoader

    @FXML // fx:id="E9"
    private Polygon E9; // Value injected by FXMLLoader

    @FXML // fx:id="E10"
    private Polygon E10; // Value injected by FXMLLoader

    @FXML // fx:id="E11"
    private Polygon E11; // Value injected by FXMLLoader

    @FXML // fx:id="F1"
    private Polygon F1; // Value injected by FXMLLoader

    @FXML // fx:id="F2"
    private Polygon F2; // Value injected by FXMLLoader

    @FXML // fx:id="F3"
    private Polygon F3; // Value injected by FXMLLoader

    @FXML // fx:id="F4"
    private Polygon F4; // Value injected by FXMLLoader

    @FXML // fx:id="F5"
    private Polygon F5; // Value injected by FXMLLoader

    @FXML // fx:id="F6"
    private Polygon F6; // Value injected by FXMLLoader

    @FXML // fx:id="F7"
    private Polygon F7; // Value injected by FXMLLoader

    @FXML // fx:id="F8"
    private Polygon F8; // Value injected by FXMLLoader

    @FXML // fx:id="F9"
    private Polygon F9; // Value injected by FXMLLoader

    @FXML // fx:id="F10"
    private Polygon F10; // Value injected by FXMLLoader

    @FXML // fx:id="F11"
    private Polygon F11; // Value injected by FXMLLoader

    @FXML // fx:id="F12"
    private Polygon F12; // Value injected by FXMLLoader

    @FXML // fx:id="G1"
    private Polygon G1; // Value injected by FXMLLoader

    @FXML // fx:id="G2"
    private Polygon G2; // Value injected by FXMLLoader

    @FXML // fx:id="G3"
    private Polygon G3; // Value injected by FXMLLoader

    @FXML // fx:id="G4"
    private Polygon G4; // Value injected by FXMLLoader

    @FXML // fx:id="G5"
    private Polygon G5; // Value injected by FXMLLoader

    @FXML // fx:id="G6"
    private Polygon G6; // Value injected by FXMLLoader

    @FXML // fx:id="G7"
    private Polygon G7; // Value injected by FXMLLoader

    @FXML // fx:id="G8"
    private Polygon G8; // Value injected by FXMLLoader

    @FXML // fx:id="G9"
    private Polygon G9; // Value injected by FXMLLoader

    @FXML // fx:id="G10"
    private Polygon G10; // Value injected by FXMLLoader

    @FXML // fx:id="G11"
    private Polygon G11; // Value injected by FXMLLoader

    @FXML // fx:id="G12"
    private Polygon G12; // Value injected by FXMLLoader

    @FXML // fx:id="G13"
    private Polygon G13; // Value injected by FXMLLoader

    @FXML // fx:id="H2"
    private Polygon H2; // Value injected by FXMLLoader

    @FXML // fx:id="H3"
    private Polygon H3; // Value injected by FXMLLoader

    @FXML // fx:id="H4"
    private Polygon H4; // Value injected by FXMLLoader

    @FXML // fx:id="H5"
    private Polygon H5; // Value injected by FXMLLoader

    @FXML // fx:id="H6"
    private Polygon H6; // Value injected by FXMLLoader

    @FXML // fx:id="H7"
    private Polygon H7; // Value injected by FXMLLoader

    @FXML // fx:id="H8"
    private Polygon H8; // Value injected by FXMLLoader

    @FXML // fx:id="H9"
    private Polygon H9; // Value injected by FXMLLoader

    @FXML // fx:id="H10"
    private Polygon H10; // Value injected by FXMLLoader

    @FXML // fx:id="H11"
    private Polygon H11; // Value injected by FXMLLoader

    @FXML // fx:id="H12"
    private Polygon H12; // Value injected by FXMLLoader

    @FXML // fx:id="H13"
    private Polygon H13; // Value injected by FXMLLoader

    @FXML // fx:id="I3"
    private Polygon I3; // Value injected by FXMLLoader

    @FXML // fx:id="I4"
    private Polygon I4; // Value injected by FXMLLoader

    @FXML // fx:id="I5"
    private Polygon I5; // Value injected by FXMLLoader

    @FXML // fx:id="I6"
    private Polygon I6; // Value injected by FXMLLoader

    @FXML // fx:id="I7"
    private Polygon I7; // Value injected by FXMLLoader

    @FXML // fx:id="I8"
    private Polygon I8; // Value injected by FXMLLoader

    @FXML // fx:id="I9"
    private Polygon I9; // Value injected by FXMLLoader

    @FXML // fx:id="I10"
    private Polygon I10; // Value injected by FXMLLoader

    @FXML // fx:id="I11"
    private Polygon I11; // Value injected by FXMLLoader

    @FXML // fx:id="I12"
    private Polygon I12; // Value injected by FXMLLoader

    @FXML // fx:id="I13"
    private Polygon I13; // Value injected by FXMLLoader

    @FXML // fx:id="J4"
    private Polygon J4; // Value injected by FXMLLoader

    @FXML // fx:id="J5"
    private Polygon J5; // Value injected by FXMLLoader

    @FXML // fx:id="J6"
    private Polygon J6; // Value injected by FXMLLoader

    @FXML // fx:id="J7"
    private Polygon J7; // Value injected by FXMLLoader

    @FXML // fx:id="J8"
    private Polygon J8; // Value injected by FXMLLoader

    @FXML // fx:id="J9"
    private Polygon J9; // Value injected by FXMLLoader

    @FXML // fx:id="J10"
    private Polygon J10; // Value injected by FXMLLoader

    @FXML // fx:id="J11"
    private Polygon J11; // Value injected by FXMLLoader

    @FXML // fx:id="J12"
    private Polygon J12; // Value injected by FXMLLoader

    @FXML // fx:id="J13"
    private Polygon J13; // Value injected by FXMLLoader

    @FXML // fx:id="K5"
    private Polygon K5; // Value injected by FXMLLoader

    @FXML // fx:id="K6"
    private Polygon K6; // Value injected by FXMLLoader

    @FXML // fx:id="K7"
    private Polygon K7; // Value injected by FXMLLoader

    @FXML // fx:id="K8"
    private Polygon K8; // Value injected by FXMLLoader

    @FXML // fx:id="K9"
    private Polygon K9; // Value injected by FXMLLoader

    @FXML // fx:id="K10"
    private Polygon K10; // Value injected by FXMLLoader

    @FXML // fx:id="K11"
    private Polygon K11; // Value injected by FXMLLoader

    @FXML // fx:id="K12"
    private Polygon K12; // Value injected by FXMLLoader

    @FXML // fx:id="K13"
    private Polygon K13; // Value injected by FXMLLoader

    @FXML // fx:id="L6"
    private Polygon L6; // Value injected by FXMLLoader

    @FXML // fx:id="L7"
    private Polygon L7; // Value injected by FXMLLoader

    @FXML // fx:id="L8"
    private Polygon L8; // Value injected by FXMLLoader

    @FXML // fx:id="L9"
    private Polygon L9; // Value injected by FXMLLoader

    @FXML // fx:id="L10"
    private Polygon L10; // Value injected by FXMLLoader

    @FXML // fx:id="L11"
    private Polygon L11; // Value injected by FXMLLoader

    @FXML // fx:id="L12"
    private Polygon L12; // Value injected by FXMLLoader

    @FXML // fx:id="L13"
    private Polygon L13; // Value injected by FXMLLoader

    @FXML // fx:id="M7"
    private Polygon M7; // Value injected by FXMLLoader

    @FXML // fx:id="M8"
    private Polygon M8; // Value injected by FXMLLoader

    @FXML // fx:id="M9"
    private Polygon M9; // Value injected by FXMLLoader

    @FXML // fx:id="M10"
    private Polygon M10; // Value injected by FXMLLoader

    @FXML // fx:id="M11"
    private Polygon M11; // Value injected by FXMLLoader

    @FXML // fx:id="M12"
    private Polygon M12; // Value injected by FXMLLoader

    @FXML // fx:id="M13"
    private Polygon M13; // Value injected by FXMLLoader

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
        assert A1 != null : "fx:id=\"A1\" was not injected: check your FXML file 'game-view.fxml'.";
        assert A2 != null : "fx:id=\"A2\" was not injected: check your FXML file 'game-view.fxml'.";
        assert A3 != null : "fx:id=\"A3\" was not injected: check your FXML file 'game-view.fxml'.";
        assert A4 != null : "fx:id=\"A4\" was not injected: check your FXML file 'game-view.fxml'.";
        assert A5 != null : "fx:id=\"A5\" was not injected: check your FXML file 'game-view.fxml'.";
        assert A6 != null : "fx:id=\"A6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert A7 != null : "fx:id=\"A7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert B1 != null : "fx:id=\"B1\" was not injected: check your FXML file 'game-view.fxml'.";
        assert B2 != null : "fx:id=\"B2\" was not injected: check your FXML file 'game-view.fxml'.";
        assert B3 != null : "fx:id=\"B3\" was not injected: check your FXML file 'game-view.fxml'.";
        assert B4 != null : "fx:id=\"B4\" was not injected: check your FXML file 'game-view.fxml'.";
        assert B5 != null : "fx:id=\"B5\" was not injected: check your FXML file 'game-view.fxml'.";
        assert B6 != null : "fx:id=\"B6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert B7 != null : "fx:id=\"B7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert B8 != null : "fx:id=\"B8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert C1 != null : "fx:id=\"C1\" was not injected: check your FXML file 'game-view.fxml'.";
        assert C2 != null : "fx:id=\"C2\" was not injected: check your FXML file 'game-view.fxml'.";
        assert C3 != null : "fx:id=\"C3\" was not injected: check your FXML file 'game-view.fxml'.";
        assert C4 != null : "fx:id=\"C4\" was not injected: check your FXML file 'game-view.fxml'.";
        assert C5 != null : "fx:id=\"C5\" was not injected: check your FXML file 'game-view.fxml'.";
        assert C6 != null : "fx:id=\"C6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert C7 != null : "fx:id=\"C7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert C8 != null : "fx:id=\"C8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert C9 != null : "fx:id=\"C9\" was not injected: check your FXML file 'game-view.fxml'.";
        assert D1 != null : "fx:id=\"D1\" was not injected: check your FXML file 'game-view.fxml'.";
        assert D2 != null : "fx:id=\"D2\" was not injected: check your FXML file 'game-view.fxml'.";
        assert D3 != null : "fx:id=\"D3\" was not injected: check your FXML file 'game-view.fxml'.";
        assert D4 != null : "fx:id=\"D4\" was not injected: check your FXML file 'game-view.fxml'.";
        assert D5 != null : "fx:id=\"D5\" was not injected: check your FXML file 'game-view.fxml'.";
        assert D6 != null : "fx:id=\"D6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert D7 != null : "fx:id=\"D7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert D8 != null : "fx:id=\"D8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert D9 != null : "fx:id=\"D9\" was not injected: check your FXML file 'game-view.fxml'.";
        assert D10 != null : "fx:id=\"D10\" was not injected: check your FXML file 'game-view.fxml'.";
        assert E1 != null : "fx:id=\"E1\" was not injected: check your FXML file 'game-view.fxml'.";
        assert E2 != null : "fx:id=\"E2\" was not injected: check your FXML file 'game-view.fxml'.";
        assert E3 != null : "fx:id=\"E3\" was not injected: check your FXML file 'game-view.fxml'.";
        assert E4 != null : "fx:id=\"E4\" was not injected: check your FXML file 'game-view.fxml'.";
        assert E5 != null : "fx:id=\"E5\" was not injected: check your FXML file 'game-view.fxml'.";
        assert E6 != null : "fx:id=\"E6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert E7 != null : "fx:id=\"E7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert E8 != null : "fx:id=\"E8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert E9 != null : "fx:id=\"E9\" was not injected: check your FXML file 'game-view.fxml'.";
        assert E10 != null : "fx:id=\"E10\" was not injected: check your FXML file 'game-view.fxml'.";
        assert E11 != null : "fx:id=\"E11\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F1 != null : "fx:id=\"F1\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F2 != null : "fx:id=\"F2\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F3 != null : "fx:id=\"F3\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F4 != null : "fx:id=\"F4\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F5 != null : "fx:id=\"F5\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F6 != null : "fx:id=\"F6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F7 != null : "fx:id=\"F7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F8 != null : "fx:id=\"F8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F9 != null : "fx:id=\"F9\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F10 != null : "fx:id=\"F10\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F11 != null : "fx:id=\"F11\" was not injected: check your FXML file 'game-view.fxml'.";
        assert F12 != null : "fx:id=\"F12\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G1 != null : "fx:id=\"G1\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G2 != null : "fx:id=\"G2\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G3 != null : "fx:id=\"G3\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G4 != null : "fx:id=\"G4\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G5 != null : "fx:id=\"G5\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G6 != null : "fx:id=\"G6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G7 != null : "fx:id=\"G7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G8 != null : "fx:id=\"G8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G9 != null : "fx:id=\"G9\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G10 != null : "fx:id=\"G10\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G11 != null : "fx:id=\"G11\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G12 != null : "fx:id=\"G12\" was not injected: check your FXML file 'game-view.fxml'.";
        assert G13 != null : "fx:id=\"G13\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H2 != null : "fx:id=\"H2\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H3 != null : "fx:id=\"H3\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H4 != null : "fx:id=\"H4\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H5 != null : "fx:id=\"H5\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H6 != null : "fx:id=\"H6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H7 != null : "fx:id=\"H7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H8 != null : "fx:id=\"H8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H9 != null : "fx:id=\"H9\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H10 != null : "fx:id=\"H10\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H11 != null : "fx:id=\"H11\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H12 != null : "fx:id=\"H12\" was not injected: check your FXML file 'game-view.fxml'.";
        assert H13 != null : "fx:id=\"H13\" was not injected: check your FXML file 'game-view.fxml'.";
        assert I3 != null : "fx:id=\"I3\" was not injected: check your FXML file 'game-view.fxml'.";
        assert I4 != null : "fx:id=\"I4\" was not injected: check your FXML file 'game-view.fxml'.";
        assert I5 != null : "fx:id=\"I5\" was not injected: check your FXML file 'game-view.fxml'.";
        assert I6 != null : "fx:id=\"I6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert I7 != null : "fx:id=\"I7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert I8 != null : "fx:id=\"I8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert I9 != null : "fx:id=\"I9\" was not injected: check your FXML file 'game-view.fxml'.";
        assert I10 != null : "fx:id=\"I10\" was not injected: check your FXML file 'game-view.fxml'.";
        assert I11 != null : "fx:id=\"I11\" was not injected: check your FXML file 'game-view.fxml'.";
        assert I12 != null : "fx:id=\"I12\" was not injected: check your FXML file 'game-view.fxml'.";
        assert I13 != null : "fx:id=\"I13\" was not injected: check your FXML file 'game-view.fxml'.";
        assert J4 != null : "fx:id=\"J4\" was not injected: check your FXML file 'game-view.fxml'.";
        assert J5 != null : "fx:id=\"J5\" was not injected: check your FXML file 'game-view.fxml'.";
        assert J6 != null : "fx:id=\"J6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert J7 != null : "fx:id=\"J7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert J8 != null : "fx:id=\"J8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert J9 != null : "fx:id=\"J9\" was not injected: check your FXML file 'game-view.fxml'.";
        assert J10 != null : "fx:id=\"J10\" was not injected: check your FXML file 'game-view.fxml'.";
        assert J11 != null : "fx:id=\"J11\" was not injected: check your FXML file 'game-view.fxml'.";
        assert J12 != null : "fx:id=\"J12\" was not injected: check your FXML file 'game-view.fxml'.";
        assert J13 != null : "fx:id=\"J13\" was not injected: check your FXML file 'game-view.fxml'.";
        assert K5 != null : "fx:id=\"K5\" was not injected: check your FXML file 'game-view.fxml'.";
        assert K6 != null : "fx:id=\"K6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert K7 != null : "fx:id=\"K7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert K8 != null : "fx:id=\"K8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert K9 != null : "fx:id=\"K9\" was not injected: check your FXML file 'game-view.fxml'.";
        assert K10 != null : "fx:id=\"K10\" was not injected: check your FXML file 'game-view.fxml'.";
        assert K11 != null : "fx:id=\"K11\" was not injected: check your FXML file 'game-view.fxml'.";
        assert K12 != null : "fx:id=\"K12\" was not injected: check your FXML file 'game-view.fxml'.";
        assert K13 != null : "fx:id=\"K13\" was not injected: check your FXML file 'game-view.fxml'.";
        assert L6 != null : "fx:id=\"L6\" was not injected: check your FXML file 'game-view.fxml'.";
        assert L7 != null : "fx:id=\"L7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert L8 != null : "fx:id=\"L8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert L9 != null : "fx:id=\"L9\" was not injected: check your FXML file 'game-view.fxml'.";
        assert L10 != null : "fx:id=\"L10\" was not injected: check your FXML file 'game-view.fxml'.";
        assert L11 != null : "fx:id=\"L11\" was not injected: check your FXML file 'game-view.fxml'.";
        assert L12 != null : "fx:id=\"L12\" was not injected: check your FXML file 'game-view.fxml'.";
        assert L13 != null : "fx:id=\"L13\" was not injected: check your FXML file 'game-view.fxml'.";
        assert M7 != null : "fx:id=\"M7\" was not injected: check your FXML file 'game-view.fxml'.";
        assert M8 != null : "fx:id=\"M8\" was not injected: check your FXML file 'game-view.fxml'.";
        assert M9 != null : "fx:id=\"M9\" was not injected: check your FXML file 'game-view.fxml'.";
        assert M10 != null : "fx:id=\"M10\" was not injected: check your FXML file 'game-view.fxml'.";
        assert M11 != null : "fx:id=\"M11\" was not injected: check your FXML file 'game-view.fxml'.";
        assert M12 != null : "fx:id=\"M12\" was not injected: check your FXML file 'game-view.fxml'.";
        assert M13 != null : "fx:id=\"M13\" was not injected: check your FXML file 'game-view.fxml'.";
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
