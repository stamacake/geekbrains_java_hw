import java.util.*;

class Unique {
    public static void countUnique(String[] words) {
        Map<String, Integer> uniqu = new HashMap<>();
        for (String word : words) {
            if (!uniqu.containsKey(word)) {
                uniqu.put(word, 1);
            } else {
                uniqu.put(word, uniqu.get(word) + 1);
            }
        }

        for (Map.Entry value : uniqu.entrySet()) {
            System.out.println(value.getKey() + " : " + value.getValue());
        }
        System.out.println();
    }
}

class PhoneBook {
    Map<String, List<Long>> people = new HashMap<>();

    public void add(String name, Long phone) {
        if (!people.containsKey(name)) {
            List<Long> phones = new ArrayList<>();
            phones.add(phone);
            people.put(name, phones);
        } else {
            List<Long> phones = people.get(name);
            phones.add(phone);
        }
    }

    public void get(String name) {
        if (people.containsKey(name)) {
            System.out.println(name);
            List<Long> phones = people.get(name);
            for (Long phone : phones) {
                System.out.println(phone);
            }

        } else {
            System.out.println(name + " - No such name");
        }
        System.out.println();
    }
}

public class Hw6 {

    public static void main(String[] args) {
        String[] words = {"A", "B", "C", "D", "E", "F", "G", "Z", "A", "K", "K", "L",
                "M", "N", "Z", "P", "A", "R", "S", "F", "W", "X", "A", "Z"};

        Unique.countUnique(words);

        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Hideo", 12345566L);
        phoneBook.add("Hideo", 15516145L);
        phoneBook.add("Todd", 23895923L);
        phoneBook.add("IceFrog", 14883228L);
        phoneBook.add("IceFrog", 32281488L);
        phoneBook.add("IceFrog", 253295209L);

        phoneBook.get("IceFrog");
        phoneBook.get("Hideo");
        phoneBook.get("QWERTY");


    }
}
