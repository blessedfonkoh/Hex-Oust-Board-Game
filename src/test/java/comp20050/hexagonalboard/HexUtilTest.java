package comp20050.hexagonalboard;

import GameController.utils.HexUtil;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class HexUtilTest {

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

    @Test
    void testGetNeighbourHex_CenterHex() {
        List<String> expected = Arrays.asList("H7", "H8", "G6", "G8", "F6", "F7");
        List<String> result = invokeGetNeighbourHex("G7");  // Helper to access private method
        assertEquals(expected, result);

        expected = Arrays.asList("B1", "B2", "A2");
        result = invokeGetNeighbourHex("A1");  // Helper to access private method
        assertEquals(expected, result); //Fails, returns [B1, B2, A0, A2, @0, @1]
    }

    @Test
    void testGetGroup_SingleHex() {
        List<String> playerHexagons = List.of("B2");
        List<String> group = HexUtil.getGroup("B2", playerHexagons);
        assertEquals(List.of("B2"), group);
    }

    @Test
    void testGetGroup_ConnectedHexes() {
        List<String> playerHexagons = List.of("B2", "B3", "C2", "C3", "C4");
        List<String> group = HexUtil.getGroup("B2", playerHexagons);
        assertTrue(group.containsAll(List.of("B2", "B3", "C2", "C3", "C4")));
        assertEquals(5, group.size());
    }

    @Test
    void testGetGroup_DisconnectedHexes() {
        List<String> playerHexagons = List.of("A1", "H9");
        List<String> group1 = HexUtil.getGroup("A1", playerHexagons);
        List<String> group2 = HexUtil.getGroup("H9", playerHexagons);
        assertEquals(List.of("A1"), group1);
        assertEquals(List.of("H9"), group2);

    }

    @Test
    void testIsInvalidMove_FirstMove() {
        List<String> players = new ArrayList<>();
        List<String> opponents = new ArrayList<>();
        boolean result = HexUtil.isInvalidMove(players, opponents, "A1");
        assertFalse(result);  // First move should be valid
    }

    @Test
    void testIsInvalidMove_ValidCapture() {
        List<String> players = List.of("H5");
        List<String> opponents = List.of("I5");
        boolean result = HexUtil.isInvalidMove(players, opponents, "H4");
        assertFalse(result);
    }

    @Test
    void testIsInvalidMove_ValidNonCapture() {
        List<String> player = List.of("C1", "D2");
        List<String> opponent = List.of("C2");

        boolean result = HexUtil.isInvalidMove(player, opponent, "H5");
        assertFalse(result); //Is valid
    }

    @Test
    void testIsInvalidMove_InvalidMove() {
        List<String> player = List.of("C1");
        List<String> opponent = List.of();

        boolean result = HexUtil.isInvalidMove(player, opponent, "C2");
        assertTrue(result); //Is invalid
    }

    @Test
    void testIsCapturingMove_ValidCapture() {
        List<String> player = List.of("B2", "C2");
        List<String> opponent = List.of("B3");

        List<String> captured = HexUtil.isCapturingMove("C3", player, opponent);
        assertNotNull(captured);
        assertTrue(captured.contains("B3"));
    }

    @Test
    void testIsCapturingMove_InvalidCapture() {
        List<String> player = List.of("A1");
        List<String> opponent = List.of("B2", "B1");
        List<String> captured = HexUtil.isCapturingMove("A2", player, opponent);
        assertNull(captured);
    }

    // Helper to access private method getNeighbourHex using reflection
    private List<String> invokeGetNeighbourHex(String hexId) {
        try {
            var method = HexUtil.class.getDeclaredMethod("getNeighbourHex", String.class);
            method.setAccessible(true);
            //noinspection unchecked
            return (List<String>) method.invoke(null, hexId);
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
            return List.of();
        }
    }
}
