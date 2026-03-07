import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ContainerShowcase extends JFrame {

    private JDialog modalDialog;
    private JDialog modelessDialog;
    private JWindow customWindow;
    private JScrollPane scrollPane;
    private JPanel mainPanel;
    private JTextArea textArea;

    public ContainerShowcase() {
        // ===== JFrame Configuration (Top-Level Container) =====
        setTitle("Ultimate Swing Container Showcase");
        setSize(1000, 700);
        setLocationRelativeTo(null); // center window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== RootPane Access (Behind the Scene) =====
        JRootPane rootPane = getRootPane();
        Container contentPane = getContentPane(); // Actual container where components go
        contentPane.setLayout(new BorderLayout());

        // ===== Main Panel (Intermediate Container) =====
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("Main JPanel (Container)"));

        // ===== Create Tabbed Container =====
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Scrollable Panel", createScrollablePanel());
        tabbedPane.addTab("Layered Pane Demo", createLayeredPaneDemo());
        tabbedPane.addTab("Controls", createControlPanel());

        // Add tabbed container to main panel
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Add main panel to frame content pane
        contentPane.add(mainPanel, BorderLayout.CENTER);

        // ===== GlassPane Demonstration (Hidden Container Layer) =====
        JPanel glassPane = (JPanel) rootPane.getGlassPane();
        glassPane.setVisible(false);
        glassPane.setLayout(new GridBagLayout());
        glassPane.add(new JLabel("GlassPane Active (Top Invisible Layer)"));
    }

    // ===== JScrollPane Demo (Viewport Container) =====
    private JPanel createScrollablePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        textArea = new JTextArea();
        textArea.setText("This JTextArea is inside a JScrollPane.\n\n");
        
        // Add large content to enable scrolling
        for (int i = 1; i <= 100; i++) {
            textArea.append("Line " + i + " - Demonstrating scrolling capability.\n");
        }

        // Create JScrollPane (Container wrapping viewport)
        scrollPane = new JScrollPane(
                textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        // ScrollPane Methods Demonstration
        scrollPane.setWheelScrollingEnabled(true);
        scrollPane.setBorder(BorderFactory.createTitledBorder("JScrollPane Container"));

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    // ===== JLayeredPane Demo (Advanced Container) =====
    private JLayeredPane createLayeredPaneDemo() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 300));

        JLabel backLabel = new JLabel("Background Layer");
        backLabel.setBounds(50, 50, 200, 50);
        backLabel.setOpaque(true);
        backLabel.setBackground(Color.LIGHT_GRAY);

        JButton middleButton = new JButton("Middle Layer Button");
        middleButton.setBounds(100, 100, 200, 50);

        JLabel frontLabel = new JLabel("Front Layer (Higher Z-Order)");
        frontLabel.setBounds(150, 150, 250, 50);
        frontLabel.setForeground(Color.RED);

        // Add components with layer priorities
        layeredPane.add(backLabel, Integer.valueOf(0));
        layeredPane.add(middleButton, Integer.valueOf(1));
        layeredPane.add(frontLabel, Integer.valueOf(2));

        return layeredPane;
    }

    // ===== Control Panel to Trigger Containers =====
    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 3, 10, 10));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Container Controls"));

        JButton showModalDialogBtn = new JButton("Show Modal JDialog");
        JButton showModelessDialogBtn = new JButton("Show Modeless JDialog");
        JButton showJWindowBtn = new JButton("Show JWindow");
        JButton toggleGlassPaneBtn = new JButton("Toggle GlassPane");
        JButton removeComponentBtn = new JButton("Remove TextArea");
        JButton addComponentBtn = new JButton("Add TextArea Back");

        // ===== Modal JDialog =====
        showModalDialogBtn.addActionListener(e -> {
            modalDialog = new JDialog(this, "Modal Dialog", true);
            modalDialog.setSize(300, 200);
            modalDialog.setLayout(new FlowLayout());
            modalDialog.add(new JLabel("This is a Modal JDialog"));
            modalDialog.add(new JButton("Close"));
            modalDialog.setLocationRelativeTo(this);
            modalDialog.setVisible(true);
        });

        // ===== Modeless JDialog =====
        showModelessDialogBtn.addActionListener(e -> {
            modelessDialog = new JDialog(this, "Modeless Dialog", false);
            modelessDialog.setSize(300, 200);
            modelessDialog.setLayout(new FlowLayout());
            modelessDialog.add(new JLabel("This is a Modeless JDialog"));
            modelessDialog.add(new JButton("I don't block the frame"));
            modelessDialog.setLocation(100, 100);
            modelessDialog.setVisible(true);
        });

        // ===== JWindow (Borderless Container) =====
        showJWindowBtn.addActionListener(e -> {
            customWindow = new JWindow(this);
            customWindow.setSize(300, 150);
            customWindow.setLayout(new BorderLayout());
            customWindow.add(new JLabel("This is a JWindow (No Title Bar)", SwingConstants.CENTER), BorderLayout.CENTER);
            customWindow.setLocation(300, 200);
            customWindow.setAlwaysOnTop(true);
            customWindow.setVisible(true);

            // Auto close after 3 seconds
            new Timer(3000, ev -> customWindow.dispose()).start();
        });

        // ===== GlassPane Toggle =====
        toggleGlassPaneBtn.addActionListener(e -> {
            JRootPane rootPane = getRootPane();
            Component glass = rootPane.getGlassPane();
            glass.setVisible(!glass.isVisible());
        });

        // ===== Remove Component (Container.remove()) =====
        removeComponentBtn.addActionListener(e -> {
            scrollPane.setViewportView(new JLabel("TextArea Removed from JScrollPane"));
            revalidate();
            repaint();
        });

        // ===== Add Component Back (Container.add()) =====
        addComponentBtn.addActionListener(e -> {
            scrollPane.setViewportView(textArea);
            revalidate();
            repaint();
        });

        // Add buttons to control panel container
        controlPanel.add(showModalDialogBtn);
        controlPanel.add(showModelessDialogBtn);
        controlPanel.add(showJWindowBtn);
        controlPanel.add(toggleGlassPaneBtn);
        controlPanel.add(removeComponentBtn);
        controlPanel.add(addComponentBtn);

        return controlPanel;
    }

    // ===== Main Method (Entry Point) =====
    public static void main(String[] args) {
        // Event Dispatch Thread (EDT) - Swing best practice
        SwingUtilities.invokeLater(() -> {
            ContainerShowcase app = new ContainerShowcase();
            app.setVisible(true);
        });
    }
}