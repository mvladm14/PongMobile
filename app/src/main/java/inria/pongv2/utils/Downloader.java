package inria.pongv2.utils;

import java.util.Collection;

import inria.pongv2.models.ParcelableGyroCoords;
import models.Coordinates;
import models.Player;
import restInterfaces.PlayerSvcApi;
import restInterfaces.PongBallSvcApi;
import retrofit.RestAdapter;

/**
 * Created by Vlad on 6/2/2015.
 */
public class Downloader {

    private static final String SERVER = "http://131.254.101.102:8080/PongServerSide";

    private static PlayerSvcApi playerSvcApi = new RestAdapter.Builder().setEndpoint(SERVER)
            .build().create(PlayerSvcApi.class);

    private static PongBallSvcApi ballSvcApi = new RestAdapter.Builder().setEndpoint(SERVER)
            .build().create(PongBallSvcApi.class);

    public static Player downloadData() {

        Collection<Player> players = playerSvcApi.getPlayersList();
        return players.iterator().next();
    }

    public static Coordinates downloadCoordinates() {
        return ballSvcApi.getCoordinates(1);
    }

    public static void uploadGyroscopeValues(double[] gyroCoords) {

        double roolX = gyroCoords[0];
        double pitchY = gyroCoords[1];
        double yawZ = gyroCoords[2];

        ParcelableGyroCoords gyroscopeCoordinates = new ParcelableGyroCoords(roolX, pitchY, yawZ);

        playerSvcApi.setGyroscopeCoordinates(1, gyroscopeCoordinates);
    }
}
