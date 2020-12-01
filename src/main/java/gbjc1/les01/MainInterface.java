package gbjc1.les01;

import java.util.Random;

public class MainInterface {
    public static Random random = new Random();
    public static void main(String[] args) {
        Running[] beingRun = new Running[5];
        // Jumping[] beingJump = new Jumping[5];
        /*
        Здесь логика мне не совсем понятна - т.к. нет родительского класса, то нет
        ясности, как объединить всех котов, роботов и людей.
        Ввиду того, что они все прагающие и бегающие, подходит и Running, и Jumping, но
        это какой-то частный случай получается. Возможно, имеет смысл сделать новый
        интерфейс "Движущиеся", который точно будет у всех, а проверять только на бег и прыжки...
        */
        beingRun[0] = new Man();
        beingRun[1] = new Man();
        beingRun[2] = new Cat();
        beingRun[3] = new Robot();
        beingRun[4] = new Cat();

        Man man1 = new Man();
        Cat cat1 = new Cat();       // Если только бегающий класс: Running cat1 = new Cat();
        Robot robot1 = new Robot();
        Treadmill track1 = new Treadmill();     // создана беговая дорожка
        Wall wall1 = new Wall();                // создана стена
//        Treadmill track1 = new Treadmill(100);  // в беговую дорожку передается длина

        // заполняем массив рандомными значениями препятствий
        Obstacles[] arr1 = new Obstacles[5];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = Obstacles.values()[random.nextInt(Obstacles.values().length)];
        }

        for (int j = 0; j < beingRun.length; j++) {
            // Выводим (для проверки массив значений препятствий
            for (int i = 0; i < arr1.length; i++) {
                // выбираем случайное значение перечисления
                switch (arr1[i]) {
                    case WALL:
                        System.out.println("The WALL!");
                        jump((Jumping) beingRun[j]);
//                        jump(man1); jump(cat1); jump(robot1);
                        break;
                    case TREADMILL:
                        System.out.println("The TREADMILL!");
                        run(beingRun[j]);
//                        run(man1); run(cat1); run(robot1);
                        break;
                }
            }
        }
    }

    static public void run(Running Object) {
        Object.run();
    }
    static public void jump(Jumping Object) {
        Object.jump();
    }


}
