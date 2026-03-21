import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AreaClientGUI extends JFrame {

    private JTextField jtf = new JTextField();
    private JTextArea jta = new JTextArea();

    private DataOutputStream toServer;
    private DataInputStream fromServer;

    public static void main(String[] args) {
        new AreaClientGUI();
    }

    public AreaClientGUI() {

        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Enter radius: "), BorderLayout.WEST);
        p.add(jtf, BorderLayout.CENTER);

        jtf.setHorizontalAlignment(JTextField.RIGHT);

        setLayout(new BorderLayout());
        add(p, BorderLayout.NORTH);
        add(new JScrollPane(jta), BorderLayout.CENTER);

        jtf.addActionListener(new TextFieldListener());

        setTitle("Area Client");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            Socket socket = new Socket("localhost", 8000);

            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());

        } catch (IOException ex) {
            jta.append("Connection error: " + ex.getMessage() + "\n");
        }
    }

    private class TextFieldListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double radius = Double.parseDouble(jtf.getText().trim());

                toServer.writeDouble(radius);
                toServer.flush();

                double area = fromServer.readDouble();

                jta.append("Radius: " + radius + "\n");
                jta.append("Area from server: " + area + "\n");

            } catch (IOException ex) {
                jta.append("Error: " + ex.getMessage() + "\n");
            }
        }
    }
}