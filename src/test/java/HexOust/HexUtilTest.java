package HexOust;

import GameController.utils.HexUtil;
import org.junit.jupiter.api.Test;
import java.util.*;

import static GameController.utils.HexUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class HexUtilTest {

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
        List<String> players = new ArrayList<>();
        List<String> opponents = new ArrayList<>();
        boolean result = isValidMove(players, opponents, "A1");
        assertTrue(result);  // First move should be valid
    }

    // Test a valid capturing move scenario
    @Test
    void testIsValidMove_ValidCapture() {
        List<String> players = List.of("H5");
        List<String> opponents = List.of("I5");
        boolean result = isValidMove(players, opponents, "H4");
        assertTrue(result);
    }

    // Test a valid move that is not a capturing move scenario
    @Test
    void testIsValidMove_ValidNonCapture() {
        List<String> player = List.of("C1", "D2");
        List<String> opponent = List.of("C2");

        boolean result = isValidMove(player, opponent, "H5");
        assertTrue(result); // Valid non-capturing move
    }

    // Test an invalid move (not adjacent and not a capture)
    @Test
    void testIsValidMove_InValidMove() {
        List<String> player = List.of("C1");
        List<String> opponent = List.of();

        boolean result = isValidMove(player, opponent, "C2");
        assertFalse(result); // Move is invalid
    }

    // Test that a valid capturing move returns the correct opponent hex
    @Test
    void testCapturedStones_ValidCapture() {
        List<String> player = List.of("B2", "C2");
        List<String> opponent = List.of("B3");

        List<String> captured = capturedStones("C3", player, opponent);
        assertNotNull(captured);
        assertTrue(captured.contains("B3"));
    }

    // Test that a non-capturing move returns null (no capture)
    @Test
    void testCapturedStones_InvalidCapture() {
        List<String> player = List.of("A1");
        List<String> opponent = List.of("B2", "B1");
        List<String> captured = capturedStones("A2", player, opponent);
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
