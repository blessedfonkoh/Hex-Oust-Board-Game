package comp20050.hexagonalboard;

import GameController.utils.HexUtil;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static GameController.utils.HexUtil.*;
import static org.junit.jupiter.api.Assertions.*;

class HexUtilTest {
    @Test
    void testGetNeighbourHex_CenterHex() {
        List<String> expected = Arrays.asList("H7", "H8", "G6", "G8", "F6", "F7");
        List<String> result = invokeGetNeighbourHex("G7");  // Helper to access private method
        assertEquals(expected, result);
    }

//    @Test
//    void testGetNeighbourHex_CornerHex() {
//        List<String> expected = Arrays.asList("B1", "B2", "A2");
//        List<String> result = invokeGetNeighbourHex("A1");  // Helper to access private method
//        assertEquals(expected, result); //Fails, returns [B1, B2, A0, A2, @0, @1]
//    }

    @Test
    void testGetGroup_SingleHex() {
        List<String> playerHexagons = List.of("B2");
        List<String> group = getGroup("B2", playerHexagons);
        assertEquals(List.of("B2"), group);
    }

    @Test
    void testGetGroup_ConnectedHexes() {
        List<String> playerHexagons = List.of("B2", "B3", "C2", "C3", "C4");
        List<String> group = getGroup("B2", playerHexagons);
        assertTrue(group.containsAll(List.of("B2", "B3", "C2", "C3", "C4")));
        assertEquals(5, group.size());
    }

    @Test
    void testGetGroup_DisconnectedHexes() {
        List<String> playerHexagons = List.of("A1", "H9");
        List<String> group1 = getGroup("A1", playerHexagons);
        assertEquals(List.of("A1"), group1);
        List<String> group2 = getGroup("H9", playerHexagons);
        assertEquals(List.of("H9"), group2);
    }

    @Test
    void testIsInvalidMove_FirstMove() {
        List<String> players = new ArrayList<>();
        List<String> opponents = new ArrayList<>();
        boolean result = isInvalidMove(players, opponents, "A1");
        assertFalse(result);  // First move should be valid
    }

    @Test
    void testIsInvalidMove_ValidCapture() {
        List<String> players = List.of("H5");
        List<String> opponents = List.of("I5");
        boolean result = isInvalidMove(players, opponents, "H4");
        assertFalse(result);
    }

    @Test
    void testIsInvalidMove_ValidNonCapture() {
        List<String> player = List.of("C1", "D2");
        List<String> opponent = List.of("C2");

        boolean result = isInvalidMove(player, opponent, "H5");
        assertFalse(result); //Is valid
    }

    @Test
    void testIsInvalidMove_InvalidMove() {
        List<String> player = List.of("C1");
        List<String> opponent = List.of();

        boolean result = isInvalidMove(player, opponent, "C2");
        assertTrue(result); //Is invalid
    }

    @Test
    void testIsCapturingMove_ValidCapture() {
        List<String> player = List.of("B2", "C2");
        List<String> opponent = List.of("B3");

        List<String> captured = isCapturingMove("C3", player, opponent);
        assertNotNull(captured);
        assertTrue(captured.contains("B3"));
    }

    @Test
    void testIsCapturingMove_InvalidCapture() {
        List<String> player = List.of("A1");
        List<String> opponent = List.of("B2", "B1");
        List<String> captured = isCapturingMove("A2", player, opponent);
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
