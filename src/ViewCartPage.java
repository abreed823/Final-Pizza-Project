import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The functionality and display for the View Cart page
 *
 * @author Team 2
 */

public class ViewCartPage {
    private JButton logOutButton;
    private JPanel viewCartPanel;
    private JButton continueButton;
    private JButton backButton;
    private JTable viewCartTable;
    private JLabel cartSubtotalLabel;
    private JLabel taxLabel;
    private JLabel finalTotalLabel;
    private JLabel errorMessageLabel;
    private DefaultTableModel tableModel;

    private double tax;

    /**
     * Constructor
     */
    public ViewCartPage() {
        createTable();
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
                Main.updateItemAddedLabel(false);
                resetPage();
                Main.showCardLayout("startOrder");
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
                if(tax == 0){
                    errorMessageLabel.setText("*Please order at least one item to continue.");
                }else{
                    resetPage();
                    Main.showCardLayout("checkOut");
                }
            }
        });
        viewCartPanel.addComponentListener(new ComponentAdapter() {
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
     * Creates the JTable that shows the ordered items
     */
    private void createTable(){
        tableModel = new DefaultTableModel(
                null,
                new String[]{"Item Type", "Item Description", "Quantity", "Price"}
        );
        viewCartTable.setModel(tableModel);

        TableColumnModel columnModel = viewCartTable.getColumnModel();
        columnModel.getColumn(0).setMinWidth(100);
        columnModel.getColumn(0).setMaxWidth(100);
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setMinWidth(750);
        columnModel.getColumn(1).setMaxWidth(750);
        columnModel.getColumn(1).setPreferredWidth(750);
        columnModel.getColumn(2).setMinWidth(100);
        columnModel.getColumn(2).setMaxWidth(100);
        columnModel.getColumn(2).setPreferredWidth(100);
        columnModel.getColumn(3).setMinWidth(100);
        columnModel.getColumn(3).setMaxWidth(100);
        columnModel.getColumn(3).setPreferredWidth(100);
    }

    /**
     * Adds new items to the table
     * @param data the array of data to be added to the row
     */
    public void addTableRow(String[] data){
        tableModel.addRow(data);
    }

    /**
     * Resets the page to its original state
     */
    public void resetPage(){
        errorMessageLabel.setText("");
    }

    /**
     * Completely resets application and cart when user logs out
     */
    public void factoryReset(){
        resetPage();
        tableModel.setRowCount(0);
    }

    /**
     * Returns the JPanel to the Main class
     * @return the panel to return
     */
    public JPanel getPanel(){
        return viewCartPanel;
    }
    
}
