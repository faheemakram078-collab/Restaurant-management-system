package src;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class LoginPanel extends JPanel {
    private Image backgroundImage;

    public LoginPanel(AppWindow window) {
        setLayout(null);

        // 1. Load Background Image safely
        try {
            backgroundImage = new ImageIcon("images/background.jpg").getImage();
        } catch (Exception e) {
            System.out.println("Background image could not be read.");
        }

        // ==========================================================
        // MAIN FLOATING CONTAINER CARD
        // ==========================================================
        JPanel mainCard = new JPanel();
        mainCard.setLayout(null);
        mainCard.setBackground(new Color(15, 32, 53, 240)); 
        mainCard.setBounds(225, 40, 450, 530); 
        mainCard.setBorder(new LineBorder(new Color(212, 175, 55), 1)); 
        add(mainCard);

        // Branding
        JLabel titleLabel = new JLabel("MUFAYA'S RESTAURANT", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 26));
        titleLabel.setForeground(new Color(212, 175, 55)); 
        titleLabel.setBounds(0, 25, 450, 40);
        mainCard.add(titleLabel);

        JLabel subTitleLabel = new JLabel("AUTHENTIC DESI PAKISTANI TASTE", JLabel.CENTER);
        subTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 10));
        subTitleLabel.setForeground(new Color(200, 200, 200)); 
        subTitleLabel.setBounds(0, 60, 450, 20);
        mainCard.add(subTitleLabel);

        // Logo
        JLabel logoLabel = new JLabel("", JLabel.CENTER);
        logoLabel.setBounds(150, 95, 150, 150);
        try {
            ImageIcon icon = new ImageIcon("images/logo.png");
            Image scaledImage = icon.getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            logoLabel.setText("[ Logo Missing ]");
            logoLabel.setForeground(Color.WHITE);
        }
        mainCard.add(logoLabel);

        // Inputs
        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblUser.setForeground(new Color(212, 175, 55));
        lblUser.setBounds(50, 260, 350, 20);
        mainCard.add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUser.setBackground(new Color(24, 45, 72));
        txtUser.setForeground(Color.WHITE);
        txtUser.setCaretColor(Color.WHITE);
        txtUser.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(212, 175, 55)));
        txtUser.setBounds(50, 285, 350, 35);
        mainCard.add(txtUser);

        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPass.setForeground(new Color(212, 175, 55));
        lblPass.setBounds(50, 340, 350, 20);
        mainCard.add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPass.setBackground(new Color(24, 45, 72));
        txtPass.setForeground(Color.WHITE);
        txtPass.setCaretColor(Color.WHITE);
        txtPass.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(212, 175, 55)));
        txtPass.setBounds(50, 365, 350, 35);
        mainCard.add(txtPass);

        // Login Button
        JButton btnLogin = new JButton("AUTHENTICATE SYSTEM ACCESS");
        btnLogin.setBackground(new Color(212, 175, 55)); 
        btnLogin.setForeground(new Color(15, 32, 53)); 
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBounds(50, 435, 350, 45);
        mainCard.add(btnLogin);

        // ==========================================================
        // COMBINED AUTHENTICATION & ROLE-BASED LOGIC
        // ==========================================================
        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();
            
            ArrayList<String> users = DBContext.getUsers();
            boolean authenticated = false;
            String role = "";

            for (String u : users) {
                String[] parts = u.split(",");
                // Ensure line has [User, Pass, Role]
                if (parts.length >= 3 && parts[0].equalsIgnoreCase(user) && parts[1].equals(pass)) {
                    authenticated = true;
                    role = parts[2]; 
                    break;
                }
            }

            if (authenticated) {
                JOptionPane.showMessageDialog(this, "Access Authorized. Welcome, " + user + "!");
                
                // CRITICAL: Set the role BEFORE switching screens
                window.setAuthenticatedRole(role); 
                
                txtUser.setText("");
                txtPass.setText("");
                window.showScreen("DASHBOARD");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials provided.", "Access Denied", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(new Color(15, 32, 53));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}