package HexOust;

import GameController.utils.TurnUtil;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class TurnUtilTest {

    private Pane turnPane;
    private TurnUtil turnUtil;

    // Initialize JavaFX runtime before all test cases
    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    // Set up new instances of TurnUtil before each test
    @BeforeEach
    void setUp() {
        turnPane = new Pane();
        turnUtil = new TurnUtil(turnPane);
    }

    // Test initial turn when game is launched: red player always starts first
    @Test
    void testInitialTurn() {
        assertTrue(turnUtil.isRedTurn());
    }

    // Test switchTurn method: checking blue player after switching from initial turn
    @Test
    void testSwitchTurn() {
        boolean initial = turnUtil.isRedTurn();
        turnUtil.switchTurn();
        assertNotEquals(initial, turnUtil.isRedTurn());
    }

    // Test resetStone method: checking turn state after game reset
    @Test
    void testResetTurn() {
        turnUtil.switchTurn();
        assertFalse(turnUtil.isRedTurn());
        turnUtil.resetTurn();
        assertTrue(turnUtil.isRedTurn());
    }

    // Test displayTurn method: checking turn pane UI displays red circle
    @Test
    void testDisplayTurnRed() {
        turnUtil.resetTurn();
        turnUtil.displayTurn();
        Circle circle = (Circle) turnPane.getChildren().get(turnPane.getChildren().size() - 1);
        assertEquals(Color.RED, circle.getFill());
        assertEquals(Color.MAROON, circle.getStroke());
    }

    // Test displayTurn method: checking turn pane UI displays blue circle
    @Test
    void testDisplayTurnBlue() {
        turnUtil.switchTurn();
        turnUtil.displayTurn();
        Circle circle = (Circle) turnPane.getChildren().get(turnPane.getChildren().size() - 1);
        assertEquals(Color.BLUE, circle.getFill());
        assertEquals(Color.NAVY, circle.getStroke());
    }
}
