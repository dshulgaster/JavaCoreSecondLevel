package gbjc1.les01;

public interface Participant {
    void run();
    void jump();
    void noRun();
    void noJump();
    boolean limitRun(int value);
    boolean limitJump(int value);
    //    String name = "";
}
