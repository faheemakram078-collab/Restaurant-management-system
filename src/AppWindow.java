package src;

import javax.swing.*;
import java.awt.*;

public class AppWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;

    public AppWindow() {
        setTitle("SpicyBites | Premium Restaurant Suite");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window on screen

        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // Registering our distinct panels to the main window container
        mainContainer.add(new LoginPanel(this), "LOGIN");
        mainContainer.add(new Dashboard(this), "DASHBOARD");
        mainContainer.add(new AdminPanel(this), "ADMIN");
        mainContainer.add(new OrderPanel(this), "ORDER");

        add(mainContainer);
        showScreen("LOGIN"); // Display login first
    }

    public void showScreen(String screenName) {
        cardLayout.show(mainContainer, screenName);
    }
}
