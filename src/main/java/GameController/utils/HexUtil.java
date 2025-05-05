package GameController.utils;

import static HexOust.GameController.*;

import java.util.*;

public class HexUtil {

    /**
     * Determines whether a hexagon is a valid move for the current player.
     *
     * @param hexId ID of the hexagon being considered.
     * @return true if the hexagon is a valid move, false otherwise.
     */
    public static boolean isValidMove(String hexId) {

        List<String> neighbours = getNeighbourHex(hexId);
        List<String> playerNeighbours = new ArrayList<>();

        // Collect neighbouring hexagons occupied by the current player
        for (String n : neighbours) {
            if (getCurrentPlayerHexes().contains(n)) {
                playerNeighbours.add(n);
            }
        }

        // Valid if no neighbours are occupied by player
        if (playerNeighbours.isEmpty()) {
            return true;
        }
        // Otherwise, valid only if a capture is possible
        return capturedStones(hexId) != null;
    }

    /**
     * Retrieves all valid neighbour hexagon IDs surrounding a given hex ID.
     *
     * @param hexId The ID of the hexagon to find neighbours for.
     * @return List of neighbour hexagon IDs.
     */
    private static List<String> getNeighbourHex(String hexId) {
        char letter = hexId.charAt(0); // Row character (A, B, C, etc.)
        int num = Integer.parseInt(hexId.substring(1)); // Column number

        return new ArrayList<>(Arrays.asList(String.valueOf((char) (letter + 1)) + num,
                String.valueOf((char) (letter + 1)) + (num + 1),
                String.valueOf(letter) + (num - 1),
                String.valueOf(letter) + (num + 1),
                String.valueOf((char) (letter - 1)) + (num - 1),
                String.valueOf((char) (letter - 1)) + num));
    }

    /**
     * Finds a connected group of stones belonging to the player starting from the given hex ID.
     *
     * @param hexId          ID of the starting hexagon.
     * @param playerHexagons List of hex IDs occupied by the player.
     * @return List of hexagon IDs forming the connected group, or null if hexId is not in player's list.
     */
    public static List<String> getGroup(String hexId, List<String> playerHexagons) {
        if (playerHexagons.contains(hexId)) {
            return searchGroup(hexId, playerHexagons, new ArrayList<>());
        }
        return null;
    }


    /**
     * Recursively collects connected hexagons occupied by the player.
     *
     * @param hexId          Current hexagon ID in the search.
     * @param playerHexagons List of all hexagons occupied by the player.
     * @param visited        Tracks visited hexagons to avoid cycles.
     * @return A group of connected hexagon IDs.
     */
    private static List<String> searchGroup(String hexId, List<String> playerHexagons, List<String> visited) {
        List<String> group = new ArrayList<>();
        group.add(hexId);
        visited.add(hexId);

        List<String> neighbours = HexUtil.getNeighbourHex(hexId);

        for (String n : neighbours) {
            if (playerHexagons.contains(n) && !visited.contains(n)) {
                group.addAll(searchGroup(n, playerHexagons, visited));
            }
        }
        return group;
    }


    /**
     * Determines which opponent stones are captured by placing a stone on the given hex ID.
     *
     * @param hexId ID of the hexagon where the player intends to place a stone.
     * @return List of opponent hex IDs to be removed if capture occurs, otherwise null.
     */
    public static List<String> capturedStones(String hexId) {

        List<String> playerHexCopy = new ArrayList<>(getCurrentPlayerHexes());
        playerHexCopy.add(hexId);

        List<String> playerGroup = getGroup(hexId, playerHexCopy);
        List<String> capturedStones = new ArrayList<>();

        int opponentSize = 0;

        for (String hex : playerGroup) {
            List<String> neighbours = getNeighbourHex(hex);

            for (String n : neighbours) {
                if (getOpponentPlayerHexes().contains(n)) {
                    List<String> opponentGroup = getGroup(n, getOpponentPlayerHexes());
                    capturedStones.addAll(opponentGroup);
                    opponentSize = Math.max(opponentSize, opponentGroup.size());
                }
            }
        }

        if (playerGroup.size() > opponentSize && opponentSize > 0) {
            return capturedStones;
        }
        return null;
    }
}


