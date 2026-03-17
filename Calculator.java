import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A scientific calculator with hybrid event handling approach:
 * - Lambda expressions for simple, independent actions
 * - Inner classes for complex, shared logic
 * - Method references for reusable operations
 */
public class Calculator extends JFrame {
    
    // ============= INSTANCE VARIABLES =============
    // These maintain the calculator's state between operations
    
    private JTextField display;           // Shows current number/result
    private JLabel expressionLabel;        // Shows the full expression (e.g., "5 + 3 =")
    
    private double firstNumber = 0;        // First operand for binary operations
    private String operator = "";           // Current operator (+, -, *, /, ^)
    private boolean startNewNumber = true;  // Flag to start new number after operator
    private String currentExpression = "";  // Stores the expression being built

    // ============= CONSTRUCTOR =============
    // Sets up the UI and event handlers
    public Calculator() {
        // Basic window setup
        setTitle("Calculator");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Initialize all UI components
        initializeDisplay();
        initializeButtonPanel();
        
        // Make window visible
        setVisible(true);
    }

    // ============= UI INITIALIZATION METHODS =============
    
    /**
     * Creates the top display panel showing expression and current number
     */
    private void initializeDisplay() {
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        // Expression label (smaller text above main display)
        expressionLabel = new JLabel(" ");
        expressionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        expressionLabel.setHorizontalAlignment(JLabel.RIGHT);
        expressionLabel.setForeground(new Color(150, 150, 150));
        displayPanel.add(expressionLabel, BorderLayout.NORTH);
        
        // Main display (large text for current number)
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 48));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBorder(null);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        displayPanel.add(display, BorderLayout.CENTER);
        
        add(displayPanel, BorderLayout.NORTH);
    }

    /**
     * Creates all calculator buttons with their respective event handlers
     */
    private void initializeButtonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Button layout: rows and columns
        String[][] buttons = {
                {"C", "⌫", "√", "^"},
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"0", ".", "=", "+"}
        };

        // Create each button and assign appropriate handler
        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                String text = buttons[row][col];
                
                JButton btn = createStyledButton(text);
                assignButtonHandler(btn, text);  // Different handlers based on button type

                gbc.gridx = col;
                gbc.gridy = row;
                panel.add(btn, gbc);
            }
        }

        add(panel, BorderLayout.CENTER);
    }

    /**
     * Creates a button with appropriate styling based on its function
     */
    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, text.length() > 1 ? 16 : 20));
        btn.setFocusPainted(false);

        // Color coding based on button type (iPhone style)
        if (text.matches("[0-9.]")) {
            btn.setBackground(new Color(60, 60, 60));
            btn.setForeground(Color.WHITE);
        } else if (text.equals("=")) {
            btn.setBackground(new Color(255, 149, 0));
            btn.setForeground(Color.WHITE);
        } else if (text.equals("C") || text.equals("⌫")) {
            btn.setBackground(new Color(165, 165, 165));
        } else if (text.equals("√") || text.equals("^")) {
            btn.setBackground(new Color(120, 120, 120));
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(new Color(255, 149, 0));
            btn.setForeground(Color.WHITE);
        }

        return btn;
    }

    // ============= EVENT HANDLER ASSIGNMENT =============
    // This is where the HYBRID approach is implemented
    // Different types of buttons get different types of handlers

    /**
     * Assigns the appropriate event handler based on button type
     * Demonstrates the hybrid approach:
     * - Lambda for simple actions (clear, backspace)
     * - Inner class for complex shared logic (numbers, operators)
     * - Method reference for reusable operations
     */
    private void assignButtonHandler(JButton btn, String text) {
        
        // ===== CASE 1: LAMBDA EXPRESSIONS =====
        // Used for simple, independent actions that don't need complex shared state
        
        if (text.equals("C")) {
            // Clear button - simple reset operation
            btn.addActionListener(e -> {
                display.setText("0");
                firstNumber = 0;
                operator = "";
                startNewNumber = true;
                currentExpression = "";
                expressionLabel.setText(" ");
            });
        }
        else if (text.equals("⌫")) {
            // Backspace button - simple text manipulation
            btn.addActionListener(e -> {
                String currentText = display.getText();
                if (currentText.equals("Error")) {
                    display.setText("0");
                } else if (currentText.length() > 1) {
                    if (currentText.length() == 2 && currentText.startsWith("-")) {
                        display.setText("0");
                    } else {
                        display.setText(currentText.substring(0, currentText.length() - 1));
                    }
                } else {
                    display.setText("0");
                }
                updateExpression();
            });
        }
        
        // ===== CASE 2: INNER CLASS INSTANCE =====
        // Used for buttons that share complex logic (numbers, operators)
        // This avoids code duplication and centralizes the logic
        
        else if (text.matches("[0-9]")) {
            // Number buttons use a dedicated inner class
            btn.addActionListener(new NumberButtonHandler(text));
        }
        else if (text.equals(".")) {
            // Decimal button uses another inner class
            btn.addActionListener(new DecimalButtonHandler());
        }
        else if (text.matches("[+\\-*/]")) {
            // Basic operators use the OperatorHandler inner class
            btn.addActionListener(new OperatorButtonHandler(text));
        }
        else if (text.equals("^")) {
            // Exponent operator
            btn.addActionListener(new OperatorButtonHandler("^"));
        }
        
        // ===== CASE 3: SINGLE-USE INNER CLASSES =====
        // For buttons with unique but complex logic
        
        else if (text.equals("√")) {
            // Square root has unique error checking (can't sqrt negative)
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double number = Double.parseDouble(display.getText());
                    if (number < 0) {
                        display.setText("Error");
                        startNewNumber = true;
                        return;
                    }
                    double result = Math.sqrt(number);
                    currentExpression = "√(" + number + ")";
                    display.setText(formatResult(result));
                    startNewNumber = true;
                    operator = "";
                    expressionLabel.setText(currentExpression + " =");
                }
            });
        }
        else if (text.equals("=")) {
            // Equals button needs to perform calculation and update state
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calculate();
                    operator = "";
                    startNewNumber = true;
                }
            });
        }
    }

    // ============= INNER CLASSES FOR EVENT HANDLING =============
    // These encapsulate related button logic
    
    /**
     * Handles all number buttons (0-9)
     * Inner class allows reuse of the same logic for all 10 digits
     */
    private class NumberButtonHandler implements ActionListener {
        private final String digit;
        
        public NumberButtonHandler(String digit) {
            this.digit = digit;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (startNewNumber) {
                display.setText(digit);
                startNewNumber = false;
                if (!operator.isEmpty()) {
                    currentExpression = firstNumber + " " + operator + " ";
                }
            } else {
                // Prevent multiple leading zeros
                if (display.getText().equals("0") && digit.equals("0")) {
                    // Do nothing - keep single zero
                } else if (display.getText().equals("0") && !digit.equals("0")) {
                    display.setText(digit);
                } else {
                    display.setText(display.getText() + digit);
                }
            }
            updateExpression();
        }
    }
    
    /**
     * Handles decimal point button
     * Separate class because decimal has unique rules (can't have two decimals)
     */
    private class DecimalButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (startNewNumber) {
                display.setText("0.");
                startNewNumber = false;
                if (!operator.isEmpty()) {
                    currentExpression = firstNumber + " " + operator + " ";
                }
            } else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
            updateExpression();
        }
    }
    
    /**
     * Handles all operator buttons (+, -, *, /, ^)
     * Centralizes the operator logic to avoid repetition
     */
    private class OperatorButtonHandler implements ActionListener {
        private final String op;
        
        public OperatorButtonHandler(String op) {
            this.op = op;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!operator.isEmpty() && !startNewNumber) {
                calculate();  // Chain operations: 5 + 3 - 2 etc.
            }
            firstNumber = Double.parseDouble(display.getText());
            operator = op;
            startNewNumber = true;
            currentExpression = firstNumber + " " + operator + " ";
            expressionLabel.setText(currentExpression);
        }
    }

    // ============= CALCULATION LOGIC METHODS =============
    
    /**
     * Performs the actual calculation based on current operator
     * Called by operator handlers and equals button
     */
    private void calculate() {
        if (operator.isEmpty()) return;

        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;
        String calculationString = "";

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                calculationString = firstNumber + " + " + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                calculationString = firstNumber + " - " + secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                calculationString = firstNumber + " * " + secondNumber;
                break;
            case "/":
                if (secondNumber == 0) {
                    display.setText("Error");
                    expressionLabel.setText(firstNumber + " ÷ " + secondNumber + " =");
                    operator = "";
                    startNewNumber = true;
                    return;
                }
                result = firstNumber / secondNumber;
                calculationString = firstNumber + " ÷ " + secondNumber;
                break;
            case "^":
                result = Math.pow(firstNumber, secondNumber);
                calculationString = firstNumber + " ^ " + secondNumber;
                break;
        }

        display.setText(formatResult(result));
        expressionLabel.setText(calculationString + " =");
        firstNumber = result;
    }

    /**
     * Formats the result to avoid unnecessary decimal places
     * e.g., 5.0 becomes "5", 3.33333 becomes "3.33333333"
     */
    private String formatResult(double result) {
        if (Double.isInfinite(result) || Double.isNaN(result)) {
            return "Error";
        }
        if (result == (long) result) {
            return String.valueOf((long) result);
        } else {
            String formatted = String.format("%.8f", result);
            formatted = formatted.replaceAll("0*$", "").replaceAll("\\.$", "");
            return formatted;
        }
    }

    /**
     * Updates the expression label to show the current calculation
     */
    private void updateExpression() {
        if (!operator.isEmpty()) {
            expressionLabel.setText(currentExpression + display.getText());
        } else if (!currentExpression.isEmpty() && !currentExpression.contains("=")) {
            expressionLabel.setText(currentExpression + display.getText());
        }
    }

    // ============= MAIN METHOD =============
    public static void main(String[] args) {
        // Use system look and feel for native appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create calculator on Event Dispatch Thread (EDT) for thread safety
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}