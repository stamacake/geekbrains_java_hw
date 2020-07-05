import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

class TestSpeed{
    public static void testspeed(ArrayList<Integer> arrayList, LinkedList<Integer> linkedList){
        int arrSize = arrayList.size()/2;
        System.out.println("Кол-во элементов:"+arrayList.size());
        long time1 = System.currentTimeMillis();

        for (int i = 0; i <10000 ; i++) {
            arrayList.get(arrSize);
        }
        System.out.println("ArrayList: "+(System.currentTimeMillis()-time1));

        int linkSize = linkedList.size()/2;
        long time2 = System.currentTimeMillis();
        for (int i = 0; i <10000 ; i++) {
            linkedList.get(linkSize);
        }
        System.out.println("LinkedList: "+(System.currentTimeMillis()-time2));
    }

    public static void deleteCenter(ArrayList arrayList, LinkedList linkedList){
        int arrSize = arrayList.size()/2;
        System.out.println("Кол-во элементов:"+arrayList.size());
        long time1 = System.currentTimeMillis();
        for (int i = 0; i <arrSize ; i++) {
            arrayList.remove(arrayList.size()/2);
        }
        System.out.println("ArrayList: "+(System.currentTimeMillis()-time1));

        int linkSize = linkedList.size()/2;
        long time2 = System.currentTimeMillis();
        for (int i = 0; i <linkSize ; i++) {
            linkedList.remove(linkedList.size()/2);
        }
        System.out.println("LinkedList: "+(System.currentTimeMillis()-time2));
    }
}

class MyEntry{
    private Integer key;
    private Integer value;

    public MyEntry(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }
}

class MyEntryTest{
    public static void find(ArrayList<MyEntry> arrayList, HashMap hashMap){

        final Random random = new Random();
        long time1 = System.currentTimeMillis();
        for (int i = 0; i <100000 ; i++) {
            int valueFind = random.nextInt(50000);
            for(int j=0;j<arrayList.size();j++){
                if(arrayList.get(j).getKey()==valueFind) break;
            }
        }
        System.out.println("ArrayList<MyEntry>: "+(System.currentTimeMillis()-time1));


        long time2 = System.currentTimeMillis();
        for (int i = 0; i <100000 ; i++) {
            int valueFind = random.nextInt(50000);
            hashMap.containsKey(valueFind);
        }
        System.out.println("HashMap: "+(System.currentTimeMillis()-time2));
    }

}


public class Hw7 {
    public static void main(String[] args) {
        final Random random = new Random();
        ArrayList<Integer> arrayList10 = new ArrayList<>();
        ArrayList<Integer> arrayList100 = new ArrayList<>();
        ArrayList<Integer> arrayList10000 = new ArrayList<>();
        ArrayList<Integer> arrayList100000 = new ArrayList<>();

        LinkedList<Integer> linkedList10 = new LinkedList<>();
        LinkedList<Integer> linkedList100 = new LinkedList<>();
        LinkedList<Integer> linkedList10000 = new LinkedList<>();
        LinkedList<Integer> linkedList100000 = new LinkedList<>();

        for (int i = 0; i <10000 ; i++) {
            if(i<10){
                arrayList10.add(random.nextInt());
                arrayList100.add(random.nextInt());
                arrayList10000.add(random.nextInt());
                arrayList100000.add(random.nextInt());
                linkedList10.add(random.nextInt());
                linkedList100.add(random.nextInt());
                linkedList10000.add(random.nextInt());
                linkedList100000.add(random.nextInt());


            }else
            if(i<100){

                arrayList100.add(random.nextInt());
                arrayList10000.add(random.nextInt());
                arrayList100000.add(random.nextInt());
                linkedList100.add(random.nextInt());
                linkedList10000.add(random.nextInt());
                linkedList100000.add(random.nextInt());

            }else
            if(i<10000){

                arrayList10000.add(random.nextInt());
                arrayList100000.add(random.nextInt());
                linkedList10000.add(random.nextInt());
                linkedList100000.add(random.nextInt());

            }else
            if(i<100000){

                arrayList100000.add(random.nextInt());
                linkedList100000.add(random.nextInt());
            }
        }

        System.out.println("Test get");
        TestSpeed.testspeed(arrayList10, linkedList10);
        TestSpeed.testspeed(arrayList100, linkedList100);
        TestSpeed.testspeed(arrayList10000, linkedList10000);
        TestSpeed.testspeed(arrayList100000, linkedList100000);

        System.out.println();

        System.out.println("Test delete");
        TestSpeed.deleteCenter(arrayList100, linkedList100);
        TestSpeed.deleteCenter(arrayList10000, linkedList10000);
        TestSpeed.deleteCenter(arrayList100000, linkedList100000);

        System.out.println();

        ArrayList<MyEntry> myEntries = new ArrayList<>();
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (int i = 0; i <50000 ; i++) {
            int tmp = random.nextInt();
            myEntries.add(new MyEntry(i, tmp));
            hashMap.put(i, tmp);
        }

        System.out.println("Test HashMap");
        MyEntryTest.find(myEntries,hashMap);

    }
}
