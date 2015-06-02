package inria.pongv2.utils;

import inria.pongv2.models.ParcelableCoordinates;
import inria.pongv2.models.ParcelableGyroCoords;
import inria.pongv2.models.ParcelablePlayer;
import models.Coordinates;
import models.GyroscopeCoordinates;
import models.Player;

/**
 * Created by Vlad on 5/29/2015.
 */
public class Converter {

    public static double[] convertFloatsToDoubles(float[] input) {
        if (input == null) {
            return null; // Or throw an exception - your choice
        }
        double[] output = new double[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = input[i];
        }
        return output;
    }

    public static ParcelablePlayer convertPlayerToParcelablePlayer(Player player) {

        ParcelableGyroCoords coords = convertGyroCoordsToParcelableGyroCoords(player.getGyroscopeCoordinates());

        return new ParcelablePlayer(player.getId(), player.getUsername(), coords);
    }

    public static ParcelableGyroCoords convertGyroCoordsToParcelableGyroCoords(GyroscopeCoordinates gyroscopeCoordinates) {
        return new ParcelableGyroCoords(gyroscopeCoordinates.getX(), gyroscopeCoordinates.getY(), gyroscopeCoordinates.getZ());
    }

    public static ParcelableCoordinates convertCoordinatesToParcelable(Coordinates coordinates) {
        return new ParcelableCoordinates(coordinates.getX(),coordinates.getY());
    }
}
