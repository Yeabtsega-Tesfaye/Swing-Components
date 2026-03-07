import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class RegistrationGUI extends JFrame {

    public RegistrationGUI() {
        // Basic Frame Setup
        setTitle("GUI individual Assignment 1");
        setSize(580, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Color Constants
        Color lightGreen = new Color(210, 245, 170);
        Color saveGreen = new Color(0, 128, 0);
        Color resetBlue = new Color(0, 0, 255);

        // Main Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(lightGreen);
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Menu Bar ---
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(new JMenu("File"));
        menuBar.add(new JMenu("Edit"));
        menuBar.add(new JMenu("View"));
        menuBar.add(new JMenu("Navigate"));
        setJMenuBar(menuBar);

        // --- Left Side Components ---
        
        // Full Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Full Name"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        JTextField nameField = new JTextField();
        nameField.setBorder(new LineBorder(Color.RED, 1));
        formPanel.add(nameField, gbc);

        // Gender
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        formPanel.add(new JLabel("Gender"), gbc);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(lightGreen);
        genderPanel.add(new JRadioButton("Male"));
        genderPanel.add(new JRadioButton("Female"));
        gbc.gridx = 1;
        formPanel.add(genderPanel, gbc);

        // Languages
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Language you ..."), gbc);
        JPanel langPanel = new JPanel();
        langPanel.setLayout(new BoxLayout(langPanel, BoxLayout.Y_AXIS));
        langPanel.setBackground(lightGreen);
        langPanel.add(new JCheckBox("Amharic", true));
        langPanel.add(new JCheckBox("English"));
        langPanel.add(new JCheckBox("Germany")); // Keeping your typo from image
        gbc.gridx = 1;
        formPanel.add(langPanel, gbc);

        // Department
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Department"), gbc);
        String[] depts = {"Computer Science", "Information Systems", "Engineering"};
        gbc.gridx = 1;
        formPanel.add(new JComboBox<>(depts), gbc);

        // Year of Entry
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Year of Entry"), gbc);
        gbc.gridx = 1;
        formPanel.add(new JComboBox<>(new String[]{"", "2021", "2022", "2023"}), gbc);

        // Hobbies
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Hobbies"), gbc);
        gbc.gridx = 1;
        JTextArea hobbiesArea = new JTextArea(5, 20);
        formPanel.add(new JScrollPane(hobbiesArea), gbc);

        // Nationality
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Nationality Type"), gbc);
        JPanel natPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        natPanel.setBackground(lightGreen);
        natPanel.add(new JButton("Ethiopian"));
        natPanel.add(new JButton("Foriegn"));
        gbc.gridx = 1;
        formPanel.add(natPanel, gbc);

        // Date of Birth
        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(new JLabel("Date of Birth"), gbc);
        gbc.gridx = 1;
        JPanel dobPanel = new JPanel(new BorderLayout());
        dobPanel.add(new JTextField(), BorderLayout.CENTER);
        dobPanel.add(new JButton("📅"), BorderLayout.EAST);
        formPanel.add(dobPanel, gbc);

        // Favorite Foods
        gbc.gridx = 0; gbc.gridy = 8;
        formPanel.add(new JLabel("Favorite Foods"), gbc);
        gbc.gridx = 1;
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Tuna Steak");
        JList<String> foodList = new JList<>(listModel);
        JScrollPane foodScroll = new JScrollPane(foodList);
        foodScroll.setPreferredSize(new Dimension(150, 40));
        formPanel.add(foodScroll, gbc);

        // --- Profile Image (Right Side) ---
        gbc.gridx = 3; gbc.gridy = 3;
        gbc.gridheight = 3;
        // Placeholder for the image
        JLabel imageLabel = new JLabel("Avatar Placeholder", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(150, 150));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.WHITE);
        formPanel.add(imageLabel, gbc);

        // --- Bottom Buttons ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(lightGreen);
        
        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(saveGreen);
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        
        JButton resetBtn = new JButton("Reset Form");
        resetBtn.setBackground(resetBlue);
        resetBtn.setForeground(Color.WHITE);
        resetBtn.setFocusPainted(false);
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(resetBtn);

        // Final Assembly
        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RegistrationGUI().setVisible(true);
        });
    }
}