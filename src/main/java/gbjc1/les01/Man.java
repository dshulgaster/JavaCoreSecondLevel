package gbjc1.les01;

public class Man implements Participant {
    private final int limitRun, limitJump;
    private final String name;

    public Man (int limitRun, int limitJump, String name) {
        this.name = name;
        this.limitRun = limitRun;
        this.limitJump = limitJump;
    }

    @Override
    public int run() {
        System.out.println("Я человек, " + this.name +  ", бегаю! Мой лимит на пробег: " + this.limitRun + " м.");
        return this.limitRun;
    }

    @Override
    public int jump() {
        System.out.println("Я человек, " + this.name +  ", прыгаю! Мой лимит на прыжок: " + this.limitJump  + " м.");
        return this.limitJump;
    }

    @Override
    public String toString() {
        return "Man{" +
                "name='" + name + '\'' + " " +
                "limitRun=" + limitRun +
                ", limitJump=" + limitJump +
                '}';
    }
}
