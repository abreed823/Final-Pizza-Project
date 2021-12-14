import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The functionality and display for the Start Order page
 *
 * @author Team 2
 */
public class StartOrderPage {
    private JPanel startOrderPanel;
    private JButton logOutButton;
    private JButton pizzaButton;
    private JButton sidesButton;
    private JButton drinksButton;
    private JButton viewCartButton;
    private JButton backButton;
    private JLabel itemAddedLabel;
    private JLabel cartSubtotalLabel;

    /**
     * Constructor
     */
    public StartOrderPage() {

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
                Main.showCardLayout("customerWelcome");
            }
        });
        pizzaButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showCardLayout("pizza");
            }
        });
        sidesButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showCardLayout("sides");
            }
        });
        drinksButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showCardLayout("drinks");
            }
        });
        viewCartButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showCardLayout("viewCart");
            }
        });
        startOrderPanel.addComponentListener(new ComponentAdapter() {
            /**
             * Invoked when the component has been made visible.
             *
             * @param e
             */
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                updateCartSubtotalLabel();
            }
        });
    }

    /**
     * Updates the Item Added labeled
     * @param add if an item was added to the cart
     */
    public void updateItemAddedLabel(boolean add){
        if(add) {
            itemAddedLabel.setText("Item added successfully!");
        }else{
            itemAddedLabel.setText("");
        }
    }

    /**
     * Updates the cart subtotal label
     */
    public void updateCartSubtotalLabel(){
        cartSubtotalLabel.setText(Main.getCartTotalString());
    }

    /**
     * Completely resets page and cart when user logs out
     */
    public void factoryReset(){
        itemAddedLabel.setText("");
        updateCartSubtotalLabel();
    }

    /**
     * Returns the JPanel to the Main class
     * @return the panel to return
     */
    public JPanel getPanel(){
        return startOrderPanel;
    }
}
