import java.net.*;
import java.io.*;

class Server {
    public static void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started... Waiting for clients");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);

                ClientHandler handler = new ClientHandler(socket);
                handler.start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// ============== CLIENT HANDLER (THREAD) ==============
class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true
            );

            String msg;

            while ((msg = input.readLine()) != null) {
                System.out.println("Client: " + msg);

                if ("stop".equals(msg)) {
                    System.out.println("Client disconnected");
                    break;
                }

                output.println("Server received: " + msg);
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// ================= CLIENT =================
class Client {
    public static void startClient() {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server");

            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true
            );

            BufferedReader keyboard = new BufferedReader(
                new InputStreamReader(System.in)
            );

            // THREAD 1 → RECEIVING FROM SERVER
            Thread receiveThread = new Thread(() -> {
                try {
                    String response;
                    while ((response = input.readLine()) != null) {
                        System.out.println(response);
                    }
                } catch (Exception e) {
                    System.out.println("Connection closed.");
                }
            });

            receiveThread.start();

            // MAIN THREAD → SENDING TO SERVER
            String userInput;
            while (true) {
                userInput = keyboard.readLine();
                output.println(userInput);

                if ("stop".equals(userInput)) {
                    break;
                }
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// ================= MAIN =================
public class SocketPrograming {
    public static void main(String[] args) {
        
        // Choose what to run:
        // Run server OR client (not both in same run)

        // 👉 Uncomment ONE at a time:

        // Server.startServer();

        // Client.startClient();
    }
}