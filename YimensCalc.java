import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;

public class YimensCalc {
    int boardwidth = 360;
    int boardheight = 600; // Increased height to accommodate expression display
    Color black = Color.BLACK;
    Color white = Color.WHITE;
    Color darkGray = new Color(50, 50, 50); // For buttons background
    Color lightGray = new Color(200, 200, 200); // For top buttons
    String[] buttonValues = {
            "AC", "+/-", "%", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };
    String[] rightSymbols = { "+", "*", "-", "/", "=" };
    String[] topSymbols = { "AC", "+/-", "%" };
    JFrame frame = new JFrame("YimensCalc");
    JLabel expressionLabel = new JLabel(); // New label to show the expression
    JLabel resultLabel = new JLabel(); // Label to show current input/result
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    // a+b, a-b, a*b....
    String A = "0";
    String operator = null;
    String B = null;
    boolean operatorJustPressed = false; // Flag to track if operator was just pressed

    YimensCalc(){
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        // Display panel with two labels
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBackground(black);
        
        // Expression label (top part of display - shows the full expression)
        expressionLabel.setForeground(lightGray);
        expressionLabel.setBackground(black);
        expressionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        expressionLabel.setHorizontalAlignment(JLabel.RIGHT);
        expressionLabel.setText("");
        expressionLabel.setOpaque(true);
        expressionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        
        // Result label (bottom part of display - shows current input/result)
        resultLabel.setForeground(white);
        resultLabel.setBackground(black);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        resultLabel.setHorizontalAlignment(JLabel.RIGHT);
        resultLabel.setText("0");
        resultLabel.setOpaque(true);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        // Add both labels to display panel
        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(black);
        textPanel.add(expressionLabel, BorderLayout.NORTH);
        textPanel.add(resultLabel, BorderLayout.SOUTH);
        
        displayPanel.add(textPanel, BorderLayout.CENTER);
        frame.add(displayPanel, BorderLayout.NORTH);
        
        // Buttons panel
        buttonsPanel.setLayout(new GridLayout(5, 4, 1, 1)); // Added gaps between buttons
        buttonsPanel.setBackground(black);
        frame.add(buttonsPanel);

        for(int i = 0; i < buttonValues.length; i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(BorderFactory.createLineBorder(black, 2));
            
            // Color scheme: Black and White only
            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(lightGray);
                button.setForeground(black);
            }
            else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(darkGray);
                button.setForeground(white);
            }
            else{
                button.setBackground(white);
                button.setForeground(black);
            }
            
            buttonsPanel.add(button);
            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    
                    if(buttonValue.equals("√")){
                        double numDisplay = Double.parseDouble(resultLabel.getText());
                        expressionLabel.setText("√(" + resultLabel.getText() + ")");
                        numDisplay = Math.sqrt(numDisplay);
                        resultLabel.setText(removeZeroDecimal(numDisplay));
                    }
                    else if(Arrays.asList(rightSymbols).contains(buttonValue)){
                        if(buttonValue.equals("=")){
                            if(A != null && operator != null && !operatorJustPressed){
                                B = resultLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);
                                
                                // Update expression
                                expressionLabel.setText(A + " " + operator + " " + B + " =");
                                
                                if(operator.equals("+")){
                                    resultLabel.setText(removeZeroDecimal(numA + numB));
                                }
                                else if(operator.equals("-")){
                                    resultLabel.setText(removeZeroDecimal(numA - numB));    
                                }
                                else if(operator.equals("*")){
                                    resultLabel.setText(removeZeroDecimal(numA * numB));
                                }
                                else if(operator.equals("/")){
                                    if(numB != 0){
                                        resultLabel.setText(removeZeroDecimal(numA / numB));
                                    } else {
                                        resultLabel.setText("Error");
                                        expressionLabel.setText("Division by zero");
                                    }
                                }
                                clearAll();
                            }
                        }
                        else if("+-*/".contains(buttonValue)){
                            if(operator == null){
                                A = resultLabel.getText();
                                operator = buttonValue;
                                expressionLabel.setText(A + " " + operator);
                                resultLabel.setText("0");
                                operatorJustPressed = true;
                            } else if (!operatorJustPressed) {
                                // If there's already an operator, calculate intermediate result
                                B = resultLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);
                                
                                if(operator.equals("+")){
                                    A = removeZeroDecimal(numA + numB);
                                }
                                else if(operator.equals("-")){
                                    A = removeZeroDecimal(numA - numB);    
                                }
                                else if(operator.equals("*")){
                                    A = removeZeroDecimal(numA * numB);
                                }
                                else if(operator.equals("/")){
                                    if(numB != 0){
                                        A = removeZeroDecimal(numA / numB);
                                    } else {
                                        resultLabel.setText("Error");
                                        expressionLabel.setText("Division by zero");
                                        clearAll();
                                        return;
                                    }
                                }
                                
                                operator = buttonValue;
                                expressionLabel.setText(A + " " + operator);
                                resultLabel.setText("0");
                                operatorJustPressed = true;
                            } else {
                                // Just change the operator
                                operator = buttonValue;
                                expressionLabel.setText(A + " " + operator);
                            }
                        }
                    } 
                    else if(Arrays.asList(topSymbols).contains(buttonValue)){
                        if(buttonValue.equals("AC")){
                            clearAll();
                            expressionLabel.setText("");
                            resultLabel.setText("0");
                        }
                        else if(buttonValue.equals("+/-")){
                            double numDisplay = Double.parseDouble(resultLabel.getText());
                            numDisplay *= -1;
                            resultLabel.setText(removeZeroDecimal(numDisplay));
                            if(operator == null) {
                                A = resultLabel.getText();
                            }
                        }
                        else if(buttonValue.equals("%")){
                            double numDisplay = Double.parseDouble(resultLabel.getText());
                            numDisplay /= 100;
                            resultLabel.setText(removeZeroDecimal(numDisplay));
                            if(operator == null) {
                                A = resultLabel.getText();
                            }
                        }
                    } 
                    else { // digits and decimal
                        operatorJustPressed = false;
                        
                        if(buttonValue.equals(".")){
                            if(!resultLabel.getText().contains(".")) {
                                resultLabel.setText(resultLabel.getText() + buttonValue);  
                            }  
                        }
                        else if("0123456789".contains(buttonValue)){
                            if(resultLabel.getText().equals("0") || resultLabel.getText().equals("Error")){
                                resultLabel.setText(buttonValue);
                            }
                            else{
                                resultLabel.setText(resultLabel.getText() + buttonValue);
                            }
                        }
                    }
                }
            });
        }
        frame.setVisible(true);
    }

    void clearAll() {
        A = "0";
        operator = null;
        B = null;
        operatorJustPressed = false;
    }

    String removeZeroDecimal(double numDisplay) {
        if (numDisplay % 1 == 0) {
            return Integer.toString((int) numDisplay);
        }
        // Remove trailing zeros after decimal
        String text = Double.toString(numDisplay);
        if (text.contains(".")) {
            text = text.replaceAll("0*$", "").replaceAll("\\.$", "");
        }
        return text;
    }
    
    public static void main(String[] args) {
        new YimensCalc();
    }
}