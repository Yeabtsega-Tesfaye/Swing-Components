import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIAssignment1 extends JFrame {
    // Form fields
    private JTextField nameField;
    private JRadioButton maleRadio, femaleRadio;
    private JCheckBox amharicCheck, englishCheck, germanCheck;
    private JComboBox<String> deptCombo;
    private JTextField yearField;
    private JTextField hobbiesField;
    private JComboBox<String> nationalityCombo;
    private JTextField dobField;
    private JTextField favFoodField;

    public GUIAssignment1() {
        setTitle("GUI individual Assignment 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu bar with File menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Title label
        JLabel titleLabel = new JLabel("GUI individual Assignment 1", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);

        // Form panel with GridLayout (rows, 2 columns)
        JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 5)); // 0 rows means unlimited, 10 hgap, 5 vgap
        formPanel.setBorder(new TitledBorder("File"));

        // Initialize components
        nameField = new JTextField();
        maleRadio = new JRadioButton("Male", true);
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        amharicCheck = new JCheckBox("Amharic");
        englishCheck = new JCheckBox("English");
        germanCheck = new JCheckBox("German");

        deptCombo = new JComboBox<>(new String[]{"Computer Science", "Information Technology", "Engineering", "Mathematics"});
        yearField = new JTextField();
        hobbiesField = new JTextField();
        nationalityCombo = new JComboBox<>(new String[]{"Local", "Foreign", "Other"});
        dobField = new JTextField();
        favFoodField = new JTextField();

        // Add rows: label + component
        // Full Name
        formPanel.add(new JLabel("Full Name:"));
        formPanel.add(nameField);

        // Gender
        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        formPanel.add(genderPanel);

        // Language you speak
        formPanel.add(new JLabel("Language you speak:"));
        JPanel langPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        langPanel.add(amharicCheck);
        langPanel.add(englishCheck);
        langPanel.add(germanCheck);
        formPanel.add(langPanel);

        // Department
        formPanel.add(new JLabel("Department:"));
        formPanel.add(deptCombo);

        // Year of Entry
        formPanel.add(new JLabel("Year of Entry:"));
        formPanel.add(yearField);

        // Hobbies
        formPanel.add(new JLabel("Hobbies:"));
        formPanel.add(hobbiesField);

        // Nationality Type
        formPanel.add(new JLabel("Nationality Type:"));
        formPanel.add(nationalityCombo);

        // Date of Birth
        formPanel.add(new JLabel("Date of Birth:"));
        formPanel.add(dobField);

        // Favorite Foods
        formPanel.add(new JLabel("Favorite Foods:"));
        formPanel.add(favFoodField);

        add(formPanel, BorderLayout.CENTER);

        // Button panel for Save and Reset
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton saveButton = new JButton("Save");
        JButton resetButton = new JButton("Reset Form");
        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect data and show a summary dialog
                StringBuilder data = new StringBuilder();
                data.append("Full Name: ").append(nameField.getText()).append("\n");
                data.append("Gender: ").append(maleRadio.isSelected() ? "Male" : "Female").append("\n");
                data.append("Languages: ");
                if (amharicCheck.isSelected()) data.append("Amharic ");
                if (englishCheck.isSelected()) data.append("English ");
                if (germanCheck.isSelected()) data.append("German ");
                data.append("\n");
                data.append("Department: ").append(deptCombo.getSelectedItem()).append("\n");
                data.append("Year of Entry: ").append(yearField.getText()).append("\n");
                data.append("Hobbies: ").append(hobbiesField.getText()).append("\n");
                data.append("Nationality: ").append(nationalityCombo.getSelectedItem()).append("\n");
                data.append("Date of Birth: ").append(dobField.getText()).append("\n");
                data.append("Favorite Foods: ").append(favFoodField.getText());

                JOptionPane.showMessageDialog(GUIAssignment1.this, data.toString(), "Saved Data", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset to default values
                nameField.setText("");
                maleRadio.setSelected(true);
                amharicCheck.setSelected(false);
                englishCheck.setSelected(false);
                germanCheck.setSelected(false);
                deptCombo.setSelectedItem("Computer Science");
                yearField.setText("");
                hobbiesField.setText("");
                nationalityCombo.setSelectedItem("Local");
                dobField.setText("");
                favFoodField.setText("");
            }
        });

        pack();
        setLocationRelativeTo(null); // center on screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIAssignment1());
    }
}