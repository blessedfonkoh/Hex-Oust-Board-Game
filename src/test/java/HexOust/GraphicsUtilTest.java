package HexOust;

import GameController.utils.GraphicsUtil;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class GraphicsUtilTest {

    private GameController gameController;
    private GraphicsUtil graphicsUtil;

    // Ensure JavaFX environment is initialized before any test runs
    @BeforeAll
    static void setupJavaFX() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    // Set up the game controller and graphics utility before each test
    @BeforeEach
    void setUp() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("game-view.fxml"));
        Parent root = fxmlLoader.load();
        gameController = fxmlLoader.getController();
        gameController.boardPane = (Pane) root.lookup("#boardPane");
        gameController.turnPane = (Pane) root.lookup("#turnPane");
        gameController.initialize();

        graphicsUtil = new GraphicsUtil(gameController.boardPane, gameController.turn);
    }

    // Test if an X is successfully created on a specific hex tile
    @Test
    void testCreateX() {
        Polygon hex = (Polygon) gameController.boardPane.lookup("#A1");
        assertNotNull(hex);
        graphicsUtil.createX(hex);
        assertTrue(gameController.boardPane.getChildren().contains(graphicsUtil.getXLine1()));
        assertTrue(gameController.boardPane.getChildren().contains(graphicsUtil.getXLine2()));
    }

    // Test that an X can be removed properly from a hex tile
    @Test
    void testRemoveX() {
        Polygon hex = (Polygon) gameController.boardPane.lookup("#H5");
        assertNotNull(hex);
        graphicsUtil.createX(hex);
        assertTrue(gameController.boardPane.getChildren().contains(graphicsUtil.getXLine1()));
        assertTrue(gameController.boardPane.getChildren().contains(graphicsUtil.getXLine2()));

        graphicsUtil.removeX();
        assertFalse(gameController.boardPane.getChildren().contains(graphicsUtil.getXLine1()));
        assertFalse(gameController.boardPane.getChildren().contains(graphicsUtil.getXLine2()));
    }

    // Test if a tick mark is successfully created on a specific hex tile
    @Test
    void testCreateTick() {
        Polygon hex = (Polygon) gameController.boardPane.lookup("#J5");
        assertNotNull(hex);
        graphicsUtil.createTick(hex);
        assertTrue(gameController.boardPane.getChildren().contains(graphicsUtil.getTickLine1()));
        assertTrue(gameController.boardPane.getChildren().contains(graphicsUtil.getTickLine2()));
    }

    // Test that a tick mark can be removed properly from a hex tile
    @Test
    void testRemoveTick() {
        Polygon hex = (Polygon) gameController.boardPane.lookup("#B4");
        assertNotNull(hex);
        graphicsUtil.createTick(hex);
        assertTrue(gameController.boardPane.getChildren().contains(graphicsUtil.getTickLine1()));
        assertTrue(gameController.boardPane.getChildren().contains(graphicsUtil.getTickLine2()));

        graphicsUtil.removeTick();
        assertFalse(gameController.boardPane.getChildren().contains(graphicsUtil.getTickLine1()));
        assertFalse(gameController.boardPane.getChildren().contains(graphicsUtil.getTickLine2()));
    }

}
