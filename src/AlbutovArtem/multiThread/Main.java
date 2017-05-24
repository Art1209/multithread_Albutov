package AlbutovArtem.multiThread;

public class Main {
    public static void main(String[] args) {
        GuiWindow win1 = new GuiWindow(); // Create GUI window
        Thread thread1 = new Thread(new TypeListener(win1)); // Creating and starting threads
        Thread thread2 = new Thread(new MinimumOuter(win1));
        thread1.start();
        thread2.start();
    }
}
