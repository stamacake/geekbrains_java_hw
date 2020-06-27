import java.util.ArrayList;
import java.util.Arrays;


class ArrayToList<T>{
    public static <T> ArrayList<T> arrayToList(T[] x){
        ArrayList<T> arr = new ArrayList<>();
        for (int i=0;i<x.length;i++ ) {
            arr.add(x[i]);
        }
        return  arr;
    }
}

class ChangeElems<T>{
    public static <T> void change(T[] arr, int x1, int x2){
        if(arr.length<x1-1 || arr.length<x2-1){
            System.out.println("No such index");
            return;
        }
        T temp = arr[x1];
        arr[x1]=arr[x2];
        arr[x2]=temp;
        System.out.println("Changed");
    }
}

class Fruit{
    protected float weight;
    int count = 0;
    float fruitWeight(){
        return weight;
    }
}

class Apple extends Fruit{
    float weight = 1.0f;

    float fruitWeight(){
        return weight;
    }

}

class Orange extends Fruit{
    float weight = 1.5f;
    float fruitWeight(){
        return weight;
    }
}

class Box<T extends Fruit>{
    ArrayList<T> fruitBox = new ArrayList<>();

    float getWeight(){
        float sum = 0;
       for(T x:fruitBox){
         sum += x.fruitWeight();
       }
        return sum;
    }

    void add(T e){
        fruitBox.add(e);
    }

    boolean compare(Box box){
        return getWeight()==box.getWeight();
    }

    boolean sendFruit(Box box, int num){
        if(num>0 && fruitBox.size()>=num) {
            if (fruitBox.get(0).getClass().equals(box.fruitBox.get(0).getClass())) {
                for(int i=0;i<num;i++){
                    box.fruitBox.add(fruitBox.get(0));
                    fruitBox.remove(0);

                }
                return true;
            } else return false;
        } else return false;
    }
}


public class Hw5 {
    public static void changeElements(Object[] x, int x1, int x2){
        if(x.length<x1-1 || x.length<x2-1){
            System.out.println("No such index");
            return;
        }
        Object temp = x[x1];
        x[x1]=x[x2];
        x[x2]=temp;
        System.out.println("Changed");
    }



    public static void main(String[] args) {

        String[] arr1 = {"a","b","c"};
        System.out.println(Arrays.toString(arr1));
        changeElements(arr1,0,2);
        System.out.println(Arrays.toString(arr1));
        ChangeElems.change(arr1,1,2);
        System.out.println(Arrays.toString(arr1));
        ArrayList<String> arr1List = ArrayToList.arrayToList(arr1);
        System.out.println("ArrayList: "+arr1List.toString());

        System.out.println();

        Integer[] arr2 = {1,2,3};
        System.out.println(Arrays.toString(arr2));
        changeElements(arr2,0,2);
        System.out.println(Arrays.toString(arr2));
        ChangeElems.change(arr2,1,2);
        System.out.println(Arrays.toString(arr2));
        ArrayList<Integer> arr2List = ArrayToList.arrayToList(arr2);
        System.out.println("ArrayList: "+arr2List.toString());

        Box appleBox = new Box();
        appleBox.add(new Apple());
        //System.out.println(appleBox.fruitBox.size());
        System.out.println("Applebox: "+appleBox.getWeight());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        System.out.println("Applebox: "+appleBox.getWeight());

        Box orangeBox = new Box();
        orangeBox.add(new Orange());
        System.out.println("OrangeBox: "+orangeBox.getWeight());
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        System.out.println("OrangeBox: "+orangeBox.getWeight());

        System.out.println(appleBox.compare(orangeBox));
        Box orangeBox2 = new Box();
        orangeBox2.add(new Orange());
        orangeBox2.add(new Orange());
        System.out.println(appleBox.compare(orangeBox2));

        System.out.println();

        System.out.println(appleBox.sendFruit(appleBox,1));
        System.out.println(appleBox.sendFruit(orangeBox,4));
        System.out.println("1t box: "+orangeBox.fruitBox.size()+" шт. "+"2d box: "+orangeBox2.fruitBox.size()+" шт.");
        System.out.println(orangeBox.sendFruit(orangeBox2,1));
        System.out.println("1t box: "+orangeBox.fruitBox.size()+" шт. "+"2d box: "+orangeBox2.fruitBox.size()+" шт.");
    }
}
