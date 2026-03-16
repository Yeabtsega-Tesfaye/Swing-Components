import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

    private JTextField display;
    private JLabel expressionLabel;
    
    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;
    private String currentExpression = "";

    public Calculator() {

        setTitle("Calculator");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // DISPLAY PANEL (shows expression and current input)
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        
        // Expression label (shows the full expression)
        expressionLabel = new JLabel(" ");
        expressionLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        expressionLabel.setHorizontalAlignment(JLabel.RIGHT);
        expressionLabel.setForeground(new Color(150, 150, 150));
        displayPanel.add(expressionLabel, BorderLayout.NORTH);
        
        // Main display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 48));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBorder(null);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        displayPanel.add(display, BorderLayout.CENTER);
        
        add(displayPanel, BorderLayout.NORTH);

        // BUTTON PANEL
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Button definitions with their grid positions (now with more functions)
        String[][] buttons = {
                {"C", "⌫", "√", "^"},
                {"7", "8", "9", "/"},
                {"4", "5", "6", "*"},
                {"1", "2", "3", "-"},
                {"0", ".", "=", "+"}
        };

        for (int row = 0; row < buttons.length; row++) {
            for (int col = 0; col < buttons[row].length; col++) {
                String text = buttons[row][col];
                
                JButton btn = new JButton(text);
                btn.setFont(new Font("Arial", Font.BOLD, text.length() > 1 ? 16 : 20));
                btn.setFocusPainted(false);

                // Colors (iPhone style with function buttons)
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

                btn.addActionListener(this);

                gbc.gridx = col;
                gbc.gridy = row;
                gbc.gridwidth = 1;

                panel.add(btn, gbc);
            }
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        // NUMBER
        if (cmd.matches("[0-9]")) {
            if (startNewNumber) {
                display.setText(cmd);
                startNewNumber = false;
                // Clear expression when starting new number after operator
                if (!operator.isEmpty()) {
                    currentExpression = firstNumber + " " + operator + " ";
                }
            } else {
                // Prevent multiple leading zeros
                if (display.getText().equals("0") && cmd.equals("0")) {
                    // Do nothing - keep single zero
                } else if (display.getText().equals("0") && !cmd.equals("0")) {
                    display.setText(cmd);
                } else {
                    display.setText(display.getText() + cmd);
                }
            }
            updateExpression();
        }

        // DECIMAL
        else if (cmd.equals(".")) {
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

        // OPERATOR (+, -, *, /)
        else if (cmd.matches("[+\\-*/]")) {
            if (!operator.isEmpty() && !startNewNumber) {
                // If there's a pending operation, calculate it first
                calculate();
            }
            firstNumber = Double.parseDouble(display.getText());
            operator = cmd;
            startNewNumber = true;
            currentExpression = firstNumber + " " + operator + " ";
            expressionLabel.setText(currentExpression);
        }

        // SQUARE ROOT
        else if (cmd.equals("√")) {
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

        // EXPONENTIAL (x^y)
        else if (cmd.equals("^")) {
            if (!operator.isEmpty() && !startNewNumber) {
                calculate();
            }
            firstNumber = Double.parseDouble(display.getText());
            operator = "^";
            startNewNumber = true;
            currentExpression = firstNumber + " ^ ";
            expressionLabel.setText(currentExpression);
        }

        // EQUALS
        else if (cmd.equals("=")) {
            calculate();
            operator = "";
            startNewNumber = true;
        }

        // CLEAR
        else if (cmd.equals("C")) {
            display.setText("0");
            firstNumber = 0;
            operator = "";
            startNewNumber = true;
            currentExpression = "";
            expressionLabel.setText(" ");
        }

        // BACKSPACE
        else if (cmd.equals("⌫")) {
            String text = display.getText();
            
            if (text.equals("Error")) {
                display.setText("0");
            } else if (text.length() > 1) {
                // Don't delete the minus sign if it's the only character
                if (text.length() == 2 && text.startsWith("-")) {
                    display.setText("0");
                } else {
                    display.setText(text.substring(0, text.length() - 1));
                }
            } else {
                display.setText("0");
            }
            updateExpression();
        }
    }

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
                calculationString = firstNumber + " × " + secondNumber;
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

    private String formatResult(double result) {
        // Handle decimal formatting
        if (Double.isInfinite(result) || Double.isNaN(result)) {
            return "Error";
        }
        if (result == (long) result) {
            return String.valueOf((long) result);
        } else {
            // Limit to 8 decimal places to avoid long decimals
            String formatted = String.format("%.8f", result);
            // Remove trailing zeros
            formatted = formatted.replaceAll("0*$", "").replaceAll("\\.$", "");
            return formatted;
        }
    }

    private void updateExpression() {
        if (!operator.isEmpty()) {
            expressionLabel.setText(currentExpression + display.getText());
        } else if (!currentExpression.isEmpty() && !currentExpression.contains("=")) {
            expressionLabel.setText(currentExpression + display.getText());
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() ->
                new Calculator().setVisible(true)
        );
    }
}