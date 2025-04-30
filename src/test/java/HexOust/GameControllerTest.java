package HexOust;

import GameController.utils.MessageUtil;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private GameController gameController;
    private GameUIController gameUIController;


    // Initialize JavaFX runtime before all test cases
    @BeforeAll
    static void setupJavaFX() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    // Load FXML file resources and set up new instances of GameController before each test
    @BeforeEach
    void setUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("game-view.fxml"));
        Parent root = fxmlLoader.load();
        gameUIController = fxmlLoader.getController();
        gameController = gameUIController.gameController;
        gameController.boardPane = (Pane) root.lookup("#boardPane");
        gameUIController.turnPane = (Pane) root.lookup("#turnPane");
        gameUIController.initialize();

        MessageUtil.suppressMessages = true;
    }

    @AfterEach
    public void tearDown() {
        MessageUtil.suppressMessages = false;
    }

    // Test logStonePlacement method: verify stone placement logs for red player
    @Test
    void testLogStonePlacementRed() {
        GameController.turn.resetTurn();
        gameController.logStonePlacement("D5");
        assertTrue(GameController.getRedHexagons().contains("D5"));
        assertFalse(GameController.getBlueHexagons().contains("D5"));
    }

    // Test logStonePlacement method: verify stone placement logs for blue player
    @Test
    void testLogStonePlacementBlue() {
        GameController.turn.switchTurn();
        gameController.logStonePlacement("E6");
        assertTrue(GameController.getBlueHexagons().contains("E6"));
        assertFalse(GameController.getRedHexagons().contains("E6"));
    }

    // Test createStone method: testing dimensions and properties of stone
    @Test
    void testCreateStone() {
        Polygon hexagon = new Polygon();
        Circle stone = gameController.createStone(hexagon);

        assertNotNull(stone);
        assertEquals(12, stone.getRadius());
        assertEquals(GameController.turn.isRedTurn() ? Color.RED : Color.BLUE, stone.getFill());
    }

    // Test the restartGame method: ensure method resets the game state properly
    @Test
    void testRestartGame() {
        GameController.getRedHexagons().add("A1");
        GameController.getBlueHexagons().add("B2");

        Circle redStone = new Circle(12, Color.RED);
        Circle blueStone = new Circle(12, Color.BLUE);
        gameController.boardPane.getChildren().add(redStone);
        gameController.boardPane.getChildren().add(blueStone);

        Polygon disabledHex = new Polygon();
        disabledHex.setDisable(true);
        gameController.boardPane.getChildren().add(disabledHex);

        gameController.restartGame();

        assertTrue(GameController.getRedHexagons().isEmpty());
        assertTrue(GameController.getBlueHexagons().isEmpty());
        assertFalse(gameController.boardPane.getChildren().contains(redStone));
        assertFalse(disabledHex.isDisabled());
    }

    // testShowErrorMessage: check if showErrorMessage correctly flags invalid moves
    @Test
    public void testShowErrorMessage_InvalidMove() {
        GameController.turn.resetTurn();
        gameController.logStonePlacement("D5");

        Polygon currentHex = new Polygon();
        currentHex.setId("D6");

        boolean result = gameController.showErrorMessage(currentHex);
        assertTrue(result);
    }

    // testShowErrorMessage: check if showErrorMessage correctly flags valid moves
    @Test
    public void testShowErrorMessage_ValidMove() {
        GameController.turn.resetTurn();
        gameController.logStonePlacement("D5");

        Polygon currentHex = new Polygon();
        currentHex.setId("A6");

        boolean result = gameController.showErrorMessage(currentHex);
        assertFalse(result);
    }

    // testSkipTurn: Simulate full board state, player will be skipped
    @Test
    public void testSkipTurn_FullBoard() {
        GameController.turn.resetTurn();
        boolean initial = GameController.turn.isRedTurn();
        String[] hexCells = {
                "A1", "A2", "A3", "A4", "A5", "A6", "A7",
                "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8",
                "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9",
                "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10",
                "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10", "E11",
                "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12",
                "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10", "G11", "G12", "G13",
                "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "H11", "H12", "H13",
                "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10", "I11", "I12", "I13",
                "J4", "J5", "J6", "J7", "J8", "J9", "J10", "J11", "J12", "J13",
                "K5", "K6", "K7", "K8", "K9", "K10", "K11", "K12", "K13",
                "L6", "L7", "L8", "L9", "L10", "L11", "L12", "L13",
                "M7", "M8", "M9", "M10", "M11", "M12", "M13"
        };
        for (String hex : hexCells){
            gameController.logStonePlacement(hex);
        }
        gameController.skipTurn();
        assertNotEquals(initial, GameController.turn.isRedTurn());
    }

    // testSkipTurn: Simulate empty board state, player will not be skipped
    @Test
    public void testSkipTurn_EmptyBoard() {
        GameController.turn.resetTurn();
        boolean initial = GameController.turn.isRedTurn();

        gameController.skipTurn();
        assertEquals(initial, GameController.turn.isRedTurn());
    }

    // testPlaceStone: Simulate a mouse click on a valid hexagon and confirm a stone is added to board
    @Test
    public void testPlaceStone_validMove() {
        GameController.turn.resetTurn(); // Red's turn

        Polygon hex = new Polygon();
        hex.setId("C3");
        hex.setDisable(false);
        hex.setPickOnBounds(true);
        gameController.boardPane.getChildren().add(hex);  // Needed for scene graph

        MouseEvent event = new MouseEvent(
                hex, // <<-- this is the actual source node
                hex, // target node
                MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0,
                null, 1, false, false, false, false,
                false, false, false, false,
                false, false,
                new PickResult(hex, 0, 0)
        );

        int initialStoneCount = (int) gameController.boardPane.getChildren().stream()
                .filter(n -> n instanceof Circle).count();

        // Simulate placing the stone
        gameUIController.placeStone(event);

        int finalStoneCount = (int) gameController.boardPane.getChildren().stream()
                .filter(n -> n instanceof Circle).count();

        assertEquals(initialStoneCount + 1, finalStoneCount);
        assertTrue(GameController.getRedHexagons().contains("C3") || GameController.getBlueHexagons().contains("C3"));
    }

    // testPlaceStone: Simulate a mouse click on a invalid hexagon and shows error
    @Test
    public void testPlaceStone_invalidMove_showError() {
        gameController.turn.resetTurn();
        gameController.logStonePlacement("E2"); // Add a stone

        Polygon hex = new Polygon();
        hex.setId("E3"); // Assume not a valid move (depends on your showErrorMessage logic)
        hex.setDisable(false);
        hex.setPickOnBounds(true);
        gameController.boardPane.getChildren().add(hex);  // Needed for scene graph

        MouseEvent event = new MouseEvent(
                hex, // <<-- this is the actual source node
                hex, // target node
                MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0,
                null, 1, false, false, false, false,
                false, false, false, false,
                false, false,
                new PickResult(hex, 0, 0)
        );


        int initialCount = gameController.boardPane.getChildren().size();
        gameUIController.placeStone(event);
        int finalCount = gameController.boardPane.getChildren().size();

        // Should not place a stone if invalid
        assertEquals(initialCount, finalCount);
    }

    // testPlaceStone: Simulate a mouse click on a disabled hexagon
    @Test
    public void testPlaceStone_disabledHexagon() {
        Polygon hex = new Polygon();
        hex.setId("D4");
        hex.setDisable(true);
        hex.setPickOnBounds(true);
        gameController.boardPane.getChildren().add(hex);

        MouseEvent event = new MouseEvent(
                hex, // <<-- this is the actual source node
                hex, // target node
                MouseEvent.MOUSE_CLICKED,
                0, 0, 0, 0,
                null, 1, false, false, false, false,
                false, false, false, false,
                false, false,
                new PickResult(hex, 0, 0)
        );

        int initialCount = gameController.boardPane.getChildren().size();
        gameUIController.placeStone(event);
        int finalCount = gameController.boardPane.getChildren().size();

        assertEquals(initialCount, finalCount);
    }

    //testProcessMove_NullHex: Checking null exception for method
    @Test
    void testProcessMove_NullHex() {
        assertThrows(NullPointerException.class, () -> gameController.processMove(null));
    }

    //testWinningMove_RedWins: Checking winning logic, red should win
    @Test
    public void testIsWinningMove_RedWins() {
        //Blue has no stones -> red wins
        GameController.turn.resetTurn();
        gameController.logStonePlacement("A1");
        assertTrue(gameController.isWinningMove());
    }

    //testWinningMove_BlueWins: Checking winning logic, blue should win
    @Test
    public void testIsWinningMove_BlueWins() {
        //Red has no stones -> blue wins
        GameController.turn.switchTurn();
        gameController.logStonePlacement("A1");
        assertTrue(gameController.isWinningMove());
    }

    //testWinningMove_NoWins: Checking winning logic, not winning state
    @Test
    public void testIsWinningMove_NoWins() {
        GameController.turn.resetTurn();
        gameController.logStonePlacement("B1"); // Red
        gameController.logStonePlacement("B2"); // Red

        GameController.turn.switchTurn();
        gameController.logStonePlacement("A1"); // Blue
        gameController.logStonePlacement("A2"); // Blue

        // Both red and blue have stones
        assertFalse(gameController.isWinningMove());
    }
}
