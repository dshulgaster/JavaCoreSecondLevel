package gbjc1.les01;

public enum Obstacles {
    WALL("Wall", 2), TREADMILL("TREADMILL", 170);

    private String obstaclesName;
    private int value;

    public String getObstaclesName() {
        return obstaclesName;
    }

    Obstacles(String obstaclesName, int value) {
        this.obstaclesName = obstaclesName;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
