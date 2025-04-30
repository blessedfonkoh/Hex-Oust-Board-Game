package HexOust;

import GameController.utils.*;
import static GameController.utils.HexUtil.*;
import static HexOust.GameController.*;

import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class HexUtilTest {
    @BeforeAll
    static void setUp() {
        GameController.turn = new TurnUtil(new Pane());
    }

    // Test for getting neighbours of a center hex ("G7"), expecting 6 surrounding hexes
    @Test
    void testGetNeighbourHex_CenterHex() {
        List<String> expected = Arrays.asList("H7", "H8", "G6", "G8", "F6", "F7");
        List<String> result = invokeGetNeighbourHex("G7");  // Helper to access private method
        assertEquals(expected, result);
    }

    // Test that a single hex returns itself as a group
    @Test
    void testGetGroup_SingleHex() {
        List<String> playerHexagons = List.of("B2");
        List<String> group = getGroup("B2", playerHexagons);
        assertEquals(List.of("B2"), group);
    }

    // Test that all connected hexes are grouped correctly
    @Test
    void testGetGroup_ConnectedHexes() {
        List<String> playerHexagons = List.of("B2", "B3", "C2", "C3", "C4");
        List<String> group = getGroup("B2", playerHexagons);
        assertTrue(group.containsAll(List.of("B2", "B3", "C2", "C3", "C4")));
        assertEquals(5, group.size());
    }

    // Test that disconnected hexes are not grouped together
    @Test
    void testGetGroup_DisconnectedHexes() {
        List<String> playerHexagons = List.of("A1", "H9");
        List<String> group1 = getGroup("A1", playerHexagons);
        assertEquals(List.of("A1"), group1);
        List<String> group2 = getGroup("H9", playerHexagons);
        assertEquals(List.of("H9"), group2);
    }

    // First move should always be valid (no surrounding constraints)
    @Test
    void testIsValidMove_FirstMove() {

        boolean result = isValidMove( "A1");
        assertTrue(result);  // First move should be valid
    }

    // Test a valid capturing move scenario
    @Test
    void testIsValidMove_ValidCapture() {
        getCurrentPlayerHexes().add("H5");
        getOpponentPlayerHexes().add("I5");
        boolean result = isValidMove( "H4");
        assertTrue(result);
    }

    // Test a valid move that is not a capturing move scenario
    @Test
    void testIsValidMove_ValidNonCapture() {

        getCurrentPlayerHexes().add("C1");
        getCurrentPlayerHexes().add("D2");
        getOpponentPlayerHexes().add("C2");
        boolean result = isValidMove("H5");
        assertTrue(result); // Valid non-capturing move
    }

    // Test an invalid move (not adjacent and not a capture)
    @Test
    void testIsValidMove_InValidMove() {

        getCurrentPlayerHexes().add("C1");
        boolean result = isValidMove("C2");

        assertFalse(result); // Move is invalid
    }

    // Test that a valid capturing move returns the correct opponent hex
    @Test
    void testCapturedStones_ValidCapture() {

        getCurrentPlayerHexes().add("B2");
        getCurrentPlayerHexes().add("C2");
        getOpponentPlayerHexes().add("B3");

        List<String> captured = capturedStones("C3");
        assertNotNull(captured);
        assertTrue(captured.contains("B3"));
    }

    // Test that a non-capturing move returns null (no capture)
    @Test
    void testCapturedStones_InvalidCapture() {

        getCurrentPlayerHexes().add("A1");
        getOpponentPlayerHexes().add("B2");
        getOpponentPlayerHexes().add("B1");
        List<String> captured = capturedStones("A2");
        assertNull(captured);
    }

    // Helper method to access the private getNeighbourHex method using reflection
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
