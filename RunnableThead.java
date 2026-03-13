import java.lang.Runnable;

public class RunnableThead implements Runnable {
    public void run() {
        System.out.print("Runnable!");
    }

    public static void main(String[] args) {
        RunnableThead t1 = new RunnableThead();
        t1.run();
    }
}
