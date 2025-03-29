package GameController.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HexUtil {

    /**
     * Method to check if a Hexagon ID is a valid move or not.
     *
     * @param playersHexagons list containing a players placed stones.
     * @param opponentsHexagons list containing opponents placed stones.
     * @param hexId ID of Hexagon which player has hovered over.
     * @return true if that Hexagon is a valid move, false otherwise.
     */
    public static boolean isInvalidMove(List<String> playersHexagons, List<String> opponentsHexagons, String hexId) {

       List<String> playerNeighbours = getNeighbourHex(hexId); //gets neighbours of hexagon hovered over.
       List<String> neighbouringIDs = new ArrayList<>();
        for(String n : playerNeighbours) {
            //adds the neighbours which are occupied by the player.
            if (playersHexagons.contains(n)) {
                neighbouringIDs.add(n);
            }
        }
        //if no neighbours r occupied by player, then it's a valid move (e,g the very first round)
        if(neighbouringIDs.isEmpty()){
            return false;
        }
        //if its not a capturing move, the player's group is smaller than the opponents, therefore move is invalid
        return isCapturingMove(hexId, playersHexagons, opponentsHexagons) == null;
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

    /**
     * Method that returns a list of hexagon IDs that form a connected group, including the given hexId.
     *
     * @param hexId The ID of the hexagon to start the group search from.
     * @param playerHexagons The list of hexagon IDs occupied by the player.
     * @return A list of hexagon IDs representing the connected group that includes the given hexId.
     */

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

    /**
     * method which determines if placing a stone at the given hexId would result in capturing move or not.
     * A capturing move occurs when the player's new stone creates a group larger than all the adjacent
     * opponent's groups.
     *
     * @param hexId The ID of the hexagon where the stone is being placed.
     * @param playerHexagons The list of hexagon IDs occupied by the player.
     * @param opponentHexagons The list of hexagon IDs occupied by the opponent.
     * @return A list of hexagon IDs representing the opponent's stones to be removed if the move is capturing.
     *         Returns null if the move does not result in a capture.
     */
    public static List<String> isCapturingMove(String hexId, List<String> playerHexagons, List<String> opponentHexagons) {
        //created a clone of playerHexagons.
        //this is because when a user hovers on a hex, its not listed in playerHexagons since it hasnt yet been clicked.
        //i made a clone so that i can add hexID (the hovered hexagon) to the list, so that this method can be used in
        //isValidMove.
        List<String> playerHexClone = new ArrayList<>(playerHexagons);
        playerHexClone.add(hexId);

        List<String> group = getGroup(hexId, playerHexClone);
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
        if (getGroup(hexId, playerHexClone).size() > maxGroup && maxGroup > 0) {
            return removeStones;
        }
        return null;
    }
}


