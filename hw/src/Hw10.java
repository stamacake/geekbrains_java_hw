import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


class Car implements Runnable {
    private static int CARS_COUNT;

    private Race race;
    private int speed;
    private String name;
    private final CountDownLatch startSignal;
    private final CountDownLatch startSignal2;
    private final CountDownLatch doneSignal;


    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch startSignal, CountDownLatch startSignal2, CountDownLatch doneSignal) {
        this.race = race;
        this.speed = speed;
        this.startSignal = startSignal;
        this.startSignal2 = startSignal2;
        this.doneSignal = doneSignal;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {

            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");

            startSignal.countDown();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            startSignal2.await();
        } catch (Exception e){
        }

        for (int i = 0; i < race.getStages().size(); i++) {

            race.getStages().get(i).overcome(this);
        }

        doneSignal.countDown();
        if(Race.win==1){
            Race.win--;
            System.out.println(this.name+ " - WIN");
        }
    }
}

abstract class Stage {
    int length;
    String description;

    public String getDescription() {
        return description;
    }

    public abstract void overcome(Car c);
}

class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void overcome(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {

    static Semaphore smp = new Semaphore(Race.COMPETITORS_COUNT/2);

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }



    @Override
    public void overcome(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smp.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Race {
    public static final int COMPETITORS_COUNT = 20;
    public static volatile int win =1;

    private List<Stage> stages;

    public List<Stage> getStages() { return stages; }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public void begin() {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Car[] cars = new Car[COMPETITORS_COUNT];
        CountDownLatch startSignal = new CountDownLatch(COMPETITORS_COUNT);
        CountDownLatch startSignal2 = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(COMPETITORS_COUNT);

        for (int i = 0; i < cars.length; i++) {
            cars[i] =new Car(this, 20 + (int) (Math.random() * 10), startSignal ,startSignal2, doneSignal);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();

        }


        try{
            startSignal.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            startSignal2.countDown();
        } catch (Exception e){
        }

        try {
            doneSignal.await();
        } catch (Exception e){
            System.out.println("exp");
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

public class Hw10 {
    public static void main(String[] args) {
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        race.begin();
    }
}
