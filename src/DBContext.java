package src;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;

public class DBContext {
    private static final String USER_FILE = "data/users.txt";
    private static final String MENU_FILE = "data/menu.txt";
    private static final String ORDER_FILE = "data/orders.txt"; // Added Order File Path

    public static ArrayList<String> readData(String fileName) {
        ArrayList<String> records = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return records; // Safety check

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) records.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading " + fileName + ": " + e.getMessage());
        }
        return records;
    }

    public static void writeData(String fileName, ArrayList<String> data) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, false))) {
            for (String record : data) {
                pw.println(record);
            }
            pw.flush();
        } catch (IOException e) {
            System.out.println("Error writing to " + fileName + ": " + e.getMessage());
        }
    }

    // =========================================================================
    // SECURITY INFRASTRUCTURE: PASSWORD HASHING (SHA-256)
    // =========================================================================
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes("UTF-8"));
            
            // Convert byte array into a readable 64-character hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            System.out.println("Error hashing password: " + e.getMessage());
            return password; // Fallback to raw text if encryption subsystem encounters a failure
        }
    }

    // Staff Methods
    public static ArrayList<String> getUsers() { return readData(USER_FILE); }
    public static void saveUsers(ArrayList<String> data) { writeData(USER_FILE, data); }

    // Menu Methods
    public static ArrayList<String> getMenu() { return readData(MENU_FILE); }
    public static void saveMenu(ArrayList<String> data) { writeData(MENU_FILE, data); }

    // Order Methods (FIXES YOUR ERROR)
    public static ArrayList<String> getOrders() { return readData(ORDER_FILE); }
    public static void saveOrders(ArrayList<String> data) { writeData(ORDER_FILE, data); }
}