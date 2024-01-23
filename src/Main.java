public class Main {
    public static void main(String[] args) {
        int amount = 5;

        Controller controller = new Controller(amount);
        controller.showView();
        controller.startPhilosopherThreads();
        System.out.println("Leaving Main.");
    }
}