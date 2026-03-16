import java.net.*;
import java.io.*;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started... Waiting for clients");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                ClientHandler handler = new ClientHandler(socket);
                handler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {

    Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            BufferedReader input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter output = new PrintWriter(
                    socket.getOutputStream(), true);

            String message;

            while ((message = input.readLine()) != null) {

                System.out.println("Client: " + message);

                output.println("Server: Hello Client");
                System.out.println("Client connected from port: " + socket.getPort());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}