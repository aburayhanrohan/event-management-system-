import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class EventUI extends JFrame {

    public EventUI() {
        setTitle("KHAN EVENT CLUB");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load background image
        ImageIcon img = loadImage("even.jpg");
        JLabel backgroundLabel = new JLabel();
        if (img != null) {
            backgroundLabel.setIcon(img);
        } else {
            JOptionPane.showMessageDialog(this, "Image not found: even.jpg", "Error", JOptionPane.ERROR_MESSAGE);
        }
        backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(backgroundLabel, BorderLayout.CENTER);

        // Top Navigation Bar
        JPanel navBar = new JPanel(new BorderLayout());
        navBar.setBackground(new Color(240, 230, 220));
        
        JLabel contactLabel = new JLabel("  Phone: 901522  |  WhatsApp: +880191-2548444");
        contactLabel.setForeground(Color.BLACK);
        contactLabel.setFont(new Font("Arial", Font.BOLD, 20));
        navBar.add(contactLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 20));
        buttonPanel.setBackground(new Color(240, 230, 220));

        String[] navItems = {"Home", "Offers"};
        for (String item : navItems) {
            JButton button = createStyledButton(item);
            buttonPanel.add(button);
            button.addActionListener(e -> {
                if (item.equals("Offers")) {
                    showOffersFrame();
                }
            });
        }

        JButton bookNow = createStyledButton("\uD83D\uDCCC Book Now");
        bookNow.setBackground(new Color(189, 135, 80));
        bookNow.setForeground(Color.WHITE);
        bookNow.addActionListener(e -> new ContactForm());
        buttonPanel.add(bookNow);

        JButton adminButton = createStyledButton("Admin Login");
        adminButton.setBackground(new Color(255, 99, 71));
        adminButton.setForeground(Color.WHITE);
        adminButton.addActionListener(e -> showAdminLoginFrame());
        buttonPanel.add(adminButton);

        navBar.add(buttonPanel, BorderLayout.EAST);
        add(navBar, BorderLayout.NORTH);
        
        setVisible(true);
    }

    private ImageIcon loadImage(String imagePath) {
        try {
            ImageIcon imgIcon = new ImageIcon(getClass().getResource(imagePath));
            Image img = imgIcon.getImage();

            int frameWidth = 1920;
            int frameHeight = 1080;

            Image resizedImg = img.getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImg);
        } catch (Exception e) {
            return null;
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setPreferredSize(new Dimension(200, 60));
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237));
            }
        });
        return button;
    }

    private void showOffersFrame() {
        JFrame offersFrame = new JFrame("Offers");
        offersFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        offersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        ImageIcon offerImg = loadImage("offer.jpg");
        JLabel backgroundLabel = new JLabel();
        if (offerImg != null) {
            backgroundLabel.setIcon(offerImg);
        } else {
            JOptionPane.showMessageDialog(offersFrame, "Image not found: offer.jpg", "Error", JOptionPane.ERROR_MESSAGE);
        }
        backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.setVerticalAlignment(SwingConstants.CENTER);
        offersFrame.add(backgroundLabel);
        offersFrame.setVisible(true);
    }

    private void showAdminLoginFrame() {
        JFrame loginFrame = new JFrame("Admin Login");
        loginFrame.setSize(400, 300);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        JButton loginButton = new JButton("Login");
        loginPanel.add(loginButton);

        loginFrame.add(loginPanel, BorderLayout.CENTER);
        loginButton.addActionListener(e -> {
            if (usernameField.getText().equals("admin") && new String(passwordField.getPassword()).equals("1234")) {
                JOptionPane.showMessageDialog(loginFrame, "Login Successful!");
                loginFrame.dispose();
                openAdminDashboard();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid Credentials! Try Again.");
            }
        });
        loginFrame.setVisible(true);
    }

    private void openAdminDashboard() {
        JOptionPane.showMessageDialog(this, "Welcome to the Admin Dashboard!");
    }

    public static void main(String[] args) {
        new EventUI();
    }
}
