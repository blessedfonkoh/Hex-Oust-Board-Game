package GameController.utils;
import java.util.*;

public class HexUtil {

    /**
     * Checks if a Hexagon ID is a valid move.
     *
     * @param playersHexagons list containing a player's placed stones.
     * @param opponentsHexagons list containing opponent's placed stones.
     * @param hexId ID of Hexagon which player has hovered over.
     * @return true if Hexagon is a valid move, false otherwise.
     */
    public static boolean isValidMove(List<String> playersHexagons, List<String> opponentsHexagons, String hexId) {

       List<String> neighbours = getNeighbourHex(hexId); // Gets all neighbours of hexagon hovered over.
       List<String> playerNeighbours = new ArrayList<>();

       // Get neighbouring hexagons occupied by player.
        for(String n : neighbours) {
            if (playersHexagons.contains(n)) {
                playerNeighbours.add(n);
            }
        }
        // If no neighbours are occupied by player, it's a valid move.
        if(playerNeighbours.isEmpty()){
            return true;
        }
        // If capturedStones is not null, move is valid.
        return capturedStones(hexId, playersHexagons, opponentsHexagons) != null;
    }

    /**
     * Gets all neighbouring Hex IDs for a given Hex ID
     *
     * @param hexId : The ID of the hexagon to get surrounding neighbours of
     * @return A list of hexagon IDs representing the neighbouring hexagons.
     */
    private static List<String> getNeighbourHex(String hexId) {
        char letter = hexId.charAt(0); // Letter for the row e.g A,B,C
        int num = Integer.parseInt(hexId.substring(1)); // Number for the column

        // Get surrounding Hex IDs
        return new ArrayList<>(Arrays.asList(String.valueOf((char) (letter + 1)) + num,
                String.valueOf((char) (letter + 1)) + (num + 1),
                String.valueOf(letter) + (num - 1),
                String.valueOf(letter) + (num + 1),
                String.valueOf((char) (letter - 1)) + (num - 1),
                String.valueOf((char) (letter - 1)) + num));
    }

    /**
     * Returns a list of hexagon IDs that form a connected group, including the given hexId.
     *
     * @param hexId The ID of the hexagon to start the group search from.
     * @param playerHexagons The list of hexagon IDs occupied by the player.
     * @return A list of hexagon IDs that form a connected group.
     */
    public static List<String> getGroup(String hexId, List<String> playerHexagons) {
        if (playerHexagons.contains(hexId)) {
            return searchGroup(hexId, playerHexagons, new ArrayList<>());
        }
        return null;
    }


    /**
     * Recursively gets all neighbours of stones with the same colour,
     *
     * @param hexId id of the hexagon you are searching.
     * @param playerHexagons list containing a players placed stones.
     * @param visited list to keep track of visited hexagons.
     * @return A list of hexagon IDs forming a connected group.
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
     * Gets captured stones.
     *
     * @param hexId ID of the hexagon where the stone is being placed.
     * @param playerHexagons list of hexagon IDs occupied by player.
     * @param opponentHexagons list of hexagon IDs occupied by opponent.
     * @return  Hexagon IDs representing the opponent's stones to be removed if the move is capturing.
     *          Return Null otherwise.
     */
    public static List<String> capturedStones(String hexId, List<String> playerHexagons, List<String> opponentHexagons) {

        // Copy playerHexagons to add hexID (the hovered hexagon) to the list.
        List<String> playerHexCopy = new ArrayList<>(playerHexagons);
        playerHexCopy.add(hexId);

        List<String> playerGroup = getGroup(hexId, playerHexCopy);
        List<String> capturedStones = new ArrayList<>();

        int opponentSize = 0; // Track size of opponent's largest group

        for (String hex : playerGroup) {
            List<String> neighbours = getNeighbourHex(hex);
            for (String n : neighbours) {
                if (opponentHexagons.contains(n)) {
                    List<String> opponentGroup = getGroup(n, opponentHexagons);

                    // Add all stones to be removed
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


