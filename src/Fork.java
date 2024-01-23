public class Fork {
    private boolean permit;
    public Fork(){
        this.permit = true;
    }

    public synchronized boolean grab() {
        if (this.permit) {
            this.permit = false;
            return true;
        }
        return false;
    }

    public synchronized void drop(){
        this.permit = true;
    }
}
