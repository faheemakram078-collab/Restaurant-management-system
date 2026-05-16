package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class Dashboard extends JPanel {
    // Single static background image reference
    private Image backgroundImage;
    private final String imagePath = "images/image4.png";

    // Custom Luxury Color Palette Configuration
    private final Color deepNavy = new Color(5, 10, 48); // Requested color hex: #050A30
    private final Color lightBlueAccent = new Color(135, 206, 250); // Soft sky outline trim
    private final Color vividGlassTint = new Color(15, 10, 5, 110); // Ultra appealing transparent image mask

    // Embedded Sliding Drawer Panel State variables
    private JPanel sidebarDrawer;
    private boolean isDrawerOpen = false;

    public Dashboard(AppWindow window) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- 1. SAFE LOAD SINGLE BACKGROUND PHOTO ---
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        } else {
            System.out.println("CRITICAL ERROR: Cannot find " + imagePath);
        }

        // Layered Container engine to draw panels over background photos neatly
        JLayeredPane layeredMainEngine = new JLayeredPane();
        layeredMainEngine.setLayout(null);

        // ==========================================================
        // 2. TOP NAVIGATION STRIP (DEEP LUXURY NAVY #050A30)
        // ==========================================================
        JPanel topStrip = new JPanel(new BorderLayout());
        topStrip.setBackground(deepNavy);
        topStrip.setPreferredSize(new Dimension(900, 75));
        topStrip.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, lightBlueAccent));

        // LEFT NAVIGATION BRAND SECTION (Fancy MuFaYa'S Logo)
        JPanel leftBrand = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 15));
        leftBrand.setOpaque(false);

        JLabel logoStyleText = new JLabel("MuFaYa'S");
        logoStyleText.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 32));
        logoStyleText.setForeground(Color.WHITE);

        leftBrand.add(logoStyleText);
        topStrip.add(leftBrand, BorderLayout.WEST);

        // RIGHT NAVIGATION SECTION (Three-Bars Menu Button)
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

        // ==========================================================
        // 3. CENTRAL HERO AREA: STATIC ENGAGING IMAGE LAYOUT
        // ==========================================================
        JPanel mainHero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    // Modern rendering configuration for maximum crisp clarity
                    Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(new Color(40, 40, 40));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    g.drawString("⚠️ Background Image Missing: " + imagePath, 50, 50);
                }
                // High-appeal dark contrast overlay to let colors pop out vibrantly
                g.setColor(vividGlassTint);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainHero.setLayout(new BorderLayout());

        // LEFT-ALIGNED VERTICAL TEXT COMPONENT
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setOpaque(false);
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.setBorder(new EmptyBorder(60, 50, 50, 20));

        JLabel descHeading = new JLabel("<html><i><b>AUTHENTIC<br>PAKISTANI<br>CUISINE</b></i></html>");
        descHeading.setFont(new Font("Serif", Font.PLAIN, 34));
        descHeading.setForeground(Color.WHITE);

        JLabel descSubText = new JLabel("<html><body style='width: 450px;'>"
                + "<i>Welcome to a premium hospitality experience where tradition meets culinary perfection.<br><br>"
                + "At MuFaYa'S, we take pride in preserving our heritage through hand-picked spices, "
                + "time-honored cooking techniques, and an absolute commitment to elite flavors.<br><br>"
                + "From our legendary sizzling karahis and slow-cooked handis to our signature aromatic biryanis, "
                + "every dish is prepared fresh to offer an unforgettable journey through the deep flavors of Pakistan.</i>"
                + "</body></html>");
        descSubText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descSubText.setForeground(Color.WHITE);

        descriptionPanel.add(descHeading);
        descriptionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        descriptionPanel.add(descSubText);

        mainHero.add(descriptionPanel, BorderLayout.WEST);

        // ==========================================================
        // 4. SIDEBAR MENU DRAWER COMPONENT (DEEP NAVY BACKGROUND)
        // ==========================================================
        sidebarDrawer = new JPanel();
        sidebarDrawer.setLayout(new BoxLayout(sidebarDrawer, BoxLayout.Y_AXIS));
        sidebarDrawer.setBackground(deepNavy);
        sidebarDrawer.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, lightBlueAccent));
        sidebarDrawer.setVisible(false); // Pop-out panel initially closed

        // Brand Label Banner explicitly written ABOVE items inside the menu drawer
        JLabel drawerMenuBrand = new JLabel("MuFaYa'S");
        drawerMenuBrand.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));
        drawerMenuBrand.setForeground(Color.WHITE);
        drawerMenuBrand.setAlignmentX(Component.CENTER_ALIGNMENT);
        drawerMenuBrand.setBorder(new EmptyBorder(30, 10, 20, 10));
        sidebarDrawer.add(drawerMenuBrand);

        // Add visual separation line below header
        JSeparator menuDivider = new JSeparator();
        menuDivider.setMaximumSize(new Dimension(200, 2));
        menuDivider.setForeground(lightBlueAccent);
        sidebarDrawer.add(menuDivider);
        sidebarDrawer.add(Box.createRigidArea(new Dimension(0, 15)));

        // Modular Menu Buttons with embedded functional mini icons
        JButton itemPos = createSidebarButton(" 💻  POS Terminal");
        JButton itemAdmin = createSidebarButton(" ⚙️  Admin Panel");
        JButton itemLogout = createSidebarButton(" 🚪  Secure Logout");

        sidebarDrawer.add(itemPos);
        sidebarDrawer.add(itemAdmin);
        sidebarDrawer.add(itemLogout);

        // Functional navigation routing mapping configurations
        itemPos.addActionListener(e -> window.showScreen("ORDER"));
        itemAdmin.addActionListener(e -> window.showScreen("ADMIN"));
        itemLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(window, "Confirm active user terminal logout?",
                    "Logout Verification", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION)
                window.showScreen("LOGIN");
        });

        // Add standard panel layouts to execution stack layer positions
        layeredMainEngine.add(mainHero, JLayeredPane.DEFAULT_LAYER);
        layeredMainEngine.add(sidebarDrawer, JLayeredPane.PALETTE_LAYER);

        // Automatic Layout Resizing Calculation Engine
        layeredMainEngine.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int w = layeredMainEngine.getWidth();
                int h = layeredMainEngine.getHeight();
                mainHero.setBounds(0, 0, w, h);
                sidebarDrawer.setBounds(w - 260, 0, 260, h); // Smooth width scaling allocations
            }
        });

        // Toggle visibility state dynamically upon user clicking hamburger trigger icon
        menuButton.addActionListener(e -> {
            isDrawerOpen = !isDrawerOpen;
            sidebarDrawer.setVisible(isDrawerOpen);
            layeredMainEngine.repaint();
        });

        add(layeredMainEngine, BorderLayout.CENTER);

        // ==========================================================
        // 5. BOTTOM STRIP (DEEP LUXURY NAVY #050A30)
        // ==========================================================
        JPanel bottomStrip = new JPanel(new BorderLayout());
        bottomStrip.setBackground(deepNavy);
        bottomStrip.setPreferredSize(new Dimension(900, 50));
        bottomStrip.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, lightBlueAccent));

        JLabel contactInfo = new JLabel("    📞 Helpline: +92 (51) 111-MUFAYA   |   ✉ Email: contact@mufayas.com");
        contactInfo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        contactInfo.setForeground(Color.WHITE);
        bottomStrip.add(contactInfo, BorderLayout.WEST);

        JLabel tagLine = new JLabel("Taste of Premium Tradition   ");
        tagLine.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        tagLine.setForeground(Color.WHITE);
        bottomStrip.add(tagLine, BorderLayout.EAST);

        add(bottomStrip, BorderLayout.SOUTH);
    }

    /**
     * Helper constructor method to build beautiful uniform sidebar links with
     * custom styling
     */
    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14)); // Custom Bold Italic styling enabled
        btn.setForeground(Color.WHITE);
        btn.setBackground(deepNavy);
        btn.setOpaque(true);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT); // Aligns icons neatly to the left wall boundary
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(260, 60));
        btn.setBorder(new EmptyBorder(15, 30, 15, 20));
        return btn;
    }
}