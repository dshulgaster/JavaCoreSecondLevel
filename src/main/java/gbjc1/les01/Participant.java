package gbjc1.les01;

public interface Participant extends Ranner, Jumper {
    default boolean doIt (Obstacle obstacle) {
        return doAction(obstacle);
    }

    private boolean doAction(Obstacle obstacle) {
        return obstacle.passObstacleBy(this);
    }
}
