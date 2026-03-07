package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;

public class AssignmentOne extends JFrame {
    private ImageIcon logo;
    private JTextField nameField;
    private JRadioButton maleRadio, femaleRadio;
    private JCheckBox amharicCheck, englishCheck, germanCheck;
    private JComboBox<String> deptCombo;
    private JComboBox<String> yearCombo;
    private   JTextArea hobbiesArea;
    private JDateChooser dateChooser;
    private SpinnerListModel favFoodModel;
    private JSpinner favFoodSpinner;

    public AssignmentOne() {

        setTitle("GUI individual Assignment 1");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color lightGreen = new Color(210, 245, 170);
        Color saveGreen = new Color(0, 128, 0);
        Color resetBlue = new Color(0, 0, 255);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        menuBar.add(new JMenu("Edit"));
        menuBar.add(new JMenu("View"));
        menuBar.add(new JMenu("Navigate"));
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        setJMenuBar(menuBar);

        JLabel titleLabel = new JLabel("Individual Assignment One", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(lightGreen);
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Initialize components
        nameField = new JTextField();
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        amharicCheck = new JCheckBox("Amharic",true);
        englishCheck = new JCheckBox("English");
        germanCheck = new JCheckBox("German");

        deptCombo = new JComboBox<>(new String[]{"Computer Science", "Information Technology", "Engineering", "Mathematics"});
        yearCombo = new JComboBox<>(new String[]{"", "2014", "2015", "2016"});
        hobbiesArea = new JTextArea(5,20);
        String[] foods = {"Burger", "Firfir", "Aynet"};
        favFoodModel = new SpinnerListModel(foods);
        favFoodSpinner = new JSpinner(favFoodModel);

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Full Name"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        nameField.setBorder(new LineBorder(Color.RED, 1));
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        formPanel.add(new JLabel("Gender"), gbc);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setBackground(lightGreen);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        gbc.gridx = 1;
        formPanel.add(genderPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Language you speak "), gbc);
        JPanel langPanel = new JPanel();
        langPanel.setLayout(new BoxLayout(langPanel, BoxLayout.Y_AXIS));
        langPanel.setBackground(lightGreen);
        langPanel.add(amharicCheck);
        langPanel.add(englishCheck);
        langPanel.add(germanCheck); 
        gbc.gridx = 1;
        formPanel.add(langPanel, gbc);

       gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Department"), gbc);
        gbc.gridx = 1;
        formPanel.add(deptCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Year of Entry"), gbc);
        gbc.gridx = 1;       
        formPanel.add(yearCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Hobbies"), gbc);
        gbc.gridx = 1;
        formPanel.add(new JScrollPane(hobbiesArea), gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Nationality Type"), gbc);
        JPanel natPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        natPanel.setBackground(lightGreen);
        natPanel.add(new JButton("Ethiopian"));
        natPanel.add(new JButton("Foriegn"));
        gbc.gridx = 1;
        formPanel.add(natPanel, gbc);

        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(new JLabel("Select Date of Birth:"), gbc);
        gbc.gridx = 1;
        dateChooser = new JDateChooser();
        formPanel.add(dateChooser,gbc);

        gbc.gridx = 0; gbc.gridy = 8;
        formPanel.add(new JLabel("Favorite Foods"), gbc);
        gbc.gridx = 1;
        
JList<String> foodList = new JList<>(foods);
foodList.setVisibleRowCount(1); // This makes it thin like a text field
foodList.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255))); 
JScrollPane scrollPane = new JScrollPane(foodList);
        formPanel.add(scrollPane, gbc);
        

gbc.gridx = 3; 
gbc.gridy = 3;
gbc.gridheight = 3;

ImageIcon originalIcon = new ImageIcon("../student.jpg");
Image scaledImage = originalIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
ImageIcon image = new ImageIcon(scaledImage);

JLabel imageLabel = new JLabel(image, JLabel.CENTER);
imageLabel.setPreferredSize(new Dimension(150, 150));
imageLabel.setOpaque(true);
imageLabel.setBackground(Color.WHITE);

// White padding + gray border
imageLabel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.GRAY, 1),
        BorderFactory.createEmptyBorder(5, 5, 5, 5)
));

formPanel.add(imageLabel, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(lightGreen);
        
        JButton saveBtn = new JButton("Save");
        saveBtn.setBackground(saveGreen);
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFocusPainted(false);
        
        JButton resetBtn = new JButton("Reset");
        resetBtn.setBackground(resetBlue);
        resetBtn.setForeground(Color.WHITE);
        resetBtn.setFocusPainted(false);
        
        buttonPanel.add(saveBtn);
        buttonPanel.add(resetBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder data = new StringBuilder();
                data.append("Full Name: ").append(nameField.getText()).append("\n");
                data.append("Gender: ").append(maleRadio.isSelected() ? "Male" : "Female").append("\n");
                data.append("Languages: ");
                if (amharicCheck.isSelected()) data.append("Amharic ");
                if (englishCheck.isSelected()) data.append("English ");
                if (germanCheck.isSelected()) data.append("German ");
                data.append("\n");
                data.append("Department: ").append(deptCombo.getSelectedItem()).append("\n");
                data.append("Year of Entry: ").append(yearCombo.getSelectedItem()).append("\n");
                data.append("Hobbies: ").append(hobbiesArea.getText()).append("\n");

                JOptionPane.showMessageDialog(AssignmentOne.this, data.toString(), "Saved Data", JOptionPane.INFORMATION_MESSAGE);
            }
        });

resetBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        nameField.setText("");
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        amharicCheck.setSelected(true);
        englishCheck.setSelected(false);
        germanCheck.setSelected(false);
        deptCombo.setSelectedIndex(0);
        yearCombo.setSelectedIndex(0);
        hobbiesArea.setText("");
        dateChooser.setDate(null);
        favFoodSpinner.setValue("Burger");
    }
});

        pack();
        setLocationRelativeTo(null);

        logo = new ImageIcon(getClass().getResource("../logo.png"));
        setIconImage(logo.getImage());
        
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AssignmentOne());
    }
}