package inria.pongv2.services;


import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import java.util.Collection;

import inria.pongv2.activities.MainActivity;
import inria.pongv2.interfaces.PongSvcApi;
import inria.pongv2.models.GyroscopeCoordinates;
import inria.pongv2.models.Player;
import inria.pongv2.utils.Converter;
import retrofit.RestAdapter;

/**
 * Created by Vlad on 5/28/2015.
 */
public class DownloadService extends IntentService {

    private static final String SERVER = "http://131.254.101.102:8080/Pong";

    private PongSvcApi pongSvc = new RestAdapter.Builder().setEndpoint(SERVER)
            .build().create(PongSvcApi.class);


    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    private static final String TAG = "DownloadService";

    public DownloadService() {
        super(DownloadService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "Service Started!");

        final ResultReceiver receiver = intent.getParcelableExtra(MainActivity.RECEIVER);

        final float[] gyroCoords = intent.getFloatArrayExtra(MainActivity.GYRO_DATA);

        Bundle bundle = new Bundle();
        receiver.send(STATUS_RUNNING, Bundle.EMPTY);

        try {

            uploadGyroscopeValues(Converter.convertFloatsToDoubles(gyroCoords));

            Player player = downloadData();

                /* Sending result back to activity */
            if (null != player) {
                bundle.putParcelable("player", player);
                receiver.send(STATUS_FINISHED, bundle);
            }
        } catch (Exception e) {

                /* Sending error message back to activity */
            bundle.putString(Intent.EXTRA_TEXT, e.toString());
            receiver.send(STATUS_ERROR, bundle);
        }
        Log.d(TAG, "Service Stopping!");
        this.stopSelf();
    }

    private void uploadGyroscopeValues(double[] gyroCoords) {

        double roolX = gyroCoords[0];
        double pitchY = gyroCoords[1];
        double yawZ = gyroCoords[2];

        GyroscopeCoordinates gyroscopeCoordinates = new GyroscopeCoordinates(roolX, pitchY, yawZ);

        pongSvc.setGyroscopeCoordinates(1, gyroscopeCoordinates);
    }

    private Player downloadData() {

        Collection<Player> players = pongSvc.getPlayersList();
        return players.iterator().next();
    }
}
