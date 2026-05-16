package src;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JPanel {
    public Dashboard(AppWindow window) {
        setLayout(new BorderLayout());
        setBackground(new Color(236, 240, 241)); // Light Minimalist Grey

        // Dashboard Header
        JPanel header = new JPanel();
        header.setBackground(new Color(230, 126, 34)); // Warm Vibrant Orange Accent
        JLabel title = new JLabel("Control Management Dashboard System");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Hub Selection Main Options Panel
        JPanel gridPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        gridPanel.setBackground(new Color(236, 240, 241));

        JButton btnOrder = new JButton("<html><center><b>POS SYSTEM</b><br>Create & Manage Orders</center></html>");
        btnOrder.setBackground(new Color(52, 152, 219)); // Classic Blue
        btnOrder.setForeground(Color.WHITE);
        btnOrder.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton btnAdmin = new JButton("<html><center><b>ADMIN PANEL</b><br>Manage Menu & Employees</center></html>");
        btnAdmin.setBackground(new Color(155, 89, 182)); // Royal Purple
        btnAdmin.setForeground(Color.WHITE);
        btnAdmin.setFont(new Font("Arial", Font.PLAIN, 18));

        gridPanel.add(btnOrder);
        gridPanel.add(btnAdmin);
        add(gridPanel, BorderLayout.CENTER);

        // Footer Section containing logout
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnLogout = new JButton("Secure Logout");
        btnLogout.setBackground(new Color(192, 41, 43)); // Deep Crimson Red
        btnLogout.setForeground(Color.WHITE);
        footer.add(btnLogout);
        add(footer, BorderLayout.SOUTH);

        // Event Listeners for Page Swaps
        btnOrder.addActionListener(e -> window.showScreen("ORDER"));
        btnAdmin.addActionListener(e -> window.showScreen("ADMIN"));
        btnLogout.addActionListener(e -> window.showScreen("LOGIN"));
    }
}
