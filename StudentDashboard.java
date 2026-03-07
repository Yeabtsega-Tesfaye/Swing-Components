import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentDashboard {

    public static void main(String[] args) {

        // 🧱 1. TOP LEVEL CONTAINER (JFrame)
        JFrame frame = new JFrame("Smart Student Dashboard");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // 🍔 2. MENU COMPONENTS (MenuBar + Menu + SubMenu)
        JMenuBar menuBar = new JMenuBar();

        // Main Menus
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        // Menu Items (Ordinary menu components)
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Submenu (Nested Menu)
        JMenu newSubMenu = new JMenu("New Project");
        JMenuItem javaProject = new JMenuItem("Java Project");
        JMenuItem webProject = new JMenuItem("Web Project");

        newSubMenu.add(javaProject);
        newSubMenu.add(webProject);

        fileMenu.add(newSubMenu);
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        helpMenu.add(new JMenuItem("About"));

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);

        // 🧱 3. MAIN CONTAINER PANEL (BorderLayout)
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // 🧱 4. HEADER PANEL (Container)
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 144, 255));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel title = new JLabel("Student Management System");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);

        headerPanel.add(title);

        // 🧱 5. TABBED PANE (Advanced Container)
        JTabbedPane tabbedPane = new JTabbedPane();

        // 🎛️ 6. FORM PANEL (Ordinary Components)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();

        JLabel courseLabel = new JLabel("Course:");
        JComboBox<String> courseBox = new JComboBox<>(
                new String[]{"Software Engineering", "Computer Science", "IT"});

        JCheckBox agreeBox = new JCheckBox("I confirm my details");

        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(idLabel);
        formPanel.add(idField);
        formPanel.add(courseLabel);
        formPanel.add(courseBox);
        formPanel.add(agreeBox);
        formPanel.add(new JLabel(""));
        formPanel.add(submitButton);
        formPanel.add(clearButton);

        // 🎛️ 7. INFO PANEL (Image + Labels)
        JPanel infoPanel = new JPanel(new BorderLayout());

        JLabel infoText = new JLabel("Welcome to the Dashboard", JLabel.CENTER);
        infoText.setFont(new Font("Verdana", Font.BOLD, 20));

        // Image in JLabel
        ImageIcon icon = new ImageIcon("student.jpg");
        JLabel imageLabel = new JLabel(icon, JLabel.CENTER);

        infoPanel.add(infoText, BorderLayout.NORTH);
        infoPanel.add(imageLabel, BorderLayout.CENTER);

        // Add tabs
        tabbedPane.addTab("Student Form", formPanel);
        tabbedPane.addTab("Info", infoPanel);

        // 🧱 8. FOOTER PANEL (Container)
        JPanel footerPanel = new JPanel(new FlowLayout());
        footerPanel.setBackground(Color.DARK_GRAY);

        JButton logoutButton = new JButton("Logout");
        footerPanel.add(logoutButton);

        // 🎯 9. EVENT HANDLING (Interaction)
        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();

            if (!name.isEmpty() && !id.isEmpty() && agreeBox.isSelected()) {
                JOptionPane.showMessageDialog(frame,
                        "Student Registered Successfully!");
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Please fill all fields and confirm!");
            }
        });

        clearButton.addActionListener(e -> {
            nameField.setText("");
            idField.setText("");
            agreeBox.setSelected(false);
        });

        exitItem.addActionListener(e -> System.exit(0));

        logoutButton.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "Logged Out!"));

        // 🧩 10. ADD CONTAINERS TO MAIN CONTAINER
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
