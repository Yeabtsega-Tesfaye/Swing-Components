import javax.swing.*;
import java.awt.*;

public class LayoutEventDemo {
    public static void main(String[] args) {

            JFrame frame = new JFrame("Layout + Event Demo");
            frame.setSize(400, 300);
            frame.setLayout(new BorderLayout());

            // Top panel (FlowLayout)
            JPanel topPanel = new JPanel(new FlowLayout());
            JButton btn1 = new JButton("Red");
            JButton btn2 = new JButton("Green");

            // Center (GridLayout)
            JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
            JTextField field = new JTextField();
            JLabel label = new JLabel("Type something:");

            centerPanel.add(label);
            centerPanel.add(field);
            centerPanel.add(new JButton("A"));
            centerPanel.add(new JButton("B"));

            btn1.addActionListener(e -> label.setForeground(Color.RED));
            btn2.addActionListener(e -> centerPanel.setBackground(Color.GREEN));

            topPanel.add(btn1);
            topPanel.add(btn2);

            frame.add(topPanel, BorderLayout.NORTH);
            frame.add(centerPanel, BorderLayout.CENTER);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
    }
}
