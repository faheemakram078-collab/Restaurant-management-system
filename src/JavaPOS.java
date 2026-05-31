package src;
 
import java.text.MessageFormat;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
 
public class JavaPOS extends javax.swing.JPanel {
 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(JavaPOS.class.getName());
    private AppWindow window;
 
    public JavaPOS(AppWindow window) {
        this.window = window;
        initComponents();
        jbtnExit.setText("BACK TO HUB");
        for (java.awt.event.ActionListener al : jbtnExit.getActionListeners()) {
            jbtnExit.removeActionListener(al);
        }
        jbtnExit.addActionListener(e -> window.showScreen("DASHBOARD"));
        generateReceiptID(); // Generate initial receipt ID on load
    }
 
    // New Helper method to generate automatic Receipt IDs
    private void generateReceiptID() {
        Random rand = new Random();
        int randomNumber = 100000 + rand.nextInt(900000); // 6-digit random number
        jtxtBarCode.setText("REC-" + randomNumber);
    }

    public void ItemCost() {
        double sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            sum += Double.parseDouble(jTable1.getValueAt(i, 2).toString());
        }
        jtxtSubTotal.setText(Double.toString(sum));
        double cSubTotal = Double.parseDouble(jtxtSubTotal.getText());
        double cTax = (cSubTotal * 3.9) / 100;
        jtxtTax.setText(String.format("Rs. %.2f", cTax));
        jtxtSubTotal.setText(String.format("Rs. %.2f", cSubTotal));
        jtxtTotal.setText(String.format("Rs. %.2f", cSubTotal + cTax));
    }
 
    public void Change() {
        double sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            sum += Double.parseDouble(jTable1.getValueAt(i, 2).toString());
        }
        double cTax = (sum * 3.9) / 100;
        double totalAmount = sum + cTax;
 
        String cashInput = jtxtDisplay.getText().trim();
        if (cashInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the cash amount given by the customer!", "Payment Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        try {
            double cash = Double.parseDouble(cashInput);
            
            if (cash < totalAmount) {
                JOptionPane.showMessageDialog(this, "Insufficient Cash! Total bill is Rs. " + String.format("%.2f", totalAmount) + ". Additional cash required.", "Payment Error", JOptionPane.ERROR_MESSAGE);
                jtxtChange.setText("Rs. 0.00");
                return;
            }
 
            double changeAmount = cash - totalAmount;
            jtxtChange.setText(String.format("Rs. %.2f", changeAmount));
            JOptionPane.showMessageDialog(this, "Payment Successful! Transaction Completed.", "Success", JOptionPane.INFORMATION_MESSAGE);
 
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid cash amount entered!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }
 
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        jbtnCrunchBurger = new javax.swing.JButton();
        jbtnBeefSeekhKabab = new javax.swing.JButton();
        jbtnBuffaloWings = new javax.swing.JButton();
        jbtnChickenBiryani = new javax.swing.JButton();
        jbtnChickenHandi = new javax.swing.JButton();
        jbtnLassi = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jbtnLoadedFries = new javax.swing.JButton();
        jbtnGrilledChicken = new javax.swing.JButton();
        jbtnFishAndChips = new javax.swing.JButton();
        jbtnFettuccineAlfredo = new javax.swing.JButton();
        jbtnFajitaSupremePizza = new javax.swing.JButton();
        jbtnDaalMakhni = new javax.swing.JButton();
        jbtnTerragonBeefSteak = new javax.swing.JButton();
        jbtnSmashBeefBurger = new javax.swing.JButton();
        jbtnShawarma = new javax.swing.JButton();
        jbtnMuttonKarahi = new javax.swing.JButton();
        jbtnGrilledSandwitch = new javax.swing.JButton();
        jbtnMintMargarita = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jbtn7 = new javax.swing.JButton();
        jbtn8 = new javax.swing.JButton();
        jbtn4 = new javax.swing.JButton();
        jbtn5 = new javax.swing.JButton();
        jbtn6 = new javax.swing.JButton();
        jbtn1 = new javax.swing.JButton();
        jbtn2 = new javax.swing.JButton();
        jbtn3 = new javax.swing.JButton();
        jbtn0 = new javax.swing.JButton();
        jbtnDot = new javax.swing.JButton();
        jbtnC = new javax.swing.JButton();
        jbtn9 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtxtTax = new javax.swing.JTextField();
        jtxtSubTotal = new javax.swing.JTextField();
        jtxtTotal = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtxtDisplay = new javax.swing.JTextField();
        jtxtChange = new javax.swing.JTextField();
        jcboPayment = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jbtnReset = new javax.swing.JButton();
        jbtnPay = new javax.swing.JButton();
        jbtnPrint = new javax.swing.JButton();
        jbtnRemove = new javax.swing.JButton();
        jbtnExit = new javax.swing.JButton();
        jtxtBarCode = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
 
        setBackground(new java.awt.Color(30, 41, 59));
        setLayout(null);
 
        // ===== jPanel1: Menu Items =====
        jPanel1.setBackground(new java.awt.Color(30, 41, 59));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setLayout(null);
 
        setupButton(jbtnBeefSeekhKabab, "Kabab", this::jbtnBeefSeekhKababActionPerformed);
        jPanel1.add(jbtnBeefSeekhKabab); jbtnBeefSeekhKabab.setBounds(10, 10, 110, 100);
 
        setupButton(jbtnBuffaloWings, "Wings", this::jbtnBuffaloWingsActionPerformed);
        jPanel1.add(jbtnBuffaloWings); jbtnBuffaloWings.setBounds(130, 10, 110, 100);
 
        setupButton(jbtnChickenBiryani, "Biryani", this::jbtnChickenBiryaniActionPerformed);
        jPanel1.add(jbtnChickenBiryani); jbtnChickenBiryani.setBounds(250, 10, 110, 100);
 
        setupButton(jbtnChickenHandi, "Handi", this::jbtnChickenHandiActionPerformed);
        jPanel1.add(jbtnChickenHandi); jbtnChickenHandi.setBounds(370, 10, 110, 100);
 
        setupButton(jbtnLassi, "Lassi", this::jbtnLassiActionPerformed);
        jPanel1.add(jbtnLassi); jbtnLassi.setBounds(490, 10, 110, 100);
 
        setupButton(jbtnCrunchBurger, "Alfredo", this::jbtnCrunchBurgerActionPerformed);
        jPanel1.add(jbtnCrunchBurger); jbtnCrunchBurger.setBounds(610, 10, 110, 100);
 
        addMenuLabel(jPanel1, "Beef Seekh Kabab", 10, 110); addMenuLabel(jPanel1, "Rs. 250/-", 10, 130);
        addMenuLabel(jPanel1, "Buffalo Wings", 130, 110);   addMenuLabel(jPanel1, "Rs. 470/-", 130, 130);
        addMenuLabel(jPanel1, "Chicken Biryani", 250, 110); addMenuLabel(jPanel1, "Rs. 350/-", 250, 130);
        addMenuLabel(jPanel1, "Chicken Handi", 370, 110);   addMenuLabel(jPanel1, "Rs. 320/-", 370, 130);
        addMenuLabel(jPanel1, "Classic Shahi Lassi", 490, 110); addMenuLabel(jPanel1, "Rs. 120/-", 490, 130);
        addMenuLabel(jPanel1, "Crunch Burger", 610, 110);   addMenuLabel(jPanel1, "Rs. 440/-", 610, 130);
 
        setupButton(jbtnDaalMakhni, "Fish & Chips", this::jbtnDaalMakhniActionPerformed);
        jPanel1.add(jbtnDaalMakhni); jbtnDaalMakhni.setBounds(10, 150, 110, 100);
 
        setupButton(jbtnFajitaSupremePizza, "Pizza", this::jbtnFajitaSupremePizzaActionPerformed);
        jPanel1.add(jbtnFajitaSupremePizza); jbtnFajitaSupremePizza.setBounds(130, 150, 110, 100);
 
        setupButton(jbtnFettuccineAlfredo, "Fries", this::jbtnFettuccineAlfredoActionPerformed);
        jPanel1.add(jbtnFettuccineAlfredo); jbtnFettuccineAlfredo.setBounds(250, 150, 110, 100);
 
        setupButton(jbtnFishAndChips, "Margarita", this::jbtnFishAndChipsActionPerformed);
        jPanel1.add(jbtnFishAndChips); jbtnFishAndChips.setBounds(370, 150, 110, 100);
 
        setupButton(jbtnGrilledChicken, "Sandwich", this::jbtnGrilledChickenActionPerformed);
        jPanel1.add(jbtnGrilledChicken); jbtnGrilledChicken.setBounds(490, 150, 110, 100);
 
        setupButton(jbtnLoadedFries, "Karahi", this::jbtnLoadedFriesActionPerformed);
        jPanel1.add(jbtnLoadedFries); jbtnLoadedFries.setBounds(610, 150, 110, 100);
 
        addMenuLabel(jPanel1, "Daal Makhni", 10, 250);       addMenuLabel(jPanel1, "Rs. 170/-", 10, 270);
        addMenuLabel(jPanel1, "Fajita Supreme Pizza", 130, 250); addMenuLabel(jPanel1, "Rs. 900/-", 130, 270);
        addMenuLabel(jPanel1, "Fettuccine Alfredo", 250, 250); addMenuLabel(jPanel1, "Rs. 750/-", 250, 270);
        addMenuLabel(jPanel1, "Fish & Chips", 370, 250);     addMenuLabel(jPanel1, "Rs. 450/-", 370, 270);
        addMenuLabel(jPanel1, "Grilled Chicken", 490, 250);  addMenuLabel(jPanel1, "Rs. 500/-", 490, 270);
        addMenuLabel(jPanel1, "Loaded Fries", 610, 250);     addMenuLabel(jPanel1, "Rs. 350/-", 610, 270);
 
        setupButton(jbtnMintMargarita, "Naan", this::jbtnMintMargaritaActionPerformed);
        jPanel1.add(jbtnMintMargarita); jbtnMintMargarita.setBounds(10, 300, 110, 100);
 
        setupButton(jbtnGrilledSandwitch, "Sandwich", this::jbtnGrilledSandwitchActionPerformed);
        jPanel1.add(jbtnGrilledSandwitch); jbtnGrilledSandwitch.setBounds(130, 300, 110, 100);
 
        setupButton(jbtnMuttonKarahi, "Karahi", this::jbtnMuttonKarahiActionPerformed);
        jPanel1.add(jbtnMuttonKarahi); jbtnMuttonKarahi.setBounds(250, 300, 110, 100);
 
        setupButton(jbtnShawarma, "Shawarma", this::jbtnShawarmaActionPerformed);
        jPanel1.add(jbtnShawarma); jbtnShawarma.setBounds(370, 300, 110, 100);
 
        setupButton(jbtnSmashBeefBurger, "Burger", this::jbtnSmashBeefBurgerActionPerformed);
        jPanel1.add(jbtnSmashBeefBurger); jbtnSmashBeefBurger.setBounds(490, 300, 110, 100);
 
        setupButton(jbtnTerragonBeefSteak, "Steak", this::jbtnTerragonBeefSteakActionPerformed);
        jPanel1.add(jbtnTerragonBeefSteak); jbtnTerragonBeefSteak.setBounds(610, 300, 110, 100);
 
        addMenuLabel(jPanel1, "Mint Margarita", 10, 410);    addMenuLabel(jPanel1, "Rs. 200/-", 10, 430);
        addMenuLabel(jPanel1, "Grilled Sandwich", 130, 410); addMenuLabel(jPanel1, "Rs. 250/-", 130, 430);
        addMenuLabel(jPanel1, "Mutton Karahi", 250, 410);    addMenuLabel(jPanel1, "Rs. 650/-", 250, 430);
        addMenuLabel(jPanel1, "Shawarma", 370, 410);         addMenuLabel(jPanel1, "Rs. 250/-", 370, 430);
        addMenuLabel(jPanel1, "Smash Beef Burger", 490, 410); addMenuLabel(jPanel1, "Rs. 450/-", 490, 430);
        addMenuLabel(jPanel1, "Terragon Beef Steak", 610, 410); addMenuLabel(jPanel1, "Rs. 750/-", 610, 430);
 
        add(jPanel1); jPanel1.setBounds(620, 10, 750, 460);
 
        // ===== jPanel2: Numpad (FIXED BUTTON PLACEMENTS) =====
        jPanel2.setBackground(new java.awt.Color(30, 41, 59));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setLayout(null);
 
        setupNumBtn(jbtn7, "7", this::jbtn7ActionPerformed); jPanel2.add(jbtn7); jbtn7.setBounds(10, 10, 60, 70);
        setupNumBtn(jbtn8, "8", this::jbtn8ActionPerformed); jPanel2.add(jbtn8); jbtn8.setBounds(80, 10, 60, 70);
        setupNumBtn(jbtn9, "9", this::jbtn9ActionPerformed); jPanel2.add(jbtn9); jbtn9.setBounds(150, 10, 60, 70);
        
        setupNumBtn(jbtn4, "4", this::jbtn4ActionPerformed); jPanel2.add(jbtn4); jbtn4.setBounds(10, 90, 60, 70);
        setupNumBtn(jbtn5, "5", this::jbtn5ActionPerformed); jPanel2.add(jbtn5); jbtn5.setBounds(80, 90, 60, 70);
        setupNumBtn(jbtn6, "6", this::jbtn6ActionPerformed); jPanel2.add(jbtn6); jbtn6.setBounds(150, 90, 60, 70);
        
        setupNumBtn(jbtn1, "1", this::jbtn1ActionPerformed); jPanel2.add(jbtn1); jbtn1.setBounds(10, 170, 60, 70);
        setupNumBtn(jbtn2, "2", this::jbtn2ActionPerformed); jPanel2.add(jbtn2); jbtn2.setBounds(80, 170, 60, 70); // FIXED '2' POSITION
        setupNumBtn(jbtn3, "3", this::jbtn3ActionPerformed); jPanel2.add(jbtn3); jbtn3.setBounds(150, 170, 60, 70);
        
        setupNumBtn(jbtn0, "0", this::jbtn0ActionPerformed); jPanel2.add(jbtn0); jbtn0.setBounds(10, 250, 60, 70);
        setupNumBtn(jbtnDot, ".", this::jbtnDotActionPerformed); jPanel2.add(jbtnDot); jbtnDot.setBounds(80, 250, 60, 70);
        setupNumBtn(jbtnC, "C", this::jbtnCActionPerformed); jPanel2.add(jbtnC); jbtnC.setBounds(150, 250, 60, 70); // FIXED 'C' POSITION
 
        add(jPanel2); jPanel2.setBounds(10, 170, 230, 320);
 
        // ===== Order Table =====
        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Item", "Quantity", "Amount"}));
        jScrollPane1.setViewportView(jTable1);
        add(jScrollPane1); jScrollPane1.setBounds(250, 10, 360, 370);
 
        // ===== jPanel3: Bottom bar =====
        jPanel3.setBackground(new java.awt.Color(30, 41, 59));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setLayout(null);
 
        // jPanel6: Tax/SubTotal/Total
        jPanel6.setBackground(new java.awt.Color(30, 41, 59));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.setLayout(null);
 
        setupWhiteLabel(jLabel1, "Tax", new java.awt.Font("Times New Roman", 1, 24));
        jPanel6.add(jLabel1); jLabel1.setBounds(10, 20, 150, 30);
        setupWhiteLabel(jLabel2, "SubTotal", new java.awt.Font("Times New Roman", 1, 24));
        jPanel6.add(jLabel2); jLabel2.setBounds(10, 70, 150, 30);
        setupWhiteLabel(jLabel3, "Total", new java.awt.Font("Times New Roman", 1, 24));
        jPanel6.add(jLabel3); jLabel3.setBounds(10, 120, 150, 30);
 
        jtxtTax.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jPanel6.add(jtxtTax); jtxtTax.setBounds(170, 20, 160, 30);
        jtxtSubTotal.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jPanel6.add(jtxtSubTotal); jtxtSubTotal.setBounds(170, 70, 160, 30);
        jtxtTotal.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jPanel6.add(jtxtTotal); jtxtTotal.setBounds(170, 120, 160, 30);
 
        jPanel3.add(jPanel6); jPanel6.setBounds(20, 10, 370, 170);
 
        // jPanel4: Payment
        jPanel4.setBackground(new java.awt.Color(30, 41, 59));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel4.setLayout(null);
 
        setupWhiteLabel(jLabel4, "Payment Method", new java.awt.Font("Times New Roman", 1, 24));
        jPanel4.add(jLabel4); jLabel4.setBounds(10, 20, 185, 30);
        setupWhiteLabel(jLabel5, "Cash Given", new java.awt.Font("Times New Roman", 1, 24));
        jPanel4.add(jLabel5); jLabel5.setBounds(10, 70, 185, 30);
        setupWhiteLabel(jLabel6, "Change", new java.awt.Font("Times New Roman", 1, 24));
        jPanel4.add(jLabel6); jLabel6.setBounds(10, 120, 185, 30);
 
        jcboPayment.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jcboPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Cash", "Card"}));
        jPanel4.add(jcboPayment); jcboPayment.setBounds(200, 20, 160, 30);
 
        jtxtDisplay.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jPanel4.add(jtxtDisplay); jtxtDisplay.setBounds(200, 70, 160, 30);
        jtxtChange.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jPanel4.add(jtxtChange); jtxtChange.setBounds(200, 120, 160, 30);
 
        jPanel3.add(jPanel4); jPanel4.setBounds(410, 10, 390, 170);
 
        // jPanel7: Action buttons
        jPanel7.setBackground(new java.awt.Color(30, 41, 59));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel7.setLayout(null);
 
        jbtnPay.setFont(new java.awt.Font("Segoe UI", 1, 24)); jbtnPay.setText("Pay");
        jbtnPay.addActionListener(this::jbtnPayActionPerformed);
        jPanel7.add(jbtnPay); jbtnPay.setBounds(20, 20, 140, 50);
 
        jbtnReset.setFont(new java.awt.Font("Segoe UI", 1, 24)); jbtnReset.setText("Reset");
        jbtnReset.addActionListener(this::jbtnResetActionPerformed);
        jPanel7.add(jbtnReset); jbtnReset.setBounds(170, 20, 140, 50);
 
        jbtnPrint.setFont(new java.awt.Font("Segoe UI", 1, 24)); jbtnPrint.setText("Print");
        jbtnPrint.addActionListener(this::jbtnPrintActionPerformed);
        jPanel7.add(jbtnPrint); jbtnPrint.setBounds(20, 100, 140, 50);
 
        jbtnRemove.setFont(new java.awt.Font("Segoe UI", 1, 24)); jbtnRemove.setText("Remove");
        jbtnRemove.addActionListener(this::jbtnRemoveActionPerformed);
        jPanel7.add(jbtnRemove); jbtnRemove.setBounds(170, 100, 140, 50);
 
        jbtnExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); jbtnExit.setText("BACK TO HUB");
        jPanel7.add(jbtnExit); jbtnExit.setBounds(330, 20, 160, 130);
 
        jPanel3.add(jPanel7); jPanel7.setBounds(820, 10, 510, 170);
 
        add(jPanel3); jPanel3.setBounds(10, 500, 1360, 200);
 
        // BarCode Field
        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("BarCode/Receipt ID");
        add(jLabel44); jLabel44.setBounds(250, 390, 160, 20);
 
        jtxtBarCode.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jtxtBarCode.setEditable(false); // Set to uneditable for structural integrity
        add(jtxtBarCode); jtxtBarCode.setBounds(250, 420, 360, 60);
    }
 
    private void setupButton(javax.swing.JButton btn, String text, java.awt.event.ActionListener al) {
        btn.setFont(new java.awt.Font("Times New Roman", 1, 14));
        btn.setText(text);
        btn.addActionListener(al);
    }
 
    private void setupNumBtn(javax.swing.JButton btn, String text, java.awt.event.ActionListener al) {
        btn.setFont(new java.awt.Font("Times New Roman", 1, 36));
        btn.setText(text);
        btn.addActionListener(al);
    }
 
    private void addMenuLabel(javax.swing.JPanel panel, String text, int x, int y) {
        javax.swing.JLabel lbl = new javax.swing.JLabel(text);
        lbl.setFont(new java.awt.Font("Arial", 1, 13));
        lbl.setForeground(new java.awt.Color(255, 255, 255));
        panel.add(lbl);
        lbl.setBounds(x, y, 120, 18);
    }
 
    private void setupWhiteLabel(javax.swing.JLabel lbl, String text, java.awt.Font font) {
        lbl.setFont(font);
        lbl.setForeground(new java.awt.Color(255, 255, 255));
        lbl.setText(text);
    }
 
    // ===== BUTTON ACTIONS =====
    private void jbtn7ActionPerformed(java.awt.event.ActionEvent evt) { jtxtDisplay.setText(jtxtDisplay.getText() + "7"); }
    private void jbtn8ActionPerformed(java.awt.event.ActionEvent evt) { jtxtDisplay.setText(jtxtDisplay.getText() + "8"); }
    private void jbtn9ActionPerformed(java.awt.event.ActionEvent evt) { jtxtDisplay.setText(jtxtDisplay.getText() + "9"); }
    private void jbtn4ActionPerformed(java.awt.event.ActionEvent evt) { jtxtDisplay.setText(jtxtDisplay.getText() + "4"); }
    private void jbtn5ActionPerformed(java.awt.event.ActionEvent evt) { jtxtDisplay.setText(jtxtDisplay.getText() + "5"); }
    private void jbtn6ActionPerformed(java.awt.event.ActionEvent evt) { jtxtDisplay.setText(jtxtDisplay.getText() + "6"); }
    private void jbtn1ActionPerformed(java.awt.event.ActionEvent evt) { jtxtDisplay.setText(jtxtDisplay.getText() + "1"); }
    private void jbtn2ActionPerformed(java.awt.event.ActionEvent evt) { jtxtDisplay.setText(jtxtDisplay.getText() + "2"); }
    private void jbtn3ActionPerformed(java.awt.event.ActionEvent evt) { jtxtDisplay.setText(jtxtDisplay.getText() + "3"); }
    private void jbtn0ActionPerformed(java.awt.event.ActionEvent evt) { jtxtDisplay.setText(jtxtDisplay.getText() + "0"); }
    private void jbtnDotActionPerformed(java.awt.event.ActionEvent evt) {
        if (!jtxtDisplay.getText().contains(".")) jtxtDisplay.setText(jtxtDisplay.getText() + ".");
    }
    private void jbtnCActionPerformed(java.awt.event.ActionEvent evt) { jtxtDisplay.setText(""); jtxtChange.setText(""); }
 
    private void addItem(String name, double price) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();
        
        for (int i = 0; i < rowCount; i++) {
            if (model.getValueAt(i, 0).toString().equals(name)) {
                int currentQty = Integer.parseInt(model.getValueAt(i, 1).toString());
                int newQty = currentQty + 1;
                
                model.setValueAt(String.valueOf(newQty), i, 1);
                model.setValueAt(newQty * price, i, 2);
                
                ItemCost();
                return;
            }
        }
        
        model.addRow(new Object[]{name, "1", price});
        ItemCost();
    }
 
    private void jbtnBeefSeekhKababActionPerformed(java.awt.event.ActionEvent evt)    { addItem("Beef Seekh Kabab", 250); }
    private void jbtnBuffaloWingsActionPerformed(java.awt.event.ActionEvent evt)      { addItem("Buffalo Wings", 470); }
    private void jbtnChickenBiryaniActionPerformed(java.awt.event.ActionEvent evt)    { addItem("Chicken Biryani", 350); }
    private void jbtnChickenHandiActionPerformed(java.awt.event.ActionEvent evt)      { addItem("Chicken Handi", 320); }
    private void jbtnLassiActionPerformed(java.awt.event.ActionEvent evt)             { addItem("Classic Shahi Lassi", 120); }
    private void jbtnCrunchBurgerActionPerformed(java.awt.event.ActionEvent evt)      { addItem("Crunch Burger", 440); }
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt)             { }
    private void jbtnDaalMakhniActionPerformed(java.awt.event.ActionEvent evt)        { addItem("Daal Makhni", 170); }
    private void jbtnFajitaSupremePizzaActionPerformed(java.awt.event.ActionEvent evt){ addItem("Fajita Supreme Pizza", 900); }
    private void jbtnFettuccineAlfredoActionPerformed(java.awt.event.ActionEvent evt) { addItem("Fettuccine Alfredo", 750); }
    private void jbtnFishAndChipsActionPerformed(java.awt.event.ActionEvent evt)      { addItem("Fish & Chips", 450); }
    private void jbtnGrilledChickenActionPerformed(java.awt.event.ActionEvent evt)    { addItem("Grilled Chicken", 500); }
    private void jbtnLoadedFriesActionPerformed(java.awt.event.ActionEvent evt)       { addItem("Loaded Fries", 350); }
    private void jbtnMintMargaritaActionPerformed(java.awt.event.ActionEvent evt)     { addItem("Mint Margarita", 200); }
    private void jbtnGrilledSandwitchActionPerformed(java.awt.event.ActionEvent evt)  { addItem("Grilled Sandwich", 250); }
    private void jbtnMuttonKarahiActionPerformed(java.awt.event.ActionEvent evt)      { addItem("Mutton Karahi", 650); }
    private void jbtnShawarmaActionPerformed(java.awt.event.ActionEvent evt)          { addItem("Shawarma", 250); }
    private void jbtnSmashBeefBurgerActionPerformed(java.awt.event.ActionEvent evt)   { addItem("Smash Beef Burger", 450); }
    private void jbtnTerragonBeefSteakActionPerformed(java.awt.event.ActionEvent evt) { addItem("Terragon Beef Steak", 750); }
 
    private void jbtnPayActionPerformed(java.awt.event.ActionEvent evt) {
        if (jTable1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "The cart is empty!", "Cart Status", JOptionPane.WARNING_MESSAGE);
            return;
        }
 
        if (jcboPayment.getSelectedItem().equals("Cash")) {
            Change();
        } else { 
            jtxtChange.setText(""); 
            jtxtDisplay.setText(""); 
            JOptionPane.showMessageDialog(this, "Card Payment Accepted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
 
    private void jbtnResetActionPerformed(java.awt.event.ActionEvent evt) {
        ((DefaultTableModel) jTable1.getModel()).setRowCount(0);
        jtxtChange.setText(""); jtxtTax.setText(""); jtxtSubTotal.setText("");
        jtxtTotal.setText(""); jtxtDisplay.setText("");
        generateReceiptID(); // Refreshes and generates a brand new ID on Reset
    }
 
    private void jbtnPrintActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            jTable1.print(JTable.PrintMode.NORMAL,
                new MessageFormat("MuFaYa'S Cafe Receipt (" + jtxtBarCode.getText() + ")"),
                new MessageFormat("Page {0, number, integer}"));
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot Print %s%n", e.getMessage());
        }
    }
 
    private void jbtnRemoveActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            model.removeRow(row);
            ItemCost();
            if (jcboPayment.getSelectedItem().equals("Cash") && !jtxtDisplay.getText().trim().isEmpty()) {
                Change();
            } else {
                jtxtChange.setText("");
            }
        }
    }
 
    // Variables declaration
    private javax.swing.JButton jButton19, jbtnBeefSeekhKabab, jbtnBuffaloWings, jbtnChickenBiryani;
    private javax.swing.JButton jbtnChickenHandi, jbtnLassi, jbtnCrunchBurger, jbtnDaalMakhni;
    private javax.swing.JButton jbtnFajitaSupremePizza, jbtnFettuccineAlfredo, jbtnFishAndChips;
    private javax.swing.JButton jbtnGrilledChicken, jbtnLoadedFries, jbtnMintMargarita;
    private javax.swing.JButton jbtnGrilledSandwitch, jbtnMuttonKarahi, jbtnShawarma;
    private javax.swing.JButton jbtnSmashBeefBurger, jbtnTerragonBeefSteak;
    private javax.swing.JButton jbtn0, jbtn1, jbtn2, jbtn3, jbtn4, jbtn5, jbtn6, jbtn7, jbtn8, jbtn9;
    private javax.swing.JButton jbtnC, jbtnDot, jbtnPay, jbtnPrint, jbtnRemove, jbtnReset, jbtnExit;
    private javax.swing.JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel44;
    private javax.swing.JPanel jPanel1, jPanel2, jPanel3, jPanel4, jPanel6, jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jtxtBarCode, jtxtChange, jtxtDisplay, jtxtSubTotal, jtxtTax, jtxtTotal;
    private javax.swing.JComboBox<String> jcboPayment;
}