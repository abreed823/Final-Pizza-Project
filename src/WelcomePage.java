import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The functionality and display for the Welcome page
 *
 * @author Team 2
 */
public class WelcomePage {
    private JPanel welcomePanel;
    private JButton logInButton;
    private JButton createAccountButton;

    /**
     * Constructor
     */
    public WelcomePage() {
        logInButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo-reset this button to link back to the log in page
                Main.showCardLayout("login");
            }
        });
        createAccountButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.showCardLayout("createAccount");
            }
        });
    }

    /**
     * Returns the JPanel to the Main class
     * @return the panel to return
     */
    public JPanel getPanel(){
        return welcomePanel;
    }
}
