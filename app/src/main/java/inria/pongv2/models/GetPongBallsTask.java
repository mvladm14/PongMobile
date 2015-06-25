package inria.pongv2.models;

import android.os.AsyncTask;

import java.util.Collection;

import models.ball.PongBall;
import restInterfaces.PongBallSvcApi;
import retrofit.RestAdapter;

public class GetPongBallsTask extends AsyncTask<Void, Void, Collection<PongBall>> {

    private static final String SERVER = "http://131.254.101.102:8080/myriads";

    private static PongBallSvcApi ballSvcApi = new RestAdapter.Builder().setEndpoint(SERVER)
            .build().create(PongBallSvcApi.class);

    private AsyncResponse delegate = null;

    public GetPongBallsTask(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onPostExecute(Collection<PongBall> pongBalls) {
        delegate.onFinishedConnection(pongBalls);
    }

    @Override
    protected Collection<PongBall> doInBackground(Void... voids) {
        return ballSvcApi.getPongBalls();
    }
}
