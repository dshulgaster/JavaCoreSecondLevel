package gbjc1.les02;

import java.util.Arrays;

public class MainException {
    public static final int SIZE = 4;
    public static void main(String[] args) {
        MainException main = new MainException();
        String[][] arrStringTrue = {
                {"3", "45", "67", "98"},
                {"45", "6", "32", "93"},
                {"326", "94", "54", "98"},
                {"124", "57", "194", "26"}
        };

        String[][] arrStringFalse1 = {
                {"fas", "fgs", "faaasg", "dsaf", "s323"},
                {"sfdgf", "sdgv", "sad", "regf", "asdf"},
                {"sda", "sadf", "sadf", "sfagfd", "sdfda"},
        };

        String[][] arrStringFalse2 = {
                {"fasss", "fbgs", "sdas"},
                {"sfdgf", "sdgv", "asdf"},
                {"sioevf", "ssav", "asdf"},
                {"sda", "sfagfd", "sdfda"},
        };

        String[][] arrStringFalse3 = {
                {"35", "45", "fa67", "98"},
                {"45", "sd6", "32", "93"},
                {"326", "s94f", "sa54", "98"},
                {"s124", "sf", "194", "26"}
        };

        try {
            // (для проверки только одна строка должна быть не закомментируема)
            System.out.println("Сумма всех ячеек (приведенных к int): " + stringExceptionTest(arrStringTrue, "arrStringTrue"));
            // Неверное количество строк!!! в массиве arrStringFalse1
            //System.out.println("Сумма всех ячеек (приведенных к int): " + stringExceptionTest(arrStringFalse1, "arrStringFalse1"));
            // Неверное количество столбцов в 1 строке массива arrStringFalse2
            //System.out.println("Сумма всех ячеек (приведенных к int): " + stringExceptionTest(arrStringFalse2, "arrStringFalse2"));
            // Неправильное значение массива, ошибка в ячейке: (1, 1)
            System.out.println("Сумма всех ячеек (приведенных к int): " + stringExceptionTest(arrStringFalse3, "arrStringFalse3"));
        } catch (MyArraySizeException e) {
            e.printStackTrace();
            //System.out.println("Неверное кол-во строк!");
        } catch (MyArrayDataException e) {
            //e.printStackTrace();
            System.out.println("\nНеправильное значение массива, ошибка в ячейке: (" + (e.i +1) + ", " + (e.j +1) + ")\n");
        } finally {
            System.out.println("-----------------------------");
            System.out.print("Вывод всех тестовых массивов:  ");
            printArray(arrStringTrue);
            printArray(arrStringFalse1);
            printArray(arrStringFalse2);
            printArray(arrStringFalse3);
        }
    }

    // 1. Выявление исключений по кол-ву строк и столбцов в принятом массиве (сравнивается с SIZE)
    // 2. Выявление случаев, когда ячейку массива нельзя преобразовать в int
    // получить исключение MyArrayDataException с детализацией (в какой именно ячейке лежат неверные данные)
    // Необходимо просуссировать все ячейки, если они числовые
    public static int stringExceptionTest(String[][] arr, String nameArr) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (arr.length != SIZE) throw new MyArraySizeException("Неверное количество строк!!! в массиве " + nameArr);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != SIZE) throw new MyArraySizeException("Неверное количество " +
                    "столбцов в " + (i+1) + " строке массива " + nameArr);
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j, "\nНеправильное значение массива, ошибка в ячейке: (" + (i +1) + ", " + (j +1) + ")\n");
                }

                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return sum;
    }

    // Вывод массива (без проверок)
    public static void printArray(String[][] arr){
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Длина массива: " + arr.length + " на " + arr[0].length);
    }

}
