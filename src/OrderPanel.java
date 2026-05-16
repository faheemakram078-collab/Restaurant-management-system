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

        // Header Panels
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(52, 152, 219));
        JLabel title = new JLabel(" Point-Of-Sale Terminal Engine", JLabel.LEFT);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.WEST);

        JButton btnBack = new JButton("Back to Hub");
        header.add(btnBack, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // Core Middle Frame Panel
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        liveMenuDisplay = new JTextArea();
        liveMenuDisplay.setBorder(BorderFactory.createTitledBorder("Available Items"));
        liveMenuDisplay.setEditable(false);
        
        txtReceipt = new JTextArea();
        txtReceipt.setBorder(BorderFactory.createTitledBorder("Live Real-time Bill Receipt Preview"));
        txtReceipt.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtReceipt.setEditable(false);

        centerPanel.add(new JScrollPane(liveMenuDisplay));
        centerPanel.add(new JScrollPane(txtReceipt));
        add(centerPanel, BorderLayout.CENTER);

        // Interactive Footer Controllers Panel
        JPanel footerControl = new JPanel(new GridLayout(4, 2, 5, 5));
        footerControl.setPreferredSize(new Dimension(900, 160));
        footerControl.setBorder(BorderFactory.createTitledBorder("POS Checkout Operations"));

        footerControl.add(new JLabel(" Custom Order Tracking ID (Numeric):")); txtOrderId = new JTextField(); footerControl.add(txtOrderId);
        footerControl.add(new JLabel(" Selection Item ID Code:")); txtSelectId = new JTextField(); footerControl.add(txtSelectId);
        footerControl.add(new JLabel(" Total Quantity Requested:")); txtQty = new JTextField(); footerControl.add(txtQty);

        // Operation Buttons Row Layout Action panels
        JPanel actionRow = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton btnPlace = new JButton("Place/Update Order");
        JButton btnCancel = new JButton("Cancel/Delete Order");
        JButton btnCheck = new JButton("Check Extracted Bill");
        JButton btnPrint = new JButton("Generate Bill PDF/Print");

        actionRow.add(btnPlace); actionRow.add(btnCancel);
        actionRow.add(btnCheck); actionRow.add(btnPrint);
        
        add(footerControl, BorderLayout.SOUTH);
        footerControl.add(actionRow);

        // Automatic screen load execution trigger
        btnBack.addActionListener(e -> window.showScreen("DASHBOARD"));

        // Core POS Event Function Logic Handles
        btnPlace.addActionListener(e -> {
            String oId = txtOrderId.getText().trim();
            String iId = txtSelectId.getText().trim();
            String quantity = txtQty.getText().trim();

            if(oId.isEmpty() || iId.isEmpty() || quantity.isEmpty()) return;

            ArrayList<String> itemsList = DBContext.getMenu();
            String foundItem = "";
            int targetPrice = 0;

            for(String m : itemsList) {
                String[] tokens = m.split(",");
                if(tokens[0].equals(iId)) {
                    foundItem = tokens[1];
                    targetPrice = Integer.parseInt(tokens[2]);
                    break;
                }
            }

            if(foundItem.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Item ID Code not cataloged!");
                return;
            }

            int computedBill = targetPrice * Integer.parseInt(quantity);
            ArrayList<String> activeOrders = DBContext.getOrders();
            // Removes duplicate order string entry matching current unique sequence ID to update it
            activeOrders.removeIf(o -> o.split(",")[0].equals(oId));
            
            // Re-inserts fresh billing values cleanly
            activeOrders.add(oId + "," + foundItem + "," + quantity + "," + computedBill);
            DBContext.saveOrders(activeOrders);

            String billTemplate = "\n===============================\n" +
                                  "     SPICY BITES INVOICE       \n" +
                                  "===============================\n" +
                                  " Order Tracking ID: " + oId + "\n" +
                                  " Item Selection:   " + foundItem + "\n" +
                                  " Purchased Vol:    " + quantity + "\n" +
                                  " Total Valuation:  Rs. " + computedBill + "\n" +
                                  "===============================";
            txtReceipt.setText(billTemplate);
            JOptionPane.showMessageDialog(this, "Transaction Saved to Registry Ledger.");
        });

        btnCheck.addActionListener(e -> {
            String oId = txtOrderId.getText().trim();
            ArrayList<String> activeOrders = DBContext.getOrders();
            boolean checked = false;
            for(String o : activeOrders) {
                String[] token = o.split(",");
                if(token[0].equals(oId)){
                    String billTemplate = "\n===============================\n" +
                                          "     SPICY BITES INVOICE       \n" +
                                          "===============================\n" +
                                          " Order Tracking ID: " + token[0] + "\n" +
                                          " Item Selection:   " + token[1] + "\n" +
                                          " Purchased Vol:    " + token[2] + "\n" +
                                          " Total Valuation:  Rs. " + token[3] + "\n" +
                                          "===============================";
                    txtReceipt.setText(billTemplate);
                    checked = true;
                    break;
                }
            }
            if(!checked) JOptionPane.showMessageDialog(this, "No transaction records found matching tracking reference ID.");
        });

        btnCancel.addActionListener(e -> {
            String oId = txtOrderId.getText().trim();
            ArrayList<String> activeOrders = DBContext.getOrders();
            activeOrders.removeIf(o -> o.split(",")[0].equals(oId));
            DBContext.saveOrders(activeOrders);
            txtReceipt.setText("");
            JOptionPane.showMessageDialog(this, "Transaction Voided & Canceled.");
        });

        btnPrint.addActionListener(e -> {
            if(txtReceipt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Compile receipt verification invoice data prior to print extraction!");
                return;
            }
            // Generate flat standalone printing record data structure stream files onto local machine disk space
            String exportPath = "Order_Invoice_" + txtOrderId.getText().trim() + ".txt";
            try (PrintWriter out = new PrintWriter(new FileWriter(exportPath))) {
                out.print(txtReceipt.getText());
                JOptionPane.showMessageDialog(this, "Clean Digital Invoice File Compiled:\n" + exportPath + "\n(Open and press Ctrl+P to save as PDF!)");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Critical runtime print configuration interruption encountered.");
            }
        });

        // Loop array loader for refreshing items side display pane context
        ArrayList<String> menuItems = DBContext.getMenu();
        liveMenuDisplay.setText("Live Inventory Reference Codes:\n====================================\n");
        for (String item : menuItems) {
            String[] parts = item.split(",");
            liveMenuDisplay.append(" Code: " + parts[0] + " | " + parts[1] + " --> Rs. " + parts[2] + "\n");
        }
    }
}
