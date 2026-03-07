import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class OrdinaryComponentsShowcase extends JFrame {

    // ===== Core Components (Fields for global access) =====
    private JLabel label;
    private JButton button;
    private JTextField textField;
    private JTextArea textArea;
    private JCheckBox checkBox;
    private JRadioButton radio1, radio2;
    private JComboBox<String> comboBox;
    private JSlider slider;
    private JProgressBar progressBar;
    private JList<String> list;
    private JTable table;
    private JTabbedPane tabbedPane;

    public OrdinaryComponentsShowcase() {
        setTitle("Ultimate Ordinary Components Demo");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Layout
        setLayout(new BorderLayout());

        // Create Tabs (Organizing components professionally)
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Basic Controls", createBasicPanel());
        tabbedPane.addTab("Selection Components", createSelectionPanel());
        tabbedPane.addTab("Advanced Components", createAdvancedPanel());
        tabbedPane.addTab("Title", new JPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    // ===== TAB 1: Basic Components =====
    private JPanel createBasicPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        // JLabel
        label = new JLabel("Enter your name:");
        label.setFont(new Font("Arial", Font.BOLD, 16));

        // JTextField
        textField = new JTextField();
        textField.setToolTipText("Type something here");

        // JButton
        button = new JButton("Click Me");
        button.setFocusPainted(false);

        // JTextArea with Scroll
        textArea = new JTextArea(4, 20);
        textArea.setLineWrap(true);
        JScrollPane textScroll = new JScrollPane(textArea);

        // Event: Button Click
        button.addActionListener(e -> {
            String text = textField.getText();
            textArea.append("Hello, " + text + "!\n");
        });

        // Event: Enter Key in TextField
        textField.addActionListener(e -> {
            textArea.append("You pressed Enter: " + textField.getText() + "\n");
        });

        panel.add(label);
        panel.add(textField);
        panel.add(new JLabel("Button:"));
        panel.add(button);
        panel.add(new JLabel("Output Area:"));
        panel.add(textScroll);

        return panel;
    }

    // ===== TAB 2: Selection Components =====
    private JPanel createSelectionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // JCheckBox
        checkBox = new JCheckBox("Enable Feature");

        // JRadioButtons + ButtonGroup
        radio1 = new JRadioButton("Option A");
        radio2 = new JRadioButton("Option B");
        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);

        // JComboBox (Dropdown)
        String[] items = {"Java", "Python", "C++", "JavaScript"};
        comboBox = new JComboBox<>(items);

        // JSlider
        slider = new JSlider(0, 100, 50);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JLabel sliderValue = new JLabel("Slider Value: 50");

        // Slider Event (ChangeListener)
        slider.addChangeListener(e -> {
            sliderValue.setText("Slider Value: " + slider.getValue());
        });

        // Checkbox Event
        checkBox.addItemListener(e -> {
            if (checkBox.isSelected()) {
                JOptionPane.showMessageDialog(this, "Feature Enabled");
            }
        });

        panel.add(checkBox);
        panel.add(new JLabel());
        panel.add(radio1);
        panel.add(radio2);
        panel.add(new JLabel("Choose Language:"));
        panel.add(comboBox);
        panel.add(sliderValue);
        panel.add(slider);

        return panel;
    }

    // ===== TAB 3: Advanced Ordinary Components =====
    private JPanel createAdvancedPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // JList
        String[] data = {"Apple", "Banana", "Orange", "Mango", "Grapes"};
        list = new JList<>(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // List Selection Event
        list.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                JOptionPane.showMessageDialog(this,
                        "Selected: " + list.getSelectedValue());
            }
        });

        // JTable
        String[] columns = {"ID", "Name", "Score"};
        Object[][] rows = {
                {1, "Alice", 90},
                {2, "Bob", 85},
                {3, "Charlie", 95}
        };
        table = new JTable(rows, columns);
        JScrollPane tableScroll = new JScrollPane(table);

        // Progress Bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        JButton startProgress = new JButton("Start Progress");

        // Simulate progress (Thread usage correctly)
        startProgress.addActionListener(e -> {
            new Thread(() -> {
                for (int i = 0; i <= 100; i++) {
                    final int value = i;
                    SwingUtilities.invokeLater(() -> {
                        progressBar.setValue(value);
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }).start();
        });

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(new JScrollPane(list));
        topPanel.add(tableScroll);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(progressBar, BorderLayout.CENTER);
        bottomPanel.add(startProgress, BorderLayout.EAST);

        panel.add(topPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        // Proper EDT usage
        SwingUtilities.invokeLater(() -> {
            new OrdinaryComponentsShowcase().setVisible(true);
        });
    }
}