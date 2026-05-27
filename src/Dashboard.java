package src;

import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Dashboard extends JPanel {
    private Image backgroundImage;
    private final String imagePath = "images/image4.png";

    private final Color deepNavy = new Color(5, 10, 48); 
    private final Color lightBlueAccent = new Color(135, 206, 250); 
    private final Color vividGlassTint = new Color(15, 10, 5, 110); 

    private JPanel sidebarDrawer;
    private boolean isDrawerOpen = false;
    private JButton itemAdmin; 

    public Dashboard(AppWindow window) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        } else {
            System.out.println("CRITICAL ERROR: Cannot find " + imagePath);
        }

        JLayeredPane layeredMainEngine = new JLayeredPane();
        layeredMainEngine.setLayout(null);

        // --- TOP NAVIGATION ---
        JPanel topStrip = new JPanel(new BorderLayout());
        topStrip.setBackground(deepNavy);
        topStrip.setPreferredSize(new Dimension(900, 75));
        topStrip.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, lightBlueAccent));

        JPanel leftBrand = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 15));
        leftBrand.setOpaque(false);
        JLabel logoStyleText = new JLabel("MuFaYa'S");
        logoStyleText.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 32));
        logoStyleText.setForeground(Color.WHITE);
        leftBrand.add(logoStyleText);
        topStrip.add(leftBrand, BorderLayout.WEST);

        JPanel rightMenu = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 12));
        rightMenu.setOpaque(false);
        JButton menuButton = new JButton("☰");
        menuButton.setFont(new Font("Segoe UI", Font.BOLD, 28));
        menuButton.setForeground(Color.WHITE);
        menuButton.setContentAreaFilled(false);
        menuButton.setFocusPainted(false);
        menuButton.setBorderPainted(false);
        menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rightMenu.add(menuButton);
        topStrip.add(rightMenu, BorderLayout.EAST);
        add(topStrip, BorderLayout.NORTH);

        // --- CENTRAL HERO AREA ---
        JPanel mainHero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
                g.setColor(vividGlassTint);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainHero.setLayout(new BorderLayout());

        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setOpaque(false);
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.setBorder(new EmptyBorder(60, 50, 50, 20));

        JLabel descHeading = new JLabel("<html><i><b>AUTHENTIC<br>PAKISTANI<br>CUISINE</b></i></html>");
        descHeading.setFont(new Font("Serif", Font.PLAIN, 34));
        descHeading.setForeground(Color.WHITE);

        JLabel descSubText = new JLabel("<html><body style='width: 450px;'><i>Welcome to a premium hospitality experience...</i></body></html>");
        descSubText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descSubText.setForeground(Color.WHITE);

        descriptionPanel.add(descHeading);
        descriptionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        descriptionPanel.add(descSubText);
        mainHero.add(descriptionPanel, BorderLayout.WEST);

        // --- SIDEBAR MENU DRAWER ---
        sidebarDrawer = new JPanel();
        sidebarDrawer.setLayout(new BoxLayout(sidebarDrawer, BoxLayout.Y_AXIS));
        sidebarDrawer.setBackground(deepNavy);
        sidebarDrawer.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, lightBlueAccent));
        sidebarDrawer.setVisible(false);

        JLabel drawerMenuBrand = new JLabel("MuFaYa'S");
        drawerMenuBrand.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));
        drawerMenuBrand.setForeground(Color.WHITE);
        drawerMenuBrand.setAlignmentX(Component.CENTER_ALIGNMENT);
        drawerMenuBrand.setBorder(new EmptyBorder(30, 10, 20, 10));
        sidebarDrawer.add(drawerMenuBrand);

        JButton itemPos = createSidebarButton(" 💻   POS Terminal");
        itemAdmin = createSidebarButton(" ⚙️   Admin Panel"); 
        JButton itemLogout = createSidebarButton(" 🚪   Secure Logout");

        sidebarDrawer.add(itemPos);
        sidebarDrawer.add(itemAdmin);
        sidebarDrawer.add(itemLogout);

        itemPos.addActionListener(e -> window.showScreen("JAVAPOS"));
        itemAdmin.addActionListener(e -> window.showScreen("ADMIN"));
        
        itemLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(window, "Confirm logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                window.setAuthenticatedRole("");
                window.showScreen("LOGIN");
            }
        });

        layeredMainEngine.add(mainHero, JLayeredPane.DEFAULT_LAYER);
        layeredMainEngine.add(sidebarDrawer, JLayeredPane.PALETTE_LAYER);

        layeredMainEngine.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int w = layeredMainEngine.getWidth();
                int h = layeredMainEngine.getHeight();
                mainHero.setBounds(0, 0, w, h);
                sidebarDrawer.setBounds(w - 260, 0, 260, h);
            }
        });

        menuButton.addActionListener(e -> {
            isDrawerOpen = !isDrawerOpen;
            sidebarDrawer.setVisible(isDrawerOpen);
            layeredMainEngine.repaint();
        });

        add(layeredMainEngine, BorderLayout.CENTER);

        // --- BOTTOM STRIP ---
        JPanel bottomStrip = new JPanel(new BorderLayout());
        bottomStrip.setBackground(deepNavy);
        bottomStrip.setPreferredSize(new Dimension(900, 50));
        bottomStrip.add(new JLabel("  📞 Helpline: +92 (51) 111-MUFAYA"), BorderLayout.WEST);
        add(bottomStrip, BorderLayout.SOUTH);
    }

    /**
     * RBAC SECURITY INTERFACE
     */
    public void applySecuritySettings(String role) {
        if (itemAdmin != null) {
            if (role.equalsIgnoreCase("Admin")) {
                itemAdmin.setVisible(true); 
            } else {
                itemAdmin.setVisible(false); 
            }
        }
        if (sidebarDrawer != null) {
            sidebarDrawer.revalidate();
            sidebarDrawer.repaint();
        }
    }

    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(260, 60));
        btn.setBorder(new EmptyBorder(15, 30, 15, 20));
        return btn;
    }
}