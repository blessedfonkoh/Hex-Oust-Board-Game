package comp20050.hexagonalboard;

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
                String.valueOf((letter)) + (num - 1),  // Left
                String.valueOf((letter)) + (num + 1), // Right
                String.valueOf((char) (letter - 1)) + (num - 1), // Bottom left
                String.valueOf((char) (letter - 1)) + num));
    }
}
