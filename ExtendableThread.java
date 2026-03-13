import java.lang.Thread;

public class ExtendableThread extends Thread {
    public void run() {
        System.out.print("Extendable!");
    }

    public static void main(String[] args) {
        ExtendableThread t1 = new ExtendableThread();
        t1.start();
    }
}
