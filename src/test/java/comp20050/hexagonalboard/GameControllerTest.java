package comp20050.hexagonalboard;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private GameController gameController;

    @BeforeAll
    static void setupJavaFX() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    @BeforeEach
    void setUp() {
        gameController = new GameController();
        gameController.boardPane = new Pane();
        gameController.turnPane = new Pane();
        gameController.initialize();
    }

    @Test
    void testLogStonePlacementRed() {
        gameController.turn.resetTurn();
        gameController.logStonePlacement("D5");
        assertTrue(gameController.redHexagons.contains("D5"));
        assertFalse(gameController.blueHexagons.contains("D5"));
    }

    @Test
    void testLogStonePlacementBlue() {
        gameController.turn.switchTurn();
        gameController.logStonePlacement("E6");
        assertTrue(gameController.blueHexagons.contains("E6"));
        assertFalse(gameController.redHexagons.contains("E6"));
    }

    @Test
    void testCreateStone() {
        Polygon hexagon = new Polygon();
        Circle stone = gameController.createStone(hexagon);

        assertNotNull(stone);
        assertEquals(12, stone.getRadius());
        assertEquals(gameController.turn.isRedTurn() ? Color.RED : Color.BLUE, stone.getFill());
    }

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
