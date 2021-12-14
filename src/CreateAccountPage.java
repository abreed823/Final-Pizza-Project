import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.text.ParseException;

/**
 * The functionality and display for the Create Account page
 *
 * @author Team 2
 */
public class CreateAccountPage {
    private JFormattedTextField firstNameTextField;
    private JFormattedTextField lastNameTextField;
    private JFormattedTextField phoneNumberTextField;
    private JFormattedTextField addressTextField;
    private JButton backButton;
    private JButton createButton;
    private JPanel createAccountPanel;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JLabel errorMessageLabel;
    private Customer customer;

    /**
     * Constructor
     */
    public CreateAccountPage() {
        MaskFormatter phoneNumFormat;
        try {
             phoneNumFormat = new MaskFormatter("###-###-####");
             phoneNumFormat.setValidCharacters("0123456789");
             phoneNumFormat.setPlaceholderCharacter('#');
             phoneNumFormat.install(phoneNumberTextField);
        } catch (ParseException e) {
             e.printStackTrace();
        }

        phoneNumberTextField.setCaretPosition(0);

        backButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override

            public void actionPerformed(ActionEvent e) {
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                phoneNumberTextField.setText("");
                addressTextField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
                errorMessageLabel.setText("");

                Main.showCardLayout("welcome");
            }
        });
        createButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("")
                || phoneNumberTextField.getText().contains("#") || addressTextField.getText().equals("")
                || String.valueOf(passwordField.getPassword()).equals("")
                || String.valueOf(confirmPasswordField.getPassword()).equals("")){
                    errorMessageLabel.setText("Please fill out all text fields.");
                }else if(!String.valueOf(passwordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword()))){
                    errorMessageLabel.setText("Your passwords do not match.");
                }else if(Main.accountExists(phoneNumberTextField.getText())){
                    errorMessageLabel.setText("An account already exists for this phone number.");

                }else {
                    Main.addLogin(phoneNumberTextField.getText(), String.valueOf(passwordField.getPassword()));
                    customer = new Customer(firstNameTextField.getText(), lastNameTextField.getText(),
                            phoneNumberTextField.getText(), addressTextField.getText(), String.valueOf(passwordField.getPassword()));
                    AccountHandling.addAccount(customer);
                    Main.setCustomerHashMap(phoneNumberTextField.getText(), customer);
                    Main.setCurrentCustomer(phoneNumberTextField.getText());
                    resetPage();
                    Main.showCardLayout("customerWelcome");
                }
            }
        });
        phoneNumberTextField.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                phoneNumberTextField.setCaretPosition(0);
            }
        });
    }

    public void resetPage(){
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        phoneNumberTextField.setText("");
        addressTextField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        errorMessageLabel.setText("");
    }

    /**
     * Returns the JPanel to the Main class
     * @return the panel to return
     */
    public JPanel getPanel(){
        return createAccountPanel;
    }
}
