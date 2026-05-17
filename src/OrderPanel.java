package src;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class OrderPanel extends JPanel {
    private JTextArea liveMenuDisplay, txtReceipt;
    private JTextField txtOrderId, txtSelectId, txtQty;

    public OrderPanel(AppWindow window) {
        setLayout(new BorderLayout());

        // Header Panel
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        header.setPreferredSize(new Dimension(900, 50));
        JLabel title = new JLabel("  POINT-OF-SALE TERMINAL ENGINE", JLabel.LEFT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.WEST);

        JButton btnBack = new JButton("BACK TO HUB");
        btnBack.setBackground(Color.WHITE);
        header.add(btnBack, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // Center Split (Menu on left, Receipt on right)
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        liveMenuDisplay = new JTextArea();
        liveMenuDisplay.setEditable(false);
        liveMenuDisplay.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane menuScroll = new JScrollPane(liveMenuDisplay);
        menuScroll.setBorder(BorderFactory.createTitledBorder("Available Items"));
        
        txtReceipt = new JTextArea();
        txtReceipt.setEditable(false);
        txtReceipt.setFont(new Font("Monospaced", Font.BOLD, 13));
        JScrollPane receiptScroll = new JScrollPane(txtReceipt);
        receiptScroll.setBorder(BorderFactory.createTitledBorder("Live Receipt Preview"));

        centerPanel.add(menuScroll);
        centerPanel.add(receiptScroll);
        add(centerPanel, BorderLayout.CENTER);

        // Footer Input Section
        JPanel footerMain = new JPanel(new BorderLayout());
        footerMain.setPreferredSize(new Dimension(900, 180));

        JPanel inputFields = new JPanel(new GridLayout(3, 2, 5, 5));
        inputFields.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        inputFields.add(new JLabel("Order ID (Numeric):")); txtOrderId = new JTextField(); inputFields.add(txtOrderId);
        inputFields.add(new JLabel("Selection Item ID:")); txtSelectId = new JTextField(); inputFields.add(txtSelectId);
        inputFields.add(new JLabel("Quantity:"));          txtQty = new JTextField();      inputFields.add(txtQty);

        JPanel actionRow = new JPanel(new FlowLayout());
        JButton btnPlace = new JButton("Place Order");
        JButton btnCancel = new JButton("Cancel Order");
        JButton btnCheck = new JButton("Check Bill");
        JButton btnPrint = new JButton("Save to PDF/Txt");

        actionRow.add(btnPlace); actionRow.add(btnCancel);
        actionRow.add(btnCheck); actionRow.add(btnPrint);
        
        footerMain.add(inputFields, BorderLayout.CENTER);
        footerMain.add(actionRow, BorderLayout.SOUTH);
        add(footerMain, BorderLayout.SOUTH);

        // Logic
        btnBack.addActionListener(e -> window.showScreen("DASHBOARD"));

        btnPlace.addActionListener(e -> {
            try {
                String oId = txtOrderId.getText().trim();
                String iId = txtSelectId.getText().trim();
                int quantity = Integer.parseInt(txtQty.getText().trim());

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
                    JOptionPane.showMessageDialog(this, "Item ID not found!"); return;
                }

                int total = price * quantity;
                ArrayList<String> orders = DBContext.getOrders();
                orders.removeIf(o -> o.split(",")[0].equals(oId));
                orders.add(oId + "," + foundItem + "," + quantity + "," + total);
                DBContext.saveOrders(orders);

                String bill = "\n===============================\n" +
                              "      SPICY BITES INVOICE      \n" +
                              "===============================\n" +
                              " Order ID: " + oId + "\n" +
                              " Item:     " + foundItem + "\n" +
                              " Qty:      " + quantity + "\n" +
                              " Total:    Rs. " + total + "\n" +
                              "===============================";
                txtReceipt.setText(bill);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric data!");
            }
        });

        // Load Menu initially
        refreshMenu();
    }

    private void refreshMenu() {
        ArrayList<String> menuItems = DBContext.getMenu();
        liveMenuDisplay.setText("Code | Name | Price\n--------------------------\n");
        for (String item : menuItems) {
            String[] p = item.split(",");
            liveMenuDisplay.append(p[0] + " | " + p[1] + " | Rs. " + p[2] + "\n");
        }
    }
}