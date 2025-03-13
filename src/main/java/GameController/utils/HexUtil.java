package GameController.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HexUtil {
    /**
     * Method to get all the invalid hexagons that are restricted for the current player
     *
     * @param playersHexagons : A list of hexagon IDs representing the hexagons occupied by the current player.
     * @return invalidHexes : A list of hexagon IDs that are invalid for placement based on the player's occupied hexagons.
     */
    public static List<String> getInvalidHexes(List<String> playersHexagons) {

        List<String> invalidHexes = new ArrayList<>();
        for (String hexId : playersHexagons) {
            invalidHexes.addAll(getNeighbourHex(hexId));
        }
        return invalidHexes;
    }

    /**
     * Method to get all valid neighbouring hexagons for a given Hex ID
     *
     * @param hexId : The ID of the hexagon
     * @return A list of hexagon IDs representing the neighboring hexagons.
     */
    private static List<String> getNeighbourHex(String hexId) {
        char letter = hexId.charAt(0); // Letter for the row e.g A,B,C
        int num = Integer.parseInt(hexId.substring(1)); // Number for the column

        return new ArrayList<>(Arrays.asList(String.valueOf((char) (letter + 1)) + num,  // Top
                String.valueOf((char) (letter + 1)) + (num + 1), // Top
                String.valueOf(letter) + (num - 1),  // Left
                String.valueOf(letter) + (num + 1), // Right
                String.valueOf((char) (letter - 1)) + (num - 1), // Bottom left
                String.valueOf((char) (letter - 1)) + num));
    }

    public static List<String> getGroup(String hexId, List<String> playerHexagons) {
        List<String> group = new ArrayList<>();
        List<String> visited = new ArrayList<>();

        // check if the given hexagon is occupied by the player
        if (playerHexagons.contains(hexId)) {
            search(hexId, playerHexagons, visited, group);
        }

        return group;
    }

    /**
     * Method to get all the neighbours of stones with the same colour,
     * Recursively gets the neighbours of same colour and adds them to the group of where stone was placed.
     *
     * @param hexId the id of the hexagons you are searching.
     * @param playerHexagons list containing a players placed stones.
     * @param visited list to keep track of visited hexagons.
     * @param group list to store HexIds of the connected hexagons of the same colour.
     */
    private static void search(String hexId, List<String> playerHexagons, List<String> visited, List<String> group) {
        visited.add(hexId);
        group.add(hexId);

        // get neighbors of the current hexagon
        List<String> neighbors = HexUtil.getNeighbourHex(hexId);

        for (String neighbor : neighbors) {
            // recursive call
            // check if the neighbor is occupied by the player and not visited
            if (playerHexagons.contains(neighbor) && !visited.contains(neighbor)) {
                search(neighbor, playerHexagons, visited, group);
            }
        }
    }

    public static List<String> isCapturingMove(String hexId, List<String> playerHexagons, List<String> opponentHexagons) {
        List<String> group = getGroup(hexId, playerHexagons);
        List<String> removeStones = new ArrayList<>();

        int maxGroup = 0;
        for (String s : group) {
            List<String> neighbours = getNeighbourHex(s);
            for (String neighbour : neighbours) {
                if (opponentHexagons.contains(neighbour)) {
                    // For each neighbour, need to get the full group of the opponent
                    List<String> opponentGroup = getGroup(neighbour, opponentHexagons);
                    //System.out.println("To delete: " + opponentGroup);

                    // Add all grouped stones into removeStones list
                    removeStones.addAll(opponentGroup);
                    maxGroup = Math.max(maxGroup, getGroup(neighbour, opponentHexagons).size());
                }
            }
        }
        //check ravis example for bigger or equal
        if (getGroup(hexId, playerHexagons).size() > maxGroup && maxGroup > 0) {
            return removeStones;
        }
        return null;
    }
}


