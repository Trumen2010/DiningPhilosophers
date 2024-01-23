import java.util.Observable;
import java.util.Random;

public class Philosopher extends Observable implements Runnable {
    private Fork leftfork;
    private Fork rightfork;
    private int id;
    private long millisecondsSinceLastMeal;

    public Philosopher(int id, Fork leftfork, Fork rightfork){
        this.id = id;
        this.leftfork = leftfork;
        this.rightfork = rightfork;
        this.millisecondsSinceLastMeal = System.currentTimeMillis();
    }

    public void eat(){
        try{
            Thread.sleep(1250);
        }
        catch (InterruptedException e){}
        millisecondsSinceLastMeal = System.currentTimeMillis();
        this.pause();
    }

    public void philosophize(){
        try{
            Thread.sleep(750);
        }
        catch (InterruptedException e){}
        this.pause();
    }

    public void pause(){
        Random random = new Random();
        try{
            Thread.sleep(random.nextInt( 1500));
        }
        catch (InterruptedException e){}
    }

    @Override
    public void run() {
        while (System.currentTimeMillis() - this.millisecondsSinceLastMeal < 20000){
            philosophize();
            if (leftfork.grab()){
                setChanged();
                notifyObservers(new EventData(2, this.id));
                this.pause();
                if (rightfork.grab()) {
                    setChanged();
                    notifyObservers(new EventData(3, this.id));
                    eat();
                    rightfork.drop();
                    setChanged();
                    notifyObservers(new EventData(5, this.id));
                }
                leftfork.drop();
                setChanged();
                notifyObservers(new EventData(4, this.id));
            }
        }
        setChanged();
        notifyObservers(new EventData(1, this.id));
    }
}
