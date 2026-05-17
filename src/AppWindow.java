package src;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    
    // --- RBAC STEP 3: Create references to the panels ---
    private Dashboard dashboard;
    private String currentRole = "";

    public AppWindow() {
        setTitle("SpicyBites | Premium Restaurant Suite");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // 1. Initialize the dashboard separately so we can talk to it later
        dashboard = new Dashboard(this);

        // 2. Registering our distinct panels
        mainContainer.add(new LoginPanel(this), "LOGIN");
        mainContainer.add(dashboard, "DASHBOARD"); // Use the reference here
        mainContainer.add(new AdminPanel(this), "ADMIN");
        mainContainer.add(new OrderPanel(this), "ORDER");

        add(mainContainer);
        showScreen("LOGIN"); 
    }

    /**
     * THE SECURITY BRIDGE
     * This is called by LoginPanel when authentication is successful.
     */
    public void setAuthenticatedRole(String role) {
        this.currentRole = role;
        // This tells the dashboard to hide the Admin button if the user is not an Admin
        dashboard.applySecuritySettings(role);
    }

    public void showScreen(String screenName) {
        cardLayout.show(mainContainer, screenName);
    }
}
