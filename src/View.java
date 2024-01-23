import javax.swing.*;

public class View extends JFrame {
    private int amount;
    private Canvas canvas;
    public View(int amount){
        this.amount = amount;
        this.setTitle("Dining Philosopher");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.canvas = new Canvas(this.amount);

        this.add(canvas);
        this.setLocation(160, 10);
        this.setSize(1000, 650);
    }

    public void killPhilosopher(int idOfPhilosopher){
        this.canvas.killPhilosopher(idOfPhilosopher);
    }

    public void philosopherGrabsFork(int idOfPhilosopher, int idOfFork){
        this.canvas.philosopherGrabsFork(idOfPhilosopher, idOfFork);
    }

    public void philosopherDropsFork(int idOfPhilosopher, int idOfFork){
        this.canvas.philosopherDropsFork(idOfPhilosopher, idOfFork);
    }
}
