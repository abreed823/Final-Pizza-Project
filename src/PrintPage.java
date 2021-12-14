import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The functionality and display for the Print Receipt page
 *
 * @author Team 2
 */
public class PrintPage {
    private JPanel printPanel;
    private JButton logOutButton;

    /**
     * Constructor
     */
    public PrintPage() {
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
    }

    /**
     * Returns the JPanel to the Main class
     * @return the panel to return
     */
    public JPanel getPanel(){
        return printPanel;
    }
}
