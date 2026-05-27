package src;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.imageio.ImageIO;

public class LoginPanel extends JPanel {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnAuthenticate;
    private Image rightBackgroundImage;
    private Image restaurantLogoImage;

    public LoginPanel(AppWindow window) {
        setLayout(null);
        setBackground(new Color(23, 33, 43));

        // Load asset images safely
        try {
            rightBackgroundImage = ImageIO.read(new File("images/image_e89032.jpg"));
            restaurantLogoImage = ImageIO.read(new File("images/logo.png")); 
        } catch (Exception e) {
            System.out.println("Image assets could not be loaded.");
        }

        // =========================================================================
        // LEFT SIDE: CLEAN AUTHENTICATION PANEL
        // =========================================================================
        JPanel leftPanel = new JPanel(null);
        leftPanel.setBounds(0, 0, 480, 700);
        leftPanel.setBackground(new Color(28, 40, 51));

        // Logo Component
        JLabel lblLogo = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (restaurantLogoImage != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2d.drawImage(restaurantLogoImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        lblLogo.setBounds(40, 50, 110, 110);
        leftPanel.add(lblLogo);

        // System Titles
        JLabel lblMainTitle = new JLabel("Mufaya's Restaurant Suite");
        lblMainTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblMainTitle.setForeground(Color.WHITE);
        lblMainTitle.setBounds(165, 75, 300, 30); 
        leftPanel.add(lblMainTitle);

        // Subtitle text matching your branding
        JLabel lblSubTitle = new JLabel("AUTHENTIC DESI PAKISTANI TASTE");
        lblSubTitle.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblSubTitle.setForeground(new Color(212, 175, 55)); 
        lblSubTitle.setBounds(167, 105, 300, 20); 
        leftPanel.add(lblSubTitle);

        // Welcome Header
        JLabel lblWelcome = new JLabel("Welcome back");
        lblWelcome.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBounds(40, 200, 350, 40);
        leftPanel.add(lblWelcome);

        JLabel lblInstructions = new JLabel("Sign in to your account");
        lblInstructions.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblInstructions.setForeground(Color.GRAY);
        lblInstructions.setBounds(40, 240, 350, 20);
        leftPanel.add(lblInstructions);

        // Username Input Field
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsername.setBackground(new Color(36, 52, 67));
        txtUsername.setForeground(Color.WHITE);
        txtUsername.setCaretColor(Color.WHITE);
        txtUsername.setBounds(40, 290, 390, 42);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(60, 80, 100), 1),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        leftPanel.add(txtUsername);

        // Password Input Field
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setBackground(new Color(36, 52, 67));
        txtPassword.setForeground(Color.WHITE);
        txtPassword.setCaretColor(Color.WHITE);
        txtPassword.setBounds(40, 360, 390, 42);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(60, 80, 100), 1),
            BorderFactory.createEmptyBorder(0, 10, 0, 10)
        ));
        leftPanel.add(txtPassword);

        // Authentication Action Button
        btnAuthenticate = new JButton("AUTHENTICATE SYSTEM ACCESS");
        btnAuthenticate.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        Color goldColor = new Color(212, 175, 55);
        btnAuthenticate.setBackground(goldColor); 
        btnAuthenticate.setForeground(new Color(28, 40, 51)); 
        
        btnAuthenticate.setOpaque(true);
        btnAuthenticate.setContentAreaFilled(true);
        btnAuthenticate.setBorderPainted(true);
        btnAuthenticate.setFocusPainted(false);
        btnAuthenticate.setBorder(new LineBorder(goldColor, 1)); 
        
        btnAuthenticate.setBounds(40, 440, 390, 46);
        leftPanel.add(btnAuthenticate);

        add(leftPanel);

        // =========================================================================
        // RIGHT SIDE: BG ART PANEL
        // =========================================================================
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (rightBackgroundImage != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2d.drawImage(rightBackgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        rightPanel.setBounds(480, 0, 520, 700);
        add(rightPanel);

        // =========================================================================
        // FILE-BASED AUTHENTICATION LOGIC WITH DYNAMIC DEBUG LOGGING
        // =========================================================================
        btnAuthenticate.addActionListener(e -> {
            String inputUser = txtUsername.getText().trim();
            String inputPass = new String(txtPassword.getPassword()).trim();

            if (inputUser.isEmpty() || inputPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all credentials.", "Incomplete Fields", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Generate SHA-256 hash of the input password
            String hashedInput = DBContext.hashPassword(inputPass);

            System.out.println("\n--- NEW LOGIN ATTEMPT ---");
            System.out.println("Typed Username: [" + inputUser + "]");
            System.out.println("Typed Password (Hashed): [" + hashedInput + "]");

            boolean isAuthenticated = false;
            String userRole = ""; 
            File userFile = new File("data/users.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String line;
                int lineNum = 0;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue; 
                    lineNum++;

                    String[] userDetails = line.split(",");
                    if (userDetails.length >= 3) {
                        String registeredUser = userDetails[0].trim();
                        String registeredPass = userDetails[1].trim(); // Holds hashed string from users.txt
                        String registeredRole = userDetails[2].trim().replaceAll("[^a-zA-Z]", "");

                        System.out.println("Checking line " + lineNum + ": FileUser=[" + registeredUser + "], FilePass=[" + registeredPass + "], FileRole=[" + registeredRole + "]");

                        // Case-insensitive user compare, strict case-sensitive hash compare
                        if (inputUser.equalsIgnoreCase(registeredUser) && hashedInput.equals(registeredPass)) {
                            isAuthenticated = true;
                            userRole = registeredRole;
                            System.out.println("MATCH FOUND! Role identified as: " + userRole);
                            break; 
                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error reading database configuration.", "System Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
                return;
            }

            if (isAuthenticated) {
                // 1. Pass the role back to AppWindow to configure dashboard layout access tags
                window.setAuthenticatedRole(userRole);
                
                // 2. Direct screen layout engine
                window.showScreen("DASHBOARD");
                
                // FORCE REPAINT IMMEDATELY TO PREVENT FREEZING
                window.getContentPane().revalidate();
                window.getContentPane().repaint();
                
                // 3. Clear fields
                txtUsername.setText("");
                txtPassword.setText("");
            } else {
                System.out.println("AUTHENTICATION FAILED: No database record matched standard input sequence.");
                JOptionPane.showMessageDialog(this, "Access privileges could not be verified.", "Authentication Failure", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    public void doLayout() {
        super.doLayout();
        if (getComponentCount() >= 2) {
            getComponent(0).setBounds(0, 0, 480, getHeight());
            getComponent(1).setBounds(480, 0, getWidth() - 480, getHeight());
        }
    }
} 