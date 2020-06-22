class Human{
    private int maxRun;
    private int maxJump;

    public Human(int maxRun, int maxJump){
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    boolean successRun(int distance){
        if(distance<=maxRun){
            System.out.println("Успешно пробежал");
            return true;
        } else {
            System.out.println("Не смог пробежать");
            return false;
        }
    }

    boolean successJump(int level){
        if(level<=maxJump){
            System.out.println("Успешно прыгнул");
            return true;
        } else {
            System.out.println("Не смог допрыгнуть");
            return false;
        }
    }

    public int getMaxRun() {
        return maxRun;
    }

    public int getMaxJump() {
        return maxJump;
    }
}

class Cat{
    private int maxRun;
    private int maxJump;

    public Cat(int maxRun, int maxJump) {
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    boolean successRun(int distance){
        if(distance<=maxRun){
            System.out.println("Успешно пробежал");
            return true;
        } else {
            System.out.println("Не смог пробежать");
            return false;
        }
    }

    boolean successJump(int level){
        if(level<=maxJump){
            System.out.println("Успешно прыгнул");
            return true;
        } else {
            System.out.println("Не смог допрыгнуть");
            return false;
        }
    }

    public int getMaxRun() {
        return maxRun;
    }

    public int getMaxJump() {
        return maxJump;
    }
}

class Robot{
    private int maxRun;
    private int maxJump;

    public Robot(int maxRun, int maxJump) {
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    boolean successRun(int distance){
        if(distance<=maxRun){
            System.out.println("Успешно пробежал");
            return true;
        } else {
            System.out.println("Не смог пробежать");
            return false;
        }
    }

    boolean successJump(int level){
        if(level<=maxJump){
            System.out.println("Успешно прыгнул");
            return true;
        } else {
            System.out.println("Не смог допрыгнуть");
            return false;
        }
    }

    public int getMaxRun() {
        return maxRun;
    }

    public int getMaxJump() {
        return maxJump;
    }
}

class RunTrack{
    private int length;

    public RunTrack(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}

class Wall{
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }
}


public class Hw3 {
    public static void main(String[] args) {


        Object[] obstacles = new Object[10];
        for (int i = 0; i <10 ; i++) {
            if(i%2==0)
            obstacles[i]= new RunTrack(i);
            else obstacles[i]= new Wall(i);
        }

        Object[] members = new Object[6];

        for (int i = 0; i <2 ; i++) {
            members[i] = new Human(i+3,i);
        }
        for (int i = 2; i <4 ; i++) {
            members[i] = new Cat(i,i+4);
        }
        for (int i = 4; i <6 ; i++) {
            members[i] = new Robot(i+5,i+5);
        }

        for ( Object x:members ) {

            if (x instanceof Human) {
                System.out.println("Max run: "+((Human) x).getMaxRun() + " Max Jump: "+((Human) x).getMaxJump() );

            }
            if (x instanceof Cat) {
                System.out.println("Max run: "+((Cat) x).getMaxRun() + " Max Jump: "+((Cat) x).getMaxJump() );

            }
            if (x instanceof Robot) {
                System.out.println("Max run: "+((Robot) x).getMaxRun() + " Max Jump: "+((Robot) x).getMaxJump() );

            }


            boolean it1 = true, it2=true;
            for (Object y:obstacles){
                if(it1 && it2) {

                    if (x instanceof Human) {
                        if (y instanceof RunTrack) {
                            System.out.println("Need run: "+((RunTrack) y).getLength() );
                            it1 = ((Human) x).successRun(((RunTrack) y).getLength());
                        }
                        if(y instanceof Wall){
                            System.out.println("Need jump: "+((Wall) y).getHeight());
                            it2 = ((Human) x).successJump(((Wall) y).getHeight());
                        }
                    }

                    if (x instanceof Cat) {
                        if (y instanceof RunTrack) {
                            System.out.println("Need run: "+((RunTrack) y).getLength() );
                            it1 = ((Cat) x).successRun(((RunTrack) y).getLength());
                        }
                        if(y instanceof Wall){
                            System.out.println("Need jump: "+((Wall) y).getHeight());
                            it2 = ((Cat) x).successJump(((Wall) y).getHeight());
                        }
                    }

                    if (x instanceof Robot) {
                        if (y instanceof RunTrack) {
                            System.out.println("Need run: "+((RunTrack) y).getLength() );
                            it1 = ((Robot) x).successRun(((RunTrack) y).getLength());
                        }
                        if(y instanceof Wall){
                            System.out.println("Need jump: "+((Wall) y).getHeight());
                            it2 = ((Robot) x).successJump(((Wall) y).getHeight());
                        }
                    }

                }
            }
            System.out.println();
        }



    }
}
