package src;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Safe asynchronous frame rendering sequence execution thread
        SwingUtilities.invokeLater(() -> {
            AppWindow systemFrame = new AppWindow();
            systemFrame.setVisible(true);
        });
    }
}
//changed
