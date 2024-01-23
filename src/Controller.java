import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    private View view;
    private Fork[] forks;
    private int amount;

    public Controller(int amount){
        this.amount = amount;
        this.createView();
        this.createForks();
    }

    public void createView(){
        this.view = new View(amount);
    }

    public void startPhilosopherThreads() {
        for (int p = 0 ; p < amount; p++){
            Philosopher philo = new Philosopher(p, this.forks[p], this.forks[p % (amount - 1)]);
            philo.addObserver(this);
            Thread philoThread = new Thread(philo);
            philoThread.start();
        }
    }

    public void showView() {
        SwingUtilities.invokeLater(() -> {
            view.setVisible(true);
        });
    }

    public void createForks(){
        this.forks = new Fork[amount];
        for (int f = 0; f < amount; f++){
            this.forks[f] = new Fork();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        EventData eventData = (EventData) arg;
        int event = eventData.event();
        int idOfPhilosopher = eventData.philosopherID();
        if (event == 1) {
            this.view.killPhilosopher(idOfPhilosopher);
        } else if (event == 2) {
            this.view.philosopherGrabsFork(idOfPhilosopher, idOfPhilosopher);
        } else if (event == 3) {
            this.view.philosopherGrabsFork(idOfPhilosopher, (idOfPhilosopher + 1) % amount);
        } else if (event == 4) {
            this.view.philosopherDropsFork(idOfPhilosopher, idOfPhilosopher);
        } else if (event == 5) {
            this.view.philosopherDropsFork(idOfPhilosopher, (idOfPhilosopher + 1) % amount);
        }
    }
}
