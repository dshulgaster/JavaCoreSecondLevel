package gbjc1.les03;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Phonebook {
    private HashMap<String, List<String>> numbersMap;

    public Phonebook() {
        numbersMap = new HashMap<>();
    }

    public void add(String name, String number) {
        List<String> numbers = numbersMap.get(name);
        if (numbers == null) {
            numbers = new ArrayList<>();
            numbersMap.put(name, numbers);
        }
        numbers.add(number);
    }

    public Collection<String> get(String name) {
        return numbersMap.get(name);
    }
}