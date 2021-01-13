package gbjc1.les01;

public class Treadmill implements Obstacle {
    private final int length;

    public Treadmill(int length) {
        this.length = length;
    }

    @Override
    public boolean passObstacleBy(Participant participant) {
        if (participant.run() > length) {
            System.out.println("Участник " + participant + " успешно пробежал дистанцию.");
            return true;
        } else {
            System.out.println("Участник " + participant + " не смог пробежать дистанцию (" + length + " м.).");
            return false;
        }
    }
}