package HexOust;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private GameController gameController;

    // Initialize JavaFX runtime before all test cases
    @BeforeAll
    static void setupJavaFX() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    // Load FXML file resources and set up new instances of GameController before each test
    @BeforeEach
    void setUp(){
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("game-view.fxml"));
        try {
            Parent root = fxmlLoader.load();
            gameController = fxmlLoader.getController();
            gameController.boardPane = (Pane) root.lookup("#boardPane");
            gameController.turnPane = (Pane) root.lookup("#turnPane");
            gameController.initialize();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Test logStonePlacement method: verify stone placement logs for red player
    @Test
    void testLogStonePlacementRed() {
        gameController.turn.resetTurn();
        gameController.logStonePlacement("D5");
        assertTrue(gameController.getRedHexagons().contains("D5"));
        assertFalse(gameController.getBlueHexagons().contains("D5"));
    }

    // Test logStonePlacement method: verify stone placement logs for blue player
    @Test
    void testLogStonePlacementBlue() {
        gameController.turn.switchTurn();
        gameController.logStonePlacement("E6");
        assertTrue(gameController.getBlueHexagons().contains("E6"));
        assertFalse(gameController.getRedHexagons().contains("E6"));
    }

    // Test createStone method: testing dimensions and properties of stone
    @Test
    void testCreateStone() {
        Polygon hexagon = new Polygon();
        Circle stone = gameController.createStone(hexagon);

        assertNotNull(stone);
        assertEquals(12, stone.getRadius());
        assertEquals(gameController.turn.isRedTurn() ? Color.RED : Color.BLUE, stone.getFill());
    }

    // Test the restartGame method: ensure method resets the game state properly
    @Test
    void testRestartGame() {
        //  Creating RED Stone on the board
        gameController.getRedHexagons().add("A1");
        Circle redStone = new Circle(12, Color.RED);
        gameController.boardPane.getChildren().add(redStone);

        // Creating BLUE stone on the board
        gameController.getBlueHexagons().add("B2");
        Circle blueStone = new Circle(12, Color.BLUE);
        gameController.boardPane.getChildren().add(blueStone);

        // Creating a disabled Hex
        Polygon disabledHex = new Polygon();
        disabledHex.setDisable(true);
        gameController.boardPane.getChildren().add(disabledHex);

        gameController.restartGame();

        // All Lists should be empty after restarting game
        assertTrue(gameController.getRedHexagons().isEmpty());
        assertTrue(gameController.getBlueHexagons().isEmpty());

        assertFalse(gameController.boardPane.getChildren().contains(redStone)); // All stones should be cleared from the board
        assertTrue(disabledHex.isDisabled()); // Hex should now be enabled
    }

}
