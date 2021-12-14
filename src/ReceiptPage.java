import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
/**
 * The functionality and display for the Receipt page
 *
 * @author Team 2
 */
public class ReceiptPage {
    private JPanel receiptPanel;
    private JButton printButton;
    private JButton exitButton;
    private JTable receiptTable;
    private JLabel cartSubtotalLabel;
    private JLabel taxLabel;
    private JLabel finalTotalLabel;
    private JLabel customerLabel;
    private JLabel orderNumberLabel;
    private DefaultTableModel tableModel;
    private double tax;
    /**
     * Constructor
     */
    public ReceiptPage() {
        createTable();
        exitButton.addActionListener(new ActionListener() {
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
        printButton.addActionListener(new ActionListener() {
            /**
             * Invoked when the receipt page is reached. Prints order record to OrderRecords.txt
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.resetProgram();
                Main.showCardLayout("print");
            }
        });
        receiptPanel.addComponentListener(new ComponentAdapter() {
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
        receiptPanel.addComponentListener(new ComponentAdapter() {
            /**
             * Invoked when the component has been made visible.
             *
             * @param e
             */
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                int orderCount = initializeCount();
                Customer cust = Main.getCurrentCustomerObject(Main.getCurrentCustomerPhone());
                customerLabel.setText("Customer: "+cust.getFirstName()+" "+cust.getLastName());
                orderNumberLabel.setText("Order Number: "+orderCount);
                File customerRecords = new File("OrderRecords.txt");
                try {
                    FileWriter writer = new FileWriter(customerRecords,true);
                    PrintWriter printer = new PrintWriter(writer);
                    printer.println("Order #" + orderCount);
                    printer.println("Customer: " + cust.getFirstName() + " " + cust.getLastName());
                    printer.println("Phone #: " + cust.getPhoneNumber());
                    printer.println("Address: " + cust.getAddress());
                    printer.println("Items:");
                    for(int i = 0; i<receiptTable.getRowCount();i++){
                        printer.println("("+receiptTable.getValueAt(i,2)+") "+receiptTable.getValueAt(i,0) + " - "
                                +receiptTable.getValueAt(i,1) + " - "+receiptTable.getValueAt(i,3));
                    }
                    printer.println("Subtotal: "+ String.format("%.2f", Main.getCartTotalDouble()));
                    printer.println("Tax: $" + String.format("%.2f", tax));
                    printer.println("Total: $" + String.format("%.2f", Main.getCartTotalDouble() + tax));
                    printer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }


        });
    }

    /**
     * Increments the number stored in OrderCount.txt
     * @return the new order count
     */
    private int initializeCount(){
        File count = new File("OrderCount.txt");
        Scanner sc = null;
        int temp = 0;
        try {
            sc = new Scanner(count);
            temp = sc.nextInt();
            sc.close();
            temp++;
            FileWriter countWriter = null;
            countWriter = new FileWriter(count, false);
            countWriter.write(""+temp);
            countWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

       return temp;
    }
    /**
     * Creates the JTable that shows the ordered items
     */
    private void createTable(){
        tableModel = new DefaultTableModel(
                null,
                new String[]{"Item Type", "Item Description", "Quantity", "Price"}
        );
        receiptTable.setModel(tableModel);

        TableColumnModel columnModel = receiptTable.getColumnModel();
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
     * Completely resets application and cart when user logs out
     */
    public void factoryReset(){
        tableModel.setRowCount(0);
    }

    /**
     * Returns the JPanel to the Main class
     * @return the panel to return
     */
    public JPanel getPanel(){
        return receiptPanel;
    }


}
