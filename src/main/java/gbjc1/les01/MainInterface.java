package gbjc1.les01;

import java.util.Arrays;
import java.util.Random;

public class MainInterface {
    public static Random random = new Random();
    public static void main(String[] args) {
<<<<<<< HEAD
        Running[] beingRun = new Running[5];
        // Jumping[] beingJump = new Jumping[5];
        /*
        Здесь логика мне не совсем понятна - т.к. нет родительского класса, то нет
        ясности, как объединить всех котов, роботов и людей.
        Ввиду того, что они все прагающие и бегающие, подходит и Running, и Jumping, но
        это какой-то частный случай получается. Возможно, имеет смысл сделать новый
        интерфейс "Движущиеся", который точно будет у всех, а проверять только на бег и прыжки...
        */
        beingRun[0] = new Man(1500, 2);
        beingRun[1] = new Man(2000,1);
        beingRun[2] = new Cat(200, 3);
        beingRun[3] = new Robot(100,0);
        beingRun[4] = new Cat(150,2);
        
=======
        Participant[] participants = new Participant[5];    // корректирую код
        participants[0] = new Man(1500, 2);
        participants[1] = new Man(2000, 1);
        participants[2] = new Cat(200, 3);
        participants[3] = new Cat(150, 2);
        participants[4] = new Robot(100, 0);

>>>>>>> dev_les01
        Man man1 = new Man();
        Cat cat1 = new Cat();       // Если только бегающий класс: Running cat1 = new Cat();
        Robot robot1 = new Robot();

        // Создаем беговую дорожку и стену
        Treadmill track1 = new Treadmill();     // создана беговая дорожка
        Wall wall1 = new Wall();                // создана стена

        // заполняем массив рандомными значениями препятствий - Wall или TREADMILL
        Obstacles[] arr1 = new Obstacles[5];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = Obstacles.values()[random.nextInt(Obstacles.values().length)];    // либо нулевой, либо первый элемент перечисления
        }

        for (int j = 0; j < participants.length; j++) {
            // Выводим (для проверки) массив значений препятствий
            for (int i = 0; i < arr1.length; i++) {
                // выбираем случайное значение перечисления
                switch (arr1[i]) {
                    case WALL:
                        System.out.println("The WALL! Величина препятствия: " + arr1[i].getValue());
                        jump(participants[j], (participants[j]).limitJump(arr1[i].getValue()));
                        break;
                    case TREADMILL:
                        System.out.println("The TREADMILL! Величина препятствия: " + arr1[i].getValue());
                        run(participants[j], (participants[j]).limitRun(arr1[i].getValue()));
                        break;
                }
            }
        }
    }

    static public void run(Participant Object, boolean bool) {
        if (bool) {
            Object.run();
        } else Object.noRun();
    }
    static public void jump(Participant Object, boolean bool) {
        if (bool) {
            Object.jump();
        }else Object.noJump();
    }
}
