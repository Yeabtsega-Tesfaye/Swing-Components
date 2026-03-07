import javax.swing.JOptionPane;

public class OptionDemo {
    public static void main(String[] args) {
        String[] options = {"Easy", "Medium", "Hard"};

        int level = JOptionPane.showOptionDialog(
                null,
                "Choose difficulty:",
                "Game Level",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        JOptionPane.showMessageDialog(null, "You selected: " + options[level]);
    }
}

// JOptionPane.showMessageDialog(null, "Info", "Title", JOptionPane.INFORMATION_MESSAGE);
// JOptionPane.showMessageDialog(null, "Warning", "Title", JOptionPane.WARNING_MESSAGE);
// JOptionPane.showMessageDialog(null, "Error", "Title", JOptionPane.ERROR_MESSAGE);
// JOptionPane.showMessageDialog(null, "Question", "Title", JOptionPane.QUESTION_MESSAGE);


// public class StudentApp {
//     public static void main(String[] args) {
        
//         String name = JOptionPane.showInputDialog("Enter your name:");
//         String ageInput = JOptionPane.showInputDialog("Enter your age:");
        
//         int age = Integer.parseInt(ageInput);

//         if (age >= 18) {
//             JOptionPane.showMessageDialog(null, name + " is an Adult.");
//         } else {
//             JOptionPane.showMessageDialog(null, name + " is a Minor.");
//         }
//     }
// }