package comp20050.hexagonalboard;

import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Line;

import javax.swing.*;

/**
 * Game Controller Class
 * for 'game-view.fxml'
 */
public class GameController {

    private boolean turn = true; // True for red, False for blue

    private final List<String> redHexagons = new ArrayList<>(); // List to store RED players moves
    private final List<String> blueHexagons = new ArrayList<>(); // List to store BLUE players moves

    /**
     * Method to switch players turns
     */
    public void switchTurn() {
        turn = !turn;
        displayTurn(); // Calling displayTurn() method to display next turn
    }

    @FXML
    public Button restartButton;

    @FXML
    private Pane boardPane; // The container holding all hexagons

    @FXML
    private Pane turnPane; // The container holding the turns and the test "To Make a Move"

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


    /**
     * Method to allow the user to place a stone on the base-7 hexagonal grid
     *
     * @param event : Mouse event triggered by clicking on a hexagon.
     */
    @FXML
    public void placeStone(MouseEvent event) {

        Polygon hexagon = (Polygon) event.getSource();

        if (hexagon.isDisabled()) {
            return; // Preventing placement on disabled hexagons
        }

        String hexId = hexagon.getId();

        // Get restricted hexes based on current player
        List<String> invalidHexes = getInvalidHexes(turn ? redHexagons : blueHexagons);

        // Display pop-up error message if current hex is invalid, cancels stone placement
        if (invalidHexes.contains(hexagon.getId())) {
            JPanel panel = new JPanel();
            JOptionPane.showMessageDialog(panel, "Invalid Cell Placement.",
                    "Invalid Move", JOptionPane.ERROR_MESSAGE);
            return;
        }

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

        hexagon.setDisable(true); // Disabling hexagons with stones to prevent placement on them


        if (turn) { // Red's turn
            redHexagons.add(hexId); // Add placed stone to RED's list
        } else { // Blue's turn
            blueHexagons.add(hexId); // Add placed stone to BLUE's list
        }

        // Switch player's turn
        switchTurn();

    }

    // Lines to display X
    // First line "/"
    Line line1 = new Line(-8, 8, 8, -8);
    // Second line "\"
    Line line2 = new Line(-8, -8, 8, 8);

    /**
     * Method to show the red X on the board when hovering on a hexagon.
     *
     * @param event : Mouse event triggered by cursor hovering on the hexagon.
     */
    @FXML
    public void showX(MouseEvent event) {
        Polygon currentHex = (Polygon) event.getSource();

        // Get restricted hexes based on current player
        List<String> invalidHexes = getInvalidHexes(turn ? redHexagons : blueHexagons);

        // Show the X on hover if its invalid
        if (invalidHexes.contains(currentHex.getId())) {

            line1.setLayoutX(currentHex.getLayoutX());
            line1.setLayoutY(currentHex.getLayoutY());

            line2.setLayoutX(currentHex.getLayoutX());
            line2.setLayoutY(currentHex.getLayoutY());

            // Styling the lines to make them RED
            line1.setStroke(Color.RED);
            line1.setStrokeWidth(4);
            line2.setStroke(Color.RED);
            line2.setStrokeWidth(4);

            // Setting it transparent so when clicked on, error message will pop up
            line1.setMouseTransparent(true);
            line2.setMouseTransparent(true);

            // Add to the pane if not already added
            if (!boardPane.getChildren().contains(line1)) {
                boardPane.getChildren().addAll(line1, line2);
            }
        }

    }

    /**
     * Method to remove the red X from the board after hovering
     */
    @FXML
    public void removeX() {
        // Removing the line from the view of the board
        boardPane.getChildren().remove(line1);
        boardPane.getChildren().remove(line2);
    }

    /**
     * Method to get all the invalid hexagons that are restricted for the current player
     *
     * @param playersHexagons : A list of hexagon IDs representing the hexagons occupied by the current player.
     * @return invalidHexes : A list of hexagon IDs that are invalid for placement based on the player's occupied hexagons.
     */
    private List<String> getInvalidHexes(List<String> playersHexagons) {

        List<String> invalidHexes = new ArrayList<>();
        for (String hexId : playersHexagons) {
            invalidHexes.addAll(getNeighbourHex(hexId));
        }
        return invalidHexes;
    }

    /**
     * Method to get all valid neighbouring hexagons for a given Hex ID
     *
     * @param hexId : The ID of the hexagon
     * @return A list of hexagon IDs representing the neighboring hexagons.
     */
    private List<String> getNeighbourHex(String hexId) {
        char letter = hexId.charAt(0); // Letter for the row e.g A,B,C
        int num = Integer.parseInt(hexId.substring(1)); // Number for the column

        return new ArrayList<>(Arrays.asList(String.valueOf((char) (letter + 1)) + num,  // Top
                String.valueOf((char) (letter + 1)) + (num + 1), // Top
                String.valueOf((letter)) + (num - 1),  // Left
                String.valueOf((letter)) + (num + 1), // Right
                String.valueOf((char) (letter - 1)) + (num - 1), // Bottom left
                String.valueOf((char) (letter - 1)) + num));
    }

    @FXML
    public void initialize() { // Called by the FXMLLoader when initialization is complete

        displayTurn(); // Display turns, RED to make a move first

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

    /**
     * Method to restart the game by clearing the board and removing all stones
     */
    public void restartGame() {

        turn = true; // Reset turn to begin from RED

        boardPane.getChildren().removeIf(node -> node instanceof Circle); // Remove all stones from the board
        boardPane.getChildren().forEach(node -> {
            if (node instanceof Polygon) {
                node.setDisable(false); // Enable all the hexagons
            }
        });

        // Erasing Lists of placed stones for RED and BLUE
        redHexagons.clear();
        blueHexagons.clear();

        displayTurn(); // Display turn from beginning
    }

    /**
     * Method to display turns below the board.
     */
    public void displayTurn() {

        // Display stone depending on players turn
        Circle stone = new Circle(12, turn ? Color.RED : Color.BLUE);
        stone.setStroke(turn ? Color.MAROON : Color.NAVY);
        stone.setStrokeWidth(4);
        stone.setCenterX(-30);
        stone.setCenterY(-9);

        turnPane.getChildren().add(stone); // Add stone to the board

    }

}
