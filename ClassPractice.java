import java.awt.*;
import javax.swing.*;


public class ClassPractice {
    public static void main(String[] args) {
            JFrame frame = new JFrame("Practice");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            JPanel panel = new JPanel();
            // JCheckBox checkBox1 = new JCheckBox("Option 1");
            // JCheckBox checkBox2 = new JCheckBox("Option 2");
            // JCheckBox checkBox3 = new JCheckBox("Option 3");


            // panel.add(checkBox1);
            // panel.add(checkBox2);
            // panel.add(checkBox3);
            frame.setLocationRelativeTo(null);
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            frame.add(panel);

            // JLabel LName = new JLabel("Name: ");
            // JTextField TName = new JTextField("");

            // JLabel TEmail = new JLabel("Email");
            // JTextField Email = new JTextField("");

            // JLabel TPassword = new JLabel("Password: ");
            // JPasswordField TPasswordFiled = new JPasswordField("");

            // panel.add(LName);
            // panel.add(TName);
            // panel.add(TEmail);
            // panel.add(Email);
            // panel.add(TPassword);
            // panel.add(TPasswordFiled);

            // panel.setLayout(new GridLayout(3,2,10,10));

            JMenuBar menuBar = new JMenuBar();
            JMenu menu1 = new JMenu("file");
            JMenu menu2 = new JMenu("edit");


            JMenuItem item1 = new JMenuItem("open");
            JMenuItem item2 = new JMenuItem("create");
            JMenu item3 = new JMenu("edit");
            JMenuItem submenu1 = new JMenuItem("list");
            JMenuItem submenu2 = new JMenuItem("list");

            JMenuItem item4 = new JMenuItem("open");

            JMenuItem item5 = new JMenuItem("create");
            JMenuItem item6 = new JMenuItem("edit");
            JMenuItem item7 = new JMenuItem("open");

            menu1.add(item1);
            menu1.add(item2);
            menu1.add(item3);
            menu1.add(item4);

            item3.add(submenu1);
            item3.add(submenu2);

            menu2.add(item5);
            menu2.add(item6);
            menu2.add(item7);

            menuBar.add(menu1);
            menuBar.add(menu2);


            frame.setJMenuBar(menuBar);
            frame.setVisible(true);
    }
}
