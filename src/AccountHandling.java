import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.InputStream;
/**
 * Handles permanent storage of customer accounts into a text file
 *
 * @author Team 2
 * @version 1.0
 */
public class AccountHandling {
    private static ArrayList<Customer> accountStore = new ArrayList<Customer>();

    /**
     * Loads accounts from the text file into the accountStore ArrayList
     */
    public static void loadAccounts() {
        //InputStream file = ClassLoader.getSystemResourceAsStream("AccountStore.txt");
        File file = new File("AccountStore.txt");
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String accData = sc.nextLine();
                System.out.println(accData);
                String[] accSplit = accData.split("SPLIT");
                for(int i = 0; i < 5; i++){
                    System.out.println(accSplit[i]);
                }
                Customer temp = new Customer(accSplit[0], accSplit[1], accSplit[2], accSplit[3], accSplit[4]);
                accountStore.add(temp);
                Main.addLogin(temp.getPhoneNumber(), temp.getPassword());
                Main.setCustomerHashMap(temp.getPhoneNumber(), temp);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Stores new accounts into the text file
     */
    public static void updateAccountStore() {
        File file = new File("AccountStore.txt");
        try {
            FileWriter writer = new FileWriter(file, true);
            PrintWriter printer = new PrintWriter(writer);
            for (int i = 0; i < accountStore.size(); i++) {
                Customer temp = accountStore.get(i);
                printer.println(temp.getFirstName() + "SPLIT" + temp.getLastName() + "SPLIT" + temp.getPhoneNumber() + "SPLIT"
                        + temp.getAddress() + "SPLIT" + temp.getPassword());
                printer.close();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new customer into the accountStore ArrayList and updates the text file
     * @param c The customer to add
     */
    public static void addAccount(Customer c) {
        accountStore.add(c);
        updateAccountStore();
    }
}
