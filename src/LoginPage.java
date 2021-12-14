import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.HashMap;

/**
 * The functionality and display for the login page
 *
 * @author Team 2
 */
public class LoginPage {
    private JPanel logInPanel;
    private JFormattedTextField phoneNumberField;
    private JButton backButton;
    private JButton logInButton;
    private JPasswordField passwordField;
    private JLabel errorMessageLabel;
    private HashMap<String, String> loginInfo;

    /**
     * Constructor
     * @param loginInfoOriginal The HashMap for the login info
     */
    public LoginPage(HashMap<String, String> loginInfoOriginal) {
        loginInfo = new HashMap<String,String>();
        loginInfo = loginInfoOriginal;
        MaskFormatter phoneNumFormat;
        try {
            phoneNumFormat = new MaskFormatter("###-###-####");
            phoneNumFormat.setValidCharacters("0123456789");
            phoneNumFormat.setPlaceholderCharacter('#');
            phoneNumFormat.install(phoneNumberField);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        backButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPage();
                Main.showCardLayout("welcome");
            }
        });
        logInButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = phoneNumberField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if(loginInfo.containsKey(userID)){
                    if(loginInfo.get(userID).equals(password)){
                        Main.setCurrentCustomer(phoneNumberField.getText());
                        resetPage();
                        Main.showCardLayout("customerWelcome");
                    }else{
                        passwordIncorrect();
                    }
                }else if(phoneNumberField.getText().contains("#") || String.valueOf(passwordField.getPassword()).equals("")){
                    errorMessageLabel.setText("Please enter both your username and your password.");
                }else{
                    passwordIncorrect();
                }
            }
        });
    }

    /**
     * Sets the error message label if the inputted password is incorrect
     */
    public void passwordIncorrect(){
        errorMessageLabel.setText("Your username and/or password is incorrect.");
    }

    public void resetPage(){
        phoneNumberField.setText("");
        passwordField.setText("");
        errorMessageLabel.setText("");
    }

    /**
     * Returns the JPanel to the Main class
     * @return the panel to return
     */
    public JPanel getPanel(){
        return logInPanel;
    }
}
