public class PhilosopherViewObject {
    private int x;
    private int y;
    private int r;
    private final int id;

    public PhilosopherViewObject(int x, int y, int r, int idOfPhilosopher){
        this.x = x;
        this.y = y;
        this.r = r;
        this.id = idOfPhilosopher;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public int getId() {
        return id;
    }
}
