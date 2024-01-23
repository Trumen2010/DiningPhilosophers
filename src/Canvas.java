import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Canvas extends JPanel {
    private int amount;
    private PhilosopherViewObject[] philophersArray;
    private boolean[] killLines;
    private ForkViewObject[] forksArray;
    private ArrayList<HandViewObject> handsArrayList = new ArrayList<>();

    public Canvas(int amount){
        this.amount = amount;
        this.setPhilophersArray();
        this.setForkArray();
        this.setKillLines();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (int i = 0; i < this.amount; i++) {
            g.drawOval(this.philophersArray[i].getX(), this.philophersArray[i].getY(), 80, 80);
            g.drawLine(this.forksArray[i].getX1(), this.forksArray[i].getY1(), this.forksArray[i].getX2(), this.forksArray[i].getY2());
            if (this.killLines[i]) {
                g.drawLine(this.philophersArray[i].getX(), this.philophersArray[i].getY(), this.philophersArray[i].getX() + this.philophersArray[i].getR() * 2, this.philophersArray[i].getY() + this.philophersArray[i].getR() * 2);
                g.drawLine(this.philophersArray[i].getX() + this.philophersArray[i].getR() * 2, this.philophersArray[i].getY(), this.philophersArray[i].getX(), this.philophersArray[i].getY() + this.philophersArray[i].getR() * 2);
            }
            for (HandViewObject hvo : this.handsArrayList) {
                PhilosopherViewObject philosopher = hvo.getPhilosopher();
                ForkViewObject fork = hvo.getFork();
                g.drawLine(philosopher.getX() + philosopher.getR(), philosopher.getY() + philosopher.getR(), fork.getX1(), fork.getY1());
            }
        }
        g.drawOval(350, 150, 300, 300);
    }

    public PhilosopherViewObject calcPhilosophersCoordinates(int numberOfCircle){
        int x = (int) (500 + 230 * Math.cos((2 * Math.PI) / this.amount * numberOfCircle)) - 40;
        int y = (int) (300 + 230 * Math.sin((2 * Math.PI) / this.amount * numberOfCircle)) - 40;
        return new PhilosopherViewObject(x, y, 40, numberOfCircle - 1);
    }

    public ForkViewObject calcForksCoordinates(int numberOfFork){
        int x1 = (int) (500 + 130 * Math.cos((2 * Math.PI) / this.amount * (numberOfFork - .5)));
        int y1 = (int) (300 + 130 * Math.sin((2 * Math.PI) / this.amount * (numberOfFork - .5)));
        int x2 = (int) (500 + 90 * Math.cos((2 * Math.PI) / this.amount * (numberOfFork - .5)));
        int y2 = (int) (300 + 90 * Math.sin((2 * Math.PI) / this.amount * (numberOfFork - .5)));
        return new ForkViewObject(x1, y1, x2, y2, numberOfFork - 1);
    }

    public void killPhilosopher(int idOfPhilosopher){
        this.killLines[idOfPhilosopher] = true;
        repaint();
    }

    public void philosopherGrabsFork(int idOfPhilosopher, int idOfFork){
        this.handsArrayList.add(new HandViewObject(this.forksArray[idOfFork], this.philophersArray[idOfPhilosopher]));
        repaint();
    }

    public void philosopherDropsFork(int idOfPhilosopher, int idOfFork){
        int indexToVeRemoved = -1;
        for (int h = 0; h < this.handsArrayList.size(); h++) {
            if (idOfFork == this.handsArrayList.get(h).getFork().getId() && idOfPhilosopher == this.handsArrayList.get(h).getPhilosopher().getId()) {
                indexToVeRemoved = h;
                break;
            }
        }
        if (indexToVeRemoved != -1) {
            this.handsArrayList.remove(indexToVeRemoved);
        }
        repaint();

    }

    public void setPhilophersArray() {
        this.philophersArray = new PhilosopherViewObject[amount];
        for (int p = 0; p < amount; p++){
            this.philophersArray[p] = this.calcPhilosophersCoordinates(p + 1);
        }
    }

    public void setKillLines() {
        this.killLines = new boolean[amount];
        for (int k = 0; k < amount; k++){
            this.killLines[k] = false;
        }
    }

    public void setForkArray() {
        this.forksArray = new ForkViewObject[amount];
        for (int f = 0; f < amount; f++){
            this.forksArray[f] = this.calcForksCoordinates(f + 1);
        }
    }
}
