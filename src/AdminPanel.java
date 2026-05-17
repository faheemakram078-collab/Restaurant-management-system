package src;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminPanel extends JPanel {
    private JTextArea logArea;
    private JTextField txtEmpUser, txtEmpPass, txtEmpRole;
    private JTextField txtItemId, txtItemName, txtItemPrice;
    private JLabel lblTotalStaff, lblTotalItems;

    public AdminPanel(AppWindow window) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245)); // Light Luxury Grey

        // --- 1. PREMIUM TOP NAVIGATION BAR ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(15, 34, 64)); // Ramada Navy
        header.setPreferredSize(new Dimension(900, 60));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, new Color(212, 175, 55)));

        JLabel title = new JLabel("   EXECUTIVE ADMINISTRATIVE CONSOLE", JLabel.LEFT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.WEST);

        JButton btnBack = new JButton("BACK TO HUB");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnBack.setBackground(new Color(212, 175, 55));
        btnBack.setForeground(new Color(15, 34, 64));
        btnBack.setFocusPainted(false);
        header.add(btnBack, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // --- 2. MIDDLE SECTION: STATS & LOGS ---
        JPanel centerContainer = new JPanel(new BorderLayout(10, 10));
        centerContainer.setOpaque(false);
        centerContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top Stats Cards
        JPanel statsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        statsPanel.setOpaque(false);

        lblTotalStaff = createStatCard(statsPanel, "ACTIVE OPERATORS", "0", new Color(41, 128, 185));
        lblTotalItems = createStatCard(statsPanel, "MENU INVENTORY", "0", new Color(39, 174, 96));
        
        centerContainer.add(statsPanel, BorderLayout.NORTH);

        // Modern System Log Area
        logArea = new JTextArea();
        logArea.setBackground(Color.WHITE);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        logArea.setEditable(false);
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.LIGHT_GRAY), "SYSTEM ACTION LOGS"));
        centerContainer.add(logScroll, BorderLayout.CENTER);

        add(centerContainer, BorderLayout.CENTER);

        // --- 3. BOTTOM SECTION: CONTROL CARDS ---
        JPanel controlContainer = new JPanel(new GridLayout(1, 2, 20, 0));
        controlContainer.setPreferredSize(new Dimension(900, 280));
        controlContainer.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        controlContainer.setOpaque(false);

        // --- STAFF MANAGEMENT CARD ---
        JPanel panelEmp = createStyledPanel("Operator Management", new Color(15, 34, 64));
        panelEmp.add(new JLabel("Username:")); txtEmpUser = new JTextField(); panelEmp.add(txtEmpUser);
        panelEmp.add(new JLabel("Password:")); txtEmpPass = new JTextField(); panelEmp.add(txtEmpPass);
        panelEmp.add(new JLabel("Role:"));     txtEmpRole = new JTextField(); panelEmp.add(txtEmpRole);
        
        JButton btnAddEmp = createStyledButton("ADD / UPDATE STAFF", new Color(46, 204, 113));
        JButton btnDelEmp = createStyledButton("TERMINATE STAFF", new Color(231, 76, 60));
        JButton btnViewEmp = createStyledButton("VIEW ALL STAFF", new Color(15, 34, 64));
        panelEmp.add(btnAddEmp); panelEmp.add(btnDelEmp); panelEmp.add(btnViewEmp);

        // --- MENU MANAGEMENT CARD ---
        JPanel panelMenu = createStyledPanel("Inventory Management", new Color(212, 175, 55));
        panelMenu.add(new JLabel("Item ID:"));    txtItemId = new JTextField(); panelMenu.add(txtItemId);
        panelMenu.add(new JLabel("Item Name:"));  txtItemName = new JTextField(); panelMenu.add(txtItemName);
        panelMenu.add(new JLabel("Price:"));      txtItemPrice = new JTextField(); panelMenu.add(txtItemPrice);

        JButton btnAddItem = createStyledButton("ADD / UPDATE ITEM", new Color(46, 204, 113));
        JButton btnDelItem = createStyledButton("REMOVE ITEM", new Color(231, 76, 60));
        JButton btnViewMenu = createStyledButton("VIEW FULL MENU", new Color(15, 34, 64));
        panelMenu.add(btnAddItem); panelMenu.add(btnDelItem); panelMenu.add(btnViewMenu);

        controlContainer.add(panelEmp);
        controlContainer.add(panelMenu);
        add(controlContainer, BorderLayout.SOUTH);

        // --- 4. ACTION LISTENERS (LOGIC) ---

        btnBack.addActionListener(e -> window.showScreen("DASHBOARD"));

        // VIEW ALL STAFF LOGIC
        btnViewEmp.addActionListener(e -> {
            ArrayList<String> users = DBContext.getUsers();
            logArea.setText(">> FETCHING OPERATOR REGISTRY...\n");
            for (String u : users) logArea.append(" [USER] " + u.replace(",", " | ") + "\n");
            refreshStats();
        });

        // ADD / UPDATE STAFF LOGIC
        btnAddEmp.addActionListener(e -> {
            String user = txtEmpUser.getText().trim();
            String pass = txtEmpPass.getText().trim();
            String role = txtEmpRole.getText().trim();
            if(user.isEmpty() || pass.isEmpty()) return;

            ArrayList<String> users = DBContext.getUsers();
            users.removeIf(u -> u.split(",")[0].equalsIgnoreCase(user));
            users.add(user + "," + pass + "," + role);
            DBContext.saveUsers(users); // PERSIST TO FILE
            
            logArea.append(">> SUCCESS: Operator '" + user + "' updated.\n");
            txtEmpUser.setText(""); txtEmpPass.setText(""); txtEmpRole.setText("");
            refreshStats();
        });

        // TERMINATE STAFF LOGIC
        btnDelEmp.addActionListener(e -> {
            String user = txtEmpUser.getText().trim();
            ArrayList<String> users = DBContext.getUsers();
            if(users.removeIf(u -> u.split(",")[0].equalsIgnoreCase(user))) {
                DBContext.saveUsers(users); // PERSIST TO FILE
                logArea.append(">> REMOVED: Operator '" + user + "' terminated.\n");
            } else {
                logArea.append(">> ERROR: Operator '" + user + "' not found.\n");
            }
            refreshStats();
        });

        // VIEW FULL MENU LOGIC
        btnViewMenu.addActionListener(e -> {
            ArrayList<String> menu = DBContext.getMenu();
            logArea.setText(">> FETCHING MENU INVENTORY...\n");
            for (String m : menu) logArea.append(" [ITEM] " + m.replace(",", " | ") + "\n");
            refreshStats();
        });

        // ADD / UPDATE ITEM LOGIC
        btnAddItem.addActionListener(e -> {
            String id = txtItemId.getText().trim();
            String name = txtItemName.getText().trim();
            String price = txtItemPrice.getText().trim();
            if(id.isEmpty() || name.isEmpty()) return;

            ArrayList<String> menu = DBContext.getMenu();
            menu.removeIf(m -> m.split(",")[0].equals(id));
            menu.add(id + "," + name + "," + price);
            DBContext.saveMenu(menu); // PERSIST TO FILE
            
            logArea.append(">> SUCCESS: Item '" + name + "' updated in inventory.\n");
            txtItemId.setText(""); txtItemName.setText(""); txtItemPrice.setText("");
            refreshStats();
        });

        // REMOVE ITEM LOGIC
        btnDelItem.addActionListener(e -> {
            String id = txtItemId.getText().trim();
            ArrayList<String> menu = DBContext.getMenu();
            if(menu.removeIf(m -> m.split(",")[0].equals(id))) {
                DBContext.saveMenu(menu); // PERSIST TO FILE
                logArea.append(">> REMOVED: Item ID " + id + " deleted.\n");
            } else {
                logArea.append(">> ERROR: Item ID " + id + " not found.\n");
            }
            refreshStats();
        });

        refreshStats();
    }

    // --- HELPER METHODS ---

    private void refreshStats() {
        lblTotalStaff.setText(String.valueOf(DBContext.getUsers().size()));
        lblTotalItems.setText(String.valueOf(DBContext.getMenu().size()));
    }

    private JLabel createStatCard(JPanel parent, String title, String value, Color accent) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(accent, 2));
        JLabel lblTitle = new JLabel(title, JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTitle.setForeground(Color.GRAY);
        JLabel lblValue = new JLabel(value, JLabel.CENTER);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblValue.setForeground(accent);
        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblValue, BorderLayout.CENTER);
        parent.add(card);
        return lblValue;
    }

    private JPanel createStyledPanel(String title, Color themeColor) {
        JPanel p = new JPanel(new GridLayout(5, 2, 10, 10));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createTitledBorder(new LineBorder(themeColor), title, 0, 0, new Font("Segoe UI", Font.BOLD, 14), themeColor));
        return p;
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 11));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return b;
    }
}