import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The display and functionality for the checkout page
 *
 * @author Team 2
 */
public class CheckOutPage {
    private JPanel checkOutPanel;
    private JButton logOutButton;
    private JRadioButton cardRadioButton;
    private JRadioButton cashRadioButton;
    private JRadioButton checkRadioButton;
    private JButton backButton;
    private JButton continueButton;
    private JLabel errorMessageLabel;
    private JLabel cartSubtotalLabel;
    private JLabel taxLabel;
    private JLabel finalTotalLabel;

    private ButtonGroup paymentMethodButtonGroup;

    private boolean payWithCard;
    private boolean paymentMethodSelected;

    private double tax;

    /**
     * Constructor
     */
    public CheckOutPage() {
        paymentMethodButtonGroup = new ButtonGroup();
        paymentMethodButtonGroup.add(cardRadioButton);
        paymentMethodButtonGroup.add(cashRadioButton);
        paymentMethodButtonGroup.add(checkRadioButton);

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
                Main.showCardLayout("viewCart");
            }
        });
        continueButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(payWithCard && paymentMethodSelected){
                    resetPage();
                    Main.showCardLayout("payCard");
                }else if(!payWithCard && paymentMethodSelected){
                    resetPage();
                    Main.showCardLayout("payCash");
                }else if(!paymentMethodSelected){
                    errorMessageLabel.setText("Please select a payment method.");
                }
            }
        });
        cardRadioButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                payWithCard = true;
                paymentMethodSelected = true;
            }
        });
        ActionListener cashCheckListener = new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                payWithCard = false;
                paymentMethodSelected = true;
            }
        };
        cashRadioButton.addActionListener(cashCheckListener);
        checkRadioButton.addActionListener(cashCheckListener);

        checkOutPanel.addComponentListener(new ComponentAdapter() {
            /**
             * Invoked when the component has been made visible.
             *
             * @param e
             */
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                tax = Main.getCartTotalDouble() * 0.04;
                cartSubtotalLabel.setText(Main.getCartTotalString());
                taxLabel.setText("Tax: $" + String.format("%.2f", tax));
                finalTotalLabel.setText("Total: $" + String.format("%.2f", Main.getCartTotalDouble() + tax));
            }
        });
    }

    /**
     * Resets the page to its original state
     */
    public void resetPage(){
        paymentMethodButtonGroup.clearSelection();
        paymentMethodSelected = false;
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
        return checkOutPanel;
    }
}
