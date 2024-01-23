public class HandViewObject {
    private ForkViewObject fork;
    private PhilosopherViewObject philosopher;

    public HandViewObject(ForkViewObject fork, PhilosopherViewObject philosopher){
        this.fork = fork;
        this.philosopher = philosopher;
    }

    public ForkViewObject getFork() {
        return fork;
    }

    public PhilosopherViewObject getPhilosopher() {
        return philosopher;
    }
}
