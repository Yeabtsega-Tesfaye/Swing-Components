import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) {

        try {

            Socket socket = new Socket("localhost", 5000);

            BufferedReader keyboard = new BufferedReader(
                new InputStreamReader(System.in));

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true);

            String message;

            while (true) {

                System.out.print("Enter message: ");
                message = keyboard.readLine();

                output.println(message);

                String response = input.readLine();
                System.out.println(response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}