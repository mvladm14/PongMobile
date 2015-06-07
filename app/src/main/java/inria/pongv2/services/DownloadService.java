package inria.pongv2.services;


import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import inria.pongv2.activities.MainActivity;
import inria.pongv2.models.ParcelableBallCoordinates;
import inria.pongv2.utils.Converter;
import inria.pongv2.utils.Downloader;
import models.BallCoordinates;

/**
 * Created by Vlad on 5/28/2015.
 */
public class DownloadService extends IntentService {

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

        final float[] phoneCoords = intent.getFloatArrayExtra(MainActivity.PHONE_COORDS);

        Bundle bundle = new Bundle();
        receiver.send(STATUS_RUNNING, Bundle.EMPTY);

        try {

            Downloader.uploadPhoneCoordinates(Converter.convertFloatsToDoubles(phoneCoords));

            BallCoordinates ballCoordinates = Downloader.downloadCoordinates();
            ParcelableBallCoordinates parcelableBallCoordinates = Converter.convertCoordinatesToParcelable(ballCoordinates);

                /* Sending result back to activity */
            if (null != ballCoordinates) {
                bundle.putParcelable(MainActivity.BALL_COORDS, parcelableBallCoordinates);
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


}
