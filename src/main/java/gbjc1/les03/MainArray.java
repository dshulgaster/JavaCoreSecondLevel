package gbjc1.les03;

import java.util.HashMap;
import java.util.Map;

public class MainArray {
    public static void main (String[] args) {
        task1();
        task2();
    }

    public static void task1() {
        String[] words = {"dog", "wolf", "lion", "dog", "tiger", "wolf", "mouse", "dog", "rat", "cow", "horse"};
        HashMap<String, Integer> wordsCount = new HashMap<>(words.length);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int count = wordsCount.getOrDefault(word, 0);
            wordsCount.put(word, count + 1);
        }
        System.out.println(wordsCount);

        for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public static void task2() {
        Phonebook numbers = new Phonebook();
        numbers.add("Petrov", "89994392012");
        numbers.add("Ivanov", "89447821212");
        numbers.add("Egorov", "89557648834");
        numbers.add("Petrov", "89943208830");
        System.out.println(numbers.get("Petrov"));
    }
}