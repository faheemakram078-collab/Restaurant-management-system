package src;

import java.text.MessageFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Modified version of JavaPOS to integrate smoothly as a JPanel inside VS Code.
 * All breaking image icons and frame methods have been safely removed.
 */
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
        jbtnExit.addActionListener(e -> {
            window.showScreen("DASHBOARD"); 
        });
    }

    //========================================= FUNCTIONS =========================================
    
    public void ItemCost() {
        double sum = 0;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            sum = sum + Double.parseDouble(jTable1.getValueAt(i, 2).toString());
        }
        jtxtSubTotal.setText(Double.toString(sum));
        double cSubTotal = Double.parseDouble(jtxtSubTotal.getText());
        
        double cTax = (cSubTotal * 3.9) / 100;
        String iTax = String.format("Rs. %.2f", cTax);
        jtxtTax.setText(iTax);
        
        String iSubTotal = String.format("Rs. %.2f", cSubTotal);
        jtxtSubTotal.setText(iSubTotal);
        
        String iTotal = String.format("Rs. %.2f", cSubTotal + cTax);
        jtxtTotal.setText(iTotal);
        
        String iBarCode = String.format("%.2f", cSubTotal + cTax);
        jtxtBarCode.setText(iBarCode);
    }
    
    public void Change() {
        double sum = 0;
        double tax = 3.9;
        double cash = Double.parseDouble(jtxtDisplay.getText());
        
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            sum = sum + Double.parseDouble(jTable1.getValueAt(i, 2).toString());
        }
        
        double cTax = (sum * tax) / 100;
        double cTotal = sum + cTax;
        double cChange = cash - cTotal;
        
        String changeGiven = String.format("Rs. %.2f", cChange);
        jtxtChange.setText(changeGiven);
    }

    //=============================================================================================

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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
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
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(30, 41, 59));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setForeground(new java.awt.Color(51, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnCrunchBurger.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnCrunchBurger.setText("Alfredo");
        jbtnCrunchBurger.addActionListener(this::jbtnCrunchBurgerActionPerformed);
        jPanel1.add(jbtnCrunchBurger, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 110, 100));

        jbtnBeefSeekhKabab.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnBeefSeekhKabab.setText("Kabab");
        jbtnBeefSeekhKabab.addActionListener(this::jbtnBeefSeekhKababActionPerformed);
        jPanel1.add(jbtnBeefSeekhKabab, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, 100));

        jbtnBuffaloWings.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnBuffaloWings.setText("Wings");
        jbtnBuffaloWings.addActionListener(this::jbtnBuffaloWingsActionPerformed);
        jPanel1.add(jbtnBuffaloWings, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 110, 100));

        jbtnChickenBiryani.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnChickenBiryani.setText("Biryani");
        jbtnChickenBiryani.addActionListener(this::jbtnChickenBiryaniActionPerformed);
        jPanel1.add(jbtnChickenBiryani, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 110, 100));

        jbtnChickenHandi.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnChickenHandi.setText("Handi");
        jbtnChickenHandi.addActionListener(this::jbtnChickenHandiActionPerformed);
        jPanel1.add(jbtnChickenHandi, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 110, 100));

        jbtnLassi.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnLassi.setText("Lassi");
        jbtnLassi.addActionListener(this::jbtnLassiActionPerformed);
        jPanel1.add(jbtnLassi, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 110, 100));

        jButton19.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jButton19.addActionListener(this::jButton19ActionPerformed);
        jPanel1.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 110, 100));

        jbtnLoadedFries.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnLoadedFries.setText("Karahi");
        jbtnLoadedFries.addActionListener(this::jbtnLoadedFriesActionPerformed);
        jPanel1.add(jbtnLoadedFries, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 110, 100));

        jbtnGrilledChicken.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnGrilledChicken.setText("Sandwich");
        jbtnGrilledChicken.addActionListener(this::jbtnGrilledChickenActionPerformed);
        jPanel1.add(jbtnGrilledChicken, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 110, 100));

        jbtnFishAndChips.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnFishAndChips.setText("Margarita");
        jbtnFishAndChips.addActionListener(this::jbtnFishAndChipsActionPerformed);
        jPanel1.add(jbtnFishAndChips, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 110, 100));

        jbtnFettuccineAlfredo.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnFettuccineAlfredo.setText("Fries");
        jbtnFettuccineAlfredo.addActionListener(this::jbtnFettuccineAlfredoActionPerformed);
        jPanel1.add(jbtnFettuccineAlfredo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 110, 100));

        jbtnFajitaSupremePizza.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnFajitaSupremePizza.setText("Pizza");
        jbtnFajitaSupremePizza.addActionListener(this::jbtnFajitaSupremePizzaActionPerformed);
        jPanel1.add(jbtnFajitaSupremePizza, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 110, 100));

        jbtnDaalMakhni.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnDaalMakhni.setText("Fish & Chips");
        jbtnDaalMakhni.addActionListener(this::jbtnDaalMakhniActionPerformed);
        jPanel1.add(jbtnDaalMakhni, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 110, 100));

        jbtnTerragonBeefSteak.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnTerragonBeefSteak.setText("Steak");
        jbtnTerragonBeefSteak.addActionListener(this::jbtnTerragonBeefSteakActionPerformed);
        jPanel1.add(jbtnTerragonBeefSteak, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, 110, 100));

        jbtnSmashBeefBurger.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnSmashBeefBurger.setText("Burger");
        jbtnSmashBeefBurger.addActionListener(this::jbtnSmashBeefBurgerActionPerformed);
        jPanel1.add(jbtnSmashBeefBurger, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 110, 100));

        jbtnShawarma.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnShawarma.setText("Shawarma");
        jbtnShawarma.addActionListener(this::jbtnShawarmaActionPerformed);
        jPanel1.add(jbtnShawarma, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 110, 100));

        jbtnMuttonKarahi.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnMuttonKarahi.setText("Karahi");
        jbtnMuttonKarahi.addActionListener(this::jbtnMuttonKarahiActionPerformed);
        jPanel1.add(jbtnMuttonKarahi, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 300, 110, 100));

        jbtnGrilledSandwitch.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnGrilledSandwitch.setText("Sandwich");
        jbtnGrilledSandwitch.addActionListener(this::jbtnGrilledSandwitchActionPerformed);
        jPanel1.add(jbtnGrilledSandwitch, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 300, 110, 100));

        jbtnMintMargarita.setFont(new java.awt.Font("Times New Roman", 1, 14)); 
        jbtnMintMargarita.setText("Naan");
        jbtnMintMargarita.addActionListener(this::jbtnMintMargaritaActionPerformed);
        jPanel1.add(jbtnMintMargarita, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 110, 100));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Beef Seekh Kabab");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Rs. 250/-");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Buffalo Wings");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Rs. 470/-");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Chicken Biryani");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Rs. 350/-");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Chicken Handi");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Rs. 320/- ");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 130, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Classic Shahi Lassi");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Rs. 120/-");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Crunch Burger");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Rs. 440/-");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Daal Makhni");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Rs. 170/-");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Fajita Supreme Pizza");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Rs. 900/-");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Fettuccine Alfredo");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Rs. 750/-");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Fish & Chips ");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 250, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Rs. 450/-");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, -1, -1));

        jLabel27.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Grilled Chicken");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, -1, -1));

        jLabel28.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Rs. 500/-");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 270, -1, -1));

        jLabel29.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Loaded Fries");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, -1));

        jLabel30.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Rs. 350/-");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, -1, -1));

        jLabel31.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Mint Margarita");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jLabel32.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Rs. 200/-");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        jLabel33.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Grilled Sandwitch");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, -1, -1));

        jLabel34.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Rs. 250/-");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, -1, -1));

        jLabel35.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Mutton Karahi");
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, -1, -1));

        jLabel36.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Rs. 650/-");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, -1, -1));

        jLabel37.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Shawarma");
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, -1, -1));

        jLabel38.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Rs. 250/- ");
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 430, -1, -1));

        jLabel39.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Smash Beef Burger");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, -1, -1));

        jLabel40.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Rs. 450/-");
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 430, -1, -1));

        jLabel41.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Terragon Beef Steak");
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 410, -1, -1));

        jLabel42.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); 
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Rs. 750/-");
        jPanel1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 750, 460));

        jPanel2.setBackground(new java.awt.Color(30, 41, 59));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtn7.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtn7.setText("7");
        jbtn7.addActionListener(this::jbtn7ActionPerformed);
        jPanel2.add(jbtn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 70));

        jbtn8.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtn8.setText("8");
        jbtn8.addActionListener(this::jbtn8ActionPerformed);
        jPanel2.add(jbtn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 60, 70));

        jbtn4.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtn4.setText("4");
        jbtn4.addActionListener(this::jbtn4ActionPerformed);
        jPanel2.add(jbtn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 60, 70));

        jbtn5.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtn5.setText("5");
        jbtn5.addActionListener(this::jbtn5ActionPerformed);
        jPanel2.add(jbtn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 60, 70));

        jbtn6.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtn6.setText("6");
        jbtn6.addActionListener(this::jbtn6ActionPerformed);
        jPanel2.add(jbtn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 60, 70));

        jbtn1.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtn1.setText("1");
        jbtn1.addActionListener(this::jbtn1ActionPerformed);
        jPanel2.add(jbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 60, 70));

        jbtn2.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtn2.setText("2");
        jbtn2.addActionListener(this::jbtn2ActionPerformed);
        jPanel2.add(jbtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 60, 70));

        jbtn3.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtn3.setText("3");
        jbtn3.addActionListener(this::jbtn3ActionPerformed);
        jPanel2.add(jbtn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 60, 70));

        jbtn0.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtn0.setText("0");
        jbtn0.addActionListener(this::jbtn0ActionPerformed);
        jPanel2.add(jbtn0, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 60, 70));

        jbtnDot.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtnDot.setText(".");
        jbtnDot.addActionListener(this::jbtnDotActionPerformed);
        jPanel2.add(jbtnDot, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 60, 70));

        jbtnC.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtnC.setText("C");
        jbtnC.addActionListener(this::jbtnCActionPerformed);
        jPanel2.add(jbtnC, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 60, 70));

        jbtn9.setFont(new java.awt.Font("Times New Roman", 1, 36)); 
        jbtn9.setText("9");
        jbtn9.addActionListener(this::jbtn9ActionPerformed);
        jPanel2.add(jbtn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 60, 70));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 230, 320));

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Item", "Quantity", "Amount" }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 360, 370));

        jPanel3.setBackground(new java.awt.Color(30, 41, 59));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(30, 41, 59));
        jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); 
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tax");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); 
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SubTotal");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); 
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Total");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jtxtTax.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jPanel6.add(jtxtTax, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 160, 30));

        jtxtSubTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jPanel6.add(jtxtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 160, 30));

        jtxtTotal.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jPanel6.add(jtxtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 160, 30));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 370, 170));

        jPanel4.setBackground(new java.awt.Color(30, 41, 59));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); 
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Payment Method");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); 
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Cash Given");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); 
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Change");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jtxtDisplay.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jPanel4.add(jtxtDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 160, 30));

        jtxtChange.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jPanel4.add(jtxtChange, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 160, 30));

        jcboPayment.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jcboPayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Card" }));
        jPanel4.add(jcboPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 160, 30));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 390, 170));

        jPanel7.setBackground(new java.awt.Color(30, 41, 59));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnReset.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jbtnReset.setText("Reset");
        jbtnReset.addActionListener(this::jbtnResetActionPerformed);
        jPanel7.add(jbtnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 140, 50));

        jbtnPay.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jbtnPay.setText("Pay");
        jbtnPay.addActionListener(this::jbtnPayActionPerformed);
        jPanel7.add(jbtnPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 50));

        jbtnPrint.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jbtnPrint.setText("Print");
        jbtnPrint.addActionListener(this::jbtnPrintActionPerformed);
        jPanel7.add(jbtnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 140, 50));

        jbtnRemove.setFont(new java.awt.Font("Segoe UI", 1, 24)); 
        jbtnRemove.setText("Remove");
        jbtnRemove.addActionListener(this::jbtnRemoveActionPerformed);
        jPanel7.add(jbtnRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 140, 50));

        jbtnExit.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
        jbtnExit.setText("BACK TO HUB");
        jPanel7.add(jbtnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 160, 130));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 510, 170));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 1360, 200));

        jtxtBarCode.setFont(new java.awt.Font("Barcode Font", 1, 36)); 
        add(jtxtBarCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, 360, 60));

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("BarCode/Receipt ID");
        add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, -1, -1));
    }

    // =================================== BUTTON ACTIONS ===================================

    private void jbtn7ActionPerformed(java.awt.event.ActionEvent evt) {
        String Enternumber = jtxtDisplay.getText() + jbtn7.getText();
        jtxtDisplay.setText(Enternumber);
    }

    private void jbtn8ActionPerformed(java.awt.event.ActionEvent evt) {
        String Enternumber = jtxtDisplay.getText() + jbtn8.getText();
        jtxtDisplay.setText(Enternumber);
    }

    private void jbtn9ActionPerformed(java.awt.event.ActionEvent evt) {
        String Enternumber = jtxtDisplay.getText() + jbtn9.getText();
        jtxtDisplay.setText(Enternumber);
    }

    private void jbtn4ActionPerformed(java.awt.event.ActionEvent evt) {
        String Enternumber = jtxtDisplay.getText() + jbtn4.getText();
        jtxtDisplay.setText(Enternumber);
    }

    private void jbtn5ActionPerformed(java.awt.event.ActionEvent evt) {
        String Enternumber = jtxtDisplay.getText() + jbtn5.getText();
        jtxtDisplay.setText(Enternumber);
    }

    private void jbtn6ActionPerformed(java.awt.event.ActionEvent evt) {
        String Enternumber = jtxtDisplay.getText() + jbtn6.getText();
        jtxtDisplay.setText(Enternumber);
    }

    private void jbtn1ActionPerformed(java.awt.event.ActionEvent evt) {
        String Enternumber = jtxtDisplay.getText() + jbtn1.getText();
        jtxtDisplay.setText(Enternumber);
    }

    private void jbtn2ActionPerformed(java.awt.event.ActionEvent evt) {
        String Enternumber = jtxtDisplay.getText() + jbtn2.getText();
        jtxtDisplay.setText(Enternumber);
    }

    private void jbtn3ActionPerformed(java.awt.event.ActionEvent evt) {
        String Enternumber = jtxtDisplay.getText() + jbtn3.getText();
        jtxtDisplay.setText(Enternumber);
    }

    private void jbtn0ActionPerformed(java.awt.event.ActionEvent evt) {
        String Enternumber = jtxtDisplay.getText() + jbtn0.getText();
        jtxtDisplay.setText(Enternumber);
    }

    private void jbtnDotActionPerformed(java.awt.event.ActionEvent evt) {
        if (!jtxtDisplay.getText().contains(".")) {
            jtxtDisplay.setText(jtxtDisplay.getText() + jbtnDot.getText());
        }
    }

    private void jbtnCActionPerformed(java.awt.event.ActionEvent evt) {
        jtxtDisplay.setText("");
        jtxtChange.setText("");
    }

    private void jbtnBeefSeekhKababActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 250;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Beef Seekh Kabab", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnBuffaloWingsActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 470;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Buffalo Wings", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnChickenBiryaniActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 350;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Chicken Biryani", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnChickenHandiActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 320;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Chicken Handi", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnLassiActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 120;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Classic Shahi Lassi", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnCrunchBurgerActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 440;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Crunch Burger", "1", PriceOfItem});
        ItemCost();
    }

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {
        // Reserved/Empty Button
    }

    private void jbtnDaalMakhniActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 170;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Daal Makhni", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnFajitaSupremePizzaActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 900;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Fajita Supreme Pizza", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnFettuccineAlfredoActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 750;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Fettuccine Alfredo", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnFishAndChipsActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 450;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Fish & Chips", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnGrilledChickenActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 500;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Grilled Chicken", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnLoadedFriesActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 350;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Loaded Fries", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnMintMargaritaActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 200;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Mint Margarita", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnGrilledSandwitchActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 250;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Grilled Sandwich", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnMuttonKarahiActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 650;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Mutton Karahi", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnShawarmaActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 250;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Shawarma", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnSmashBeefBurgerActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 450;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Smash Beef Burger", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnTerragonBeefSteakActionPerformed(java.awt.event.ActionEvent evt) {
        double PriceOfItem = 750;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.addRow(new Object[]{"Terragon Beef Steak", "1", PriceOfItem});
        ItemCost();
    }

    private void jbtnPayActionPerformed(java.awt.event.ActionEvent evt) {
        if (jcboPayment.getSelectedItem().equals("Cash")) {
            Change();
        } else {
            jtxtChange.setText("");
            jtxtDisplay.setText("");
        }
    }

    private void jbtnResetActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        jtxtChange.setText("");
        jtxtTax.setText("");
        jtxtSubTotal.setText("");
        jtxtTotal.setText("");
        jtxtDisplay.setText("");
        jtxtBarCode.setText("");
    }

    private void jbtnPrintActionPerformed(java.awt.event.ActionEvent evt) {
        MessageFormat header = new MessageFormat("MuFaYa'S Cafe Receipt");
        MessageFormat footer = new MessageFormat("Page {0, number, integer}");
        try {
            jTable1.print(JTable.PrintMode.NORMAL, header, footer);
        } catch (java.awt.print.PrinterException e) {
            System.err.format("Cannot Print %s%n", e.getMessage());
        }
    }

    private void jbtnRemoveActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int removeRow = jTable1.getSelectedRow();
        if (removeRow >= 0) {
            model.removeRow(removeRow);
            ItemCost();
            if (jcboPayment.getSelectedItem().equals("Cash")) {
                Change();
            }
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton19;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtn0;
    private javax.swing.JButton jbtn1;
    private javax.swing.JButton jbtn2;
    private javax.swing.JButton jbtn3;
    private javax.swing.JButton jbtn4;
    private javax.swing.JButton jbtn5;
    private javax.swing.JButton jbtn6;
    private javax.swing.JButton jbtn7;
    private javax.swing.JButton jbtn8;
    private javax.swing.JButton jbtn9;
    private javax.swing.JButton jbtnBeefSeekhKabab;
    private javax.swing.JButton jbtnBuffaloWings;
    private javax.swing.JButton jbtnC;
    private javax.swing.JButton jbtnChickenBiryani;
    private javax.swing.JButton jbtnChickenHandi;
    private javax.swing.JButton jbtnCrunchBurger;
    private javax.swing.JButton jbtnDaalMakhni;
    private javax.swing.JButton jbtnDot;
    private javax.swing.JButton jbtnExit;
    private javax.swing.JButton jbtnFajitaSupremePizza;
    private javax.swing.JButton jbtnFettuccineAlfredo;
    private javax.swing.JButton jbtnFishAndChips;
    private javax.swing.JButton jbtnGrilledChicken;
    private javax.swing.JButton jbtnGrilledSandwitch;
    private javax.swing.JButton jbtnLassi;
    private javax.swing.JButton jbtnLoadedFries;
    private javax.swing.JButton jbtnMintMargarita;
    private javax.swing.JButton jbtnMuttonKarahi;
    private javax.swing.JButton jbtnPay;
    private javax.swing.JButton jbtnPrint;
    private javax.swing.JButton jbtnRemove;
    private javax.swing.JButton jbtnReset;
    private javax.swing.JButton jbtnShawarma;
    private javax.swing.JButton jbtnSmashBeefBurger;
    private javax.swing.JButton jbtnTerragonBeefSteak;
    private javax.swing.JTextField jtxtBarCode;
    private javax.swing.JTextField jtxtChange;
    private javax.swing.JTextField jtxtDisplay;
    private javax.swing.JTextField jtxtSubTotal;
    private javax.swing.JTextField jtxtTax;
    private javax.swing.JTextField jtxtTotal;
    private javax.swing.JComboBox<String> jcboPayment;
    // End of variables declaration
}