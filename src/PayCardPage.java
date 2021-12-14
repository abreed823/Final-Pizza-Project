import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

/**
 * The functionality and display for the Pay With Card page
 *
 * @author Team 2
 */
public class PayCardPage {
    private JButton logOutButton;
    private JPanel payCardPanel;
    private JFormattedTextField nameTextField;
    private JFormattedTextField cardNumberTextField;
    private JFormattedTextField expDateTextField;
    private JFormattedTextField cvvTextField;
    private JButton backButton;
    private JButton finishPayButton;
    private JLabel errorMessageLabel;

    /**
     * Constructor
     */
    public PayCardPage() {
        /*
        Sets formatting for card number text field
         */
        MaskFormatter cardNumFormat;
        try {
            cardNumFormat = new MaskFormatter("####-####-####-####");
            cardNumFormat.setValidCharacters("0123456789");
            cardNumFormat.setPlaceholderCharacter('#');
            cardNumFormat.install(cardNumberTextField);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*
        Sets formatting for expiration date text field
         */
        MaskFormatter expDate;
        try {
            expDate = new MaskFormatter("##/####");
            expDate.setValidCharacters("0123456789");
            expDate.setPlaceholderCharacter('#');
            expDate.install(expDateTextField);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /*
        Sets formatting for cvv text field
         */
        MaskFormatter cvvFormat;
        try {
            cvvFormat = new MaskFormatter("###");
            cvvFormat.setValidCharacters("0123456789");
            cvvFormat.setPlaceholderCharacter('#');
            cvvFormat.install(cvvTextField);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        logOutButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.resetProgram();
                Main.showCardLayout("welcome");
            }
        });
        backButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPage();
                Main.showCardLayout("checkOut");
            }
        });
        finishPayButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameTextField.getText().equals("") || cardNumberTextField.getText().contains("#") ||
                expDateTextField.getText().contains("#") || cvvTextField.getText().contains("#")){
                    errorMessageLabel.setText("*Please fill out all required fields.");
                }else{
                    resetPage();
                    Main.showCardLayout("receipt");
                }
            }
        });
    }

    /**
     * Resets the page to its original state
     */
    public void resetPage(){
        nameTextField.setText("");
        cardNumberTextField.setText("");
        expDateTextField.setText("");
        cvvTextField.setText("");
        errorMessageLabel.setText("");
    }

    /**
     * Completely resets application and cart when user logs out
     */
    public void factoryReset(){
        resetPage();
    }

    /**
     * Returns the JPanel to the Main class
     * @return the panel to return
     */
    public JPanel getPanel(){
        return payCardPanel;
    }
}
