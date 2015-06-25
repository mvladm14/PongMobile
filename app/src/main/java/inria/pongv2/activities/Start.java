package inria.pongv2.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import inria.pongv2.R;
import inria.pongv2.models.AsyncResponse;
import inria.pongv2.models.GetPongBallsTask;
import models.ball.PongBall;
import restInterfaces.PlayerSvcApi;
import restInterfaces.PongBallSvcApi;
import retrofit.RestAdapter;

public class Start extends Activity implements AsyncResponse {

    private static final String SERVER = "http://131.254.101.102:8080/myriads";

    private static PlayerSvcApi playerSvcApi = new RestAdapter.Builder().setEndpoint(SERVER)
            .build().create(PlayerSvcApi.class);

    private static PongBallSvcApi ballSvcApi = new RestAdapter.Builder().setEndpoint(SERVER)
            .build().create(PongBallSvcApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initializeFields();

    }

    private void initializeFields() {
        initializeUIFields();
        initializeNonUIFields();
    }

    private void initializeNonUIFields() {
        GetPongBallsTask getPongBallsTask = new GetPongBallsTask(this);
        getPongBallsTask.execute();
    }

    private void initializeUIFields() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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

    public void play(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFinishedConnection(Collection<PongBall> pongBallse) {
        Spinner spinner = (Spinner) findViewById(R.id.ballSpinner);

        List<String> strings = new ArrayList();

        Iterator<PongBall> iterator = pongBallse.iterator();
        while (iterator.hasNext()) {
            strings.add("Pong Ball " + iterator.next().getId());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strings);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
    }
}
