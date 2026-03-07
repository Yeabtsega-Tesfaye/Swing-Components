import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ControlPanelApp extends JFrame {
    JFrame farme = new JFrame("Control Panel App");
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu viewMenu = new JMenu("View");
    JMenu helpMenu = new JMenu("Help");
    JMenuItem resetMenuItem = new JMenuItem("Reset");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JPanel topPanel = new JPanel();
    JPanel sidePanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JPanel footerPanel = new JPanel();
    JTextField textField = new JTextField();
    JButton actionBtn = new JButton();
    JLabel label = new JLabel("status");
    JCheckBox checkBox1 =  new JCheckBox();
    JCheckBox checkBox2 =  new JCheckBox();
    // grouping ckeckboxes like radio button
    String[] chomboLists = {"light", "dark"};



    public ControlPanelApp() {

    }

    private void applySettings() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ControlPanelApp().setVisible(true);
        });
    }
}
