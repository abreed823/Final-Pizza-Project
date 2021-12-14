/**
 * Holds data relevant to each customet
 *
 * @author Team 2
 */
public class Customer {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String password;

    /**
     * Constructor
     * @param firstName the customer's first name
     * @param lastName the customer's last name
     * @param phoneNumber the customer's phone number
     * @param address the customer's address
     * @param password the customer's password
     */
    public Customer(String firstName, String lastName, String phoneNumber, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    /**
     * Gets the customer's first name
     * @return the customer's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the customer's last name
     * @return the customer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the customer's phone number
     * @return the customer's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the customer's address
     * @return the customer's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the customer's password
     * @return the customer's password
     */
    public String getPassword() {
        return password;
    }
}

