import java.util.ArrayList;
import java.util.Arrays;


class ArrayToList<T> {
    public static <T> ArrayList<T> arrayToList(T[] x) {
        ArrayList<T> arr = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            arr.add(x[i]);
        }
        return arr;
    }
}

class ChangeElems<T> {
    public static <T> void change(T[] arr, int x1, int x2) {
        if (arr.length < x1 - 1 || arr.length < x2 - 1) {
            System.out.println("No such index");
            return;
        }
        T temp = arr[x1];
        arr[x1] = arr[x2];
        arr[x2] = temp;
        System.out.println("Changed");
    }
}

class Fruit {
    protected float weight;
    int count = 0;

    float fruitWeight() {
        return weight;
    }
}

class Apple extends Fruit {
    private float weight = 1.0f;

    float fruitWeight() {
        return weight;
    }

}

class Orange extends Fruit {
    private float weight = 1.5f;

    float fruitWeight() {
        return weight;
    }
}

class Box<T extends Fruit> {
    private ArrayList<T> fruitBox = new ArrayList<>();

    public ArrayList<T> getFruitBox() {
        return fruitBox;
    }

    float getWeight() {
        float sum = 0;
        for (T x : fruitBox) {
            sum += x.fruitWeight();
        }
        return sum;
    }

    void add(T e) {
        fruitBox.add(e);
    }

    void addFull(ArrayList<T> box) {
        this.fruitBox.addAll(box);
    }

    boolean compareBox(Box<T> box) {
        return this.getWeight() == box.getWeight();
    }

    boolean compare(Box box) {
        return getWeight() == box.getWeight();
    }

    boolean sendAll(Box<T> box) {
        box.addFull(this.fruitBox);
        this.fruitBox.clear();
        return false;

    }

    // вариант с выбором количества
    boolean sendFruit(Box box, int num) {
        if (num > 0 && fruitBox.size() >= num) {
            if (fruitBox.get(0).getClass().equals(box.fruitBox.get(0).getClass())) {
                for (int i = 0; i < num; i++) {
                    box.fruitBox.add(fruitBox.get(0));
                    fruitBox.remove(0);

                }
                return true;
            } else return false;
        } else return false;
    }
}


public class Hw5 {

    public static void changeElements(Object[] x, int x1, int x2) {
        if (x.length < x1 - 1 || x.length < x2 - 1) {
            System.out.println("No such index");
            return;
        }
        Object temp = x[x1];
        x[x1] = x[x2];
        x[x2] = temp;
        System.out.println("Changed");
    }


    public static void main(String[] args) {

        String[] arr1 = {"a", "b", "c"};
        System.out.println(Arrays.toString(arr1));
        changeElements(arr1, 0, 2);
        System.out.println(Arrays.toString(arr1));
        ChangeElems.change(arr1, 1, 2);
        System.out.println(Arrays.toString(arr1));
        ArrayList<String> arr1List = ArrayToList.arrayToList(arr1);
        System.out.println("ArrayList: " + arr1List.toString());

        System.out.println("Другой вариант");

        Integer[] arr2 = {1, 2, 3};
        System.out.println(Arrays.toString(arr2));
        changeElements(arr2, 0, 2);
        System.out.println(Arrays.toString(arr2));
        ChangeElems.change(arr2, 1, 2);
        System.out.println(Arrays.toString(arr2));
        ArrayList<Integer> arr2List = ArrayToList.arrayToList(arr2);
        System.out.println("ArrayList: " + arr2List.toString());

        Box appleBox = new Box();
        appleBox.add(new Apple());
        System.out.println("Applebox: " + appleBox.getWeight());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        System.out.println("Applebox: " + appleBox.getWeight());

        Box orangeBox = new Box();
        orangeBox.add(new Orange());
        System.out.println("OrangeBox: " + orangeBox.getWeight());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        System.out.println("OrangeBox: " + orangeBox.getWeight());

        System.out.println("Apple 3 >< Orange 4.5 " + appleBox.compare(orangeBox));
        Box orangeBox2 = new Box();
        orangeBox2.add(new Orange());
        orangeBox2.add(new Orange());
        System.out.println("Apple 3 >< Orange 3 " + appleBox.compare(orangeBox2));
        System.out.println("Apple 3 >< Orange 3 " + appleBox.compareBox(orangeBox2));

        System.out.println();

        System.out.println("Send: " + appleBox.sendFruit(appleBox, 1));
        System.out.println("Send: " + appleBox.sendFruit(orangeBox, 4));
        System.out.println("1t box: " + orangeBox.getFruitBox().size() + " шт. " + "2d box: " + orangeBox2.getFruitBox().size() + " шт.");
        System.out.println(orangeBox.sendFruit(orangeBox2, 1));
        System.out.println("1t box: " + orangeBox.getFruitBox().size() + " шт. " + "2d box: " + orangeBox2.getFruitBox().size() + " шт.");

        System.out.println();

        orangeBox.sendAll(orangeBox2);
        System.out.println("1t box: " + orangeBox.getFruitBox().size() + " шт. " + "2d box: " + orangeBox2.getFruitBox().size() + " шт.");
    }
}
