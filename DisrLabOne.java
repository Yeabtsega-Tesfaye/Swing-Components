import java.lang.Thread;
import java.lang.Runnable;

class secondThead implements Runnable {
    public void run() {
        for(int i = 0; i < 10; i++) {
            System.out.println("Thread " + i + " " + Thread.currentThread().getName());
            Thread.yield();
        }
    }
}

class firstTheard extends Thread {
    public void run() {
        for(int i = 0; i < 10; i++) {
            try {
                Thread.sleep(3000);
                System.out.println("Thread " + i + " " + Thread.currentThread().getName());
            } catch(Exception e) {}
        }
    }
}

public class DisrLabOne {
    public static void main(String[] args) {

        firstTheard t1 = new firstTheard();
        secondThead t2 = new secondThead();

        Thread t4 = new Thread(t2);

        t1.start();
        t4.start(); // NOT run()
    }
}