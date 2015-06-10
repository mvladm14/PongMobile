package inria.pongv2.utils;

import inria.pongv2.models.ParcelableBallCoordinates;
import models.ball.BallCoordinates;

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

    public static ParcelableBallCoordinates convertCoordinatesToParcelable(BallCoordinates ballCoordinates) {
        return new ParcelableBallCoordinates(ballCoordinates.getX(), ballCoordinates.getY());
    }
}
