package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminPanel extends JPanel {
    private JTextArea displayArea;
    private JTextField txtEmpUser, txtEmpPass, txtEmpRole;
    private JTextField txtItemId, txtItemName, txtItemPrice;

    public AdminPanel(AppWindow window) {
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(155, 89, 182));
        JLabel title = new JLabel(" Admin Management Console", JLabel.LEFT);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.WEST);
        
        JButton btnBack = new JButton("Back to Hub");
        header.add(btnBack, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // Central Live Display Box
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Configuration Control Actions (Split left and right controls)
        JPanel controlContainer = new JPanel(new GridLayout(1, 2, 10, 10));
        controlContainer.setPreferredSize(new Dimension(900, 220));

        // ---- PART A: EMPLOYEE OPERATIONS MANAGER ----
        JPanel panelEmp = new JPanel(new GridLayout(5, 2, 5, 5));
        panelEmp.setBorder(BorderFactory.createTitledBorder("Manage Staff Members"));
        
        panelEmp.add(new JLabel(" Username:"));  txtEmpUser = new JTextField();  panelEmp.add(txtEmpUser);
        panelEmp.add(new JLabel(" Password:"));  txtEmpPass = new JTextField();  panelEmp.add(txtEmpPass);
        panelEmp.add(new JLabel(" Position/Role:")); txtEmpRole = new JTextField(); panelEmp.add(txtEmpRole);

        JButton btnAddEmp = new JButton("Add/Update Staff");
        JButton btnDelEmp = new JButton("Terminate Staff");
        panelEmp.add(btnAddEmp); panelEmp.add(btnDelEmp);
        
        JButton btnViewEmp = new JButton("List All Employees");
        panelEmp.add(btnViewEmp);

        // ---- PART B: INVENTORY MENU ITEM MANAGER ----
        JPanel panelMenu = new JPanel(new GridLayout(5, 2, 5, 5));
        panelMenu.setBorder(BorderFactory.createTitledBorder("Manage Kitchen Menu Items"));

        panelMenu.add(new JLabel(" Product ID:")); txtItemId = new JTextField(); panelMenu.add(txtItemId);
        panelMenu.add(new JLabel(" Item Name:"));  txtItemName = new JTextField(); panelMenu.add(txtItemName);
        panelMenu.add(new JLabel(" Base Price:")); txtItemPrice = new JTextField(); panelMenu.add(txtItemPrice);

        JButton btnAddItem = new JButton("Add/Update Product");
        JButton btnDelItem = new JButton("Delete Product");
        panelMenu.add(btnAddItem); panelMenu.add(btnDelItem);

        JButton btnViewMenu = new JButton("Refresh Live Menu");
        panelMenu.add(btnViewMenu);

        controlContainer.add(panelEmp);
        controlContainer.add(panelMenu);
        add(controlContainer, BorderLayout.SOUTH);

        // ---- ACTION INTERACTION IMPLEMENTATIONS ----
        btnBack.addActionListener(e -> window.showScreen("DASHBOARD"));

        // Staff Logic
        btnViewEmp.addActionListener(e -> {
            displayArea.setText("--- CURRENT REGISTERED SYSTEM USERS ---\nUsername\t\tPassword\t\tRole\n-------------------------------------------------\n");
            for (String u : DBContext.getUsers()) {
                String[] p = u.split(",");
                displayArea.append(p[0] + "\t\t" + p[1] + "\t\t" + p[2] + "\n");
            }
        });

        btnAddEmp.addActionListener(e -> {
            if(txtEmpUser.getText().isEmpty()) return;
            ArrayList<String> users = DBContext.getUsers();
            // Remove matching entry if updating an existing record
            users.removeIf(u -> u.split(",")[0].equalsIgnoreCase(txtEmpUser.getText().trim()));
            users.add(txtEmpUser.getText().trim() + "," + txtEmpPass.getText().trim() + "," + txtEmpRole.getText().trim());
            DBContext.saveUsers(users);
            JOptionPane.showMessageDialog(this, "Staff Data Saved/Updated Successfully.");
            btnViewEmp.doClick();
        });

        btnDelEmp.addActionListener(e -> {
            ArrayList<String> users = DBContext.getUsers();
            users.removeIf(u -> u.split(",")[0].equalsIgnoreCase(txtEmpUser.getText().trim()));
            DBContext.saveUsers(users);
            JOptionPane.showMessageDialog(this, "Staff Data Removed.");
            btnViewEmp.doClick();
        });

        // Item Inventory Logic
        btnViewMenu.addActionListener(e -> {
            displayArea.setText("--- CURRENT RESTAURANT MENU ITEMS ---\nItem ID\t\tProduct Description\t\tRetail Price\n-------------------------------------------------\n");
            for (String m : DBContext.getMenu()) {
                String[] p = m.split(",");
                displayArea.append(p[0] + "\t\t" + p[1] + "\t\tRs. " + p[2] + "\n");
            }
        });

        btnAddItem.addActionListener(e -> {
            if(txtItemId.getText().isEmpty()) return;
            ArrayList<String> menu = DBContext.getMenu();
            menu.removeIf(m -> m.split(",")[0].equals(txtItemId.getText().trim()));
            menu.add(txtItemId.getText().trim() + "," + txtItemName.getText().trim() + "," + txtItemPrice.getText().trim());
            DBContext.saveMenu(menu);
            JOptionPane.showMessageDialog(this, "Menu Item Logged/Updated.");
            btnViewMenu.doClick();
        });

        btnDelItem.addActionListener(e -> {
            ArrayList<String> menu = DBContext.getMenu();
            menu.removeIf(m -> m.split(",")[0].equals(txtItemId.getText().trim()));
            DBContext.saveMenu(menu);
            JOptionPane.showMessageDialog(this, "Item Purged from Menu Registry.");
            btnViewMenu.doClick();
        });
    }
}
