package gbjc1.les01;

public class Man implements Jumping, Running {

    @Override
    public void jump() {
        System.out.println("Я человек, прыгаю!");
    }

    @Override
    public void run() {
        System.out.println("Я человек, бегаю");
    }

}
