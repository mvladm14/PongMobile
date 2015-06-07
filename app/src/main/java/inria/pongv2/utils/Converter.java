package inria.pongv2.utils;

import inria.pongv2.models.ParcelableBallCoordinates;
import inria.pongv2.models.ParcelablePhoneCoords;
import inria.pongv2.models.ParcelablePlayer;
import models.BallCoordinates;
import models.PhoneCoordinates;
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

        ParcelablePhoneCoords coords = convertGyroCoordsToParcelableGyroCoords(player.getPhoneCoordinates());

        return new ParcelablePlayer(player.getId(), player.getUsername(), coords);
    }

    public static ParcelablePhoneCoords convertGyroCoordsToParcelableGyroCoords(PhoneCoordinates phoneCoordinates) {
        return new ParcelablePhoneCoords(phoneCoordinates.getX(), phoneCoordinates.getY(), phoneCoordinates.getZ());
    }

    public static ParcelableBallCoordinates convertCoordinatesToParcelable(BallCoordinates ballCoordinates) {
        return new ParcelableBallCoordinates(ballCoordinates.getX(), ballCoordinates.getY());
    }
}
