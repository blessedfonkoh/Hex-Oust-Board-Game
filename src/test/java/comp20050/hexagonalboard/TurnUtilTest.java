package comp20050.hexagonalboard;

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

    @BeforeAll
    static void setupJavaFX() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    @BeforeEach
    void setUp() {
        turnPane = new Pane();
        turnUtil = new TurnUtil(turnPane);
    }

    @Test
    void testInitialTurn() {
        assertTrue(turnUtil.isRedTurn());
    }

    @Test
    void testSwitchTurn() {
        boolean before = turnUtil.isRedTurn();
        turnUtil.switchTurn();
        assertNotEquals(before, turnUtil.isRedTurn());
    }

    @Test
    void testResetTurn() {
        turnUtil.switchTurn();
        assertFalse(turnUtil.isRedTurn());
        turnUtil.resetTurn();
        assertTrue(turnUtil.isRedTurn());
    }

    @Test
    void testDisplayTurnRed() {
        turnUtil.resetTurn();
        turnUtil.displayTurn();
        Circle circle = (Circle) turnPane.getChildren().get(turnPane.getChildren().size() - 1);
        assertEquals(Color.RED, circle.getFill());
        assertEquals(Color.MAROON, circle.getStroke());
    }

    @Test
    void testDisplayTurnBlue() {
        turnUtil.switchTurn();
        turnUtil.displayTurn();
        Circle circle = (Circle) turnPane.getChildren().get(turnPane.getChildren().size() - 1);
        assertEquals(Color.BLUE, circle.getFill());
        assertEquals(Color.NAVY, circle.getStroke());
    }
}
