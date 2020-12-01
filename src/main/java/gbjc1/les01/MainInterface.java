package gbjc1.les01;

public class MainInterface {
    public static void main(String[] args) {
        Man man1 = new Man();
//        Cat cat1 = new Cat();
        Running cat1 = new Cat();
        Robot robot1 = new Robot();
//        man1.jump();
//        man1.run();
//        cat1.jump();
//        cat1.run();
//        robot1.jump();
//        robot1.run();
        run(man1);
        jump(man1);
        run(cat1);
        jump((Jumping) cat1);
        run(robot1);
        jump(robot1);

    }

    static public void run(Running Object) {
        Object.run();
    }
    static public void jump(Jumping Object) {
        Object.jump();
    }


}
