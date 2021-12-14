import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The functionality and display for the Customer Welcome page
 *
 * @author Team 2
 */
public class CustomerWelcomePage {
    private JPanel customerWelcomePanel;
    private JButton logOutButton;
    private JButton startNewOrderButton;
    private JLabel welcomeLabel;

    /**
     * Constructor
     */
    public CustomerWelcomePage() {
        customerWelcomePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                Customer cust = Main.getCurrentCustomerObject(Main.getCurrentCustomerPhone());
                welcomeLabel.setText("Welcome, "+ cust.getFirstName() +"!");
            }
        });
        startNewOrderButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.resetProgram();
                Main.showCardLayout("startOrder");
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showCardLayout("welcome");
            }
        });
    }

    /**
     * Returns the JPanel to the Main class
     * @return the panel to return
     */
    public JPanel getPanel(){
        return customerWelcomePanel;
    }
}
