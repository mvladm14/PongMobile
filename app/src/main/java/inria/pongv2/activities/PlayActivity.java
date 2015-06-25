package inria.pongv2.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import inria.pongv2.R;
import inria.pongv2.models.ParcelableBallCoordinates;
import inria.pongv2.services.DownloadResultReceiver;
import inria.pongv2.services.DownloadService;


public class PlayActivity extends Activity implements SensorEventListener, DownloadResultReceiver.Receiver {

    public static final String RECEIVER = "RECEIVER";
    public static final String ACCELEROMETER = "ACCELEROMETER";
    public static final String MAGNETIC_FIELD = "MAGNETIC_FIELD";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String GRAVITY = "GRAVITY";
    public static final String LINEAR_ACCELERATION = "LINEAR_ACCELERATION";

    public static final String BALL_COORDS = "BALL COORDS";

    private TextView tv;
    private TextView tv2;
    private TextView tv3;
    private SensorManager sManager;
    private Intent mIntent;

    private float[] mAccelerometer;
    private float[] mGeomagnetic;
    private float[] mLinearAcceleration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        DownloadResultReceiver mReceiver = new DownloadResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        mIntent = new Intent(Intent.ACTION_SYNC, null, this, DownloadService.class);
        mIntent.putExtra(RECEIVER, mReceiver);

        tv = (TextView) findViewById(R.id.textView3);
        tv2 = (TextView) findViewById(R.id.textView4);
        tv3 = (TextView) findViewById(R.id.textView5);
        //get a hook to the sensor service
        sManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //when this Activity starts
    @Override
    protected void onResume() {
        super.onResume();

        sManager.registerListener(this, sManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);

    }

    //When this Activity isn't visible anymore
    @Override
    protected void onStop() {

        //unregister the sensor listener
        sManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case DownloadService.STATUS_RUNNING:

                setProgressBarIndeterminateVisibility(true);
                break;
            case DownloadService.STATUS_FINISHED:
                /* Hide progress & extract result from bundle */
                setProgressBarIndeterminateVisibility(false);

                ParcelableBallCoordinates coordinates = resultData.getParcelable(BALL_COORDS);
                tv.setText(coordinates.toString());

                break;
            case DownloadService.STATUS_ERROR:
                /* Handle the error */
                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            mLinearAcceleration = event.values;
            long timestamp = event.timestamp;

            mIntent.putExtra(LINEAR_ACCELERATION, mLinearAcceleration);
            mIntent.putExtra(TIMESTAMP, timestamp);
            startService(mIntent);
        }

        /*
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mGeomagnetic = event.values;
        }

        if (mAccelerometer != null && mGeomagnetic != null) {

            mIntent.putExtra(ACCELEROMETER, mAccelerometer);
            mIntent.putExtra(MAGNETIC_FIELD, mGeomagnetic);

            startService(mIntent);


            float R[] = new float[9];
            float I[] = new float[9];

            boolean success = SensorManager.getRotationMatrix(R, I,
                    mAccelerometer, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                convertToDegrees(orientation);
                tv2.setText("orientation = :\n" +
                        "x = " + orientation[0] + "\n" +
                        "y = " + orientation[1] + "\n" +
                        "z = " + orientation[2] + "\n");
            } else {
                Log.e("VLAD", "fraier");
            }

        }
        */
    }

    private void convertToDegrees(float[] orientation) {
        for (int i = 0; i < orientation.length; i++) {
            Double degrees = (orientation[i] * 180) / Math.PI;
            orientation[i] = degrees.floatValue();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopService(mIntent);
    }
}
