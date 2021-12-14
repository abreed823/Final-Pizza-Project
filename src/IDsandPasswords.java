import java.util.HashMap;

/**
 * Stores and adds valid combinations of user ids and passwords
 *
 * @author Team 2
 * @version 1.1
 */
public class IDsandPasswords {
    HashMap<String, String> loginInfo = new HashMap<String, String>();

    /**
     * Adds initial ids and passwords to HashMap
     */
    IDsandPasswords(){

    }

    /**
     * Retrieves the login info HashMap
     * @return the login info HashMap
     */
    public HashMap<String, String> getLoginInfo(){
        return loginInfo;
    }

    /**
     * Adds a new phone number and password pair to the Hashmap
     * @param phoneNumber the phone number to add
     * @param password the password to add
     */
    public void addLogin(String phoneNumber, String password){
        loginInfo.put(phoneNumber,password);
    }

    /**
     * CHecks if an account already exists with the given phone number
     * @param phoneNumber the phone number to check
     * @return boolean if the account exists
     */
    public boolean accountExists(String phoneNumber){
        return loginInfo.containsKey(phoneNumber);
    }
}
