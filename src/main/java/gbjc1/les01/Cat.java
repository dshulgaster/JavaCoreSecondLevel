package gbjc1.les01;

public class Cat implements Participant {
    protected int limitRun, limitJump;
    public String name = "Cat";

    public Cat() {
    }

    public Cat(int limitRun, int limitJump) {
        this.limitRun = limitRun;
        this.limitJump = limitJump;
    }

    public int getLimitRun() {
        return limitRun;
    }

    public int getLimitJump() {
        return limitJump;
    }

    @Override
    public void jump() {
        System.out.println("Я кот, прыгаю! Мой лимит на прыжок: " + this.limitJump + "\n");
    }

    @Override
    public void run() {
        System.out.println("Я кот, бегаю. Мой лимит на бег: " + this.limitRun + "\n");
    }

    @Override
    public void noJump() {
        System.out.println("Я кот, но лимит на прыжок исчерпан: " + this.limitJump + "\n");
    }

    @Override
    public void noRun() {
        System.out.println("Я кот, но лимит на бег исчерпан: " + this.limitRun + "\n");
    }


    @Override
    public boolean limitRun(int value) {
        return this.limitRun >= value;
    }

    @Override
    public boolean limitJump(int value) {
        return this.limitJump >= value;
    }


}
