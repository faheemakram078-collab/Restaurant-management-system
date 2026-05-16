package src;

import java.io.*;
import java.util.ArrayList;

public class DBContext {
    private static final String USER_FILE = "data/users.txt";
    private static final String MENU_FILE = "data/menu.txt";
    private static final String ORDER_FILE = "data/orders.txt";

    // Generic reader to load file lines into an ArrayList
    public static ArrayList<String> readData(String fileName) {
        ArrayList<String> records = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return records;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) records.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading: " + fileName);
        }
        return records;
    }

    // Generic writer to rewrite lists back into flat text files
    public static void writeData(String fileName, ArrayList<String> data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            for (String record : data) {
                bw.write(record);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to: " + fileName);
        }
    }

    public static ArrayList<String> getUsers() { return readData(USER_FILE); }
    public static void saveUsers(ArrayList<String> data) { writeData(USER_FILE, data); }

    public static ArrayList<String> getMenu() { return readData(MENU_FILE); }
    public static void saveMenu(ArrayList<String> data) { writeData(MENU_FILE, data); }

    public static ArrayList<String> getOrders() { return readData(ORDER_FILE); }
    public static void saveOrders(ArrayList<String> data) { writeData(ORDER_FILE, data); }
}
