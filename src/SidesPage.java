import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

/**
 * The functionality and display for the Sides Selection page
 */
public class SidesPage {
    private JButton logOutButton;
    private JPanel sidesPanel;
    private JCheckBox breadSticksCheckBox;
    private JCheckBox breadBitesCheckBox;
    private JCheckBox cookieCheckBox;
    private JButton cancelButton;
    private JButton addToCartButton;
    private JLabel itemTotalLabel;
    private JLabel cartSubtotalLabel;
    private JLabel errorMessageLabel;
    private JComboBox<String> breadSticksComboBox;
    private JComboBox<String> breadBitesComboBox;
    private JComboBox<String> cookieComboBox;

    private ArrayList<JCheckBox> checkBoxes;
    private ArrayList<JComboBox<String>> quantitiesToCheck;

    private double sidesPrice;
    private double breadSticksPrice;
    private double breadBitesPrice;
    private double cookiePrice;
    private String totalPrice;

    private StringBuilder itemDescription;
    private String[] sidesRow;

    /**
     * Constructor
     */
    public SidesPage() {

        breadSticksPrice = 0;
        breadBitesPrice = 0;
        cookiePrice = 0;

        checkBoxes = new ArrayList<JCheckBox>();
        checkBoxes.add(breadBitesCheckBox);
        checkBoxes.add(breadSticksCheckBox);
        checkBoxes.add(cookieCheckBox);

        quantitiesToCheck = new ArrayList<JComboBox<String>>();
        breadSticksComboBox.setEnabled(false);
        breadBitesComboBox.setEnabled(false);
        cookieComboBox.setEnabled(false);

        itemDescription = new StringBuilder();
        sidesRow = new String[4];


        cancelButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPage();
                Main.updateItemAddedLabel(false);
                Main.showCardLayout("startOrder");
            }
        });
        addToCartButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!sidesAreSelected()){
                    errorMessageLabel.setText("*Please make all required selections.");
                }else if(!quantityIsSelected()){
                    errorMessageLabel.setText("*Please select a quantity.");
                }else{
                    Main.updateCartTotal(sidesPrice);
                    Main.updateItemAddedLabel(true);
                    createTableRow();
                    resetPage();
                    Main.showCardLayout("startOrder");
                }
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
                Main.resetProgram();
                Main.showCardLayout("welcome");
            }
        });
        breadSticksCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSticksPrice();
                updateSidesTotalPrice();
                updateCheckedBoxesList(breadSticksCheckBox, breadSticksComboBox);
            }
        });
        breadBitesCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBitesPrice();
                updateSidesTotalPrice();
                updateCheckedBoxesList(breadBitesCheckBox, breadBitesComboBox);
            }
        });
        cookieCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCookiePrice();
                updateSidesTotalPrice();
                updateCheckedBoxesList(cookieCheckBox, cookieComboBox);
            }
        });
        sidesPanel.addComponentListener(new ComponentAdapter() {
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
        breadSticksComboBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSticksPrice();
                updateSidesTotalPrice();
            }
        });
        breadBitesComboBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBitesPrice();
                updateSidesTotalPrice();
            }
        });
        cookieComboBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCookiePrice();
                updateSidesTotalPrice();
            }
        });
    }

    /**
     * Updates the total price of the decides depending on if a checkbox is selected
     *
     */
    public void updateSidesTotalPrice(){
        sidesPrice = breadSticksPrice + breadBitesPrice + cookiePrice;
        totalPrice = String.format("%.2f",sidesPrice);
        itemTotalLabel.setText("Item Total: $" + totalPrice);
    }

    /**
     * Determines if there are any side item checkboxes selected
     * @return if there are any side item checkboxes selected
     */
    public boolean sidesAreSelected(){
        for(JCheckBox box: checkBoxes){
            if(box.isSelected()){
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the cart subtotal label
     */
    public void updateCartSubtotalLabel(){
        cartSubtotalLabel.setText(Main.getCartTotalString());
    }

    /**
     * Returns the drink quantity that the user selects
     * @return A string for the quantity of the drink
     */
    public String getSticksQuantity(){
        if(breadSticksComboBox.getSelectedIndex() == 0 || breadSticksComboBox.getSelectedIndex() == 1){
            return "1";
        }
        return String.valueOf(breadSticksComboBox.getSelectedItem());
    }

    /**
     * Returns the drink quantity that the user selects
     * @return A string for the quantity of the drink
     */
    public String getBitesQuantity(){
        if(breadBitesComboBox.getSelectedIndex() == 0 || breadBitesComboBox.getSelectedIndex() == 1){
            return "1";
        }
        return String.valueOf(breadBitesComboBox.getSelectedItem());
    }

    /**
     * Returns the drink quantity that the user selects
     * @return A string for the quantity of the drink
     */
    public String getCookieQuantity(){
        if(cookieComboBox.getSelectedIndex() == 0 || cookieComboBox.getSelectedIndex() == 1){
            return "1";
        }
        return String.valueOf(cookieComboBox.getSelectedItem());
    }

    /**
     * Updates the quantitiesToCheck ArrayList when a box is selected
     * @param sidesBox the checkbox that is/not selected
     * @param quantityBox the combo box to add/remove from the ArrayList
     */
    public void updateCheckedBoxesList(JCheckBox sidesBox, JComboBox<String> quantityBox){
        if(sidesBox.isSelected()){
            quantitiesToCheck.add(quantityBox);
            quantityBox.setEnabled(true);
        }else{
            quantitiesToCheck.remove(quantityBox);
            quantityBox.setSelectedIndex(0);
            quantityBox.setEnabled(false);
        }
    }

    /**
     * Checks if the quantity combo box for each selected side item is also selected
     * @return boolean if the combo box is selected
     */
    public boolean quantityIsSelected(){
        for(JComboBox<String> quantityComboBox: quantitiesToCheck){
            if(quantityComboBox.getSelectedIndex() == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * Updates the price of the bread sticks
     */
    public void updateSticksPrice(){
        if(breadSticksCheckBox.isSelected()){
            breadSticksPrice = Double.parseDouble(getSticksQuantity()) * 4;
        }else{
            breadSticksPrice = 0;
        }
        updateSidesTotalPrice();
    }

    /**
     * Updates the price of the bread bites
     */
    public void updateBitesPrice(){
        if(breadBitesCheckBox.isSelected()){
            breadBitesPrice = Double.parseDouble(getBitesQuantity()) * 2;
        }else{
            breadBitesPrice = 0;
        }
        updateSidesTotalPrice();
    }

    /**
     * Updates the price of the cookies
     */
    public void updateCookiePrice(){
        if(cookieCheckBox.isSelected()){
            cookiePrice = Double.parseDouble(getCookieQuantity()) * 4;
        }else{
            cookiePrice = 0;
        }
        updateSidesTotalPrice();
    }

    /**
     * Creates the table rows that shows the ordered items
     */
    public void createTableRow(){
        for(JComboBox<String> item: quantitiesToCheck){
            if(item.equals(breadSticksComboBox)){
                addItemsToTables("Bread Sticks", String.valueOf(item.getSelectedItem()), breadSticksPrice);
            }else if(item.equals(breadBitesComboBox)){
                addItemsToTables("Bread Bites", String.valueOf(item.getSelectedItem()), breadBitesPrice);
            }else if(item.equals(cookieComboBox)){
                addItemsToTables("Chocolate Chip Cookie", String.valueOf(item.getSelectedItem()), cookiePrice);
            }
        }
    }

    /**
     * Adds the new item to the tables of the view cart page anf the receipt page
     * @param item the description of the item that is being added
     */
    public void addItemsToTables(String item, String quantity, double price){
        sidesRow[0] = "Side";
        sidesRow[1] = item;
        sidesRow[2] = quantity;
        sidesRow[3] = "$" + String.valueOf(price) + "0";

        Main.addTableRow(sidesRow);
    }

    /**
     * Resets the page to its original state
     */
    public void resetPage(){
        for(JCheckBox checkBox: checkBoxes){
            checkBox.setSelected(false);
        }

        sidesPrice = 0;
        itemTotalLabel.setText("Item Total: $0.00");
        errorMessageLabel.setText("*Required");
        breadSticksComboBox.setSelectedIndex(0);
        breadBitesComboBox.setSelectedIndex(0);
        cookieComboBox.setSelectedIndex(0);
        breadSticksPrice = 0;
        breadBitesPrice = 0;
        cookiePrice = 0;
        quantitiesToCheck.clear();
        itemDescription.setLength(0);
        breadSticksComboBox.setEnabled(false);
        breadBitesComboBox.setEnabled(false);
        cookieComboBox.setEnabled(false);
    }

    /**
     * Completely resets page and cart when user logs out
     */
    public void factoryReset(){
        resetPage();
        updateCartSubtotalLabel();
    }

    /**
     * Returns the JPanel to the Main class
     * @return the panel to return
     */
    public JPanel getPanel(){
        return sidesPanel;
    }
}
