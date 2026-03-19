import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GridBagDemo {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(400, 300);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JButton btn1 = new JButton("Button 1");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(btn1, gbc);

        JButton btn2 = new JButton("Button 2");
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(btn2, gbc);

        JButton btn3 = new JButton("Wide Button");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // span across both columns
        frame.add(btn3, gbc);

        frame.setVisible(true);
    }
}