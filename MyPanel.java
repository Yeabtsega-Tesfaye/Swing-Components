import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel {

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Hello", 50, 50);
    }

    public static void main(String[] args) {
        
    }
}