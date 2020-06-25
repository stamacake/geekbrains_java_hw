
interface Member{
    boolean run(int x);
    boolean jump(int x);
    int getMaxRun();
    int getMaxJump();
}

class Human implements Member{

    private int maxRun;
    private int maxJump;

    public Human(int maxRun, int maxJump){
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    @Override
    public boolean run(int x) {
        if(x<=maxRun){
            System.out.println("Human успешно пробежал");
            return true;
        }
        System.out.println("Human не пробежал");
        return false;
    }

    @Override
    public boolean jump(int x) {
        if(x<=maxJump){
            System.out.println("Human успешно прыгнул");
            return true;
        }
        System.out.println("Human не прыгнул");
        return false;
    }


    public int getMaxJump() {
        return maxJump;
    }

    public int getMaxRun() {
        return maxRun;
    }
}

class Cat implements Member{

    private int maxRun;
    private int maxJump;

    public Cat(int maxRun, int maxJump){
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    @Override
    public boolean run(int x) {
        if(x<=maxRun){
            System.out.println("Cat успешно пробежал");
            return true;
        }
        System.out.println("Cat не пробежал");
        return false;
    }

    @Override
    public boolean jump(int x) {
        if(x<=maxJump){
            System.out.println("Cat успешно прыгнул");
            return true;
        }
        System.out.println("Cat не прыгнул");
        return false;
    }


    public int getMaxJump() {
        return maxJump;
    }

    public int getMaxRun() {
        return maxRun;
    }
}

class Robot implements Member{

    private int maxRun;
    private int maxJump;

    public Robot(int maxRun, int maxJump){
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    @Override
    public boolean run(int x) {
        if(x<=maxRun){
            System.out.println("Robot успешно пробежал");
            return true;
        }
        System.out.println("Robot не пробежал");
        return false;
    }

    @Override
    public boolean jump(int x) {
        if(x<=maxJump){
            System.out.println("Robot успешно прыгнул");
            return true;
        }
        System.out.println("Robot не прыгнул");
        return false;
    }


    public int getMaxJump() {
        return maxJump;
    }

    public int getMaxRun() {
        return maxRun;
    }
}

abstract class Obstacle{

    private int limit;

    public int getLimit() {
        return limit;
    }

    public Obstacle(int limit){
        this.limit = limit;
    }

    abstract boolean action(Member m);
}

class Wall extends Obstacle{
    private int limit;

    public Wall(int limit) {
        super(limit);
    }

    boolean action(Member m){
        return m.jump(getLimit());
    }



}

class Track extends Obstacle{
    private  int limit;

    public Track(int limit) {
        super(limit);
    }

    boolean action(Member m){
        return m.run(getLimit());
    }


}


public class Hw3 {

    public static void main(String[] args) {

        Member[] members = new Member[6];
        for (int i = 0; i <2 ; i++) {
            members[i] = new Human(i+3,i+1);
        }
        for (int i = 2; i <4 ; i++) {
            members[i] = new Cat(i+2,i+4);
        }
        for (int i = 4; i <6 ; i++) {
            members[i] = new Robot(i+5,i+5);
        }

        Obstacle[] obstacles = new Obstacle[10];
        for (int i = 0; i <10 ; i++) {
            if(i%2==0)
                obstacles[i]= new Track(i);
            else obstacles[i]= new Wall(i);
        }

        for(Member x:members){
            boolean it1 = true;
            System.out.println(x.getClass().getSimpleName()+" Run: "+x.getMaxRun()+" Jump: "+x.getMaxJump());

            for(Obstacle y:obstacles){

                if(it1) {
                    System.out.println("Current "+y.getClass().getSimpleName()+" "+y.getLimit());
                    it1 = y.action(x);
                }
            }
        }

    }
}
