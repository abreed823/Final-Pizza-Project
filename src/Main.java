import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 *Sprint 2 code for SWE 3313 Pizza Project
 * Links together all of the pages for the ordering system for Mom and Pop's Pizza Shop
 *
 * @author Team 2
 * @version 1.2
 * */
public class Main {
    //public static ArrayList<Customer> customerList = new ArrayList<Customer>();
    private static HashMap<String, Customer> customerHashMap = new HashMap<String, Customer>();
    private static String currentCustomer = "";
    private JFrame frame = new JFrame("Mom and Pop's Pizza Shop");
    private static JPanel contentPanel = new JPanel();
    private static CardLayout cl = new CardLayout(20, 20);

    //Initializes all pages
    private static IDsandPasswords idsandPasswords = new IDsandPasswords();
    private WelcomePage welcomePage = new WelcomePage();
    private LoginPage login = new LoginPage(idsandPasswords.getLoginInfo());
    private CreateAccountPage createAccount = new CreateAccountPage();
    private CustomerWelcomePage customerWelcome = new CustomerWelcomePage();
    private static StartOrderPage startOrder = new StartOrderPage();
    private static PizzaPage pizza = new PizzaPage();
    private static SidesPage sides = new SidesPage();
    private static DrinksPage drinks = new DrinksPage();
    private  static CheckOutPage checkOut = new CheckOutPage();
    private static ViewCartPage viewCart = new ViewCartPage();
    private static PayCardPage payCard = new PayCardPage();
    private PayCashPage payCash = new PayCashPage();
    private static ReceiptPage receipt = new ReceiptPage();
    private PrintPage print = new PrintPage();
    private static CartTotal cartTotal = new CartTotal();

    //Converts all page classes to panels
    private JPanel welcomePagePanel = welcomePage.getPanel();
    private JPanel loginPagePanel = login.getPanel();
    private JPanel createAccountPanel = createAccount.getPanel();
    private JPanel customerWelcomePanel = customerWelcome.getPanel();
    private JPanel startOrderPanel = startOrder.getPanel();
    private JPanel pizzaPanel = pizza.getPanel();
    private JPanel sidesPanel = sides.getPanel();
    private JPanel drinksPanel = drinks.getPanel();
    private JPanel checkOutPanel = checkOut.getPanel();
    private JPanel viewCartPanel = viewCart.getPanel();
    private JPanel payCardPanel = payCard.getPanel();
    private JPanel payCashPanel = payCash.getPanel();
    private JPanel receiptPanel = receipt.getPanel();
    private JPanel printPanel = print.getPanel();

    /**
     * Adds all pages to the frame to be displayed
     * */
    public Main(){
        AccountHandling.loadAccounts();
        contentPanel.setLayout(cl);
        contentPanel.add(welcomePagePanel, "welcome");
        contentPanel.add(loginPagePanel, "login");
        contentPanel.add(createAccountPanel, "createAccount");
        contentPanel.add(customerWelcomePanel,"customerWelcome");
        contentPanel.add(startOrderPanel, "startOrder");
        contentPanel.add(pizzaPanel, "pizza");
        contentPanel.add(sidesPanel, "sides");
        contentPanel.add(drinksPanel,"drinks");
        contentPanel.add(checkOutPanel,"checkOut");
        contentPanel.add(viewCartPanel,"viewCart");
        contentPanel.add(payCardPanel,"payCard");
        contentPanel.add(payCashPanel,"payCash");
        contentPanel.add(receiptPanel,"receipt");
        contentPanel.add(printPanel,"print");

        cl.show(contentPanel, "welcome");

        frame.add(contentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Allows the card layout to be controlled by other objects/pages
     *
     * @param cardName the name of the card/panel/page to be displayed
     * */
    public static void showCardLayout(String cardName){
        cl.show(contentPanel, cardName);
    }

    /**
     * Adds new username and password combination that can be used when the user logs in
     *
     * @param phoneNumber the phone number to be added to the idsAndPasswords HashMap
     * @param password the password to be added to the idsAndPasswords HashMap
     * */
    public static void addLogin(String phoneNumber, String password){
        idsandPasswords.addLogin(phoneNumber,password);
    }

    /**
     * Checks if the user tries to create an account with an existing password
     *
     * @param phoneNumber the phone number to be checked
     * @return a boolean indicating if that phone number already exists as an account
     * */
    public static boolean accountExists(String phoneNumber){
        return idsandPasswords.accountExists(phoneNumber);
    }

    /**
     * Updates the ItemAddedLabel on the Start Order page
     *
     * @param add a boolean indicating if an item has been added to the cart
     * */
    public static void updateItemAddedLabel(boolean add){
        startOrder.updateItemAddedLabel(add);
    }

    /**
     * Returns the cart total as a String
     * @return the cart total String
     */
    public static String getCartTotalString(){
        return cartTotal.getCartTotalString();
    }

    /**
     * Returns the cart total as a double
     * @return the cart total double
     */
    public static double getCartTotalDouble(){
        return cartTotal.getCartTotalDouble();
    }

    /**
     * Updates the total price of the cart
     * @param price the price to add to the cart
     */
    public static void updateCartTotal(double price){
        cartTotal.updateCartTotal(price);
    }

    /**
     * Adds items to the tables on the view cart page and receipt page
     * @param data the array of data to be added to the row
     */
    public static void addTableRow(String[] data){
        viewCart.addTableRow(data);
        receipt.addTableRow(data);
    }

    /**
     * Adds info to the customerHashMap
     * @param phoneNumber the phone number to be added
     * @param customer the Customer object to be added
     */
    public static void setCustomerHashMap(String phoneNumber, Customer customer){
        customerHashMap.put(phoneNumber, customer);
    }

    /**
     * Stores the current customer that is ordering
     * @param phoneNumber the phone number of the person who is ordering
     */
    public static void setCurrentCustomer(String phoneNumber){
        currentCustomer = phoneNumber;
    }

    /**
     * Gets the phone number associated with the current customer
     * @return the phone number of the current customer
     */
    public static String getCurrentCustomerPhone(){
        return currentCustomer;
    }

    /**
     * Gets the customer info for the current customer who is ordering
     * @param phoneNumber the phone number of the current customer
     * @return the Customer object of the current customer
     */
    public static Customer getCurrentCustomerObject(String phoneNumber){
        return customerHashMap.get(phoneNumber);
    }

    /**
     * Completely resets application and cart when user logs out
     */
    public static void resetProgram(){
        cartTotal.resetCartTotal();
        startOrder.factoryReset();
        pizza.factoryReset();
        drinks.factoryReset();
        sides.factoryReset();
        viewCart.factoryReset();
        checkOut.factoryReset();
        payCard.factoryReset();
        receipt.factoryReset();
    }

    /**
     * Main method
     * @param args arguments
     */
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
