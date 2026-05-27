package src;

import java.awt.AWTEvent;
import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    
    private Dashboard dashboard;
    private String currentRole = "";

    // --- SESSION TIMEOUT INFRASTRUCTURE ---
    private Timer idleTimer;
    // 120,000 milliseconds = 2 minutes of complete inactivity
    private static final int TIMEOUT_MS = 120000; 

    public AppWindow() {
        setTitle("SpicyBites | Premium Restaurant Suite");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // 1. Initialize the dashboard separately
        dashboard = new Dashboard(this);

        // 2. Register distinct panels
        mainContainer.add(new LoginPanel(this), "LOGIN");
        mainContainer.add(dashboard, "DASHBOARD"); 
        mainContainer.add(new AdminPanel(this), "ADMIN");
        mainContainer.add(new OrderPanel(this), "ORDER");
        mainContainer.add(new JavaPOS(this), "JAVAPOS");
        
        add(mainContainer);
        
        // --- 3. CONFIGURE GLOBAL ACTIVITY TRACKING ---
        setupIdleTimer();
        setupGlobalInputListener();

        showScreen("LOGIN"); 
    }

    /**
     * Initializes the background countdown timer.
     */
    private void setupIdleTimer() {
        idleTimer = new Timer(TIMEOUT_MS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSessionTimeout();
            }
        });
        idleTimer.setRepeats(false); // Only fire once when countdown reaches zero
    }

    /**
     * Captures every mouse movement, click, and key press across the entire window
     * to reset the idle countdown instantly.
     */
    private void setupGlobalInputListener() {
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            // If an input event occurs, refresh the session timer
            if (idleTimer != null && idleTimer.isRunning()) {
                idleTimer.restart();
            }
        }, AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK | AWTEvent.KEY_EVENT_MASK);
    }

    /**
     * Automatically logs out the active user when the inactivity threshold is crossed.
     */
    private void handleSessionTimeout() {
        // Only timeout if someone is actually logged in
        if (!currentRole.isEmpty()) {
            System.out.println(">> SECURITY ALERT: Session idle timeout reached. Terminating access keys.");
            
            // 1. Revoke active permissions
            setAuthenticatedRole("");
            
            // 2. Snap UI presentation back to Login layout
            showScreen("LOGIN");
            
            // 3. Alert the workspace terminal
            JOptionPane.showMessageDialog(this, 
                "Your session has expired due to inactivity.\nPlease log in again to clear system locks.", 
                "Security Auto-Timeout", 
                JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * THE SECURITY BRIDGE
     * Configures authorization scopes and starts/stops the tracking monitor.
     */
    public void setAuthenticatedRole(String role) {
        this.currentRole = role;
        dashboard.applySecuritySettings(role);

        // Manage active timer tracking based on status
        if (role.isEmpty()) {
            if (idleTimer != null && idleTimer.isRunning()) idleTimer.stop();
        } else {
            // User signed in successfully, start counting down for safety!
            if (idleTimer != null) {
                idleTimer.restart();
            }
            System.out.println(">> Security monitor active for role: [" + role + "]");
        }
    }

    public void showScreen(String screenName) {
        cardLayout.show(mainContainer, screenName);
    }
}
