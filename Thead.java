import java.lang.Thread;
import java.lang.Runnable;

class Hello implements Runnable {
    public void run() {
        for(int i= 0; i < 10; i++ ) {
            if(i == 6) {

            try {
                Thread.sleep(4000);

            } catch(Exception e) {

            } 
                System.out.println( "Hello Java" + i );

            }
                System.out.println( "Hello Java" + i );

   }
    }

}

class Hey extends Thread {
    public void run() {


        for(int i= 0; i < 10; i++ )
            try {
                Thread.sleep(3000);
                System.out.println( "Hey Java" );
            } catch(Exception e) {

            }
        }
}

public class Thead {
    public static void main(String[] args) {
        Hey t1 = new Hey();
        Hello t2 = new Hello();
        Thread t4 = new Thread(t2);

        t1.start();
            try {
                t1.join();
            } catch(Exception e) {

            }
        t4.run();

    }

}