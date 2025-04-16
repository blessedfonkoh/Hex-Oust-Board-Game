package comp20050.hexagonalboard;

import java.util.*;

import GameController.utils.*;

import static GameController.utils.HexUtil.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

import javax.swing.*;

/**
 * Game Controller Class
 * for 'game-view.fxml'
 */
public class GameController {

    public List<String> getBlueHexagons() {
        return blueHexagons;
    }

    public List<String> getRedHexagons() {
        return redHexagons;
    }

    private final List<String> redHexagons = new ArrayList<>(); // List to store RED players moves
    private  final List<String> blueHexagons = new ArrayList<>(); // List to store BLUE players moves


    TurnUtil turn;
    GraphicsUtil hover;

    @FXML
    Button restartButton;

    @FXML
    Pane boardPane; // The container holding all hexagons

    @FXML
    Pane turnPane; // The container holding the turns and the text "To Make a Move"

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
    List<Polygon> hexagons;

    @FXML
    public void initialize() { // Called by the FXMLLoader when initialization is complete
        turn = new TurnUtil(turnPane);
        hover = new GraphicsUtil(boardPane, turn);
        turn.displayTurn(); // Display turns, RED to make a move first

        hexagons = Arrays.asList(
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
            if (hex == null) {
                System.out.println("Hex was not injected: check your FXML file 'game-view.fxml'.");
            }
        }
    }

    /**
     * @param hexagon
     * @return
     */

    /**
     * Method to allow the user to place a stone on the base-7 hexagonal grid
     *
     * @param event : Mouse event triggered by clicking on a hexagon.
     */
    @FXML
    public void placeStone(MouseEvent event) {

        Polygon hexagon = (Polygon) event.getSource();

        if (showErrorMessage(hexagon) || hexagon.isDisabled()) {
            return; // Preventing placement on disabled/ invalid hexagons
        }

        String hexId = hexagon.getId();

        //Display stone to board
        boardPane.getChildren().add(createStone(hexagon));
        logStonePlacement(hexId);
        processMove(hexId);
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
     * @param hexId
     */

    public void logStonePlacement(String hexId) {

        if (turn.isRedTurn()) { // Red's turn
            getRedHexagons().add(hexId); // Add placed stone to RED's list
        } else { // Blue's turn
            getBlueHexagons().add(hexId); // Add placed stone to BLUE's list
        }

    }

    /**
     * Method to remove captured stones from the board
     *
     * @param capturedStones
     */

    public void removeStones(List<String> capturedStones) {
        for (String hexId : capturedStones) {
            Polygon hex = (Polygon) boardPane.lookup("#" + hexId);
            if (hex != null) {
                boardPane.getChildren().removeIf(node -> node instanceof Circle &&
                        (node.getLayoutX() == hex.getLayoutX() &&
                                node.getLayoutY() == hex.getLayoutY()));
                hex.setDisable(false);
            }
            if (getRedHexagons().contains(hexId)) {
                getRedHexagons().remove(hexId);
            } else {
                getBlueHexagons().remove(hexId);
            }
        }
    }

    /**
     * @param hexId
     */
    public void processMove(String hexId) {

        List<String> capturedStones = isCapturingMove(hexId, turn.isRedTurn() ? getRedHexagons() : getBlueHexagons(),
                turn.isRedTurn() ? getBlueHexagons() : getRedHexagons());
        if (capturedStones != null) {
            removeStones(capturedStones);

            if(isWinningMove())
            {
                if(turn.isRedTurn()){
                    showWinner("RED WINS");
                }
                else{
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
    public void showWinner(String winner) {
        //  Display pop-up message to display winner
        JDialog dialog = MessageUtil.showWinner(winner);
        dialog.setVisible(true);
    }

    private boolean isWinningMove(){
        return getRedHexagons().isEmpty() || getBlueHexagons().isEmpty();
    }

    public void skipTurn() {
        //getting list of all empty hexes for possible moves
        List<String> availableHexes = new ArrayList<>();

        for(Polygon p : hexagons){
            availableHexes.add(p.getId());
        }
        availableHexes.removeAll(getRedHexagons());
        availableHexes.removeAll(getBlueHexagons());

        for (String hexId : availableHexes) {
            if (!isInvalidMove(turn.isRedTurn() ? getRedHexagons() : getBlueHexagons(),
                    turn.isRedTurn() ? getBlueHexagons() : getRedHexagons(), hexId)) {
                //if there is a valid move, function is exited immediately
                return;
            }
        }

        //if it reaches here it means there are no possible moves so turn is skipped
        turn.switchTurn();
    }


    /**
     * Method to show the red X on the board when hovering on a hexagon.
     *
     * @param event : Mouse event triggered by cursor hovering on the hexagon.
     */
    @FXML
    public void showX(MouseEvent event) {
        Polygon currentHex = (Polygon) event.getSource();

        // Show the X on hover if its invalid
        if (isInvalidMove(turn.isRedTurn() ? getRedHexagons() : getBlueHexagons(),
                turn.isRedTurn() ? getBlueHexagons() : getRedHexagons(), currentHex.getId())) {
            hover.createX(currentHex);
        } else {
            hover.createTick(currentHex);
        }

    }

    /**
     * Method to remove the red X and green tick from the board after hovering
     */
    @FXML
    public void removeX() {
        hover.removeX();
        hover.removeTick();
    }

    /**
     * Method to show error message if user attempts to place a stone in an invalid hex.
     *
     * @param currentHex
     * @return True if error message should be displayed and false if it shouldn't
     */
    public boolean showErrorMessage(Polygon currentHex) {
        if (isInvalidMove(turn.isRedTurn() ? getRedHexagons() : getBlueHexagons(),
                turn.isRedTurn() ? getBlueHexagons() : getRedHexagons(), currentHex.getId())) {
            GraphicsUtil.showErrorMessage(); // Display error message
            return true;
        }
        return false;
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
}
