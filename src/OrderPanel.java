package src;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderPanel extends JPanel {
    private JTextArea liveMenuDisplay, txtReceipt;
    private JTextField txtOrderId, txtSelectId, txtQty;
    private JComboBox<String> cmbTableNum; // Replaced text input with a modern dropdown for table tracking

    // Lightweight in-memory tracking structure to hold active table states
    private static HashMap<String, String> tableStatuses = new HashMap<>();

    static {
        // Pre-initializing 5 dining tables as available when the program boots
        tableStatuses.put("Table 1", "AVAILABLE");
        tableStatuses.put("Table 2", "AVAILABLE");
        tableStatuses.put("Table 3", "AVAILABLE");
        tableStatuses.put("Table 4", "AVAILABLE");
        tableStatuses.put("Table 5", "AVAILABLE");
    }

    public OrderPanel(AppWindow window) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 246, 248));

        // --- HEADER BAR PANEL ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(15, 32, 53)); // Premium Corporate Deep Navy
        header.setPreferredSize(new Dimension(900, 55));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(212, 175, 55))); // Gold Trim Line
        
        JLabel title = new JLabel("   POINT-OF-SALE TRANSACTION ENGINE", JLabel.LEFT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.WEST);

        JButton btnBack = new JButton("BACK TO HUB");
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnBack.setBackground(new Color(212, 175, 55));
        btnBack.setForeground(new Color(15, 32, 53));
        btnBack.setFocusPainted(false);
        header.add(btnBack, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // --- CENTRAL WORKSPACE LAYOUT (Split Screen) ---
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        centerPanel.setBackground(new Color(245, 246, 248));
        
        liveMenuDisplay = new JTextArea();
        liveMenuDisplay.setEditable(false);
        liveMenuDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane menuScroll = new JScrollPane(liveMenuDisplay);
        menuScroll.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.LIGHT_GRAY), "Available Stock Items"));
        
        txtReceipt = new JTextArea();
        txtReceipt.setEditable(false);
        txtReceipt.setFont(new Font("Monospaced", Font.BOLD, 12));
        JScrollPane receiptScroll = new JScrollPane(txtReceipt);
        receiptScroll.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.LIGHT_GRAY), "Live Invoice Ledger Preview"));

        centerPanel.add(menuScroll);
        centerPanel.add(receiptScroll);
        add(centerPanel, BorderLayout.CENTER);

        // --- FOOTER FORM & OPERATION PANEL ---
        JPanel footerMain = new JPanel(new BorderLayout(0, 10));
        footerMain.setPreferredSize(new Dimension(900, 210));
        footerMain.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));
        footerMain.setBackground(new Color(245, 246, 248));

        JPanel inputFields = new JPanel(new GridLayout(4, 2, 10, 8));
        inputFields.setBackground(Color.WHITE);
        inputFields.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        inputFields.add(new JLabel("  Order Tracking ID (Numeric):")); 
        txtOrderId = new JTextField(); 
        inputFields.add(txtOrderId);
        
        inputFields.add(new JLabel("  Select Table Allocation:")); 
        String[] tablesList = {"Table 1", "Table 2", "Table 3", "Table 4", "Table 5"};
        cmbTableNum = new JComboBox<>(tablesList);
        inputFields.add(cmbTableNum);
        
        inputFields.add(new JLabel("  Selection Item Code ID:")); 
        txtSelectId = new JTextField(); 
        inputFields.add(txtSelectId);
        
        inputFields.add(new JLabel("  Total Quantity Requested:"));          
        txtQty = new JTextField();      
        inputFields.add(txtQty);

        JPanel actionRow = new JPanel(new GridLayout(1, 4, 10, 0));
        actionRow.setPreferredSize(new Dimension(900, 45));
        
        JButton btnPlace = new JButton("Place/Update Order");
        JButton btnCancel = new JButton("Cancel/Void Order");
        JButton btnCheck = new JButton("Check Bill Ledger");
        JButton btnPrint = new JButton("Generate Bill Text");

        actionRow.add(btnPlace); actionRow.add(btnCancel);
        actionRow.add(btnCheck); actionRow.add(btnPrint);
        
        footerMain.add(inputFields, BorderLayout.CENTER);
        footerMain.add(actionRow, BorderLayout.SOUTH);
        add(footerMain, BorderLayout.SOUTH);

        // ==========================================
        // SYSTEM INTERACTION HANDLING ENGINE LOGIC
        // ==========================================
        btnBack.addActionListener(e -> window.showScreen("DASHBOARD"));

        // PLACE ORDER BUTTON LOGIC WITH TABLE VALIDATION
        btnPlace.addActionListener(e -> {
            try {
                String oId = txtOrderId.getText().trim();
                String selectedTable = cmbTableNum.getSelectedItem().toString();
                String iId = txtSelectId.getText().trim();
                int quantity = Integer.parseInt(txtQty.getText().trim());

                if(oId.isEmpty() || iId.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All tracking metrics must be specified!");
                    return;
                }

                // --- CRUCIAL TABLE AVAILABILITY GUARD CHECK ---
                if (tableStatuses.get(selectedTable).equals("OCCUPIED")) {
                    JOptionPane.showMessageDialog(this, 
                        selectedTable + " is busy serving another party!\nPlease select an empty table or wait.", 
                        "Floor Capacity Alert", 
                        JOptionPane.WARNING_MESSAGE);
                    return; // Terminates order compilation safely
                }

                ArrayList<String> menu = DBContext.getMenu();
                String foundItem = "";
                int price = 0;

                for(String m : menu) {
                    String[] t = m.split(",");
                    if(t[0].equals(iId)) {
                        foundItem = t[1];
                        price = Integer.parseInt(t[2]);
                        break;
                    }
                }

                if(foundItem.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Item ID Code not found inside ledger!"); 
                    return;
                }

                int total = price * quantity;
                ArrayList<String> orders = DBContext.getOrders();
                orders.removeIf(o -> o.split(",")[0].equals(oId));
                
                // Save Order containing tracking ID, Item, Qty, Total and bound Table
                orders.add(oId + "," + foundItem + "," + quantity + "," + total + "," + selectedTable);
                DBContext.saveOrders(orders);

                // Lock table status to busy
                tableStatuses.put(selectedTable, "OCCUPIED");

                String bill = "\n=========================================\n" +
                              "          MUFAYA'S RESTAURANT INVOICE     \n" +
                              "=========================================\n" +
                              "  Order Tracking ID : " + oId + "\n" +
                              "  Assigned Floor     : " + selectedTable + "\n" +
                              "  Item Description  : " + foundItem + "\n" +
                              "  Quantity Bought   : " + quantity + "\n" +
                              "  Total Valuation   : Rs. " + total + "\n" +
                              "=========================================\n" +
                              "      * System Status: Seated & Occupied *";
                txtReceipt.setText(bill);
                JOptionPane.showMessageDialog(this, "Order submitted! " + selectedTable + " status updated to OCCUPIED.");
                refreshMenu(); // Dynamic state refresh
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric parameters for Quantity/ID!", "Input Discrepancy", JOptionPane.ERROR_MESSAGE);
            }
        });

        // CANCEL ORDER LOGIC (Clears the table and sets it back to AVAILABLE)
        btnCancel.addActionListener(e -> {
            String oId = txtOrderId.getText().trim();
            if(oId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter an active Order ID to cancel.");
                return;
            }

            ArrayList<String> orders = DBContext.getOrders();
            String boundTable = "";

            // Locate which table was bound to this specific order
            for (String o : orders) {
                String[] parts = o.split(",");
                if (parts[0].equals(oId) && parts.length > 4) {
                    boundTable = parts[4];
                    break;
                }
            }

            orders.removeIf(o -> o.split(",")[0].equals(oId));
            DBContext.saveOrders(orders);

            // If a table was linked to this order, free it up instantly
            if (!boundTable.isEmpty()) {
                tableStatuses.put(boundTable, "AVAILABLE");
                JOptionPane.showMessageDialog(this, "Order voided. " + boundTable + " is now free/AVAILABLE!");
            } else {
                JOptionPane.showMessageDialog(this, "Order removed from system ledger.");
            }

            txtReceipt.setText("");
            refreshMenu();
        });

        // CHECK EXISTING BILL LOGIC
        btnCheck.addActionListener(e -> {
            String oId = txtOrderId.getText().trim();
            ArrayList<String> orders = DBContext.getOrders();
            boolean found = false;

            for (String o : orders) {
                String[] parts = o.split(",");
                if (parts[0].equals(oId)) {
                    String table = parts.length > 4 ? parts[4] : "Unassigned";
                    String bill = "\n=========================================\n" +
                                  "          MUFAYA'S RESTAURANT INVOICE     \n" +
                                  "=========================================\n" +
                                  "  Order Tracking ID : " + parts[0] + "\n" +
                                  "  Assigned Floor     : " + table + "\n" +
                                  "  Item Description  : " + parts[1] + "\n" +
                                  "  Quantity Bought   : " + parts[2] + "\n" +
                                  "  Total Valuation   : Rs. " + parts[3] + "\n" +
                                  "=========================================";
                    txtReceipt.setText(bill);
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "No matching active transaction ID located.");
            }
        });

        // GENERATE PHYSICAL TXT EXPORT
        btnPrint.addActionListener(e -> {
            if (txtReceipt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No active statement generated to print.");
                return;
            }
            String path = "Invoice_" + txtOrderId.getText().trim() + ".txt";
            try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
                writer.print(txtReceipt.getText());
                JOptionPane.showMessageDialog(this, "Invoice written to local terminal space:\n" + path);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error compiling system print streams.");
            }
        });

        // Initial menu configuration load
        refreshMenu();
    }

    // Displays available stock alongside live table states to give a highly complete operational overview!
    private void refreshMenu() {
        ArrayList<String> menuItems = DBContext.getMenu();
        StringBuilder text = new StringBuilder();
        
        text.append(" Live Reference Code Registry:\n");
        text.append("====================================================\n");
        text.append(String.format("   %-10s %-25s %-15s\n", "Code ID", "Item Description", "Price (Rs.)"));
        text.append("----------------------------------------------------\n");
        for (String item : menuItems) {
            String[] p = item.split(",");
            text.append(String.format("   %-10s %-25s Rs. %-15s\n", p[0], p[1], p[2]));
        }

        text.append("\n\n Current Dining Floor Map Seating Availability:\n");
        text.append("====================================================\n");
        for (int i = 1; i <= 5; i++) {
            String name = "Table " + i;
            text.append("   " + name + " Status \t-->\t [" + tableStatuses.get(name) + "]\n");
        }

        liveMenuDisplay.setText(text.toString());
    }
}