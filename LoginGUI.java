import javax.swing.*;
import java.awt.*;

public class LoginGUI {
    public static void main(String[] args) {

        // 🧱 Top-Level Container
        JFrame frame = new JFrame("Student Login System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center window 

        // 🧱 Intermediate Container (Panel)
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10)); // rows, cols, gaps
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 🎛️ Ordinary Components
        JLabel title = new JLabel("Login Form", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JCheckBox rememberMe = new JCheckBox("Remember Me");

        JButton loginButton = new JButton("Login");
        JButton clearButton = new JButton("Clear");

        // 🧩 Adding components to container (Panel)
        panel.add(title);
        panel.add(new JLabel("")); // empty space for alignment
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(rememberMe);
        panel.add(new JLabel("")); // spacing
        panel.add(loginButton);
        panel.add(clearButton);

        // Add panel to frame (Container inside Container)
        frame.add(panel);

        // 🎯 Button Logic (Basic Interaction)
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
            }
        });

        clearButton.addActionListener(e -> {
            usernameField.setText("");
            passwordField.setText("");
        });

    JDialog modeless = new JDialog(frame, "Modeless", false);

        // Make frame visible
        frame.setVisible(true);
    }
}
