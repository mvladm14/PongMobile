package inria.pongv2.utils;

import models.ball.BallCoordinates;
import models.phone.Accelerometer;
import models.phone.Gravity;
import models.phone.LinearAcceleration;
import models.phone.MagneticField;
import restInterfaces.PlayerSvcApi;
import restInterfaces.PongBallSvcApi;
import retrofit.RestAdapter;

/**
 * Created by Vlad on 6/2/2015.
 */
public class Downloader {

    private static final String SERVER = "http://131.254.101.102:8080/myriads";

    private static PlayerSvcApi playerSvcApi = new RestAdapter.Builder().setEndpoint(SERVER)
            .build().create(PlayerSvcApi.class);

    private static PongBallSvcApi ballSvcApi = new RestAdapter.Builder().setEndpoint(SERVER)
            .build().create(PongBallSvcApi.class);

    public static BallCoordinates downloadBallCoordinates() {
        return ballSvcApi.getCoordinates(1);
    }

    public static void uploadAcceleroemeterValues(float[] accelerometer) {
        Accelerometer acc = new Accelerometer();
        acc.setValues(accelerometer);
        playerSvcApi.setAccelerometer(1, acc);
    }

    public static void uploadMagneticFieldValues(float[] magnetic) {
        MagneticField magneticField = new MagneticField();
        magneticField.setValues(magnetic);
        playerSvcApi.setMagnetic(1, magneticField);
    }

    public static void uploadTimeStamp(long sensorTimeStamp) {
        playerSvcApi.setTimeStamp(1, sensorTimeStamp);
    }

    public static void uploadGravityValues(float[] gravity) {
        Gravity gr = new Gravity();
        gr.setValues(gravity);
        playerSvcApi.setGravity(1, gr);
    }

    public static void uploadLinearAcceleration(float[] linear_acceleration) {
        LinearAcceleration linearAcceleration = new LinearAcceleration();
        linearAcceleration.setValues(linear_acceleration);
        playerSvcApi.setLinearAcceleration(1, linearAcceleration);

    }
}
