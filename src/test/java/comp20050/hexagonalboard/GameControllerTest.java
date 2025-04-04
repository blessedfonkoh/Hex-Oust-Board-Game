package comp20050.hexagonalboard;

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
    void setUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("game-view.fxml"));
        Parent root = fxmlLoader.load();
        gameController = fxmlLoader.getController();
        gameController.boardPane = (Pane) root.lookup("#boardPane");
        gameController.turnPane = (Pane) root.lookup("#turnPane");
        gameController.initialize();
    }

    // Test logStonePlacement method: verify stone placement logs for red player
    @Test
    void testLogStonePlacementRed() {
        gameController.turn.resetTurn();
        gameController.logStonePlacement("D5");
        assertTrue(gameController.redHexagons.contains("D5"));
        assertFalse(gameController.blueHexagons.contains("D5"));
    }

    // Test logStonePlacement method: verify stone placement logs for blue player
    @Test
    void testLogStonePlacementBlue() {
        gameController.turn.switchTurn();
        gameController.logStonePlacement("E6");
        assertTrue(gameController.blueHexagons.contains("E6"));
        assertFalse(gameController.redHexagons.contains("E6"));
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
        gameController.redHexagons.add("A1");
        gameController.blueHexagons.add("B2");

        Circle redStone = new Circle(12, Color.RED);
        Circle blueStone = new Circle(12, Color.BLUE);
        gameController.boardPane.getChildren().add(redStone);
        gameController.boardPane.getChildren().add(blueStone);

        Polygon disabledHex = new Polygon();
        disabledHex.setDisable(true);
        gameController.boardPane.getChildren().add(disabledHex);

        gameController.restartGame();

        assertTrue(gameController.redHexagons.isEmpty());
        assertTrue(gameController.blueHexagons.isEmpty());
        assertFalse(gameController.boardPane.getChildren().contains(redStone));
        assertFalse(disabledHex.isDisabled());
    }
}
