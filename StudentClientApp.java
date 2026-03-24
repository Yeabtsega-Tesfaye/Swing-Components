import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentClientApp extends JFrame {

    private JTextField name = new JTextField(20);
    private JTextField street = new JTextField(20);
    private JTextField city = new JTextField(15);
    private JTextField state = new JTextField(5);
    private JTextField zip = new JTextField(10);

    public StudentClientApp() {
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Name:")); add(name);
        add(new JLabel("Street:")); add(street);
        add(new JLabel("City:")); add(city);
        add(new JLabel("State:")); add(state);
        add(new JLabel("Zip:")); add(zip);

        JButton sendBtn = new JButton("Send");
        add(sendBtn);

        sendBtn.addActionListener(e -> sendData());

        setTitle("Student Client");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void sendData() {
        try {
            Socket socket = new Socket("localhost", 8000);

            ObjectOutputStream out =
                new ObjectOutputStream(socket.getOutputStream());

            StudentAddress s = new StudentAddress(
                name.getText(),
                street.getText(),
                city.getText(),
                state.getText(),
                zip.getText()
            );

            out.writeObject(s);
            out.flush();

            System.out.println("Object sent!");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StudentClientApp();
    }
}