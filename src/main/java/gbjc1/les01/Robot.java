package gbjc1.les01;

public class Robot implements Participant {
    private final int limitRun, limitJump;
    private final String model;
    //public String name = "Robot";

    public Robot(int limitRun, int limitJump, String model) {
        this.model = model;
        this.limitRun = limitRun;
        this.limitJump = limitJump;
    }

    @Override
    public int run() {
        System.out.println("Я робот, " + this.model + "бегаю! Мой лимит на пробег: " + this.limitRun + " м.");
        return this.limitRun;
    }

    @Override
    public int jump() {
        System.out.println("Я робот, " + this.model + ", прыгаю! Мой лимит на прыжок: " + this.limitJump  + " м.");
        return this.limitJump;
    }

    @Override
    public String toString() {
        return "Robot{" +
                "model='" + model + '\'' + " " +
                "limitRun=" + limitRun +
                ", limitJump=" + limitJump +
                '}';
    }
}