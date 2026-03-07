import javax.swing.*;
import java.awt.*;

public class DialogExample {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Main Window");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JButton button = new JButton("Open Dialog");

        button.addActionListener(e -> {
    
            JDialog dialog = new JDialog(frame, "My Dialog", true);
            dialog.setSize(250, 150);
            dialog.setLayout(new FlowLayout());

            JLabel label = new JLabel("This is a JDialog");
            JButton okButton = new JButton("OK");

            // 🔥 This is the important part
            okButton.addActionListener(ev -> dialog.dispose());

            dialog.add(label);
            dialog.add(okButton);

            dialog.setLocationRelativeTo(frame);
            dialog.setVisible(true);
        });

        frame.add(button);
        frame.setVisible(true);
    }
}

// JDialog dialog = new JDialog(frame, "My Dialog", true);
// dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);???̣