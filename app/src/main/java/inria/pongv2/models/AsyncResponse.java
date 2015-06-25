package inria.pongv2.models;

import java.util.Collection;

import models.ball.PongBall;

/**
 * Created by Vlad on 6/16/2015.
 */
public interface AsyncResponse {
    void onFinishedConnection(Collection<PongBall> pongBallse);
}
