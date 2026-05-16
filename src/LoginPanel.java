package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginPanel extends JPanel {
    public LoginPanel(AppWindow window) {
        setLayout(null);
        setBackground(new Color(44, 62, 80)); // Elegant Dark Blue slate background

        // Brand Title Label
        JLabel title = new JLabel("SPICY BITES RESTAURANT", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(new Color(241, 196, 15)); // Vivid Gold Color
        title.setBounds(200, 50, 500, 40);
        add(title);

        // Graphical Image Placement 
        JLabel logoLabel = new JLabel();
        logoLabel.setBounds(370, 110, 150, 120);
        try {
            ImageIcon icon = new ImageIcon("images/logo.png");
            // Automatically scales down your image to fit cleanly
            Image scaledImage = icon.getImage().getScaledInstance(130, 110, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            logoLabel.setText("[ Logo Image Missing ]");
            logoLabel.setForeground(Color.WHITE);
        }
        add(logoLabel);

        // Username Field
        JLabel lblUser = new JLabel("Username:");
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(280, 260, 100, 30);
        add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(380, 260, 200, 30);
        add(txtUser);

        // Password Field
        JLabel lblPass = new JLabel("Password:");
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(280, 310, 100, 30);
        add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(380, 310, 200, 30);
        add(txtPass);

        // Action Login Button
        JButton btnLogin = new JButton("Login");
        btnLogin.setBackground(new Color(46, 204, 113)); // Emerald Green
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBounds(380, 370, 200, 40);
        add(btnLogin);

        btnLogin.addActionListener(e -> {
            String user = txtUser.getText().trim();
            String pass = new String(txtPass.getPassword()).trim();
            
            ArrayList<String> users = DBContext.getUsers();
            boolean authenticated = false;

            for (String u : users) {
                String[] parts = u.split(",");
                if (parts[0].equals(user) && parts[1].equals(pass)) {
                    authenticated = true;
                    JOptionPane.showMessageDialog(this, "Welcome back, " + user + " ("+parts[2]+")!");
                    txtUser.setText("");
                    txtPass.setText("");
                    window.showScreen("DASHBOARD");
                    break;
                }
            }
            if (!authenticated) {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Try again!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
