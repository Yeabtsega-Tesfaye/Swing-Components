import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AreaServerGUI extends JFrame {

    private JTextArea jta = new JTextArea();

    public static void main(String[] args) {
        new AreaServerGUI();
    }

    public AreaServerGUI() {
        setLayout(new BorderLayout());
        add(new JScrollPane(jta), BorderLayout.CENTER);

        setTitle("Area Server");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            jta.append("Server started at " + new Date() + "\n");

            Socket socket = serverSocket.accept();
            jta.append("Client connected\n");

            DataInputStream inputFromClient =
                new DataInputStream(socket.getInputStream());

            DataOutputStream outputToClient =
                new DataOutputStream(socket.getOutputStream());

            while (true) {
                double radius = inputFromClient.readDouble();

                double area = radius * radius * Math.PI;

                outputToClient.writeDouble(area);

                jta.append("Radius received: " + radius + "\n");
                jta.append("Area computed: " + area + "\n");
            }

        } catch (IOException ex) {
            jta.append("Error: " + ex.getMessage());
        }
    }
}