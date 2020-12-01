package gbjc1.les01;

public class Cat implements Jumping, Running {
    @Override
    public void jump() {
        System.out.println("Я кот, прыгаю!");
    }

    @Override
    public void run() {
        System.out.println("Я кот, бегаю");
    }

}
