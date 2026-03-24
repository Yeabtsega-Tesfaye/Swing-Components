import java.io.*;
import java.net.*;

public class StudentServerApp {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started...");

            ObjectOutputStream fileOut =
                new ObjectOutputStream(new FileOutputStream("student.dat"));

            while (true) {
                Socket socket = serverSocket.accept();

                ObjectInputStream in =
                    new ObjectInputStream(socket.getInputStream());

                StudentAddress student =
                    (StudentAddress) in.readObject();

                fileOut.writeObject(student);
                fileOut.flush();

                System.out.println("Saved: " + student.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}