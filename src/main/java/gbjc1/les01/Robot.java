package gbjc1.les01;

public class Robot implements Jumping, Running  {
    @Override
    public void jump() {
        System.out.println("Я робот, прыгаю!");
    }

    @Override
    public void run() {
        System.out.println("Я робот, бегаю");
    }

}
