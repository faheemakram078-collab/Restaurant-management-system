package src;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class Dashboard extends JPanel {
    // Slideshow variables
    private Image[] slideshowImages;
    private int currentImageIndex = 0;
    private Timer slideshowTimer;

    // Smooth Pastel Blue Aesthetic Theme Config
    private final Color pastelBlue = new Color(174, 198, 207); // Soft Premium Pastel Blue
    private final Color darkNavyText = new Color(20, 35, 60); // High contrast text for strips

    public Dashboard(AppWindow window) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- 1. SAFE LOAD SLIDESHOW IMAGES WITH RUNTIME CHECK ---
        slideshowImages = new Image[3];
        String[] imagePaths = { "images/image1.jpg", "images/image2.jpg", "images/image4.png" };

        for (int i = 0; i < imagePaths.length; i++) {
            File imgFile = new File(imagePaths[i]);
            if (imgFile.exists()) {
                slideshowImages[i] = new ImageIcon(imagePaths[i]).getImage();
            } else {
                System.out.println("CRITICAL ERROR: Cannot find " + imagePaths[i]);
                System.out.println("Java looking here: " + imgFile.getAbsolutePath());
            }
        }

        // ==========================================================
        // 2. TOP NAVIGATION STRIP (PASTEL BLUE)
        // ==========================================================
        JPanel topStrip = new JPanel(new BorderLayout());
        topStrip.setBackground(pastelBlue);
        topStrip.setPreferredSize(new Dimension(900, 75));
        topStrip.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, darkNavyText));

        // LEFT NAVIGATION BRAND SECTION (Icon-Style Text)
        JPanel leftBrand = new JPanel(new FlowLayout(FlowLayout.LEFT, 25, 15));
        leftBrand.setOpaque(false);

        // Stylized brand text acts as the icon logo itself in pure white
        JLabel logoStyleText = new JLabel("M U F A Y A");
        logoStyleText.setFont(new Font("Segoe UI", Font.BOLD, 26));
        logoStyleText.setForeground(Color.WHITE);

        leftBrand.add(logoStyleText);
        topStrip.add(leftBrand, BorderLayout.WEST);

        // RIGHT NAVIGATION SECTION (Three-Bars Menu Button)
        JPanel rightMenu = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 12));
        rightMenu.setOpaque(false);

        // Three-bars hamburger menu trigger button
        JButton menuButton = new JButton("☰");
        menuButton.setFont(new Font("Segoe UI", Font.BOLD, 28));
        menuButton.setForeground(darkNavyText);
        menuButton.setBackground(pastelBlue);
        menuButton.setContentAreaFilled(false);
        menuButton.setFocusPainted(false);
        menuButton.setBorderPainted(false);
        menuButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Construct the modern overlay dropdown popup items
        JPopupMenu navigationPopup = new JPopupMenu();
        navigationPopup.setBackground(pastelBlue);
        navigationPopup.setBorder(BorderFactory.createLineBorder(darkNavyText, 1));

        JMenuItem itemPos = new JMenuItem("POS System Terminal");
        JMenuItem itemAdmin = new JMenuItem("Admin Management Panel");
        JMenuItem itemLogout = new JMenuItem("Secure Logout");

        // Style the modular menu links uniformly
        JMenuItem[] targetItems = { itemPos, itemAdmin, itemLogout };
        for (JMenuItem item : targetItems) {
            item.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            item.setBackground(pastelBlue);
            item.setForeground(darkNavyText);
            item.setOpaque(true); // FIXES DROPDOWN COLOR GLITCH
            item.setBorder(new EmptyBorder(10, 20, 10, 20));
            navigationPopup.add(item);
        }

        // Action routing logic for the popup items
        itemPos.addActionListener(e -> window.showScreen("ORDER"));
        itemAdmin.addActionListener(e -> window.showScreen("ADMIN"));
        itemLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(window, "Confirm active user terminal logout?",
                    "Logout Verification", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION)
                window.showScreen("LOGIN");
        });

        // Trigger the menu layout anchor dynamically when the ☰ icon button is clicked
        menuButton.addActionListener(e -> {
            navigationPopup.show(menuButton, -120, menuButton.getHeight() - 5);
        });

        rightMenu.add(menuButton);
        topStrip.add(rightMenu, BorderLayout.EAST);

        add(topStrip, BorderLayout.NORTH);

        // ==========================================================
        // 3. CENTRAL HERO AREA: NATIVE IMAGE SLIDESHOW BACKGROUND
        // ==========================================================
        JPanel mainHero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (slideshowImages[currentImageIndex] != null) {
                    g.drawImage(slideshowImages[currentImageIndex], 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(new Color(40, 40, 40));
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.WHITE);
                    g.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    g.drawString("⚠️ Slide Missing: " + imagePaths[currentImageIndex], 50, 50);
                }

                // Dark glass overlay tint so white text is beautifully visible
                g.setColor(new Color(0, 0, 0, 145));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainHero.setLayout(new BorderLayout());

        // LEFT-ALIGNED VERTICAL TEXT ENGINE
        JPanel descriptionPanel = new JPanel();
        descriptionPanel.setOpaque(false);
        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));
        descriptionPanel.setBorder(new EmptyBorder(50, 50, 50, 20)); // Strong structural left padding

        // Vertical split heading layout
        JLabel descHeading = new JLabel("<html><i><b>AUTHENTIC<br>PAKISTANI<br>CUISINE</b></i></html>");
        descHeading.setFont(new Font("Serif", Font.PLAIN, 46));
        descHeading.setForeground(Color.WHITE);

        // Vertically formatted description paragraph block
        JLabel descSubText = new JLabel("<html><body style='width: 450px;'>"
                + "<i>Welcome to a premium hospitality experience where tradition meets culinary perfection.<br><br>"
                + "At Mufaya's Restaurant, we take pride in preserving our heritage through hand-picked spices, "
                + "time-honored cooking techniques, and an absolute commitment to elite flavors.<br><br>"
                + "From our legendary sizzling karahis and slow-cooked handis to our signature aromatic biryanis, "
                + "every dish is prepared fresh to offer an unforgettable journey through the deep flavors of Pakistan.</i>"
                + "</body></html>");
        descSubText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descSubText.setForeground(Color.WHITE);

        descriptionPanel.add(descHeading);
        descriptionPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Consistent visual spacing gap
        descriptionPanel.add(descSubText);

        mainHero.add(descriptionPanel, BorderLayout.WEST);
        add(mainHero, BorderLayout.CENTER);

        // --- AUTOMATIC INFINITE SLIDESHOW ENGINE TIMING ENGINE ---
        slideshowTimer = new Timer(3500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentImageIndex = (currentImageIndex + 1) % slideshowImages.length;
                mainHero.revalidate(); // FIXES COMPONENT INSTABILITY DURING REPAINT
                mainHero.repaint();
            }
        });
        slideshowTimer.start();

        // ==========================================================
        // 4. BOTTOM STRIP (PASTEL BLUE)
        // ==========================================================
        JPanel bottomStrip = new JPanel(new BorderLayout());
        bottomStrip.setBackground(pastelBlue);
        bottomStrip.setPreferredSize(new Dimension(900, 50));
        bottomStrip.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, darkNavyText));

        JLabel contactInfo = new JLabel("    📞 Helpline: +92 (51) 111-MUFAYA   |   ✉ Email: contact@mufayas.com");
        contactInfo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        contactInfo.setForeground(darkNavyText);
        bottomStrip.add(contactInfo, BorderLayout.WEST);

        JLabel tagLine = new JLabel("Taste of Premium Tradition   ");
        tagLine.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        tagLine.setForeground(darkNavyText);
        bottomStrip.add(tagLine, BorderLayout.EAST);

        add(bottomStrip, BorderLayout.SOUTH);
    }
}